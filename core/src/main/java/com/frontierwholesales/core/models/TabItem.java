package com.frontierwholesales.core.models;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TabItem {
	private static final Logger LOG = LoggerFactory.getLogger(TabItem.class);

    private String sectionTitle;
    private String linkTitle;
    private String anchorTitle;
    private Boolean defaultSelected;
    private String selected = "";
    private String layout;

    public TabItem(){
    	
    }
    
    public TabItem(String sectionTitle, String linkTitle, String anchorTitle,  Boolean defaultSelected, String selected, String layout) {
        super();
        LOG.info("Begin TabItem init");
        this.sectionTitle = sectionTitle;
        this.linkTitle = linkTitle;
        this.anchorTitle = anchorTitle;
        this.defaultSelected = defaultSelected;
        this.selected = selected;
        LOG.info("End TabItem init");
    }

	public String getSectionTitle() {
		return sectionTitle;
	}

	public void setSectionTitle(String sectionTitle) {
		this.sectionTitle = sectionTitle;
	}
    
    public String getLinkTitle() {
		return linkTitle;
	}

	public void setLinkTitle(String linkTitle) {
		this.linkTitle = linkTitle;
	}
    
    public String getLayout() {
		return layout;
	}

	public void setLayout(String layout) {
		this.layout = layout;
	}

        public String getAnchorTitle(){
        return anchorTitle;
    }
	
    public void setAnchorTitle(String anchorTitle){
        this.anchorTitle = anchorTitle;
    }
    
    public Boolean getDefaultSelection(){
        if(defaultSelected){
            this.selected = "checked";
            LOG.info("this item is selected for default.");
            return defaultSelected;
        }
        return defaultSelected;
    }
    
    public void setDefaultSelection(Boolean defaultSelected){
        this.defaultSelected = defaultSelected;
    }
    public String getSelected(){
        return selected;
    }
    public void setSelected(String selected){
        this.selected = selected;
    }
	
}
