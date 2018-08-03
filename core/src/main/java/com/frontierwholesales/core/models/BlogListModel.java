package com.frontierwholesales.core.models;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.jcr.Node;
import javax.jcr.NodeIterator;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.frontierwholesales.core.beans.FrontierWholesaleBlogContent;
import com.frontierwholesales.core.utils.FrontierWholesalesUtils;

@Model(adaptables= {SlingHttpServletRequest.class,Resource.class}, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class BlogListModel {

	private static final Logger log = LoggerFactory.getLogger(BlogListModel.class);
	private List<FrontierWholesaleBlogContent> blogContent = new ArrayList<FrontierWholesaleBlogContent>();
	private FrontierWholesalesUtils util = new FrontierWholesalesUtils();
	private int totalRecs=0;
	@Optional
	private int currentPage;
	private int totalPages=1;
	@Inject
	public int pagePerContent=9;
	private int recsShow;
	private int recStart;
	private int prevPageContent;


	@Inject
	public Resource resource;
	@Inject
	public SlingHttpServletRequest slingHttpServletRequest;
	@Inject
	public String nodePath;
	@Inject
	public String pageRecord;
	
	@PostConstruct
    protected void init() {
		log.debug("Init method ");
		try {
			ResourceResolver resolver = slingHttpServletRequest.getResourceResolver();
		Resource nodeResource = resolver.getResource(nodePath);
		if(nodeResource!=null) {
			Node node = nodeResource.adaptTo(Node.class);			
			this.pagePerContent = Integer.parseInt(pageRecord);
			
			NodeIterator it = node.getNodes();
			while(it.hasNext()) {
				Node child = (Node)it.next();
				getChildren(child,resolver);
				
			}
			Collections.sort(this.blogContent,new Comparator<FrontierWholesaleBlogContent>() {
				SimpleDateFormat sdf = new SimpleDateFormat("MMM dd,yyyy",Locale.getDefault());
				@Override
				public int compare(FrontierWholesaleBlogContent a,FrontierWholesaleBlogContent b)  {
					try {
					Date d1 = sdf.parse(a.getPublishedDate());
					Date d2 = sdf.parse(b.getPublishedDate());
					return (d1.getTime() > d2.getTime()?-1:1);
					}catch(Exception anyEx) {
						log.debug("Exception in date compare "+anyEx.getMessage());
					}
					return -1;
				}
			});
			String pageNo = slingHttpServletRequest.getRequestPathInfo().getSuffix();
			if(pageNo == null) {
				pageNo = "/page/1.html/";
			}
			String pageSplit[]=pageNo.split("/");
			
			if(pageSplit.length > 0) {
			     this.currentPage = Integer.parseInt(pageSplit[2].substring(0,1));
			}else{
				this.currentPage = 1;
			}
			
			if(this.currentPage > 0) {
				this.totalRecs = this.blogContent.size();
				double totalPage;
				totalPage = Math.ceil(((double)this.totalRecs/(double)this.pagePerContent));
				
				this.prevPageContent = this.pagePerContent;
				this.pagePerContent = this.pagePerContent * this.currentPage;
				
				this.totalPages = (int)totalPage;
				
				if(this.totalRecs <= this.pagePerContent) {
					this.recsShow = this.totalRecs;
				}else {
					//show next page content
					this.recsShow = this.pagePerContent;
					
				}
				if(this.currentPage == 1) {
					this.recStart = this.currentPage;
				}else {
					
					this.recStart = (this.pagePerContent - this.prevPageContent)+1;
					
				}
				
				
			}
			
			
		}else {
			log.debug("Node is null");
		}
		}catch(Exception anyEx) {
			log.debug("Exception in list mode "+anyEx.getMessage());
		}
	}
		
		
		private void getChildren(Node parent,ResourceResolver resolver) throws Exception{
			NodeIterator it = parent.getNodes();
			
			while(it.hasNext()) { 
				Node child = (Node)it.next();
				
				if(("featured_blog").equals(child.getName())) {
					FrontierWholesaleBlogContent blog = new FrontierWholesaleBlogContent();
					Resource res = resolver.getResource(child.getPath());	        
			            ValueMap properties = res.adaptTo(ValueMap.class);
			            
			            String fileName = properties.get("fileName", (String) null);
			           
			           String path =  child.getPath();
			           String filePath =  path+".img.png/"+fileName;
			           blog.setFilePath(filePath);
			           blog.setTitle(properties.get("jcr:title", (String) null));
			           blog.setButtonLabel(properties.get("label",(String)null));			           
			           blog.setBtnLink(properties.get("linkTo",(String)null));			           
			           blog.setPageLink(properties.get("linkURL",(String)null));
			           blog.setDescription(properties.get("text",(String)null));
			           Calendar createdDate = properties.get("jcr:created",null);
			           Date dtCreated = createdDate.getTime();
			          
			           blog.setPublishedDate(util.convertToDate(dtCreated));
			           this.blogContent.add(blog);
			            
				}
				 this.getChildren(child,resolver);
				
			}
			

		}


		public List<FrontierWholesaleBlogContent> getBlogContent() {
			return blogContent;
		}
		
		public int getTotalRecs() {
			return totalRecs;
		}		

		public int getCurrentPage() {
			return currentPage;
		}
		

		public int getTotalPages() {
			return totalPages;
		}

		public int getPagePerContent() {
			return pagePerContent;
		}


		public int getRecsShow() {
			return recsShow;
		}


		public int getRecStart() {
			return recStart;
		}
		
		
		
}
