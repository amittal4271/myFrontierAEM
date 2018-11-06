package com.frontierwholesales.core.magento.services;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.cq.commerce.api.CommerceException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.frontierwholesales.core.beans.FrontierWholesalesProductSearch;
import com.frontierwholesales.core.beans.MagentoCategory;
import com.frontierwholesales.core.beans.search.MagentoSearch;
import com.frontierwholesales.core.magento.models.MagentoRelatedProduct;
import com.frontierwholesales.core.magento.services.exceptions.FrontierWholesalesBusinessException;
import com.frontierwholesales.core.magento.services.exceptions.FrontierWholesalesErrorCode;
import com.frontierwholesales.core.services.constants.FrontierWholesalesConstants;
import com.frontierwholesales.core.utils.AuthCredentials;
import com.frontierwholesales.core.utils.FrontierWholesalesUtils;
import com.google.gson.JsonParseException;



public class FrontierWholesalesMagentoCommerceConnector  {
	
	private MagentoCommerceConnectorService config;
	
	@Reference
	public void bindConfiguration(MagentoCommerceConnectorService config) {
		this.config = config;
	}
	 
    private static final Logger log = LoggerFactory.getLogger(FrontierWholesalesMagentoCommerceConnector.class);
    private String server;

    private ObjectMapper mapper = new ObjectMapper();
   
    private static final int TIME_OUT=6000;
    
    private static final String CART_ITEM_API="/rest/V1/carts/mine/items";
    

    public FrontierWholesalesMagentoCommerceConnector() {
        mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
    }
    
   public String getServer() {
	   
	   return this.server;
   }
   
   public void setServer(String server) {
	   this.server = server;
   }
    

	public String getToken(String username, String password)throws FrontierWholesalesBusinessException{
        AuthCredentials authCredentials = new AuthCredentials(username, password);

        log.debug(" AUTHENTICATING " + username + " Against server:" + getServer());
        String api ="/rest/V1/integration/customer/token";
    	String response = constructAPIMethod("Login", null, api, "addItemToCart", authCredentials,null);
 	   
        return "Bearer " + response.replace("\"", "");

    }
    
    public String getAdminToken() throws FrontierWholesalesBusinessException{
    	log.debug("getAdminToken Method ");
    	
    	return "Bearer "+this.config.getAppToken();

    }
    
   



    public String initCart(String authToken) throws FrontierWholesalesBusinessException {
    	log.debug("initCart method Start ");
        String cartId;
		try {
			cartId = Request.Post(this.server + "/rest/V1/carts/mine")
			        .addHeader(FrontierWholesalesConstants.AUTHORIZATION, authToken)               
			        .execute().returnContent().asString();
		} catch (IOException e) {

			throw new FrontierWholesalesBusinessException("Service Error", FrontierWholesalesErrorCode.GENERAL_SERVICE_ERROR);
		}
        log.debug("initCart method End ");
        return (cartId != null)?cartId.replace("\"", ""):null;
    }


    /**
     * 
     * @param userToken
     * @return
     * @throws Exception
     */
   public String getCartId(String userToken) throws FrontierWholesalesBusinessException{
	   log.debug("initCart method Start ");
	   String response;
	try {
		response = Request.Get(this.server+"/rest/V1/carts/mine")
				   .addHeader(FrontierWholesalesConstants.AUTHORIZATION, FrontierWholesalesConstants.BEARER+userToken)               
		           .execute().returnContent().asString();
	} catch (Exception e) {
		
		throw new FrontierWholesalesBusinessException("Service Error", FrontierWholesalesErrorCode.GENERAL_SERVICE_ERROR);
	}
	   log.debug("initCart method End ");
	   return response;
   }


    public String getAttributeID(String attributeName) throws CommerceException {
    	
        try {
            String response = Request.Get(this.server+"/rest/V1/products/attributes/" + attributeName)
                    .execute().returnContent().asString();

            HashMap results = mapper.readValue(response, HashMap.class);
            return results.get("attribute_id").toString();
        } catch (IOException e) {
            throw new CommerceException(e.getMessage());
        }
    }
    
   public String getCartTotalWithItems(String token) throws FrontierWholesalesBusinessException{
	   log.debug("getCartTotalWithItems method Start "+token);
	   String api = "/rest/V1/frontier/aem/carts/mine/totals/details";
   		String response = constructAPIMethod("Get", token, api, "getCartTotalWithItems", null,null);
   		log.debug("getCartTotalWithItems method End ");
	   	return response;
   }
    
