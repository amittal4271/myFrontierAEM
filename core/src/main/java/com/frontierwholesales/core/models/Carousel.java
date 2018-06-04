package com.frontierwholesales.core.models;

import org.osgi.annotation.versioning.ConsumerType;

import com.adobe.cq.wcm.core.components.models.List;

@ConsumerType
public interface Carousel extends List {
	/**
     * Name of the resource property storing the path to the image node on the page.
     *
     * @see #PN_IMAGE_NODE_PATH
     * @since com.adobe.cq.wcm.core.components.models 11.0.0
     */
    String PN_HERO_IMAGE_OTHER_NODE_PATH = "hero_image";
    
    /**
     * Name of the resource property storing the path to the image node on the page.
     *
     * @see #PN_HERO_IMAGE_NODE_PATH
     * @since com.adobe.cq.wcm.core.components.models 11.0.0
     */
    String PN_HERO_IMAGE_NODE_PATH = "root/hero_image";
    
    /**
     * Name of the image node.
     * 
     * @see #PN_IMAGE_NODE
     */
    String PN_IMAGE_NODE = "image";
    
    /**
     * Resource type of the one column page template.
     * 
     * @see #PN_ONE_COLUMN_PAGE_RESOURCE_TYPE
     */
    String PN_ONE_COLUMN_PAGE_RESOURCE_TYPE = "^(weretail|frontierwholesales)/components/structure/page$";
}
