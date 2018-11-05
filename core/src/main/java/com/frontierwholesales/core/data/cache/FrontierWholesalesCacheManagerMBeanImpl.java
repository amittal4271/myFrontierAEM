package com.frontierwholesales.core.data.cache;

import javax.management.DynamicMBean;
import javax.management.NotCompliantMBeanException;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Service;

import com.adobe.granite.jmx.annotation.AnnotatedStandardMBean;


@Component(immediate = true)
@Property(name = "jmx.objectname", value="com.frontierwholesales.core.data.cache:type=FrontierWholesalesCacheManagerMBean")
@Service(value = DynamicMBean.class)
public class FrontierWholesalesCacheManagerMBeanImpl extends AnnotatedStandardMBean implements FrontierWholesalesCacheManagerMBean {
	 

	public FrontierWholesalesCacheManagerMBeanImpl() throws NotCompliantMBeanException {
		super(FrontierWholesalesCacheManagerMBean.class);
		
	}

	@Override
	public String removeCacheWithKey(String Cachekey) {
		
		return CacheManager.removeCacheWithKey(Cachekey);
	}

	@Override
	public String removeCacheAll() {
		
		return CacheManager.removeCacheAll();
	}

	

}
