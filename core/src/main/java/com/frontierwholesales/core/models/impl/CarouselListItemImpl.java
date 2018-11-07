package com.frontierwholesales.core.models.impl;

import java.util.Calendar;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Nonnull;

import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceUtil;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.jcr.resource.api.JcrResourceConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.cq.wcm.core.components.internal.models.v2.PageImpl;
import com.adobe.cq.wcm.core.components.models.ListItem;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import com.day.cq.wcm.foundation.Image;
import com.frontierwholesales.core.models.Carousel;
import com.frontierwholesales.core.services.constants.FrontierWholesalesConstants;

public class CarouselListItemImpl implements ListItem {
    private static final Logger LOGGER = LoggerFactory.getLogger(CarouselListItemImpl.class);

    protected SlingHttpServletRequest request;
    protected Page page;
    protected String imagePath;
    protected String imageLink;
    protected boolean isValidTemplate;
    protected boolean isValidImageNode = true;
    protected boolean isValidImageRef = true;

    public CarouselListItemImpl( @Nonnull SlingHttpServletRequest request, @Nonnull Page page ) {
        this.request = request;
        this.page = page;
        Page redirectTarget = getRedirectTarget(page);
        if( redirectTarget != null && !redirectTarget.equals(page) ) {
            this.page = redirectTarget;
        }
        this.imagePath = determineImagePath(page);
    }
    
    private String determineImagePath( @Nonnull Page page ) {
    	if( this.page == null ) {
    		this.page = page;
    	}
    	if( this.page != null ) {
    		ValueMap pageProps = this.page.getProperties();
    		String pageResType = pageProps.get(JcrResourceConstants.SLING_RESOURCE_TYPE_PROPERTY, "");
    		final Pattern p = Pattern.compile(Carousel.PN_ONE_COLUMN_PAGE_RESOURCE_TYPE);
        	Matcher m = p.matcher(pageResType);
        	this.isValidTemplate = m.find();
    		if( this.isValidTemplate ) {
	    		Resource imageResource = this.page.getContentResource(Carousel.PN_HERO_IMAGE_NODE_PATH);
	    		LOGGER.debug("Image Resource with template: {}, using path to image node: {}", imageResource, Carousel.PN_HERO_IMAGE_NODE_PATH);
	    		if( imageResource == null ) {
	    			imageResource = this.page.getContentResource(Carousel.PN_HERO_IMAGE_OTHER_NODE_PATH);
	    			LOGGER.debug("Image Resource with template: {}, using path to image node: {}", imageResource, Carousel.PN_HERO_IMAGE_OTHER_NODE_PATH);
	    		}
	    		this.isValidImageNode = (imageResource != null);
	    		if( this.isValidImageNode ) {
	    		
	    			ValueMap imageMap =  ResourceUtil.getValueMap(imageResource) ;
	    			String imageSrc = imageMap.get(Image.PN_FILE_NAME, String.class);
	    			
	    			if( StringUtils.isBlank(imageSrc) ) {
	    				ValueMap childImageMap =  imageResource.getChild(Carousel.PN_IMAGE_NODE) != null ? ResourceUtil.getValueMap(imageResource.getChild(Carousel.PN_IMAGE_NODE)) : ValueMap.EMPTY;
	    				imageSrc = childImageMap.get(Image.PN_FILE_NAME, String.class);
	    			}
	    			this.isValidImageRef = imageSrc != null;
	    			imageSrc = imageResource.getPath() +".img.png/"+imageSrc;
	    			
	    			imageLink = imageMap.get("linkURL", String.class);
	    			
	    			if(imageLink != null && imageLink.startsWith("/") && !imageLink.contains(FrontierWholesalesConstants.CONSTANT_EXTENSION_HTML)) {
	    				imageLink += FrontierWholesalesConstants.CONSTANT_EXTENSION_HTML;
	    			} 
	    			
	    			return imageSrc;	// is there default value if no image file found?
	    		}
    		}
    	}
    	return null;
    }
    
    public String getImagePath() {
    	return this.imagePath;
    }
    
    public String getImageLink() {
    	return this.imageLink;
    }
    
    public boolean isValidTemplate() {
    	return this.isValidTemplate;
    }
    
    public boolean isValidImageNode() {
    	return this.isValidImageNode;
    }
    
    public boolean isValidImageRef() {
    	return this.isValidImageRef;
    }

    @Override
    public String getURL() {
    	String vanityURL = page.getVanityUrl();
    	
    	if(StringUtils.isEmpty(vanityURL) ) {
    		if(!StringUtils.isEmpty(imageLink)){
    			return imageLink;
    		} else {
    			return request.getContextPath() + page.getPath() + FrontierWholesalesConstants.CONSTANT_EXTENSION_HTML;
    		}
    		
    	} else {
    		return request.getContextPath() + vanityURL;
    	}
    }

    @Override
    public String getTitle() {
        String title = page.getNavigationTitle();
        if( title == null ) {
            title = page.getPageTitle();
        }
        if( title == null ) {
            title = page.getTitle();
        }
        if( title == null ) {
            title = page.getName();
        }
        return title;
    }

    @Override
    public String getDescription() {
        return page.getDescription();
    }

    @Override
    public Calendar getLastModified() {
        return page.getLastModified();
    }

    @Override
    public String getPath() {
        return page.getPath();
    }

    private Page getRedirectTarget( @Nonnull Page page ) {
        Page result = page;
        String redirectTarget;
        PageManager pageManager = page.getPageManager();
        Set<String> redirectCandidates = new LinkedHashSet<>();
        redirectCandidates.add(page.getPath());
        while( result != null && StringUtils.isNotEmpty((redirectTarget = result.getProperties().get(PageImpl.PN_REDIRECT_TARGET, String.class))) ) {
            result = pageManager.getPage(redirectTarget);
            if( result != null && !redirectCandidates.add(result.getPath()) ) {
                
                    LOGGER.warn("Detected redirect loop for the following pages: {}.", redirectCandidates.toString());
                    break;
                
            }
        }
        return result;
    }

}
