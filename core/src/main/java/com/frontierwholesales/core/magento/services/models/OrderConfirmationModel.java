package com.frontierwholesales.core.magento.services.models;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

@Model(adaptables= {SlingHttpServletRequest.class,Resource.class})
public class OrderConfirmationModel extends BaseModel{

	@Override
	protected void init() {
		// TODO Auto-generated method stub
		try {
			String adminToken = getAdminToken();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
