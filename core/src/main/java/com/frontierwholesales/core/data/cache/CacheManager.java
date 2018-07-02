package com.frontierwholesales.core.data.cache;

public class CacheManager {

	private static java.util.HashMap<String,Object> cacheHashMap = new java.util.HashMap<String,Object>();
	 
	   public static void putCache(Object object, String identifier)
	   {
	     cacheHashMap.put(identifier, object);
	   }
	 
	   public static Object getCache(String identifier)
	   {
	   Object object = cacheHashMap.get(identifier);   
	       return object;   
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
