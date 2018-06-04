package com.frontierwholesales.core.models;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.PropertyIterator;
import javax.jcr.Session;
import javax.jcr.query.Query;
import javax.jcr.query.QueryManager;
import javax.jcr.query.QueryResult;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;


import com.frontierwholesales.core.beans.FrontierWholesalesProducts;

@Model(adaptables= {SlingHttpServletRequest.class,Resource.class}, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class ProductDetailsModel {
 
		@Inject
		public Resource resource;
		@Inject
		public SlingHttpServletRequest slingHttpServletRequest;
	
		private FrontierWholesalesProducts product;	
				
		@PostConstruct
	    protected void init() {
			getProductDetails();
				
	    }
		
		public void getProductDetails() {
			String path="";
			String imgPath="";
				
			 try {
					//Resource resc = slingHttpServletRequest.getResourceResolver().getResource("/etc/commerce/products/we-retail/"+productName);
				 
					Session session = slingHttpServletRequest.getResourceResolver().adaptTo(Session.class);
					QueryManager queryManager = session.getWorkspace().getQueryManager();
					
					Resource res = slingHttpServletRequest.getResource();
					ResourceResolver resourceResolver = slingHttpServletRequest.getResourceResolver();
					String requestSku = (String) slingHttpServletRequest.getAttribute("sku");
					// TODO: Remove
					//requestSku = "MT02";
					System.out.println("-DEBUG: sku: "+requestSku);
					
					String sqlStatement="SELECT * FROM [nt:unstructured] AS node\n" + 
				    		"WHERE ISDESCENDANTNODE(node, \"/etc/commerce/products/we-retail\")\n" + 
				    		"AND CONTAINS([baseSku],'"+ requestSku+"') AND CONTAINS([cq:commerceType],'product')";		
					
					Query query = queryManager.createQuery(sqlStatement,"JCR-SQL2");	   		   
				    QueryResult result = query.execute();	  
				    NodeIterator nodeIter = result.getNodes();
				    
				    while ( nodeIter.hasNext() ) {
				    	Node node = nodeIter.nextNode();
				        path = node.getPath();
				        System.out.println("-DEBUG: "+path);
				        // Load product details from node properties:
				        res = resourceResolver.getResource(path);	        
			            ValueMap properties = res.adaptTo(ValueMap.class);
			            
			            this.product = new FrontierWholesalesProducts();		            
			            this.product.setTitle(properties.get("jcr:title", (String) null));
			            System.out.println("-DEBUG: "+this.product.getTitle());
			            
			            this.product.setSummary(properties.get("summary", (String) null));
			            this.product.setDescription(properties.get("description", (String) null));
			            this.product.setProductSKU(properties.get("baseSku", (String) null));
			            this.product.setPrice(node.getProperty("price").getDouble());
			            
			            //String title = cNode.getProperty("jcr:title").getValue().getString();		                
			            
			    		NodeIterator cNode = node.getNodes();
						while ( cNode.hasNext() ) {
							Node imgNode = cNode.nextNode();
							if(imgNode.getName().equals("image")) {
								imgPath = imgNode.getProperty("fileReference").getValue().getString();
								this.product.setImagePath(imgPath);	
			                	System.out.println("-DEBUG: "+this.product.getImagePath());
							}				 				 
						}					    		
				    }					
				 }catch(Exception anyEx) {
					 anyEx.printStackTrace();					
				 }
		 }

		public FrontierWholesalesProducts getProduct() {
			return product;
		}

		public void setProduct(FrontierWholesalesProducts product) {
			this.product = product;
		}
	   
	}