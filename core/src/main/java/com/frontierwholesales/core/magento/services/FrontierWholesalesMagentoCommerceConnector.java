package com.frontierwholesales.core.magento.services;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.felix.scr.annotations.Activate;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.ConfigurationPolicy;
import org.apache.felix.scr.annotations.Properties;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Service;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;
import org.apache.jackrabbit.oak.spi.security.ConfigurationParameters;
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

@Component(
		immediate = true,
        label = "Frontier Wholesales Magento Commerce Provider",
        configurationFactory = true,
        metatype = true,
        policy = ConfigurationPolicy.REQUIRE
)
@Service(FrontierWholesalesMagentoCommerceConnector.class)
@Properties(value = {
    @Property(name = "service.description", value = "Frontier Wholesales  Magento Commerce Provider"),
    @Property(name = "service.vendor", value = "Frontier Wholesales"),
})
public class FrontierWholesalesMagentoCommerceConnector {

    private static final Logger log = LoggerFactory.getLogger(FrontierWholesalesMagentoCommerceConnector.class);
    

    private ObjectMapper mapper = new ObjectMapper();

    private static String server = "http://localhost:8000";
    
    
    private static String appToken="";
  
    
    @Property(
    		
            label = "Magento Server App Token",
            description = "Magento Server App Token",
            value = "token"
    )
    public static String APP_TOKEN ="appToken";
    

    @Property(
    		
            label = "Magento Server",
            description = "Magento Server",
            value = "http://frontierb2b.ztech.io/index.php"
    )
    public static final String SERVER = "server";
    
   

    public static final int TIME_OUT=6000;

    public FrontierWholesalesMagentoCommerceConnector() {
        mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
    }


   
    @Activate
    protected void activate(Map<String, Object> properties) {

        ConfigurationParameters cfg = ConfigurationParameters.of(properties);
        
        appToken = cfg.getConfigValue(APP_TOKEN, "");
        
        server = cfg.getConfigValue(SERVER, "http://frontierb2b.ztech.io/index.php");
       
       
    }

    


	public  String getAppToken() {
		
		return appToken;
	}



	public  void setAppToken(String appToken) {
		FrontierWholesalesMagentoCommerceConnector.appToken = appToken;
	}





	public static String getServer() {
        return server;
    }

    public static void setServer(String server) {
    	FrontierWholesalesMagentoCommerceConnector.server = server;
    }
    
    

	public String getToken(String username, String password)throws Exception{
        AuthCredentials authCredentials = new AuthCredentials(username, password);

        log.debug(" AUTHENTICATING " + username + " Against server:" + server);
        
        InputStream inputStream = Request.Post(server + "/rest/V1/integration/customer/token")
                .bodyString(mapper.writeValueAsString(authCredentials), ContentType.APPLICATION_JSON)
                .execute().returnResponse().getEntity().getContent();
        String response = FrontierWholesalesUtils.parseMagentoResponseObject(inputStream,"getToken");
        return "Bearer " + response.replace("\"", "");

    }
    
    public String getAdminToken() throws FrontierWholesalesBusinessException{
    	log.debug("getAdminToken Method");
    	
    	return "Bearer "+appToken;

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
   	
	   InputStream inputStream = Request.Get(server+"/rest/V1/frontier/aem/carts/mine/totals/details")
	   			.addHeader("Authorization",token)
	   			.addHeader("ContentType","application/json")
	   			.execute().returnResponse().getEntity().getContent();
	   
   		String response = FrontierWholesalesUtils.parseMagentoResponseObject(inputStream,"getCartTotalWithItems");
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
    	log.debug("user token is"+token+" itemId "+itemId);
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
    	
    	log.debug("api url for add to cart "+server+"/rest/V1/carts/mine/items");
    	log.debug("json item is "+jsonData);
    	
    	InputStream inputStream = Request.Post(server+"/rest/V1/carts/mine/items")
    			.addHeader("Authorization",token)
    			.addHeader("ContentType","application/json")
    			.bodyString(jsonData,ContentType.APPLICATION_JSON)
    			.execute().returnResponse().getEntity().getContent(); 	 
    	 
    	 
    	String response = FrontierWholesalesUtils.parseMagentoResponseObject(inputStream,"addItemToCart");
    	    		
    	log.debug("Add to cart response is "+response);
    	return response;
    }
    
