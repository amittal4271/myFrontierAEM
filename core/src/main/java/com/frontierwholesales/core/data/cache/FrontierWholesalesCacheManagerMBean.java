package com.frontierwholesales.core.data.cache;

import com.adobe.granite.jmx.annotation.Description;
import com.adobe.granite.jmx.annotation.Name;

@Description("FrontierWholesales Cache Manager JMX Bean")
public interface FrontierWholesalesCacheManagerMBean {

	 @Description("Remove the cache with cache id ")
	    String removeCacheWithKey(@Name("cacheKey") @Description("Valid cache key") String Cachekey);

	    @Description("Remove All Cache")
	    String removeCacheAll(); 
	  
}
