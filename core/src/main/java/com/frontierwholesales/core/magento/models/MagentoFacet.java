package com.frontierwholesales.core.magento.models;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public final class MagentoFacet {
	public final String position;
	public final String attribute_code;
	public final String frontend_input;
	private final List<MagentoFacetOption> options;
	public final String default_frontend_label;
	public final String backend_type;
	
	@JsonCreator
    public MagentoFacet( 	@JsonProperty("position") String position, 
							@JsonProperty("attribute_code") String attribute_code,
							@JsonProperty("frontend_input") String frontend_input,
	    					@JsonProperty("options") List<MagentoFacetOption> options,
							@JsonProperty("default_frontend_label") String default_frontend_label,
							@JsonProperty("backend_type") String backend_type
						) {
        this.position = position;
        this.attribute_code = attribute_code;
        this.frontend_input = frontend_input;
        this.options = options;
        this.default_frontend_label = default_frontend_label;
        this.backend_type = backend_type;
    }

	public String getPosition() {
		return position;
	}

	public String getAttribute_code() {
		return attribute_code;
	}

	public String getFrontend_input() {
		return frontend_input;
	}

	public String getDefault_frontend_label() {
		return default_frontend_label;
	}

	public String getBackend_type() {
		return backend_type;
	}
	
	public List<MagentoFacetOption> getOptions() {
		
		Collections.sort(options, new Comparator<MagentoFacetOption>() {
            @Override
            public int compare(MagentoFacetOption offer1, MagentoFacetOption offer2)
            {
                String label1 = (offer1.getLabel() != null) ? offer1.getLabel() : "";
                String label2 = (offer2.getLabel() != null) ? offer2.getLabel() : "";
                
                label1 = label1.toLowerCase();
                label2 = label2.toLowerCase();
                
                return  label1.compareTo(label2);
            }
        });
		
		return options;
	}
	
}