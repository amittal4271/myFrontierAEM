package com.frontierwholesales.core.magento.services;

import java.io.IOException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.felix.scr.annotations.Activate;
import org.apache.felix.scr.annotations.Properties;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.sling.SlingFilter;
import org.apache.felix.scr.annotations.sling.SlingFilterScope;
import org.apache.sling.api.SlingHttpServletResponse;
import org.osgi.service.component.ComponentContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



@SlingFilter(
	label = "Cross-Origin Request Filter",
	metatype=true,
	scope=SlingFilterScope.REQUEST,
	order=-100)
@Properties({
		@Property(name="sling.filter.pattern",description=""),
		@Property(name="sling.name.externalDomain",description="http://frontierb2b.ztech.io/index.jsp")
	})

public class CorsService implements Filter {

	private String allowedDomains;
	private static final Logger log = LoggerFactory.getLogger(CorsService.class);
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		log.debug("init method");
	}
	
	@Activate
	protected void activate(final ComponentContext context) throws Exception{
		final Map<String,String> properties = (Map<String,String>)context.getProperties();
		this.allowedDomains = properties.get("sling.name.externalDomain");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		log.debug("do filter method");
		if(response instanceof SlingHttpServletResponse) {
			if(this.allowedDomains != "") {
				SlingHttpServletResponse httpResponse = (SlingHttpServletResponse)response;
				httpResponse.setHeader("Access-Control-Allow-Domain",this.allowedDomains);
				httpResponse.setHeader("Access-Control-Allow-Credentials","true");
			}
		}
		chain.doFilter(request, response);
		
	}

	@Override
	public void destroy() {
		log.debug("destroy method ");
		
	}

	

}
