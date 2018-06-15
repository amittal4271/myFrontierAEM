package com.frontierwholesales.core.beans;

import java.util.ArrayList;

/**
 * POJO for Nested Multi Field items
 * 
 * @author aahlawat
 *
 */
public class TouchNestedMultiFieldBean {

	private String title;
	private String link;
	private int level;
	private ArrayList<TouchNestedMultiFieldBean> subMenuItems = new ArrayList<TouchNestedMultiFieldBean>();
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public ArrayList<TouchNestedMultiFieldBean> getSubMenuItems() {
		return subMenuItems;
	}

	public void setSubMenuItems(ArrayList<TouchNestedMultiFieldBean> subMenuItems) {
		this.subMenuItems = subMenuItems;
	}
	
	public void addSubMenuItem(TouchNestedMultiFieldBean subMenuItem) {
		this.subMenuItems.add(subMenuItem);
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
	
}