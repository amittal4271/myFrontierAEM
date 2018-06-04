package com.frontierwholesales.core.magento.services;

import java.io.IOException;
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
import com.frontierwholesales.core.magento.models.MagentoRelatedProduct;
import com.frontierwholesales.core.utils.AuthCredentials;

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
    
    private static String adminUser="";
    
    private static String adminPassword="";
    
    private long PAGE_SIZE=2;

    @Property(
           
            label = "Magento Server",
            description = "Magento Server",
            value = "http://frontierb2b.ztech.io/index.php"
    )
    public static final String SERVER = "server";
    
    @Property(
            
            label = "Magento Server admin user",
            description = "Magento Server admin user",
            value = "admin"
    )
    public static final String ADMIN_USER="adminUser";
    
    @Property(
    		
            label = "Magento Server admin password",
            description = "Magento Server admin password",
            passwordValue = "admin"
    )
    public static final String ADMIN_PASSWORD="adminPassword";


    public FrontierWholesalesMagentoCommerceConnector() {
        mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
    }


   
    @Activate
    protected void activate(Map<String, Object> properties) {

        ConfigurationParameters cfg = ConfigurationParameters.of(properties);
       
        server = cfg.getConfigValue(SERVER, "http://frontierb2b.ztech.io/index.php");
        
        adminUser = cfg.getConfigValue(ADMIN_USER, "admin");
        
        adminPassword = cfg.getConfigValue(ADMIN_PASSWORD, "admin");
        log.info("user "+adminUser+" pwd "+adminPassword);
       
    }


    public static String getServer() {
        return server;
    }

    public static void setServer(String server) {
    	FrontierWholesalesMagentoCommerceConnector.server = server;
    }
    
    

    public String getAdminUser() {
    	log.debug("get method "+adminUser);
		return adminUser;
	}



	public void setAdminUser(String adminUser) {
		FrontierWholesalesMagentoCommerceConnector.adminUser = adminUser;
	}



	public String getAdminPassword() {
		return adminPassword;
	}



	public void setAdminPassword(String adminPassword) {
		FrontierWholesalesMagentoCommerceConnector.adminPassword = adminPassword;
	}



	public String getToken(String username, String password)throws Exception{
        AuthCredentials authCredentials = new AuthCredentials(username, password);

        log.debug(" AUTHENTICATING " + username + " Against server:" + server);
        String token = Request.Post(server + "/rest/V1/integration/customer/token")
                .bodyString(mapper.writeValueAsString(authCredentials), ContentType.APPLICATION_JSON)
                .execute().returnContent().asString();
        return "Bearer " + token.replace("\"", "");

    }
    
    public String getAdminToken()throws Exception{
        AuthCredentials authCredentials = new AuthCredentials(adminUser, adminPassword);

        log.debug(" AUTHENTICATING " +adminUser + " Against server:" + server);
        String token = Request.Post(server + "/rest/all/V1/integration/admin/token")
                .bodyString(mapper.writeValueAsString(authCredentials), ContentType.APPLICATION_JSON)
                .execute().returnContent().asString();
        return "Bearer " + token.replace("\"", "");

    }
    
   



    public String initCart(String authToken) throws Exception {
        
    	String cartId = null;
    	try {
         cartId = Request.Post(server + "/rest/V1/carts/mine")
                .addHeader("Authorization", authToken)
               
                .execute().returnContent().asString();
    	}catch(Exception ioEx) {
    		log.error("Error in cart initialization: ERROR" + ioEx.getMessage());
    		System.out.println("Error "+ioEx.getMessage());
    	}
        return (cartId != null)?cartId.replace("\"", ""):null;
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
    	String newItem= null;
    	try {
    	 newItem = Request.Post(server+"/rest/V1/carts/mine/items")
    			.addHeader("Authorization",token)
    			.addHeader("ContentType","application/json")
    			.bodyString(jsonData,ContentType.APPLICATION_JSON)
    			.execute().returnContent().asString();
    	}catch(IOException ioEx) {
    		log.error(" addItemToCart: ERROR: " + ioEx.getMessage());
    	}
    	return newItem;
    }
    
    public String getProducts(String adminToken,FrontierWholesalesProductSearch search) {
    	
        String response=null;
        String orderByPrice= "";
        String featured ="";
        String newProduct="";
        String searchCriteria = "searchCriteria[currentPage]="+search.getCurrentPage()+"&searchCriteria[pageSize]="+search.getNoOfRecsPerPage()+"&"+
        						 "searchCriteria[filterGroups][0][filters][0][value]="+search.getCategoryId()+"&"+
        						 "searchCriteria[filterGroups][0][filters][0][field]=category_id";
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
        
        try {
            
            response = Request.Get(server+"/rest/V1/products?"+searchCriteria)
                    .addHeader("Authorization", adminToken)
                    .execute().returnContent().asString();
         
            
        } catch (IOException e) {
            e.printStackTrace();
            log.error("Error getting Product List: ERROR: " + e.getMessage());
        }
        return response;
    }
    
    public String getCategories(String adminToken,int categoryId){
       String predicate=(categoryId > 0)?"?rootCategoryId="+categoryId:"";
        String response=null;
        try {
            response = Request.Get(server+"/rest/V1/categories"+predicate)
                    .addHeader("Authorization", adminToken)
                    .execute().returnContent().asString();
           
        } catch (IOException e) {
            e.printStackTrace();
            log.error("Error Getting Categories from Server: ERROR" + e.getMessage());
        }
        return response;
    }


    public List<MagentoRelatedProduct> getRelatedProductsForSku( String authToken, String sku ) {
        log.info("Getting related products for SKU: {}", sku);
        List<MagentoRelatedProduct> productList = new ArrayList<>();
        String response;
        try {
        	// products
        	response = Request.Get(server + "/rest/V1/products/" + sku + "/links/related")
                    .addHeader("Authorization", authToken)
                    .execute().returnContent().asString();
            log.debug("Related products for SKU response from endpoint:\n {}", response);

            productList = mapper.readValue(response, new TypeReference<List<MagentoRelatedProduct>>(){});
        } catch( IOException e ) {
        	e.printStackTrace();
            log.error("IOException trying to retrieve related products for sku: {}\n{}", sku, e);
        }
        return productList;
    }

}
