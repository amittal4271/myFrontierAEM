package com.frontierwholesales.core.magento.services.models;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.frontierwholesales.core.beans.FrontierWholesaleOrderConfirmation;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

@Model(adaptables= {SlingHttpServletRequest.class,Resource.class})
public class OrderConfirmationModel extends BaseModel{

	private static final Logger log = LoggerFactory.getLogger(OrderConfirmationModel.class);
	
	private FrontierWholesaleOrderConfirmation confirmation;
	@Override
	protected void init() {
		log.debug("Init method OrderConfirmationModel Class");
		try {
			String adminToken = getAdminToken();
			String confirmationPath = slingHttpServletRequest.getRequestPathInfo().getSuffix();
			if(confirmationPath !=null) {
				String[] pathSplit = confirmationPath.split("/");
				if(pathSplit.length > 0) {
					log.debug("cof1");
					String confirmationNr = pathSplit[1];
					log.debug("cof2 "+confirmationNr);
					String response = connector.getOrderConfirmation(adminToken, confirmationNr);
					log.debug("response is "+response);
					  this.confirmation = transferJsonToObject(response);
					  log.debug("transferred to class "+confirmation.getCustomerEmail());
					
				}else {
					log.debug("path is invalid");
				}
				
			}
			log.debug("confirmation path is "+confirmationPath);
			
		} catch (Exception e) {
			
			log.error("Error in OrderConfirmation Page ",e.getMessage());
		}
	}
	
	private FrontierWholesaleOrderConfirmation transferJsonToObject(String object) throws Exception{
		Gson json = new Gson();
		FrontierWholesaleOrderConfirmation conf = new FrontierWholesaleOrderConfirmation();
		JsonElement confirmationObject = json.fromJson(object, JsonElement.class);
		JsonObject confObject = confirmationObject.getAsJsonObject();
		conf.setConfirmationId(confObject.get("increment_id").getAsString());
		conf.setCustomerEmail(confObject.get("customer_email").getAsString());
		JsonArray itemsArray = confObject.getAsJsonArray("items");
		List<FrontierWholesaleOrderConfirmation.Items> listItems = new ArrayList<FrontierWholesaleOrderConfirmation.Items>();
		for(JsonElement element:itemsArray) {
			FrontierWholesaleOrderConfirmation.Items item = new FrontierWholesaleOrderConfirmation().new Items();
			JsonObject itemObject = element.getAsJsonObject();
			item.setSku(itemObject.get("sku").getAsString());
			item.setQty_ordered(itemObject.get("qty_ordered").getAsString());
			item.setName(itemObject.get("name").getAsString());
			item.setPrice(itemObject.get("price").getAsString());
			
			listItems.add(item);
		}
		conf.setItems(listItems);
		return conf;
	}

	public FrontierWholesaleOrderConfirmation getConfirmation() {
		return confirmation;
	}
	
	

}