    public String getCartTotal(String token) throws FrontierWholesalesBusinessException{
    	log.debug("getCartTotal method Start ");
    	
    	String total;
		try {
			total = Request.Get(this.server+"/rest/V1/carts/mine/totals")
					.addHeader(FrontierWholesalesConstants.AUTHORIZATION,FrontierWholesalesConstants.BEARER+token)
					.addHeader("ContentType","application/json")
					.execute().returnContent().asString();
		} catch (Exception e) {
			throw new FrontierWholesalesBusinessException("Service Error", FrontierWholesalesErrorCode.GENERAL_SERVICE_ERROR);
		}
    	log.debug("getCartTotal method End ");
    	return total;
    }
    
   
    
    public String removeCartItem(String token,String itemId) throws FrontierWholesalesBusinessException{
    	
    	log.debug("removeCartItem Method Start ");
    	String isItemRemoved;
    	try {
    	 isItemRemoved = Request.Delete(this.server+CART_ITEM_API+"/"+itemId)
				.addHeader(FrontierWholesalesConstants.AUTHORIZATION,token)
				
				.execute().returnContent().asString();
    	
    	}catch(Exception anyEx) {
    		throw new FrontierWholesalesBusinessException("Service Error", FrontierWholesalesErrorCode.GENERAL_SERVICE_ERROR);
    	}
    	log.debug("removeCartItem Method End ");
		return isItemRemoved;
    
    }
    
   
    
   
    
    public String addItemToCart(String token,String jsonData) throws FrontierWholesalesBusinessException{
    	
    	log.debug("json item is "+jsonData);
    	
    	String api =CART_ITEM_API;
    	String response = constructAPIMethod("Post", token, api, "addItemToCart", null,jsonData);
 	   
    	    		
    	log.debug("Add to cart response is "+response);
    	return response;
    }
    
    public String getProducts(String adminToken,FrontierWholesalesProductSearch search) throws FrontierWholesalesBusinessException{
    	
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
        
        log.debug("FrontierWholesalesMagentoCommerceConnector getProducts End");
        return response;
    }
    
    public String getBrands(String adminToken) throws FrontierWholesalesBusinessException{
    	log.debug("getBrands Start");
    	
    	String api="/rest/V1/frontier/product/attributes/brandlist";
        String response = constructAPIMethod("Get",adminToken,api,"getBrands",null,null);
    	
    	 log.debug("getBrands End");
    	 return response;
    }
    
    public String getProductFacets(String adminToken) throws FrontierWholesalesBusinessException{
    	String response=null;
    	try {
    		
            response = Request.Get(this.server+"/rest/V1/products/attributes?searchCriteria[currentPage]=1&searchCriteria[pageSize]=1000&searchCriteria[sortOrders][0][field]=position&searchCriteria[sortOrders][0][direction]=ASC&fields=items[attribute_code,default_frontend_label,frontend_input,backend_type,position,options]")
                    .addHeader(FrontierWholesalesConstants.AUTHORIZATION, FrontierWholesalesConstants.BEARER+adminToken)
                    .execute().returnContent().asString();
         
            
        } catch (IOException e) {
            
            log.error("Error getting Product Facet List: ERROR: " + e.getMessage());
            throw new FrontierWholesalesBusinessException("IO Error", FrontierWholesalesErrorCode.IO_ERROR);
        }
        return response;
    }
    
    public String getProducts(String adminToken, MagentoSearch search) throws FrontierWholesalesBusinessException {
    		
    		String queryString = search.toString();
    		
    		String api="/rest/V1/products?"+queryString;
            String response = constructAPIMethod("Get",adminToken,api,"getProducts",null,null);
        		
			log.debug("Calling product search [{}]");
			
    		return response;
    }
    
    public String getProducts(String adminToken, String queryString) throws FrontierWholesalesBusinessException {
		String response = null;      
		String serviceURL =   "/rest/V1/mailman/search?" + queryString;
		
		try {
			log.debug("Calling product search v2 [{}]", serviceURL);
			
             response = constructAPIMethod("Get",adminToken,serviceURL,"getProductDetails",null,null);
			
		} catch(Exception anyEx) {
			throw new FrontierWholesalesBusinessException("IO Error", FrontierWholesalesErrorCode.IO_ERROR);
		}
			
			return response;
	}
    
