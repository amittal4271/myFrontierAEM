package com.frontierwholesales.core.models;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.PropertyIterator;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;



import com.frontierwholesales.core.beans.FrontierWholesalesProducts;

@Model(adaptables= {SlingHttpServletRequest.class,Resource.class}, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class ProductListModel {

	 @Inject
	  public Resource resource;
	 @Inject
	public SlingHttpServletRequest slingHttpServletRequest;
	
	@Inject
	private Node node;
	
	private List<FrontierWholesalesProducts> products;
	
	 public ProductListModel(SlingHttpServletRequest r){

         this.slingHttpServletRequest = r;
         
	 }
	
	 @PostConstruct
	    protected void init() {
		  this.products = new ArrayList<FrontierWholesalesProducts>();
		 getProductList("MH");
		
		
	    }


	 public void getProductList(String productName) {
		 System.out.println("Inside getProductList "+productName);
		 try {
				Resource resc = slingHttpServletRequest.getResourceResolver().getResource("/etc/commerce/products/we-retail/"+productName);
			 
				if(resc != null)
			    {
			    node = resc.adaptTo(Node.class);
			    NodeIterator nodeItr = node.getNodes();
			   
			        while(nodeItr.hasNext())
		                 {
		        		
		                 Node cNode = nodeItr.nextNode();
		                 System.out.println("while loop "+cNode.getName());
		                 if(!cNode.getName().equals("jcr:content")) {
		                	
		                	String nodeName = cNode.getName();
		                	String nodeType = cNode.getPrimaryNodeType().getName();
		                	System.out.println("node type "+nodeType);
		                	if(nodeType.equals("sling:Folder")) {
		                		String productPath = cNode.getParent().getName()+"/"+nodeName;
		                		getProductList(productPath);
		                	}else {
		                   FrontierWholesalesProducts product = new FrontierWholesalesProducts();
			               String title = cNode.getProperty("jcr:title").getValue().getString();
			                System.out.println("title is "+title);
			                product.setDescription(title);
			                product.setPrice(cNode.getProperty("price").getDouble());
			               
			                product.setChildNode(cNode);
			                
			               NodeIterator childIterator = cNode.getNodes();
			                while(childIterator.hasNext()) {
			                	Node imgNode = childIterator.nextNode();
			                	System.out.println("child node is "+imgNode.getName());
			                	if(imgNode.getName().equals("image")){
			                		
				                	String path = imgNode.getProperty("fileReference").getValue().getString();
				                	System.out.println("path is "+path);
				                	product.setImagePath(path);
			                	}
			                	/**/
			                }
			                System.out.println("before adding product ");
			                this.products.add(product);
			                System.out.println("product size is "+this.products.size());
		                 }
		                 }
		
			           }
		
			    }
			 }catch(Exception anyEx) {
				 anyEx.printStackTrace();
				
			 }
	 }

	public List<FrontierWholesalesProducts> getProducts() {
		return products;
	}

	 
	 
}
