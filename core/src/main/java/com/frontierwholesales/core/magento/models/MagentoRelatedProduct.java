package com.frontierwholesales.core.magento.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public final class MagentoRelatedProduct {
	public final String sku;
    public final String link_type;
    public final String linked_product_sku;
    public final String linked_product_type;
    public final long position;
    public final ExtensionAttributes extension_attributes;

    @JsonCreator
    public MagentoRelatedProduct( @JsonProperty("sku") String sku, 
    									@JsonProperty("link_type") String link_type, 
    									@JsonProperty("linked_product_sku") String linked_product_sku, 
    									@JsonProperty("linked_product_type") String linked_product_type, 
    									@JsonProperty("position") long position,
    									@JsonProperty("extension_attributes") ExtensionAttributes extension_attributes ) {
        this.sku = sku;
        this.link_type = link_type;
        this.linked_product_sku = linked_product_sku;
        this.linked_product_type = linked_product_type;
        this.position = position;
        this.extension_attributes = extension_attributes;
    }
    
    public String getSku() {
    	return sku;
    }
    
    public String getLinkType() {
    	return link_type;
    }
    
    public String getLinkedProductSku() {
    	return linked_product_sku;
    }
    
    public String getLinkedProductType() {
    	return linked_product_type;
    }
    
    public long getPosition() {
    	return position;
    }
    
    public ExtensionAttributes getExtensionAttributes() {
    	return extension_attributes;
    }
    
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static final class ExtensionAttributes {
        public final long qty;

        @JsonCreator
        public ExtensionAttributes( @JsonProperty("qty") long qty ) {
        	this.qty = qty;
        }
    }
}