    public String getProductDetails(String adminToken,String  productID) throws FrontierWholesalesBusinessException {
    	
        	     
        	String api="/rest/V1/products/"+productID;
            return constructAPIMethod("Get",adminToken,api,"getProductDetails",null,null);
        		
        
    }
    
    public MagentoCategory getCategories(String adminToken,int categoryId,String server) throws FrontierWholesalesBusinessException{
    	log.debug("getCategories Method Start ");
       String predicate=(categoryId > 0)?"?rootCategoryId="+categoryId:"";
       MagentoCategory category=null;
       this.server = server;
       String api = "/rest/V1/categories"+predicate;
       
       String response = constructAPIMethod("Get", adminToken, api, "getCategories", null,null);
       
    	try {
			category = mapper.readValue(response,MagentoCategory.class);
		} catch(JsonParseException parseEx) {
   	 		log.debug("Error in getCategories Method"+parseEx.getMessage());
	 		throw new FrontierWholesalesBusinessException("Json Parse Error", FrontierWholesalesErrorCode.JSON_PARSE_ERROR);
   	 	}catch(IOException ioEx) {
   	 		log.debug("Error in getCategories Method "+ioEx.getMessage());
   	 		throw new FrontierWholesalesBusinessException("IOError", FrontierWholesalesErrorCode.IO_ERROR);
   	 	}catch(Exception anyEx) {
   	 		log.debug("Error in getCategories Method "+anyEx.getMessage());
   	 		throw new FrontierWholesalesBusinessException("Service Error", FrontierWholesalesErrorCode.GENERAL_SERVICE_ERROR);
   	 	}
    	log.debug("getCategories Method End");
        return category;
    }
    
  


    public List<MagentoRelatedProduct> getRelatedProductsForSku(String adminToken, String sku ) throws FrontierWholesalesBusinessException{
        log.info("Getting related products for SKU: {}", sku);
        List<MagentoRelatedProduct> productList;
        String api = "/rest/V1/products/" + sku + "/links/related";
   		String response = constructAPIMethod("Get", adminToken, api, "getRelatedProductsForSku", null,null);	   
       
         try {
			productList = mapper.readValue(response, new TypeReference<List<MagentoRelatedProduct>>(){});
		} catch (com.fasterxml.jackson.core.JsonParseException e) {
			log.debug("Error in getRelatedProductsForSku Method "+e.getMessage());
   	 		throw new FrontierWholesalesBusinessException("PARSE Error", FrontierWholesalesErrorCode.JSON_PARSE_ERROR);
		} catch (JsonMappingException e) {
			log.debug("Error in getRelatedProductsForSku Method "+e.getMessage());
   	 		throw new FrontierWholesalesBusinessException("MAPPING Error", FrontierWholesalesErrorCode.JSON_MAPPING_ERROR);
		} catch (IOException e) {
			log.debug("Error in getRelatedProductsForSku Method "+e.getMessage());
   	 		throw new FrontierWholesalesBusinessException("IO Error", FrontierWholesalesErrorCode.IO_ERROR);
		}
   
        return productList;
    }
    
    public String getParentChildrenCategories(String adminToken,int categoryId) throws FrontierWholesalesBusinessException{
    	log.debug("getParentChildrenCategories Start");
    	String api = "/rest/V1/frontier/categories/"+categoryId;
    	String response = constructAPIMethod("Get", adminToken, api, "getParentChildrenCategories", null,null);
 	   
    	log.debug("getParentChildrenCategories End");
    	return response;
    }
    
    public String addItemToWishList(String adminToken,String jsonData) throws FrontierWholesalesBusinessException{
    	log.debug("addItemToWishList Start");
    	
    	String api ="/rest/all/V1/frontier/wishlist/add";
    	String response = constructAPIMethod("Post", adminToken, api, "addItemToWishList", null,jsonData);
 	   
	    log.debug("addItemToWishList End");
	    return response;
    	
    }
    
    public String getRequisitionList(String userToken) throws FrontierWholesalesBusinessException{
    	log.debug("getRequisitionList Start");
    	
    	String api ="/rest/default/V1/frontier/customer/requisitionlist";
    	String response = constructAPIMethod("Get", userToken, api, "getRequisitionList", null,null);
 	   
    	log.debug("getRequisitionList End");
    	return response;
    	
    	
    }
    
