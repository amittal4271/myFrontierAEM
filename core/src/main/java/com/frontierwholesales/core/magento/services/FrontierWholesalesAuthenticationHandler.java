package com.frontierwholesales.core.magento.services;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Base64;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import org.apache.felix.scr.annotations.Activate;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Properties;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.apache.sling.auth.core.spi.AuthenticationFeedbackHandler;
import org.apache.sling.auth.core.spi.AuthenticationHandler;
import org.apache.sling.auth.core.spi.AuthenticationInfo;
import org.apache.sling.auth.core.spi.DefaultAuthenticationFeedbackHandler;
import org.osgi.framework.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.commons.Externalizer;
import com.frontierwholesales.core.services.constants.FrontierWholesalesConstants;



@Component(metatype = true, immediate = true, label = "Frontier Wholesales Authentication Handelr",
description="Authenticates User ")
@Service
@Properties({
@Property(name = AuthenticationHandler.PATH_PROPERTY, value = "/frontier_auth_handler"),
@Property(name = Constants.SERVICE_DESCRIPTION, value = "Frontier Wholesales Custom Authentication Handler") })



public class FrontierWholesalesAuthenticationHandler extends DefaultAuthenticationFeedbackHandler implements AuthenticationHandler,AuthenticationFeedbackHandler {
	 private static final Logger log = LoggerFactory.getLogger(FrontierWholesalesAuthenticationHandler.class);
	
	 private FrontierWholesalesMagentoCommerceConnector connector = new FrontierWholesalesMagentoCommerceConnector();
	
	
	 @Reference(target = "(service.pid=com.day.crx.security.token.impl.impl.TokenAuthenticationHandler)")
	    private AuthenticationHandler wrappedAuthHandler;

	    private boolean wrappedIsAuthFeedbackHandler = false;

	    @Override
	    public AuthenticationInfo extractCredentials(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
	        // Wrap the response object to capture any calls to sendRedirect(..) so it can be released in a controlled
	        // manner later.
	        final DeferredRedirectHttpServletResponse deferredRedirectResponse =
	                new DeferredRedirectHttpServletResponse(httpServletRequest, httpServletResponse);
	        log.debug("extract credentials...");
	        return wrappedAuthHandler.extractCredentials(httpServletRequest, deferredRedirectResponse);
	    }

	    @Override
	    public boolean requestCredentials(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
	        return wrappedAuthHandler.requestCredentials(httpServletRequest, httpServletResponse);
	    }

	    @Override
	    public void dropCredentials(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
	        wrappedAuthHandler.dropCredentials(httpServletRequest, httpServletResponse);
	    }

	    @Override
	    public void authenticationFailed(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationInfo authenticationInfo) {
	        // Wrap the response so we can release any previously deferred redirects
	        final DeferredRedirectHttpServletResponse deferredRedirectResponse =
	                new DeferredRedirectHttpServletResponse(httpServletRequest, httpServletResponse);
	        log.debug("authenticationFailed...");
	        if (this.wrappedIsAuthFeedbackHandler) {
	            ((AuthenticationFeedbackHandler) wrappedAuthHandler).authenticationFailed(httpServletRequest, deferredRedirectResponse, authenticationInfo);
	        }

		try {
	            deferredRedirectResponse.releaseRedirect();
	        } catch (IOException e) {
	            log.error("Could not release redirect", e,e.getMessage());
	            httpServletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
	        }
	    }

