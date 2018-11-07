package com.frontierwholesales.core.data.cache;

public class CacheManager {

	private static java.util.HashMap<String,Object> cacheHashMap = new java.util.HashMap<>();
	private CacheManager() {
		
	}
	 
	   public static void putCache(Object object, String identifier)
	   {
	     cacheHashMap.put(identifier, object);
	   }
	 
	   public static Object getCache(String identifier)
	   {
	    return cacheHashMap.get(identifier);   
	          
	   }
	 
	    public static String removeCacheWithKey(String identifier)
	   {
	     if(!cacheHashMap.containsKey(identifier))
	     {
	      return "Invalid Key..";
	     }
	 cacheHashMap.remove(identifier);   
	 return "Removed cache with key "+identifier;
	   
	   }
	 
	    public static String removeCacheAll()
	   {
	     cacheHashMap.clear();
	     return "Removed cache";
	   
	   }
}