    public String getProducts(String adminToken,FrontierWholesalesProductSearch search) throws Exception{
    	
        log.debug("FrontierWholesalesMagentoCommerceConnector getProducts Start");
        String orderByPrice= "";
        String featured ="";
        String newProduct="";
        String searchCriteria = "searchCriteria[currentPage]="+search.getCurrentPage()+"&searchCriteria[pageSize]="+search.getNoOfRecsPerPage()+"&"+
        						 "searchCriteria[filterGroups][0][filters][0][value]="+search.getCategoryId()+"&"+
        						 "searchCriteria[filterGroups][0][filters][0][field]=category_id";
        
        searchCriteria += "&searchCriteria[filterGroups][1][filters][0][field]=visibility&"+
        		"searchCriteria[filterGroups][1][filters][0][value]=2&"+
        		"searchCriteria[filterGroups][1][filters][1][field]=visibility&"+
        		"searchCriteria[filterGroups][1][filters][1][value]=4&"+
        		"searchCriteria[filterGroups][2][filters][0][field]=status&"+
        		"searchCriteria[filterGroups][2][filters][0][value]=1";
        		
       
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
        
       
            
           InputStream inputStream = Request.Get(server+"/rest/V1/products?"+searchCriteria)
                    .addHeader("Authorization", adminToken)
                    .connectTimeout(TIME_OUT)
                    .execute().returnResponse().getEntity().getContent();
         
          
        String response = FrontierWholesalesUtils.parseMagentoResponseObject(inputStream,"getProducts");
        log.debug("FrontierWholesalesMagentoCommerceConnector getProducts End");
        return response;
    }
    
    public String getBrands(String adminToken) throws Exception{
    	log.debug("getBrands Start");
    	String response=null;
    
    		
    	 InputStream inputStream = Request.Get(server+"/rest/V1/frontier/product/attributes/brandlist")
                    .addHeader("Authorization", adminToken)
                    .connectTimeout(TIME_OUT)
                    .execute().returnResponse().getEntity().getContent();
         
    	 response = FrontierWholesalesUtils.parseMagentoResponseObject(inputStream,"getBrands");
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
    		String response = null;
    		String queryString = search.toString();
    		String serviceURL = server + "/rest/V1/products?" + queryString;

			log.debug("Calling product search [{}]", serviceURL);
			InputStream inputStream = Request.Get(serviceURL).addHeader("Authorization", adminToken)
					.execute().returnResponse().getEntity().getContent();
		
			response =  FrontierWholesalesUtils.parseMagentoResponseObject(inputStream,"getProducts");
    		return response;
    }
    
    public String getProducts(String adminToken, String queryString) {
		String response = null;      
		String serviceURL = server + "/rest/V1/mailman/search?" + queryString;

		try {
			log.debug("Calling product search v2 [{}]", serviceURL);
			response = Request.Get(serviceURL).addHeader("Authorization", adminToken)
					.execute().returnContent().asString();
		} catch (IOException e) {
			log.error("Error getting Product List", e);
		}
			
			return response;
	}
    
    public String getProductDetails(String adminToken,String  productID) throws Exception {
    	
        	String response=null;       
      
        	InputStream inputStream  = Request.Get(server+"/rest/V1/products/"+productID)
                    .addHeader("Authorization", adminToken)
                    .execute().returnResponse().getEntity().getContent();        
        	response =  FrontierWholesalesUtils.parseMagentoResponseObject(inputStream,"getProductDetails");
    		return response;
        
    }
    
