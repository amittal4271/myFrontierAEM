package com.frontierwholesales.core.magento.services;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.metatype.annotations.Designate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.cq.commerce.api.CommerceException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.frontierwholesales.core.beans.FrontierWholesalesProductSearch;
import com.frontierwholesales.core.beans.MagentoCategory;
import com.frontierwholesales.core.beans.search.MagentoSearch;
import com.frontierwholesales.core.magento.models.MagentoRelatedProduct;
import com.frontierwholesales.core.magento.services.exceptions.FrontierWholesalesBusinessException;
import com.frontierwholesales.core.magento.services.exceptions.FrontierWholesalesErrorCode;
import com.frontierwholesales.core.utils.AuthCredentials;
import com.frontierwholesales.core.utils.FrontierWholesalesUtils;



@Component(service = FrontierWholesalesMagentoCommerceConnector.class,
           configurationPolicy = ConfigurationPolicy.REQUIRE
           )
@Designate(ocd = FrontierWholesaleMagentoCommerceConnectorService.class,factory=true)


public class FrontierWholesalesMagentoCommerceConnector {
	
	 
	//private FrontierWholesaleMagentoCommerceConnectorService service;
	 
    private static final Logger log = LoggerFactory.getLogger(FrontierWholesalesMagentoCommerceConnector.class);
    

    private ObjectMapper mapper = new ObjectMapper();
   
   
    
    private static String appToken="";
    private static String server="";
    public static final int TIME_OUT=6000;
    

    public FrontierWholesalesMagentoCommerceConnector() {
        mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
    }
    
    @Activate
    protected void activate(FrontierWholesaleMagentoCommerceConnectorService config) {
    	
    	log.debug("*************************************************");
    	FrontierWholesalesMagentoCommerceConnector.configureValues(config);
    	
       
    }
   
    protected static void configureValues(FrontierWholesaleMagentoCommerceConnectorService config) {
    	appToken = config.appToken();
    	server = config.serverName();
    }

 
	public static String getAppToken() {
		log.debug(" admin token is "+appToken);
		return appToken;
	}

   
	public static String getServer() {
		log.debug(" server name is "+server);
        return server;
    }

 
    

	public String getToken(String username, String password)throws Exception{
        AuthCredentials authCredentials = new AuthCredentials(username, password);

        log.debug(" AUTHENTICATING " + username + " Against server:" + getServer());
        String api ="/rest/V1/integration/customer/token";
    	String response = constructAPIMethod("Login", null, api, "addItemToCart", authCredentials,null);
 	   
        /*InputStream inputStream = Request.Post(getServer()+ "/rest/V1/integration/customer/token")
                .bodyString(mapper.writeValueAsString(authCredentials), ContentType.APPLICATION_JSON)
                .execute().returnResponse().getEntity().getContent();
        String response = FrontierWholesalesUtils.parseMagentoResponseObject(inputStream,"getToken");*/
        return "Bearer " + response.replace("\"", "");

    }
    
    public String getAdminToken() throws FrontierWholesalesBusinessException{
    	log.debug("getAdminToken Method ");
    	
    	return "Bearer "+getAppToken();

    }
    
   



    public String initCart(String authToken) throws Exception {
        
        String cartId = Request.Post(server + "/rest/V1/carts/mine")
                .addHeader("Authorization", authToken)
               
                .execute().returnContent().asString();
    	
        return (cartId != null)?cartId.replace("\"", ""):null;
    }


    /**
     * 
     * @param userToken
     * @return
     * @throws Exception
     */
   public String getCartId(String userToken) throws Exception{
	   String response = Request.Get(server+"/rest/V1/carts/mine")
			   .addHeader("Authorization", userToken)               
               .execute().returnContent().asString();
	   return response;
   }


    public String getAttributeID(String attributeName) throws CommerceException {

        try {
            String response = Request.Get(server+"/rest/V1/products/attributes/" + attributeName)
                    .execute().returnContent().asString();

            HashMap results = mapper.readValue(response, HashMap.class);
            return results.get("attribute_id").toString();
        } catch (IOException e) {
            throw new CommerceException(e.getMessage());
        }
    }
    
