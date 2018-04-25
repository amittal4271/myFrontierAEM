package com.frontierwholesales.core.servlets;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletException;

import org.apache.commons.lang3.time.FastDateFormat;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.PropertyUnbounded;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.commons.json.JSONArray;
import org.apache.sling.commons.json.JSONException;
import org.apache.sling.commons.json.JSONObject;
import org.apache.sling.commons.json.io.JSONWriter;
import org.apache.sling.commons.osgi.PropertiesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import com.day.cq.commons.Externalizer;
import com.day.cq.commons.Filter;
import com.day.cq.wcm.api.NameConstants;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageFilter;
import com.day.cq.wcm.api.PageManager;
import com.frontierwholesales.core.services.SitemapConfig;


@SlingServlet(paths = "/bin/sitemap", methods = "GET")

public class Sitemap extends SlingAllMethodsServlet {

	private static final long serialVersionUID = 1L;
	//private static final String RESOURCE_PATH = "/content/frontierwholesales/en";
	private String excludeFromSiteMapProperty;
	
	private int maxDepth = 7;
	
	private static final Logger logger = LoggerFactory.getLogger(Sitemap.class);


    @Property(name = "sling.servlet.resourceTypes", unbounded = PropertyUnbounded.ARRAY, label = "Sling Resource Type", description = "Sling Resource Type for the Home Page component or components.")
    private static final String RESOURCE_PATH = "/content/frontierwholesales/en";

	
	@Property(label = "Exclude from Sitemap Property", description = "The boolean [cq:Page]/jcr:content property name which indicates if the Page should be hidden from the Sitemap. Default value: hideInNav")
    private static final String PROP_EXCLUDE_FROM_SITEMAP_PROPERTY = "exclude.property";

	
	@Reference
	private SitemapConfig sitemapConfig;
    
	protected void activate(Map<String, Object> properties) {
		this.excludeFromSiteMapProperty = PropertiesUtil.toString(properties.get(PROP_EXCLUDE_FROM_SITEMAP_PROPERTY),
                NameConstants.PN_HIDE_IN_NAV);
		//this.externalizerDomain = PropertiesUtil.toString(properties.get(PROP_EXTERNALIZER_DOMAIN), DEFAULT_EXTERNALIZER_DOMAIN);
    
	}

	@Override
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType(request.getResponseContentType());
        
		int order = 0;
		int depth = 1;
        
        try {
            ResourceResolver resourceResolver = request.getResourceResolver();
            Resource rootRes = resourceResolver.getResource(RESOURCE_PATH);
    		Page rootPage = rootRes.adaptTo(Page.class);
            
    		response.setContentType("application/json");
    		response.setCharacterEncoding("utf-8");
            
            JSONArray rootArray = new JSONArray();
            
            if(rootPage.listChildren().hasNext()){
                Iterator<Page> children = rootPage.listChildren(new DepthPageFilter(depth), true);				
        		
				while (children.hasNext()) {
            		Page child = children.next();
//            		logger.info("------> CHILD PATH: "+child.getPath());
//        	  		logger.info("in the doGet() calling the processPage again at child: " + child.toString()); 
					JSONObject currentResponse = processPage(child, order, depth);
					if(currentResponse !=null){
//						logger.info("current repose is not null ");
						rootArray.put(currentResponse);
						order++;
					}
				}
    	    }

            rootArray.write((response.getWriter()));

        } catch (Exception e) {
			logger.error("Error:" + e);
		}
    }

	private JSONObject processPage(Page currentPage, int order, int depth) throws Exception  {
				
        JSONArray subPages = new JSONArray();
        String hideInSitemap = (String)currentPage.getProperties().get("hideInSitemap", null); 
        int thisDepth = depth;
        
		if (hideInSitemap == null){
//	        logger.info("child depth is currenty at: " + thisDepth);

            int childOrder = 0;

		    if(currentPage.listChildren().hasNext()){
	            Iterator<Page> children = currentPage.listChildren(new DepthPageFilter(depth+1), true);
				
	            while (children.hasNext()) {
	            	Page child = children.next();
            		if(thisDepth <= maxDepth){
            			JSONObject processedSubPage = processPage(child, childOrder, thisDepth+1);
// 			            logger.info("subPages depth is currenty at: " + thisDepth);						
						if(processedSubPage != null){
							subPages.put(processedSubPage);
							JSONObject parentParams = new JSONObject();
							parentParams.put("id", child.getName());
							processedSubPage.put("params", parentParams);
//							logger.info("child order " + childOrder);
							childOrder++;
						}
            		}	
	            }
			}
			//logger.info(currentPage.getPath() + "order: " + order);
            logger.info("hitting the return with the subPages of: " + String.valueOf(subPages.length()));
            
            JSONObject currentPageJSON = new JSONObject();
            
            currentPageJSON.put("title", currentPage.getTitle());
            currentPageJSON.put("order", order);
            currentPageJSON.put("route",  currentPage.getPath());
			currentPageJSON.put("type",   "ContentPageComponent");
			
			JSONObject parentParams = new JSONObject();
			parentParams.put("id", currentPage.getName());
			
			currentPageJSON.put("params", parentParams);
			
			logger.info(subPages.toString());
			logger.info("subPages Length: "+ String.valueOf(subPages.length()));
			if(subPages.length()> 0){
				logger.info("finally putting the subPages in!");
				currentPageJSON.put("Pages",subPages);
			}
            
			return currentPageJSON;
		} else {
			return null;
		}
	}   
	
	private class DepthPageFilter implements Filter {

		int depth=1;
		String ignorePathPattern = "^/content/frontierwholesales/.*?/";
//		
//		public DepthPageFilter(int depth, String ignorePathPattern) {
//			super();
//			this.depth = depth;
//			this.ignorePathPattern = ignorePathPattern;
//		}

		public DepthPageFilter(int depth) {
			super();
			this.depth = depth;
		}
		
		@Override
		public boolean includes(Object element) {
			Page p = (Page) element;
			
			String path = p.getPath();
			path=path.replaceFirst(ignorePathPattern, "");
			
			int curDepth =  path.split("\\/").length;
			return depth==curDepth;
		}
	}
}