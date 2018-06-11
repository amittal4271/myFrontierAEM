package com.frontierwholesales.core.beans;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MagentoCategory {

	 public final long id;
	    public final long parent_id;
	    public final String name;
	    public final boolean is_active;
	    public final long position;
	    public final long level;
	    public final long product_count;
	    public final MagentoCategory children_data[];
	   // public String path;

	    @JsonCreator
	    public MagentoCategory(@JsonProperty("id") long id, @JsonProperty("parent_id") long parent_id, @JsonProperty("name") String name,
	                           @JsonProperty("is_active") boolean is_active,
	                           @JsonProperty("position") long position,
	                           @JsonProperty("level") long level,
	                           @JsonProperty("product_count") long product_count,
	                           @JsonProperty("children_data") MagentoCategory[] children_data){
	        this.id = id;
	        this.parent_id = parent_id;
	        this.name = name;
	        this.is_active = is_active;
	        this.position = position;
	        this.level = level;
	        this.product_count = product_count;
	        this.children_data = children_data;
	    }

	   }