	    @Override
	    public boolean authenticationSucceeded(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationInfo authenticationInfo) {
	        boolean result = false;
	        log.debug("authenticationSucceeded...");
	        // Wrap the response so we can release any previously deferred redirects
	        final DeferredRedirectHttpServletResponse deferredRedirectResponse =
	                new DeferredRedirectHttpServletResponse(httpServletRequest, httpServletResponse);

	        if (this.wrappedIsAuthFeedbackHandler) {
	            result = ((AuthenticationFeedbackHandler) wrappedAuthHandler).authenticationSucceeded(httpServletRequest, httpServletResponse, authenticationInfo);
	        }
	       
		try {
			
			final String authorization = httpServletRequest.getHeader("Authorization");
		    if (authorization != null && authorization.startsWith("Basic")) {
		        // Authorization: Basic base64credentials
		        String base64Credentials = authorization.substring("Basic".length()).trim();
		        String credentials = new String(Base64.getDecoder().decode(base64Credentials),
		                Charset.forName("UTF-8"));
		        // credentials = username:password
		        final String[] values = credentials.split(":",2);
			
			
			String username= values[0];
			
			String password = values[1];
			
			String token = (String)httpServletRequest.getSession().getAttribute(FrontierWholesalesConstants.MAGENTO_USER_TOKEN);
			
			if(token == null) {
			token = connector.getToken(username, password);
			
			httpServletRequest.getSession().setAttribute(FrontierWholesalesConstants.MAGENTO_USER_TOKEN, token);
			}
			
		
				if(token != null) {
					
					String scheme = httpServletRequest.getScheme();
					String url = "";
					if(scheme.equals("http")) {
						url = scheme+"://"+httpServletRequest.getServerName()+":"+httpServletRequest.getServerPort();
					}else {
						url = scheme+"://"+httpServletRequest.getServerName();
					}
					
					deferredRedirectResponse.sendRedirect(url+"/content/frontierwholesales/en/myaccount.html");
					deferredRedirectResponse.setStatus(HttpServletResponse.SC_OK);
					
				     deferredRedirectResponse.releaseRedirect();
					} else {
						log.error("Invalid Credentials");
						deferredRedirectResponse.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,"Error");
					}
		    }
	        } catch (Exception e) {
	            log.error("Could not release redirect", e);
	            try {
	            	deferredRedirectResponse.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,"Error");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					log.error("Error in login ",e,e.getMessage());
					
				}
	           
	            result = false;
	        }

	        return result;
	    }


	    @Activate
	    protected void activate(final Map<String, String> config) {
	        this.wrappedIsAuthFeedbackHandler = false;
	        log.debug("activated...");
	        if (wrappedAuthHandler != null) {
	            log.debug("Registered wrapped authentication feedback handler");
	            this.wrappedIsAuthFeedbackHandler = wrappedAuthHandler instanceof AuthenticationFeedbackHandler;
	        }
	    }


	    /**
	     * It is not uncommon (Example: OOTB SAML Authentication Handler) for response.sendRedirect(..) to be called
	     * in extractCredentials(..). When sendRedirect(..) is called, the response immediately flushes causing the browser
	     * to redirect.
	     */
	    private class DeferredRedirectHttpServletResponse extends HttpServletResponseWrapper {
	        private String ATTR_KEY = DeferredRedirectHttpServletResponse.class.getName() + "_redirectLocation";

	        private HttpServletRequest request = null;

	        public DeferredRedirectHttpServletResponse(final HttpServletRequest request, final HttpServletResponse response) {
	            super(response);
	            this.request = request;
	        }


	        /**
	         * This method captures the redirect request and stores it to the Request so it can be leveraged later.
	         * @param location the location to redirect to
	         */
	        @Override
	        public void sendRedirect(String location) {
	        	 log.info("sendRedirect...");
	            // Capture the sendRedirect location, and hold onto it so it can be released later (via releaseRedirect())
	            this.request.setAttribute(ATTR_KEY, location);
	        }

	        /**
	         * Invokes super.sendRedirect(..) with the value captured in this.sendRedirect(..)
	         * @throws IOException 
	         */
	        public final void releaseRedirect() throws IOException {
	        	 log.info("releaseRedirect...");
	            final String location = (String) this.request.getAttribute(ATTR_KEY);
	            log.info("final location is "+location);
	            if (location != null) {
	                super.sendRedirect(location);
	            }
	        }
	    }
	
}
