package com.frontierwholesales.core.magento.services;

import java.io.IOException;
import java.util.HashMap;
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
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
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
    	log.info("get method "+adminUser);
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

        log.info(" AUTHENTICATING " + username + " Against server:" + server);
        String token = Request.Post(server + "/rest/V1/integration/customer/token")
                .bodyString(mapper.writeValueAsString(authCredentials), ContentType.APPLICATION_JSON)
                .execute().returnContent().asString();
        return "Bearer " + token.replace("\"", "");

    }
    
    public String getAdminToken()throws Exception{
        AuthCredentials authCredentials = new AuthCredentials(adminUser, adminPassword);

        log.info(" AUTHENTICATING " +adminUser + " Against server:" + server);
        String token = Request.Post(server + "/rest/all/V1/integration/admin/token")
                .bodyString(mapper.writeValueAsString(authCredentials), ContentType.APPLICATION_JSON)
                .execute().returnContent().asString();
        return "Bearer " + token.replace("\"", "");

    }



    public String initCart(String authToken) throws Exception {
        String predicate = (authToken == null) ?  "guest-carts/" : "carts/mine";

        String cartId = Request.Post(server + "/rest/V1/" + predicate)
                .addHeader("Authorization", authToken)
                .bodyString("{}", ContentType.APPLICATION_JSON)
                .execute().returnContent().asString();
        return cartId.replace("\"", "");
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


}
