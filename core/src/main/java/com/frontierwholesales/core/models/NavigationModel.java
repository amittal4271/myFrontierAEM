package com.frontierwholesales.core.models;

import com.adobe.cq.sightly.WCMUsePojo;
import com.day.cq.wcm.api.Page;
import com.frontierwholesales.core.beans.FrontierWholesalePages;
import com.frontierwholesales.core.beans.MagentoCategory;
import com.frontierwholesales.core.magento.services.FrontierWholesalesMagentoCommerceConnector;
import com.frontierwholesales.core.services.constants.FrontierWholesalesConstants;
import com.frontierwholesales.core.utils.FrontierWholesalesUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.Cookie;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

	public class NavigationModel extends WCMUsePojo {
		private List<FrontierWholesalePages> frontierWholesalePages;
		private static final Logger LOGGER = LoggerFactory.getLogger(NavigationModel.class);
		private FrontierWholesalesMagentoCommerceConnector connector = new FrontierWholesalesMagentoCommerceConnector();
		
		private MagentoCategory categories;
		public void activate() throws Exception {
			LOGGER.debug("activate method of navigation Start");
			Page root = this.getCurrentPage().getAbsoluteParent(2);
			FrontierWholesalePages frontierRoot = new FrontierWholesalePages(root);
			this.frontierWholesalePages = this.getChildren(frontierRoot, 0);
			
			String adminToken =  connector.getAdminToken();
			
			this.categories = getAllCategories(adminToken,2);
			
			LOGGER.debug("activate method of navigation End");
		}

		private List<FrontierWholesalePages> getChildren(FrontierWholesalePages parent, int level) {
			ArrayList<FrontierWholesalePages> pages = new ArrayList<FrontierWholesalePages>();
			Iterator<Page> children = parent.getPage().listChildren();

			while (children.hasNext()) {
				Page page = (Page) children.next();
				
				FrontierWholesalePages frontierWholesalePage = new FrontierWholesalePages(page);
				String showNav = frontierWholesalePage.getShowInNav();				
				if (showNav != null && showNav.equals("true")) {
					
					frontierWholesalePage.setChildList(this.getChildren(frontierWholesalePage, level + 1));
					pages.add(frontierWholesalePage);
				}
			}

			return pages;
		}
		
		private MagentoCategory getAllCategories(String adminToken,int categoryId) throws Exception{
			MagentoCategory categories = connector.getCategories(adminToken, categoryId);
			
			return categories;
		}

		public List<FrontierWholesalePages> getFrontierWholesalePages() {
			return this.frontierWholesalePages;
		}
		
		public MagentoCategory getCategories() {
			return categories;
		}
}

