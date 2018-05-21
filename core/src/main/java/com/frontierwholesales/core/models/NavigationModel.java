package com.frontierwholesales.core.models;

import com.adobe.cq.sightly.WCMUsePojo;
import com.day.cq.wcm.api.Page;
import com.frontierwholesales.core.beans.FrontierWholesalePages;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

	public class NavigationModel extends WCMUsePojo {
		private List<FrontierWholesalePages> frontierWholesalePages;
		private static final Logger LOGGER = LoggerFactory.getLogger(NavigationModel.class);

		public void activate() throws Exception {
			Page root = this.getCurrentPage().getAbsoluteParent(2);
			FrontierWholesalePages frontierRoot = new FrontierWholesalePages(root);
			this.frontierWholesalePages = this.getChildren(frontierRoot, 0);
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

		public List<FrontierWholesalePages> getFrontierWholesalePages() {
			return this.frontierWholesalePages;
		}
}

