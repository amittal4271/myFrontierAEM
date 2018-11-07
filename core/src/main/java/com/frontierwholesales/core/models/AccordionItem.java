package com.frontierwholesales.core.models;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AccordionItem {
    private static final Logger LOG = LoggerFactory.getLogger(AccordionItem.class);

    private String sectionTitle;
    private String linkTitle;
    private String anchorTitle;
    private Boolean defaultSelected;
    private String selected = "";
    private String layout;
    private long l;

    public AccordionItem(String sectionTitle, String linkTitle, String anchorTitle,  Boolean defaultSelected, String selected, long l) {
        super();
        LOG.info("Begin AccordionItem init");
        this.sectionTitle = sectionTitle;
        this.linkTitle = linkTitle;
        this.anchorTitle = anchorTitle;
        this.defaultSelected = defaultSelected;
        this.selected = selected;
        this.l = l;
        LOG.info("End AccordionItem init");
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
    public long getL(){
    	return l;
    }
    public void setL(long l){
    	this.l = l;
    }
    
}
