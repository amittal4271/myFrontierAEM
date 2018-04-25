package com.frontierwholesales.core.servlets;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

import javax.jcr.ItemExistsException;
import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.PathNotFoundException;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.ValueFormatException;
import javax.jcr.lock.LockException;
import javax.jcr.nodetype.ConstraintViolationException;
import javax.jcr.version.VersionException;
import javax.servlet.ServletException;

import org.apache.commons.lang3.StringUtils;
import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageFilter;
import com.day.cq.wcm.api.PageManager;
import com.day.cq.wcm.api.WCMException;
@SlingServlet(paths = "/bin/createPage", methods = "GET")

public class CreatePageServlet extends SlingAllMethodsServlet {
	
	private static final long serialVersionUID = 1L;

	private static final Logger logger = LoggerFactory.getLogger(CreatePageServlet.class);
	private static final String SOURCE_PATH = "/content/frontierwholesales/en/about-frontierwholesales/news";
	private static final String TARGET_PATH = "/content/frontierwholesales/en/pressrelease";
	private static final String TARGET_PATH_PARAMETER = "targetPath";
	private static final String SOURCE_PATH_PARAMETER = "sourcePath";
	private static final String RESOURCE_TYPE_PATH ="frontierwholesales/components/content/text";
	private static final String DATE_FORMAT_TYPE = "MMM dd, yyyy hh:mm a";
	private static final String COMPONENT_PATH ="/content/columncontrol/col-cntrl-1";
	private static final String TEMPLATE_NAME="/apps/frontierwholesales/templates/page-onecolumn";
	private static final String COMPONENT_RESOURCE_TYPE = "frontierwholesales/components/content/pressrelease";

	@Override
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {
		ResourceResolver resourceResolver = request.getResourceResolver();

	    Session session = resourceResolver.adaptTo(Session.class);

		
		try {
			
			String sourcePath = request.getParameter(SOURCE_PATH_PARAMETER);
			String targetPath = request.getParameter(TARGET_PATH_PARAMETER);
			logger.info("SourcePath"+sourcePath);
			logger.info("Target Path"+targetPath);
			if(StringUtils.isEmpty(targetPath)){
				targetPath = TARGET_PATH;
			}
			if(StringUtils.isEmpty(sourcePath)){
				sourcePath = SOURCE_PATH;
			}
			Resource sourceResoruce = resourceResolver.getResource(sourcePath);
			if(sourceResoruce!=null){
				
				Page sourcePage = sourceResoruce.adaptTo(Page.class);
				Iterator<Page> children = sourcePage.listChildren(new PageFilter(),false);
				PageManager pageManager = resourceResolver.adaptTo(PageManager.class);

				while(children.hasNext()){
					Page childPage = children.next();
					String pageName = childPage.getName();
					String pageTitle = childPage.getTitle();
					logger.info("PageTitle"+pageTitle);
					logger.info("PageName:"+pageName);
					if(childPage.hasContent()){
						String path = childPage.getContentResource().getPath()+COMPONENT_PATH;
						Resource resource = resourceResolver.getResource(path);
						Node baseNode = resource.adaptTo(Node.class);
						logger.info("Path of the baseNode"+path);
						int i =0;
						NodeIterator nodeIter = baseNode.getNodes();
						while(nodeIter.hasNext()){
								
								Node resultNode = nodeIter.nextNode();
								
								if(resultNode!=null && resultNode.getProperty("sling:resourceType").getString().equals(RESOURCE_TYPE_PATH)){
									if(i==1){
										
										logger.info("Node Path inside Loop:"+resultNode.getPath());
										String text = resultNode.getProperty("text").getString();
										
										
										if(StringUtils.isNotEmpty(text)){
											logger.info("**Text**"+text);
											String compTitle  = StringUtils.substringBetween(text, "<h1>", "</h1>");
											String date = StringUtils.substringBetween(text, "<p>", "<br>");
											String dateTime = StringUtils.substringBetween(text,"<br>","</p>");
								            Calendar calendarTime = null;
											
								            if(StringUtils.isNotEmpty(date) && StringUtils.isNotEmpty(dateTime)){

												SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT_TYPE);
												dateTime = dateTime.replace("\n", "").replace("\r", "");
												String dateInString = date +" "+dateTime;
												logger.info("Date in String:"+dateInString);
												try {

										            Date dateCal = formatter.parse(dateInString);
										            logger.info("Date Formatted"+dateCal);
										            calendarTime = Calendar.getInstance();
										            calendarTime.setTime(dateCal);

										        } catch (ParseException e) {
										            logger.error(e.getMessage());
										        }
											}
											String description = StringUtils.substringAfter(text,"</p>");
											if(StringUtils.isNotEmpty(compTitle)) {
												logger.info(compTitle);
											}
											if(StringUtils.isNotEmpty(date)){
												logger.info("Date"+date);
											}
											if(StringUtils.isNotEmpty(dateTime)){
												logger.info("Date Time"+dateTime);
											}
											if(StringUtils.isNotEmpty(description)){
												logger.info(description);
											}
											
											createPage(resourceResolver, targetPath, pageManager, pageName, pageTitle, compTitle, description, calendarTime);
	
										}
									}
									i++;									
								}
							
						}
						
						
					}
				}

				
			}
			
			session.save();


		}
		catch (Exception e) {
			logger.error("Error:" + e);

		}

	}

	private void createPage(ResourceResolver resourceResolver, String rootPath, PageManager pageManager, String pageName, String pageTitle, String title, String text, Calendar calendarTime)
			throws WCMException, RepositoryException, ItemExistsException, PathNotFoundException, VersionException,
			ConstraintViolationException, LockException, ValueFormatException {
		
		Page newPage = pageManager.create(rootPath, pageName, TEMPLATE_NAME, pageTitle);
		logger.info("Page Created:");
		logger.info("Published Text:"+text);
		logger.info("Published Title:"+title);
		Node jcrNode = null;
		 if (newPage.hasContent()) {
			 logger.info("Inside the new page creation:");
			    jcrNode = newPage.getContentResource().adaptTo(Node.class);
			    String nodePath = jcrNode.getPath();
			    String path  =  nodePath+COMPONENT_PATH;
			    Resource resource = resourceResolver.getResource(path);
			    Node pressReleaseNode = resource.adaptTo(Node.class);
		        Node textNode = pressReleaseNode.addNode("pressrelease");
		        textNode.setProperty("sling:resourceType", COMPONENT_RESOURCE_TYPE);
		        if(calendarTime!=null){
		        textNode.setProperty("pressreleasepublishdate", calendarTime);
		        }
		        textNode.setProperty("pressreleasesubtitle", "Hello World");
		        textNode.setProperty("pressreleasetext", text);
		        textNode.setProperty("pressreleasetitle", title);
		        textNode.setProperty("textIsRich", "true");

		}
	}

}