   public String getCartTotalWithItems(String token) throws Exception{
	   log.debug("server name is "+server);
	   String api = "/rest/V1/frontier/aem/carts/mine/totals/details";
   		String response = constructAPIMethod("Get", token, api, "getCartTotalWithItems", null,null);
	   
	   	return response;
   }
    
    public String getCartTotal(String token) throws Exception{
    	log.debug("server name is "+server);
    	
    	String total = Request.Get(server+"/rest/V1/carts/mine/totals")
    			.addHeader("Authorization",token)
    			.addHeader("ContentType","application/json")
    			.execute().returnContent().asString();
    	return total;
    }
    
    public String getShoppingCartList(String token) throws Exception {
    	 
		String cartList = Request.Get(server+"/rest/V1/carts/mine/items")
				.addHeader("Authorization",token)
				
				.execute().returnContent().asString();
		
		return cartList;
				
	}
    
    public String removeCartItem(String token,String itemId) throws Exception{
    	
    	log.debug("url is "+server+"/rest/V1/carts/mine/items/"+itemId);
    	String isItemRemoved = Request.Delete(server+"/rest/V1/carts/mine/items/"+itemId)
				.addHeader("Authorization",token)
				
				.execute().returnContent().asString();
		
		return isItemRemoved;
    }
    
    public String updateCartItem(String token,String itemId,String jsonData) throws Exception{
    	String updatedItem = Request.Put(server+"/rest/V1/carts/mine/items/"+itemId)
				.addHeader("Authorization",token)
				.bodyString(jsonData, ContentType.APPLICATION_JSON)
				.execute().returnContent().asString();
		
		return updatedItem;
    }
    
   
    
    public String addItemToCart(String token,String jsonData) throws Exception{
    	
    	log.debug("json item is "+jsonData);
    	
    	String api ="/rest/V1/carts/mine/items";
    	String response = constructAPIMethod("Post", token, api, "addItemToCart", null,jsonData);
 	   
    	
    	/*InputStream inputStream = Request.Post(server+"/rest/V1/carts/mine/items")
    			.addHeader("Authorization",token)
    			.addHeader("ContentType","application/json")
    			.bodyString(jsonData,ContentType.APPLICATION_JSON)
    			.execute().returnResponse().getEntity().getContent(); 	 
    	 
    	 
    	String response = FrontierWholesalesUtils.parseMagentoResponseObject(inputStream,"addItemToCart");*/
    	    		
    	log.debug("Add to cart response is "+response);
    	return response;
    }
    
    public String getProducts(String adminToken,FrontierWholesalesProductSearch search) throws Exception{
    	
        log.debug("FrontierWholesalesMagentoCommerceConnector getProducts Start");
        String orderByPrice= "";
        String featured ="";
        String newProduct="";
        String searchCriteriaGroups="searchCriteria[filterGroups]";
        String filters="[filters]";
        String searchCriteria = "searchCriteria[currentPage]="+search.getCurrentPage()+"&searchCriteria[pageSize]="+search.getNoOfRecsPerPage()+"&"+
        		searchCriteriaGroups+"[0]"+filters+"[0][value]="+search.getCategoryId()+"&"+
        		searchCriteriaGroups+"[0]"+filters+"[0][field]=category_id";
        
        searchCriteria += "&searchCriteria[filterGroups][1]"+filters+"[0][field]=visibility&"+
        		searchCriteriaGroups+"[1]"+filters+"[0][value]=2&"+
        		searchCriteriaGroups+"[1]"+filters+"[1][field]=visibility&"+
        		searchCriteriaGroups+"[1]"+filters+"[1][value]=4&"+
        		searchCriteriaGroups+"[2]"+filters+"[0][field]=status&"+
        		searchCriteriaGroups+"[2]"+filters+"[0][value]=1";
        		
       
        if(search.getFacetSearchQuery() != null) {
        	searchCriteria = searchCriteria + search.getFacetSearchQuery();
        }
        if(search.getSortByPrice() != null) {
         orderByPrice = "&searchCriteria[sortOrders][0][field]=price&searchCriteria[sortOrders][0][direction]="+search.getSortByPrice();
         searchCriteria = searchCriteria + orderByPrice;
        }
        
        if(search.getSortByFeatured() != null) {
        	featured ="&searchCriteria[sortOrders][0][field]=featured&searchCriteria[sortOrders][0][direction]=DESC";
        	 searchCriteria = searchCriteria + featured;
        }
        
        if(search.getSortByNewProduct() != null) {
        	newProduct="&searchCriteria[sortOrders][0][field]=created_at&searchCriteria[sortOrders][0][direction]=DESC";
        	 searchCriteria = searchCriteria + newProduct;
        }
        
        log.debug("search query is "+searchCriteria);
        
       
            String api="/rest/V1/products?"+searchCriteria;
            String response = constructAPIMethod("Get",adminToken,api,"getProducts",null,null);
         /*  InputStream inputStream = Request.Get(server+"/rest/V1/products?"+searchCriteria)
                    .addHeader("Authorization", adminToken)
                    .connectTimeout(TIME_OUT)
                    .execute().returnResponse().getEntity().getContent();*/
         
          
      //  String response = FrontierWholesalesUtils.parseMagentoResponseObject(inputStream,"getProducts");
        log.debug("FrontierWholesalesMagentoCommerceConnector getProducts End");
        return response;
    }
    