    public String getCustomerDetails(String userToken) throws FrontierWholesalesBusinessException{
    	log.debug("getCustomerDetails Start ");
    	log.debug("user token is "+userToken);
    	String api = "/rest/default/V1/fc/customers/me";
    	String response = constructAPIMethod("Get",userToken,api,"getCustomerDetails",null,null);    		
    	log.debug("getCustomerDetails End");
    	return response;
    }
  
    
    public String getUserRole(String adminToken,String customerId) throws FrontierWholesalesBusinessException{
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
    		String methodName,AuthCredentials authCredentials,String postParam) throws FrontierWholesalesBusinessException{
    	log.debug("constructAPIMethod "+ methodName+ " Start ");
    	String apiURL=this.server+api;
    	token = (token != null && !token.startsWith("Bearer "))?"Bearer "+token:token;
    	InputStream inputStream = null;
    	
   	 	String response = null;
   	 	try {
	   	 	switch (apiType) {
	   	 	case "Get" :
	   	 		//api for get method
	   	 		inputStream = Request.Get(apiURL)
	   	 		.addHeader(FrontierWholesalesConstants.AUTHORIZATION,token)
	   	 		.connectTimeout(TIME_OUT)	   	 		
	   	 		.execute().returnResponse().getEntity().getContent();	   	 
	   	 		break;
	   	 	case "Login" :
	   	 	  inputStream = Request.Post(apiURL)
             .bodyString(mapper.writeValueAsString(authCredentials), ContentType.APPLICATION_JSON)
             .execute().returnResponse().getEntity().getContent();
	   	 	  break;
	   	 	  
	   	 	case "Post":
		   	 	inputStream = Request.Post(apiURL)		   	 	
		   	 	.addHeader(FrontierWholesalesConstants.AUTHORIZATION,token)		   	 		
		   	 	.addHeader(FrontierWholesalesConstants.CONTENT_TYPE,FrontierWholesalesConstants.CONTENT_TYPE_JSON)
		   	 	.connectTimeout(TIME_OUT)			   	 	
		   	 	.bodyString(postParam,ContentType.APPLICATION_JSON)
	            .execute().returnResponse().getEntity().getContent();
		   	 	  break;
	   	 case "PostWithoutToken":
		   	 	inputStream = Request.Post(apiURL)		   	 	
		   	 		   	 		
		   	 	.addHeader(FrontierWholesalesConstants.CONTENT_TYPE,FrontierWholesalesConstants.CONTENT_TYPE_JSON)
		   	 	.connectTimeout(TIME_OUT)	   	
		   	 	.bodyString(postParam,ContentType.APPLICATION_JSON)
	            .execute().returnResponse().getEntity().getContent();
		   	 	  break;
	   	case "Put":
	   	 	inputStream = Request.Put(apiURL)		   	 	
	   	 	.addHeader(FrontierWholesalesConstants.AUTHORIZATION,token)		   	 		
	   	 	.addHeader(FrontierWholesalesConstants.CONTENT_TYPE,FrontierWholesalesConstants.CONTENT_TYPE_JSON)
	   	 	.connectTimeout(TIME_OUT)	   	
	   	 	.bodyString(postParam,ContentType.APPLICATION_JSON)
            .execute().returnResponse().getEntity().getContent();
	   	 	  break;
	   	 	default:
	   	 		inputStream = Request.Get(apiURL)
	   	 		.addHeader(FrontierWholesalesConstants.AUTHORIZATION,token)
	   	 		.connectTimeout(TIME_OUT)	   	 		
	   	 		.execute().returnResponse().getEntity().getContent();		   	 	  
	   	 		break;
	   	 	}
   	 	response = FrontierWholesalesUtils.parseMagentoResponseObject(inputStream,methodName);
   	 	}catch(IOException ioEx) {
   	 		log.debug("Error in constructAPIMethod "+methodName+" "+ioEx.getMessage());
   	 		throw new FrontierWholesalesBusinessException(ioEx.getMessage(), FrontierWholesalesErrorCode.IO_ERROR);
   	 	}catch(Exception anyEx){
   	 		log.debug("Error in constructAPIMethod "+methodName+" "+anyEx.getMessage());
   	 		throw new FrontierWholesalesBusinessException(anyEx.getMessage(), FrontierWholesalesErrorCode.GENERAL_SERVICE_ERROR);
   	 	}finally {
    		if(inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					
					log.debug("Error during stream closing");
				}
    		}
    	}
    	log.debug("constructAPIMethod "+ methodName+ " End");
    	return response;
   	 	
    }

	

}


