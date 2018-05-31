package com.frontierwholesales.core.models.myaccount;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Via;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.frontierwholesales.core.beans.TouchNestedMultiFieldBean;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;


@Model (adaptables = SlingHttpServletRequest.class, defaultInjectionStrategy=DefaultInjectionStrategy.OPTIONAL)
public class AuthorableNavigation {

	private Logger log = LoggerFactory.getLogger(AuthorableNavigation.class);
	
	@SlingObject
	SlingHttpServletResponse response;

	@Inject
	SlingHttpServletRequest request;	

	@Inject
	@Via("resource")
	public String[] authorableNavigationItems;
	
	@Inject
	@Via("resource")
	public String navRoot;
	
	private List<TouchNestedMultiFieldBean> navMenuItems = new ArrayList<>();

	/**
	 * Method to get Multi field data
	 * 
	 * @return submenuItems
	 */
	private List<TouchNestedMultiFieldBean> setNestedMultiFieldItems() {

		JsonObject jObj;
		JsonArray jNestedArr;
		Gson gson = new Gson();
		try {
			String[] itemsProps = authorableNavigationItems;
			if (itemsProps != null) {
				for (int i = 0; i < itemsProps.length; i++) {
					JsonElement json = gson.fromJson(itemsProps[i], JsonElement.class);
					jObj = json.getAsJsonObject();
					TouchNestedMultiFieldBean menuItem = new TouchNestedMultiFieldBean();
					String title = jObj.get("title").getAsString();
					String path = jObj.get("link").getAsString();
					jNestedArr = jObj.getAsJsonArray("authorableNavigationSubItems");
					
					if (jNestedArr != null && !jNestedArr.isJsonNull() && jNestedArr.size() > 0) {
						for(JsonElement subItem : jNestedArr) {
							JsonObject jNestedObj = subItem.getAsJsonObject();
							TouchNestedMultiFieldBean subMenuItem = new TouchNestedMultiFieldBean();
							subMenuItem.setTitle(jNestedObj.get("title").getAsString());
							subMenuItem.setLink(jNestedObj.get("link").getAsString());
							subMenuItem.setLevel(1);
							menuItem.addSubMenuItem(subMenuItem);
						}
						
					}
					menuItem.setTitle(title);
					menuItem.setLink(path);
					menuItem.setLevel(0);
					navMenuItems.add(menuItem);
				}
			}
		} catch (Exception e) {
			log.error("Exception while Multifield data {}", e.getMessage(), e);
		}
		return navMenuItems;
	}
	

	public List<TouchNestedMultiFieldBean> getNavigationItems() {
		return navMenuItems;
	}
	
	@PostConstruct
	protected void activate() {
		setNestedMultiFieldItems();
	}

}