    public String getBrands(String adminToken) throws Exception{
    	log.debug("getBrands Start");
    	
    	String api="/rest/V1/frontier/product/attributes/brandlist";
        String response = constructAPIMethod("Get",adminToken,api,"getBrands",null,null);
    		
    	/* InputStream inputStream = Request.Get(server+"/rest/V1/frontier/product/attributes/brandlist")
                    .addHeader("Authorization", adminToken)
                    .connectTimeout(TIME_OUT)
                    .execute().returnResponse().getEntity().getContent();
         
    	 response = FrontierWholesalesUtils.parseMagentoResponseObject(inputStream,"getBrands");*/
    	 log.debug("getBrands End");
    	 return response;
    }
    
    public String getProductFacets(String adminToken) {
    	String response=null;
    	try {
    		
            response = Request.Get(server+"/rest/V1/products/attributes?searchCriteria[currentPage]=1&searchCriteria[pageSize]=1000&searchCriteria[sortOrders][0][field]=position&searchCriteria[sortOrders][0][direction]=ASC&fields=items[attribute_code,default_frontend_label,frontend_input,backend_type,position,options]")
                    .addHeader("Authorization", adminToken)
                    .execute().returnContent().asString();
         
            
        } catch (IOException e) {
            e.printStackTrace();
            log.error("Error getting Product Facet List: ERROR: " + e.getMessage());
        }
        return response;
    }
    
    public String getProducts(String adminToken, MagentoSearch search) throws Exception {
    		
    		String queryString = search.toString();
    		
    		String api="/rest/V1/products?"+queryString;
            String response = constructAPIMethod("Get",adminToken,api,"getProducts",null,null);
        		
			log.debug("Calling product search [{}]");
			/*InputStream inputStream = Request.Get(serviceURL).addHeader("Authorization", adminToken)
					.execute().returnResponse().getEntity().getContent();
		
			response =  FrontierWholesalesUtils.parseMagentoResponseObject(inputStream,"getProducts");*/
    		return response;
    }
    
    public String getProducts(String adminToken, String queryString) throws FrontierWholesalesBusinessException {
		String response = null;      
		String serviceURL =   "/rest/V1/mailman/search?" + queryString;
		
		try {
			log.debug("Calling product search v2 [{}]", serviceURL);
			
             response = constructAPIMethod("Get",adminToken,serviceURL,"getProductDetails",null,null);
			/*inputStream = Request.Get(serviceURL).addHeader("Authorization", adminToken)
					.execute().returnResponse().getEntity().getContent();
			response =  FrontierWholesalesUtils.parseMagentoResponseObject(inputStream,"getProducts");*/
		} catch (IOException e) {
			log.error("Error getting Product List", e);
		}catch(Exception anyEx) {
			throw new FrontierWholesalesBusinessException("ServiceError", FrontierWholesalesErrorCode.IO_ERROR);
		}
			
			return response;
	}
    
