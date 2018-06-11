package com.frontierwholesales.core.magento.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public final class MagentoBrand {
	public final String label;
	public final String value;
	
	@JsonCreator
    public MagentoBrand( @JsonProperty("label") String label, 
    						@JsonProperty("value") String value ) {
        this.label = label;
        this.value = value;
    }
	
	public String getLabel() {
		return label;
	}
	
	public String getValue() {
		return value;
	}
}
