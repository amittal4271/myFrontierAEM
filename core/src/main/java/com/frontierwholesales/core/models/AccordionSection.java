package com.frontierwholesales.core.models;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Via;
import org.apache.sling.models.annotations.injectorspecific.Self;

import com.google.gson.Gson;

@Model(adaptables = SlingHttpServletRequest.class)
public class AccordionSection extends BaseComponent {

	@Inject
	@Via("resource")
	@Default(values = "")
	private String[] accordionItems;
	@Inject
	@Via("resource")
	@Default(values = "")
	private String[] southaccordionItems;
	@Inject
	@Via("resource")
	@Default(values = "")
	private String[] northaccordionItems;
	@Inject
	@Via("resource")
	@Default(values = "")
	private String[] residentialaccordionItems;
	@Inject
	@Via("resource")
	@Default(values = "")
	private String[] commercialaccordionItems;
	
    @Inject @Self private SlingHttpServletRequest request;

		
    private List<AccordionItem> listItems;
    private List<AccordionItem> southListItems;
    private List<AccordionItem> northListItems;
    private List<AccordionItem> residentialListItems;
    private List<AccordionItem> commercialListItems;
    private String accordionName;
    private long l;
    
    @Override public void init() {
    	LOG.info("AccordionSection Component Model init Begin");
    	//LOG.info(request.getResource().getName());
    	if(request!=null && request.getResource()!=null)
    	{
    		accordionName = request.getResource().getName();
    	}
        try {
        	listItems = new ArrayList<AccordionItem>(); 
        	southListItems =  new ArrayList<AccordionItem>();
        	northListItems = new ArrayList<AccordionItem>();
        	residentialListItems = new ArrayList<AccordionItem>();
        	commercialListItems = new ArrayList<AccordionItem>();
        	
        	if(isNotEmptyAccordionItems(accordionItems)){
        		buildAccordionItems(listItems,accordionItems);
        	}
        	if(isNotEmptyAccordionItems(southaccordionItems)){
        		buildAccordionItems(southListItems,southaccordionItems);
        	}
        	if(isNotEmptyAccordionItems(northaccordionItems)){
        		buildAccordionItems(northListItems,northaccordionItems);
        	}
        	if(isNotEmptyAccordionItems(residentialaccordionItems)){
        		buildAccordionItems(residentialListItems, residentialaccordionItems);
        	}
        	if(isNotEmptyAccordionItems(commercialaccordionItems)){
        		buildAccordionItems(commercialListItems, commercialaccordionItems);
        	}
            l= listItems.stream().distinct().count();
        	LOG.info("Number of accordions" + l);

        } catch (Exception e) {
			LOG.info("AccordionItem parsys exception: " + e.getMessage());
		}
        LOG.info("AccordionSection Component Model init End");
    }

    private boolean isNotEmptyAccordionItems(String[] pAccordionItems) {
		return pAccordionItems !=null && !pAccordionItems[0].equals("") && ArrayUtils.isNotEmpty(pAccordionItems);
	}
    
	private void buildAccordionItems(List<AccordionItem> pListItems, String[] pAccordionItems) {
		for(int i=0; i<pAccordionItems.length; i++){
			String accordionItem = pAccordionItems[i];
			Gson gson = new Gson();	            
			AccordionItem item = gson.fromJson(accordionItem, AccordionItem.class);
			pListItems.add(item);
            LOG.info("This accordion: " + item.getSectionTitle() +" is: " +  item.getSelected());
            LOG.info("Accordion's Link title: " + item.getLinkTitle());
            LOG.info("Is this Accordion's selected as default? " + item.getDefaultSelection());
            LOG.info("Accordion's Anchor title: " + item.getAnchorTitle());
		}
	}

	public List<AccordionItem> getListItems() {
        return listItems;
    }

    public void setListItems(List<AccordionItem> listItems) {
        this.listItems = listItems;
    }

	public List<AccordionItem> getSouthListItems() {
		return southListItems;
	}

	public void setSouthListItems(List<AccordionItem> southListItems) {
		this.southListItems = southListItems;
	}

	public List<AccordionItem> getNorthListItems() {
		return northListItems;
	}

	public void setNorthListItems(List<AccordionItem> northListItems) {
		this.northListItems = northListItems;
	}

	public List<AccordionItem> getResidentialListItems() {
		return residentialListItems;
	}

	public void setResidentialListItems(List<AccordionItem> residentialListItems) {
		this.residentialListItems = residentialListItems;
	}

	public List<AccordionItem> getCommercialListItems() {
		return commercialListItems;
	}

	public void setCommercialListItems(List<AccordionItem> commercialListItems) {
		this.commercialListItems = commercialListItems;
	}

	public String getAccordionName() {
		return accordionName;
	}

	public void setAccordionName(String accordionName) {
		this.accordionName = accordionName;
	}
    
}