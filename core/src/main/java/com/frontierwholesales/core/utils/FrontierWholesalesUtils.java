package com.frontierwholesales.core.utils;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class FrontierWholesalesUtils {

	/**
	 * update for single json object
	 * @param object
	 * @param items
	 * @param key
	 * @param value
	 * @return
	 */
	public static String updateJsonObject(String object,String items,String key,String value) {
		Gson json = new Gson();
		
		JsonElement element = json.fromJson(object, JsonElement.class);
		JsonObject jsonObject = element.getAsJsonObject();
		JsonElement itemElement = jsonObject.get(items);
		
		JsonObject itemObject = itemElement.getAsJsonObject();
		itemObject.addProperty(key, value);
		
		JsonElement updatedElement = json.fromJson(itemObject, JsonElement.class);
		jsonObject.add(items,updatedElement);
		
		return jsonObject.toString();
	}
	
	 public static JsonObject convertStringToJSONObject(String jsonObject)throws Exception {
		 Gson gson = new Gson();
		 JsonElement element = gson.fromJson(jsonObject, JsonElement.class);
			JsonObject object = element.getAsJsonObject();
			
			return object;
	 }
}
