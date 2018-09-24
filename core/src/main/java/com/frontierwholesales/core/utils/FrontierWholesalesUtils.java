package com.frontierwholesales.core.utils;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.Session;
import javax.jcr.query.Query;
import javax.jcr.query.QueryManager;
import javax.jcr.query.QueryResult;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.frontierwholesales.core.magento.services.FrontierWholesalesMagentoCommerceConnector;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class FrontierWholesalesUtils {

	private static final Logger log = LoggerFactory.getLogger(FrontierWholesalesUtils.class);
	
	private FrontierWholesalesMagentoCommerceConnector commerceConnector = new FrontierWholesalesMagentoCommerceConnector();
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
	 
	 public static String getIdFromObject(String object,String key) throws Exception{
		 Gson gson = new Gson();
		 JsonElement element = gson.fromJson(object, JsonElement.class);
		 JsonObject jsonObject = element.getAsJsonObject();
		 
		return jsonObject.get(key).getAsString();
	 }
	 
	 /**
	     * Add the provided HTTP Cookie to the Response
	     *
	     * @param cookie   Cookie to add
	     * @param response Response to add Cookie to
	     * @return true unless cookie or response is null
	     */
	    public static boolean addCookie(final Cookie cookie, final HttpServletResponse response) {
	        if (cookie == null || response == null) {
	            return false;
	        }

	        response.addCookie(cookie);
	        return true;
	    }

	    /**
	     * Get the named cookie from the HTTP Request
	     *
	     * @param request    Request to get the Cookie from
	     * @param cookieName name of Cookie to get
	     * @return the named Cookie, null if the named Cookie cannot be found
	     */
	    public static Cookie getCookie(final HttpServletRequest request, final String cookieName) {
	        if (StringUtils.isBlank(cookieName)) {
	            return null;
	        }

	        final Cookie[] cookies = request.getCookies();
	        if (cookies == null) {
	            return null;
	        }

	        if (cookies.length > 0) {
	            for (final Cookie cookie : cookies) {
	                if (StringUtils.equals(cookieName, cookie.getName())) {
	                    return cookie;
	                }
	            }
	        }

	        return null;
	    }

	    /**
	     * Gets Cookies from the Request whose names match the provides Regex
	     *
	     * @param request Request to get the Cookie from
	     * @param regex   Regex to match against Cookie names
	     * @return Cookies which match the Regex
	     */
	    public static List<Cookie> getCookies(final HttpServletRequest request, final String regex) {
	        final ArrayList<Cookie> foundCookies = new ArrayList<Cookie>();
	        if (StringUtils.isBlank(regex)) {
	            return foundCookies;
	        }

	        final Cookie[] cookies = request.getCookies();
	        if (cookies == null) {
	            return Collections.emptyList();
	        }

	        final Pattern p = Pattern.compile(regex);
	        for (final Cookie cookie : cookies) {
	            final Matcher m = p.matcher(cookie.getName());
	            if (m.matches()) {
	                foundCookies.add(cookie);
	            }
	        }

	        return foundCookies;
	    }

	    /**
	     * <p>
	     * Extend the cookie life.
	     * <p></p>
	     * This can be used when a cookie should be valid for X minutes from the last point of activity.
	     * <p></p>
	     * This method will leave expired or deleted cookies alone.
	     * </p>
	     *
	     * @param request    Request to get the Cookie from
	     * @param response   Response to write the extended Cookie to
	     * @param cookieName Name of Cookie to extend the life of
	     * @param expiry     New Cookie expiry
	     */
	    public static boolean extendCookieLife(final HttpServletRequest request, final HttpServletResponse response,
	                                           final String cookieName, final String cookiePath, final int expiry) {
	        final Cookie cookie = getCookie(request, cookieName);
	        if (cookie == null) {
	            return false;
	        }

	        if (cookie.getMaxAge() <= 0) {
	            return false;
	        }

	        final Cookie responseCookie = (Cookie) cookie.clone();
	        responseCookie.setMaxAge(expiry);
	        responseCookie.setPath(cookiePath);

	        addCookie(responseCookie, response);

	        return true;
	    }

	    /**
	     * Remove the named Cookies from Response
	     *
	     * @param request     Request to get the Cookies to drop
	     * @param response    Response to expire the Cookies on
	     * @param cookieNames Names of cookies to drop
	     * @return Number of Cookies dropped
	     */
	    public static int dropCookies(final HttpServletRequest request, final HttpServletResponse response, final String cookiePath, final String... cookieNames) {
	        int count = 0;
	        if (cookieNames == null) {
	            return count;
	        }

	        final List<Cookie> cookies = new ArrayList<Cookie>();
	        for (final String cookieName : cookieNames) {
	            cookies.add(getCookie(request, cookieName));
	        }

	        return dropCookies(response, cookies.toArray(new Cookie[cookies.size()]), cookiePath);
	    }

	    /**
	     * Internal method used for dropping cookies
	     *
	     * @param response
	     * @param cookies
	     * @param cookiePath
	     * @return
	     */
	    private static int dropCookies(final HttpServletResponse response, final Cookie[] cookies, final String cookiePath) {
	        int count = 0;

	        for (final Cookie cookie : cookies) {
	            if (cookie == null) {
	                continue;
	            }

	            final Cookie responseCookie = (Cookie) cookie.clone();
	            responseCookie.setMaxAge(0);
	            responseCookie.setPath(cookiePath);
	            responseCookie.setValue("");

	            addCookie(responseCookie, response);
	            count++;
	        }

	        return count;
	    }

	    /**
	     * Remove the Cookies whose names match the provided Regex from Response
	     *
	     * @param request  Request to get the Cookies to drop
	     * @param response Response to expire the Cookies on
	     * @param regexes  Regex to find Cookies to drop
	     * @return Number of Cookies dropped
	     */
	    public static int dropCookiesByRegex(final HttpServletRequest request, final HttpServletResponse response, final String cookiePath, final String... regexes) {
	        return dropCookiesByRegexArray(request, response, cookiePath, regexes);
	    }

	    /**
	     * Remove the Cookies whose names match the provided Regex from Response
	     *
	     * @param request  Request to get the Cookies to drop
	     * @param response Response to expire the Cookies on
	     * @param regexes  Regex to find Cookies to drop
	     * @return Number of Cookies dropped
	     */
	    public static int dropCookiesByRegexArray(final HttpServletRequest request, final HttpServletResponse response, final String cookiePath, final String[] regexes) {
	        int count = 0;
	        if (regexes == null) {
	            return count;
	        }
	        final List<Cookie> cookies = new ArrayList<Cookie>();

	        for (final String regex : regexes) {
	            cookies.addAll(getCookies(request, regex));
	        }

	        return dropCookies(response, cookies.toArray(new Cookie[cookies.size()]), cookiePath);
	    }

	    /**
	     * Removes all cookies for the domain
	     *
	     * @param request  Request to get the Cookies to drop
	     * @param response Response to expire the Cookies on
	     */
	    public static int dropAllCookies(final HttpServletRequest request, final HttpServletResponse response, final String cookiePath) {
	        final Cookie[] cookies = request.getCookies();

	        if (cookies == null) {
	            return 0;
	        }

	        return dropCookies(response, cookies, cookiePath);
	    }
	    
	    /**
	     * Retrieve image based on SKU
	     * @param productSku
	     * @param request
	     * @return
	     * @throws Exception
	     */
	    public String getImagePath(String productSku,SlingHttpServletRequest request) throws Exception{
			log.debug("getImagePath Start");
			Session session = request.getResourceResolver().adaptTo(Session.class);
			QueryManager queryManager = session.getWorkspace().getQueryManager();
			String path="";
			String imgPath="";
			
			Resource res = request.getResource();
			ResourceResolver resourceResolver = request.getResourceResolver();
			
			
			String sqlStatement="SELECT * FROM [nt:unstructured] AS node\n" + 
		    		"WHERE ISDESCENDANTNODE(node, \"/content/dam/FrontierImages/product/"+ productSku+"\")"+
		    		"ORDER BY node.title"; 
		    			
			
			Query query = queryManager.createQuery(sqlStatement,"JCR-SQL2");	   		   
		    QueryResult result = query.execute();	  
		    NodeIterator nodeIter = result.getNodes();
			  
		    while ( nodeIter.hasNext() ) {
		    	
		    	Node node = nodeIter.nextNode();
		        path = node.getPath();
		       
		        res = resourceResolver.getResource(path);	        
		        String name = node.getName();
		        if(name.equals("jcr:content")) {
		        
		        	ValueMap properties = res.adaptTo(ValueMap.class);
		        	
		        	
		        		String relativePath = properties.get("dam:relativePath",(String)null);
			        	
			        	if(relativePath != null) {
			        		boolean bFind = Pattern.compile(Pattern.quote("1_"), Pattern.CASE_INSENSITIVE).matcher(relativePath).find();
			        		if(bFind) {
			        				        			
			        			imgPath = "/content/dam/"+ relativePath;
			        			
			        			 return imgPath;
			        		}
			        	}
		        	}
		        	
		        	
		        }    
		    return "";		    
		}
	    
	    /**
	     * 
	     * @param id
	     * @param customerToken
	     * @return
	     * @throws Exception
	     */
	    public  String getCustomerDetailsByParameter(String id,String customerToken) throws Exception{
	    		String groupId ="";
	    		if(customerToken != null) {
					String customerDetails = commerceConnector.getCustomerDetails(customerToken);
					
					if(customerDetails != null) {
						
	    	
			    		Gson json = new Gson();
			    		JsonElement element = json.fromJson(customerDetails, JsonElement.class);
			    		
			    		
			    		JsonObject object = element.getAsJsonObject();
			    		JsonObject customerObject = object.getAsJsonObject("frontier_customer");
			    		
			    		groupId = customerObject.get(id).getAsString();
					} 		
	    		
	    	
	    		}
	    		return groupId;
	    }
	   
	    /**
	     * 
	     * @param dtCreated
	     * @return
	     * @throws Exception
	     */
	    public String convertToDate(Date dtCreated) throws Exception{
	    	 log.debug("convertToDate Start...");
	    	 String newDate = null;
	    	 SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy", Locale.getDefault());
	    	 newDate = sdf.format(dtCreated);
	    	 log.debug("convertToDate End...");
	    	 return newDate;
	    }
	    
	    public String parseJsonObject(String productList,int recsPerPage,int currentPage,
				SlingHttpServletRequest request, String groupId) throws Exception{
			
			Gson json = new Gson();
			JsonElement element = json.fromJson(productList, JsonElement.class);
			
			
			JsonObject object = element.getAsJsonObject();
			JsonArray itemArray = object.getAsJsonArray("items");
			
			
//			DecimalFormat priceFormat=new DecimalFormat("#0.00");
			for(int i=0;i<itemArray.size();i++) {
				
				JsonElement itemElement = itemArray.get(i);
				JsonObject itemObject = itemElement.getAsJsonObject();
				//JsonElement priceElement = itemObject.get("price");
				//itemObject.addProperty("formattedPrice", "$"+priceFormat.format(priceElement.getAsDouble()));
				JsonElement skuElement = itemObject.get("sku");
				
				itemObject.addProperty("imgPath", this.getImagePath(skuElement.getAsString(),request));
				JsonArray attributesArray = itemObject.getAsJsonArray("custom_attributes");
				
				String status = itemObject.get("status").getAsString();
				String visibility = itemObject.get("visibility").getAsString();
				
				String newProduct="";
				String closeOut="";
				String sale="";
				
				for(JsonElement attributesElement:attributesArray) {
					JsonObject attrObject = attributesElement.getAsJsonObject();
					JsonElement codeElement = attrObject.get("attribute_code");
					
					if(codeElement.getAsString().equals("new_product")) {
						newProduct = attrObject.get("value").getAsString();
						itemObject.addProperty("new_product", newProduct);
					}
					
					if(codeElement.getAsString().equals("close_out")) {
						closeOut = attrObject.get("value").getAsString();
						itemObject.addProperty("close_out", closeOut);
					}
					
					if(codeElement.getAsString().equals("on_sale")) {
						sale = attrObject.get("value").getAsString().equals("Yes")?"1":"0";						
						itemObject.addProperty("on_sale", sale);
					}
					
					if(codeElement.getAsString().equals("special_price")) {
						itemObject.addProperty("special_price", attrObject.get("value").getAsDouble());
					}
					
					if(codeElement.getAsString().equals("bulk")){
						itemObject.addProperty("bulk",  attrObject.get("value").getAsString());
					}
					
					if(codeElement.getAsString().equals("url_key")) {
						String url = attrObject.get("value").getAsString();
						url = url.replaceAll(" ", "-");
						itemObject.addProperty("url", url);
					}
					
					if(newProduct.equals("1")  && closeOut.equals("1") && sale.equals("1")) {
						
						itemObject.addProperty("new_product","1");
						itemObject.addProperty("close_out", "0");
						itemObject.addProperty("on_sale", "0");
					}else if(closeOut.equals("1")  && sale.equals("1")) {
						
						itemObject.addProperty("new_product","0");
						itemObject.addProperty("close_out", "1");
						itemObject.addProperty("on_sale", "0");
					}else if(newProduct.equals("1") && sale.equals("1")) {
						
						itemObject.addProperty("new_product","1");
						itemObject.addProperty("close_out", "0");
						itemObject.addProperty("on_sale", "0");
					}else if(newProduct.equals("1") && closeOut.equals("1")) {
						
						itemObject.addProperty("new_product","1");
						itemObject.addProperty("close_out", "0");
						itemObject.addProperty("on_sale", "0");
					}
				}
				
				if(groupId != null) {
					JsonArray tierPriceArr = itemObject.getAsJsonArray("tier_prices");
					if(tierPriceArr != null)
					for(JsonElement tierElem : tierPriceArr) {
						JsonObject tierObject = tierElem.getAsJsonObject();
						JsonElement groupElement = tierObject.get("customer_group_id");
					
						if(groupElement.getAsString().equals(groupId)) {
							itemObject.addProperty("tierprice", tierObject.get("value").getAsDouble());
						}
					}
				}
				
				itemObject.remove("tier_prices");
				itemObject.remove("price");
				
			}
			
//			JsonElement arrayElement = json.fromJson(itemArray, JsonElement.class);
//			object.add("items", arrayElement);
			object.addProperty("recsPerPage", recsPerPage);
			
			JsonElement totalElement = object.get("total_count");
			int total = totalElement.getAsInt();
			double dPage;
			if(total > 28) {
			 dPage = ((double)total / (double)recsPerPage);
			
			dPage = Math.ceil(dPage);
			}else {
				dPage = 0;
			}
			object.addProperty("pageTotal", (int)dPage);
			//object.add("categories", catElement.getAsJsonObject());
			
			object.addProperty("response_creation_time", new Date()+"");
			return object.toString();
		}
	    
	    public String addCategoryListToJson(String objectJson, String catList) {
	    	try {
	    	Gson json = new Gson();
	    	
	    	JsonElement element = json.fromJson(objectJson, JsonElement.class);
			JsonObject object = element.getAsJsonObject();
	    	
	    	JsonElement catListElement = json.fromJson(catList, JsonElement.class);
			object.add("categorylist", catListElement);
			
			return object.toString();
	    	} catch(Exception e) {
	    		log.error("Could not add category list", e);
	    		return objectJson;
	    	}
	    }
	    
	    public static String parseMagentoResponseObject(InputStream inStream) throws Exception{
	    	log.debug("parseMagentoResponseObject Start");
	    	String response = IOUtils.toString(inStream, StandardCharsets.UTF_8.name());
	    	Gson json = new Gson();
	    	
	    	JsonElement element = json.fromJson(response, JsonElement.class);
	    	String msg = null;
	    	if (element.isJsonObject()) {
	    		JsonObject object = element.getAsJsonObject();
	    		 msg = (object.get("message")!=null)?object.get("message").getAsString():null;
	    	}
	    	
	    	if(msg != null) {
	    		log.debug("parseMagentoResponseObject Error "+msg);
	    		throw new Exception(msg);
	    	}
	    	log.debug("parseMagentoResponseObject End ");
	    	return response;
	    }
}