    public String getProductDetails(String adminToken,String  productID) throws Exception {
    	
        	     
        	String api="/rest/V1/products/"+productID;
            String response = constructAPIMethod("Get",adminToken,api,"getProductDetails",null,null);
        		
        	/*InputStream inputStream  = Request.Get(server+"/rest/V1/products/"+productID)
                    .addHeader("Authorization", adminToken)
                    .execute().returnResponse().getEntity().getContent();        
        	response =  FrontierWholesalesUtils.parseMagentoResponseObject(inputStream,"getProductDetails");*/
    		return response;
        
    }
    
    public MagentoCategory getCategories(String adminToken,int categoryId) throws Exception{
    	log.debug("getCategories Method Start ");
       String predicate=(categoryId > 0)?"?rootCategoryId="+categoryId:"";
       MagentoCategory category=null;
       
       String api = "/rest/V1/categories"+predicate;
       
       String response = constructAPIMethod("Get", adminToken, api, "getCategories", null,null);
  
    	category = mapper.readValue(response,MagentoCategory.class);
    	log.debug("getCategories Method End");
        return category;
    }
    
  


    public List<MagentoRelatedProduct> getRelatedProductsForSku(String adminToken, String sku ) throws Exception{
        log.info("Getting related products for SKU: {}", sku);
        List<MagentoRelatedProduct> productList = new ArrayList<>();
        String api = "/rest/V1/products/" + sku + "/links/related";
   		String response = constructAPIMethod("Get", adminToken, api, "getRelatedProductsForSku", null,null);
	   
        
       /* InputStream inputStream = Request.Get(server + "/rest/V1/products/" + sku + "/links/related")
                    .addHeader("Authorization", adminToken)
                    .connectTimeout(TIME_OUT)
                    .execute().returnResponse().getEntity().getContent();
            log.debug("Related products for SKU response from endpoint:\n {}");
        String response =  FrontierWholesalesUtils.parseMagentoResponseObject(inputStream,"getRelatedProductsForSku");*/
        productList = mapper.readValue(response, new TypeReference<List<MagentoRelatedProduct>>(){});
   
        return productList;
    }
    
    public String getParentChildrenCategories(String adminToken,int categoryId) throws Exception{
    	log.debug("getParentChildrenCategories Start");
    	String api = "/rest/V1/frontier/categories/"+categoryId;
    	String response = constructAPIMethod("Get", adminToken, api, "getParentChildrenCategories", null,null);
 	   
    	/*InputStream inputStream = Request.Get(server+"/rest/V1/frontier/categories/"+categoryId)
    				.addHeader("Authorization",adminToken)
    				.connectTimeout(TIME_OUT)
    				.execute().returnResponse().getEntity().getContent();
    	 String response =  FrontierWholesalesUtils.parseMagentoResponseObject(inputStream,"getParentChildrenCategories");*/
    	log.debug("getParentChildrenCategories End");
    	return response;
    }
    
    public String addItemToWishList(String adminToken,String jsonData) throws Exception{
    	log.debug("addItemToWishList Start");
    	
    	String api ="/rest/all/V1/frontier/wishlist/add";
    	String response = constructAPIMethod("Post", adminToken, api, "addItemToWishList", null,jsonData);
 	   
    	
    	/*InputStream inputStream  = Request.Post(server+"/rest/all/V1/frontier/wishlist/add")
					.addHeader("Authorization",adminToken)
					.addHeader("ContentType","application/json")
					.connectTimeout(TIME_OUT)
					.bodyString(jsonData,ContentType.APPLICATION_JSON)
					.execute().returnResponse().getEntity().getContent();
    	 String response =  FrontierWholesalesUtils.parseMagentoResponseObject(inputStream,"addItemToWishList");*/
	    log.debug("addItemToWishList End");
	    return response;
    	
    }
    
