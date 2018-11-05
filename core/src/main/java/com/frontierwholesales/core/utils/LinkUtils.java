package com.frontierwholesales.core.utils;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LinkUtils
{
  private static final Logger LOGGER = LoggerFactory.getLogger(StringUtils.class);
  private static final Pattern PROTOCOL_REGEX = Pattern.compile("^(https?://)");
  private static final Pattern RELATIVE_PATH_REGEX = Pattern.compile("^/[A-Za-z0-9]*");
  private static final Pattern DOMAIN_REGEX = Pattern.compile("^[A-Za-z0-9]*\\.[A-Za-z0-9]*\\..*");
  private LinkUtils() {
	  
  }
  public static String setupUrl(String url)
  {
    LOGGER.debug("----------------------------------------------------------");
    LOGGER.debug("***** Raw url: {}", url);
    if (url == null) {
      url = "";
    }
    String adjustedUrl;
   
    if (noModificationNeed(url).booleanValue())
    {
      LOGGER.debug("***** url doesn't need modification");
      adjustedUrl = url;
    }
    else
    {
     
      if (isRelative(url).booleanValue())
      {
        LOGGER.debug("***** url is relative with no extension, setting to .html");
        adjustedUrl = url + ".html";
      }
      else
      {
       
        if (isExternal(url).booleanValue())
        {
          LOGGER.debug("***** url is external with no protocol, setting to relative protocol //");
          adjustedUrl = "//" + url;
        }
        else
        {
          LOGGER.debug("***** url didn't fit into handled cases may be an error");
          adjustedUrl = url;
        }
      }
    }
    LOGGER.debug("***** Adjusted url: {}", adjustedUrl);
    LOGGER.debug("");
    return adjustedUrl;
  }
  
  private static Boolean noModificationNeed(String url)
  {
    Boolean noModificationNeeded = Boolean.valueOf(isAbsolute(url));
    noModificationNeeded = Boolean.valueOf((noModificationNeeded.booleanValue()) || (StringUtils.startsWith(url, "#")));
    noModificationNeeded = Boolean.valueOf((noModificationNeeded.booleanValue()) || (StringUtils.isBlank(url)));
    noModificationNeeded = Boolean.valueOf((noModificationNeeded.booleanValue()) || (StringUtils.startsWithIgnoreCase(url, "mailto:")));
    noModificationNeeded = Boolean.valueOf((noModificationNeeded.booleanValue()) || (PROTOCOL_REGEX.matcher(url).find()));
    noModificationNeeded = Boolean.valueOf((noModificationNeeded.booleanValue()) || (StringUtils.startsWith(url, "//")));
    noModificationNeeded = Boolean.valueOf((noModificationNeeded.booleanValue()) || (relativePathNoModificationNeeded(url).booleanValue()));
    
    return noModificationNeeded;
  }
  
  private static boolean isAbsolute(String url)
  {
    try
    {
      URL aUri = new URL(url);
      
      return aUri.toURI().isAbsolute();
    }
    catch (URISyntaxException urlEx)
    {
      LOGGER.warn(urlEx.getMessage());
    }
    catch (MalformedURLException ex)
    {
      LOGGER.warn(ex.getMessage());
    }
    return false;
  }
  
  private static Boolean isRelative(String url)
  {
    return Boolean.valueOf(RELATIVE_PATH_REGEX.matcher(url).find());
  }
  
  private static Boolean relativePathNoModificationNeeded(String url)
  {
    return Boolean.valueOf((isRelative(url).booleanValue()) && (StringUtils.isNotBlank(FilenameUtils.getExtension(url))));
  }
  
  private static Boolean isExternal(String url)
  {
    return Boolean.valueOf(DOMAIN_REGEX.matcher(url).find());
  }
}