    public MagentoCategory getCategories(String adminToken,int categoryId) throws Exception{
    	log.debug("getCategories Method Start");
       String predicate=(categoryId > 0)?"?rootCategoryId="+categoryId:"";
       MagentoCategory category=null;
  
    	InputStream inputStream  = Request.Get(server+"/rest/V1/categories"+predicate)
                .addHeader("Authorization", adminToken)
                .connectTimeout(TIME_OUT)
                .execute().returnResponse().getEntity().getContent();
    	String response =  FrontierWholesalesUtils.parseMagentoResponseObject(inputStream,"getCategories");
    	category = mapper.readValue(response,MagentoCategory.class);
    	log.debug("getCategories Method End");
        return category;
    }
    
    public String getCustomerShippingAddress(String adminToken,String customerId) {
    	
    	String response = null;
    	 try {
             response = Request.Get(server+"/rest/V1/customers/"+ customerId+"/shippingAddress")
                     .addHeader("Authorization", adminToken)
                     .execute().returnContent().asString();
           
         } catch (IOException e) {
             e.printStackTrace();
             log.error("Error Getting Shipping address from Server: ERROR" + e.getMessage());
         }
         return response;
    }
    
    public String cartShippingAddress(String userToken,String billingAddress) throws Exception{
    	String response = Request.Post(server+"/rest/V1/carts/mine/shipping-address")
    					.addHeader("Authorization",userToken)
    					.addHeader("ContentType","application/json")
    					.bodyString(billingAddress,ContentType.APPLICATION_JSON)
    					.execute().returnContent().asString();
    	return response;
    }
    
    public String getCustomerBillingAddress(String adminToken,String customerId) {
    	
    	String response = null;
    	 try {
             response = Request.Get(server+"/rest/V1/customers/"+ customerId+"/billingAddress")
                     .addHeader("Authorization", adminToken)
                     .execute().returnContent().asString();
           
         } catch (IOException e) {
             e.printStackTrace();
             log.error("Error Getting billing address from Server: ERROR" + e.getMessage());
         }
         return response;
    }
    
    public String cartBillingAddress(String userToken,String billingAddress) throws Exception{
    	String response = Request.Post(server+"/rest/V1/carts/mine/billing-address")
    					.addHeader("Authorization",userToken)
    					.addHeader("ContentType","application/json")
    					.bodyString(billingAddress,ContentType.APPLICATION_JSON)
    					.execute().returnContent().asString();
    	return response;
    }
    
    public String shippingMethods(String userToken) throws Exception{
    	String response = Request.Get(server+"/rest/V1/carts/mine/shipping-methods")
    					.addHeader("Authorization",userToken)
    					.addHeader("ContentType","application/json")
    					.execute().returnContent().asString();
    	return response;
    }
    
    public String getCustomer(String userToken) throws Exception{
    	
    	String response = Request.Get(server+"/rest/V1/customers/me")
			.addHeader("Authorization",userToken)
			.addHeader("ContentType","application/json")
			.execute().returnContent().asString();
    	return response;
    }
    
    public String getShippingInfo(String userToken,String address) throws Exception{
    	String response = Request.Post(server+"/rest/V1/carts/mine/shipping-information")
    			.addHeader("Authorization",userToken)
    			.addHeader("ContentType","application/json")
    			.bodyString(address,ContentType.APPLICATION_JSON)
    			.execute().returnContent().asString();
    	return response;
    }

    public String getCustomerOrders(String adminToken,String customerId) throws Exception{
    	String orders="/rest/V1/orders?searchCriteria[pageSize]=50"
    			+ "&searchCriteria[currentPage]=1&searchCriteria[filterGroups][0][filters][0][value]="+customerId
    			+ "&searchCriteria[filterGroups][0][filters][0][field]=customer_id";
    	String response = Request.Get(server+orders)
    			         .addHeader("Authorization",adminToken)
    			         .execute().returnContent().asString();
    	return response;
    }
    
    public String submitPayment(String userToken,String paymentInfo) throws Exception{
    	String response=null;
    	
    	try {
    	response = Request.Post(server+"/rest/V1/carts/mine/payment-information")
    			.addHeader("Authorization",userToken)
    			.addHeader("ContentType","application/json")
    			.bodyString(paymentInfo,ContentType.APPLICATION_JSON)
    			.execute().returnContent().asString();
    	
    	}catch(Exception anyEx) {
    		log.error("Exception during the payment submission ",anyEx,anyEx.getMessage());
    		
    	}
    	return response;
    }


