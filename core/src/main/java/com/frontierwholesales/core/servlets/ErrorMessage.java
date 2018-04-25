package com.frontierwholesales.core.servlets;

import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Iterator;
import java.util.Arrays;
import javax.jcr.Node;
import javax.jcr.Property;
import javax.jcr.Value;
import javax.jcr.NodeIterator;
import javax.jcr.PathNotFoundException;

import javax.servlet.ServletException;

import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.api.request.RequestParameter;
import org.apache.sling.commons.json.io.JSONWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageFilter;
import com.frontierwholesales.core.services.ErrorMessagesConfig;

@SlingServlet(paths = "/bin/errorMessage", methods = "GET")
public class ErrorMessage extends SlingAllMethodsServlet {

	private static final long serialVersionUID = 1L;

	private static final Logger logger = LoggerFactory.getLogger(ErrorMessage.class);
	
	@Reference
	private ErrorMessagesConfig errorMessagesConfig;

	@Override
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {
		try {
			logger.info("Entered the GET Method");

			ResourceResolver resourceResolver = request.getResourceResolver();
			
			String resourcePath = errorMessagesConfig.getErrorMessagesNodePath();

			Resource rootRes = resourceResolver.getResource(resourcePath);

			response.setContentType("application/json");
			response.setCharacterEncoding("utf-8");
			JSONWriter writer = new JSONWriter(response.getWriter());
			writer.object();
			
			
			RequestParameter errorCodeParameter = request.getRequestParameter("errorcode");
			RequestParameter errorMessageLanguageParameter = request.getRequestParameter("language");
			RequestParameter errorMessageTagParameter = request.getRequestParameter("tag");
			
			String errorCodeFromRequest = null;
			
			
			if (null != errorCodeParameter)
				errorCodeFromRequest = errorCodeParameter.getString();
			
			writer.key("errorcode");
			writer.value(errorCodeFromRequest);
			
			String languageFromRequest = null;
			
			if (null != languageFromRequest)
				languageFromRequest = errorMessageLanguageParameter.getString();
			else
				languageFromRequest = "en";
			
			writer.key("language");
			writer.value(languageFromRequest);
			

			String tagFromRequest = null;
			
			if (null != errorMessageTagParameter)
				tagFromRequest = "frontierwholesales:" + errorMessageTagParameter.getString();
			else
				tagFromRequest = "frontierwholesales:north";

			
			writer.key("tag");
			writer.value(tagFromRequest);
			
			if (rootRes != null && errorCodeFromRequest != null && !errorCodeFromRequest.isEmpty()) 
			{
				Node errorMessagesListNode = rootRes.adaptTo(Node.class);
				
				//Get the child item nodes. Each item is an error message
				NodeIterator errorMessagesListIterator = errorMessagesListNode.getNodes();
				String defaultErrorMessage = "";
				
				
			    while (errorMessagesListIterator.hasNext()) 
			    {
			    	Node currentNode = errorMessagesListIterator.nextNode();
			    	try
			    	{
			    		Property errorCodeProperty = currentNode.getProperty("jcr:title");
			    		String errorCode = "";
				    	Property errorMessageProperty = currentNode.getProperty("value");
				    	String errorMessage = "";
				    	
				    	Property errorCodeTagsProperty = currentNode.getProperty("cq:tags");
				    	
				    	
			    		String errorCodeTags = "";
				    	
						if (null == errorCodeProperty || errorCodeProperty.getString().isEmpty())
							continue;
						
						errorCode = errorCodeProperty.getString();
						
						if (null == errorMessageProperty || errorMessageProperty.getString().isEmpty())
							errorMessage = "";
						else
							errorMessage = errorMessageProperty.getString();
						
						errorCode = errorCodeProperty.getString();
						
						if (null == errorCodeTagsProperty)
							errorCodeTags = "";
						
						logger.debug("Error Code 	= " + errorCode);
						logger.debug("Error Message 	= " + errorMessage);
						logger.debug("Error Tags 	= " + errorCodeTags);
						
				    	if (errorCode.equalsIgnoreCase(errorCodeFromRequest))
				    	{
				    		defaultErrorMessage = errorMessage;
				    		
				    		if (null != errorCodeTagsProperty)
				    		{
				    			Value[] tagValues = errorCodeTagsProperty.getValues();
				    			
				    			for(Value currentTagValue: tagValues)
				    			{
				    				String currentTag = currentTagValue.getString();
				    				
				    				if (null != currentTag && currentTag.equalsIgnoreCase(tagFromRequest))
				    					break;
				    			}
				    		}
				    		
				    		
			        		
				        }
			    	}
			    	catch(PathNotFoundException e)
			    	{
			    		logger.debug("Path not found " + e.getMessage());
			    	}
			    	
			     }
			    writer.key("errormessage");
    			writer.value(defaultErrorMessage);
			}
			else {
				logger.error("Resource is NULL");
			}
			writer.endObject();
		} 
		catch (Exception e) {
			logger.error("Error:" + e);
		}
	}

}