    public String getRequisitionList(String userToken) throws Exception{
    	log.debug("getRequisitionList Start");
    	
    	String api ="/rest/default/V1/frontier/customer/requisitionlist";
    	String response = constructAPIMethod("Get", userToken, api, "getRequisitionList", null,null);
 	   
    	/*InputStream inputStream = Request.Get(server+"/rest/default/V1/frontier/customer/requisitionlist")
				.addHeader("Authorization",userToken)
				.connectTimeout(TIME_OUT)
				.execute().returnResponse().getEntity().getContent();
    	 String response =  FrontierWholesalesUtils.parseMagentoResponseObject(inputStream,"getRequisitionList");*/
    	log.debug("getRequisitionList End");
    	return response;
    	
    	
    }
    
    public String getCustomerDetails(String userToken) throws Exception{
    	log.debug("getCustomerDetails Start");
    	String api = "/rest/default/V1/fc/customers/me";
    	String response = constructAPIMethod("Get",userToken,api,"getCustomerDetails",null,null);    		
    	log.debug("getCustomerDetails End");
    	return response;
    }
  
    
    public String getUserRole(String adminToken,String customerId) throws Exception{
    	log.debug("getUserRole Start");
    	String apiWithParam = "/rest/all/V1/customers/"+customerId;
    	String response = constructAPIMethod("Get",adminToken,apiWithParam,"getUserRole",null,null);
    	log.debug("getUserRole End");
    	return response;
    }
    
    
    /**
     * 
     * @param apiType (Method type)
     * @param token (Admin or user token)
     * @param api (api url)
     * @param methodName (Calling method for debugging)
     * @param authCredentials (This is for login method)
     * @param postParam (Parameter for Post Method)
     * @return
     * @throws Exception
     */
    public String constructAPIMethod(String apiType,String token,String api,
    		String methodName,AuthCredentials authCredentials,String postParam) throws Exception{
    	log.debug("constructAPIMethod Start");
    	InputStream inputStream = null;
   	 	String response = null;
   	 	try {
	   	 	switch (apiType) {
	   	 	case "Get" :
	   	 		//api for get method
	   	 		inputStream = Request.Get(server+api)
	   	 		.addHeader("Authorization",token)
	   	 		.connectTimeout(TIME_OUT)	   	 		
	   	 		.execute().returnResponse().getEntity().getContent();	   	 
	   	 		break;
	   	 	case "Login" :
	   	 	  inputStream = Request.Post(server+api)
             .bodyString(mapper.writeValueAsString(authCredentials), ContentType.APPLICATION_JSON)
             .execute().returnResponse().getEntity().getContent();
	   	 	  break;
	   	 	  
	   	 	case "Post":
		   	 	inputStream = Request.Post(server+api)		   	 	
		   	 	.addHeader("Authorization",token)		   	 		
		   	 	.addHeader("ContentType","application/json")
		   	 	.connectTimeout(TIME_OUT)	   	
		   	 	.bodyString(postParam,ContentType.APPLICATION_JSON)
	            .execute().returnResponse().getEntity().getContent();
		   	 	  break;
	   	 case "PostWithoutToken":
		   	 	inputStream = Request.Post(server+api)		   	 	
		   	 		   	 		
		   	 	.addHeader("ContentType","application/json")
		   	 	.connectTimeout(TIME_OUT)	   	
		   	 	.bodyString(postParam,ContentType.APPLICATION_JSON)
	            .execute().returnResponse().getEntity().getContent();
		   	 	  break;
	   	case "Put":
	   	 	inputStream = Request.Put(server+api)		   	 	
	   	 	.addHeader("Authorization",token)		   	 		
	   	 	.addHeader("ContentType","application/json")
	   	 	.connectTimeout(TIME_OUT)	   	
	   	 	.bodyString(postParam,ContentType.APPLICATION_JSON)
            .execute().returnResponse().getEntity().getContent();
	   	 	  break;
	   	 	default:
	   	 		inputStream = Request.Get(server+api)
	   	 		.addHeader("Authorization",token)
	   	 		.connectTimeout(TIME_OUT)	   	 		
	   	 		.execute().returnResponse().getEntity().getContent();		   	 	  
	   	 		break;
	   	 	}
   	 	response = FrontierWholesalesUtils.parseMagentoResponseObject(inputStream,methodName);
   	 	}finally {
    		if(inputStream != null)
    			inputStream.close();
    	}
    	log.debug("constructAPIMethod End");
    	return response;
   	 	
    }

}