    public List<MagentoRelatedProduct> getRelatedProductsForSku(String adminToken, String sku ) throws Exception{
        log.info("Getting related products for SKU: {}", sku);
        List<MagentoRelatedProduct> productList = new ArrayList<>();
       
        
        InputStream inputStream = Request.Get(server + "/rest/V1/products/" + sku + "/links/related")
                    .addHeader("Authorization", adminToken)
                    .connectTimeout(TIME_OUT)
                    .execute().returnResponse().getEntity().getContent();
            log.debug("Related products for SKU response from endpoint:\n {}");
        String response =  FrontierWholesalesUtils.parseMagentoResponseObject(inputStream,"getRelatedProductsForSku");
        productList = mapper.readValue(response, new TypeReference<List<MagentoRelatedProduct>>(){});
   
        return productList;
    }
    
    public String getParentChildrenCategories(String adminToken,int categoryId) throws Exception{
    	log.debug("getParentChildrenCategories Start");
    	InputStream inputStream = Request.Get(server+"/rest/V1/frontier/categories/"+categoryId)
    				.addHeader("Authorization",adminToken)
    				.connectTimeout(TIME_OUT)
    				.execute().returnResponse().getEntity().getContent();
    	 String response =  FrontierWholesalesUtils.parseMagentoResponseObject(inputStream,"getParentChildrenCategories");
    	log.debug("getParentChildrenCategories End");
    	return response;
    }
    
    public String addItemToWishList(String adminToken,String jsonData) throws Exception{
    	log.debug("addItemToWishList Start");
    	
    	InputStream inputStream  = Request.Post(server+"/rest/all/V1/frontier/wishlist/add")
					.addHeader("Authorization",adminToken)
					.addHeader("ContentType","application/json")
					.connectTimeout(TIME_OUT)
					.bodyString(jsonData,ContentType.APPLICATION_JSON)
					.execute().returnResponse().getEntity().getContent();
    	 String response =  FrontierWholesalesUtils.parseMagentoResponseObject(inputStream,"addItemToWishList");
	    log.debug("addItemToWishList End");
	    return response;
    	
    }
    
    public String getRequisitionList(String userToken) throws Exception{
    	log.debug("getRequisitionList Start");
    	InputStream inputStream = Request.Get(server+"/rest/default/V1/frontier/customer/requisitionlist")
				.addHeader("Authorization",userToken)
				.connectTimeout(TIME_OUT)
				.execute().returnResponse().getEntity().getContent();
    	 String response =  FrontierWholesalesUtils.parseMagentoResponseObject(inputStream,"getRequisitionList");
    	log.debug("getRequisitionList End");
    	return response;
    	
    	
    }
    
    public String getCustomerDetails(String userToken) throws Exception{
    	log.debug("getCustomerDetails Start");
    	InputStream inputStream = Request.Get(server+"/rest/default/V1/fc/customers/me")
				.addHeader("Authorization",userToken)
				.connectTimeout(TIME_OUT)
				.execute().returnResponse().getEntity().getContent();
    	 String response =  FrontierWholesalesUtils.parseMagentoResponseObject(inputStream,"getCustomerDetails");
    	log.debug("getCustomerDetails End");
    	return response;
    }
    
    public String getOrderConfirmation(String adminToken,String confirmationNr) throws Exception{
    	log.debug("getOrderConfirmation Start");
    	InputStream inputStream = Request.Get(server+"/rest/V1/orders/"+confirmationNr)
				.addHeader("Authorization",adminToken)
				.connectTimeout(TIME_OUT)
				.execute().returnResponse().getEntity().getContent();
    	 String response =  FrontierWholesalesUtils.parseMagentoResponseObject(inputStream,"getOrderConfirmation");
    	inputStream.close();
    	log.debug("getOrderConfirmation Start");
    	return response;
    }

}


