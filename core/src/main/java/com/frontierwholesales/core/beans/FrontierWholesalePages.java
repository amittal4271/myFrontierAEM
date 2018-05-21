package com.frontierwholesales.core.beans;

import com.day.cq.wcm.api.Page;
import com.frontierwholesales.core.utils.LinkUtils;
import java.util.List;
import org.apache.sling.api.resource.ValueMap;

public class FrontierWholesalePages
{
  private String link;
  private String name;
  private String iconClass;
  private String contentPath;
  private List<FrontierWholesalePages> childList;
  private Page page;
  private String showInNav;
  private String hideInMobileNav;
  private String column;
  private List<List<FrontierWholesalePages>> columns;
  private String title;
  private String uniqueId;
  private String sideNav;
  
  public FrontierWholesalePages(Page page)
  {
    this.page = page;
    ValueMap properties = page.getProperties();
    String pagePath = "#";
    String uniquePath = "";
    int depth = page.getDepth();
    if (depth > 0)
    {
      pagePath = page.getPath();
      uniquePath = page.getPath();
      
      uniquePath = uniquePath.replace("/", ".");
      if (uniquePath.indexOf(" ") > 0) {
        uniquePath = uniquePath.replace(" ", "");
      }
      uniquePath = uniquePath.substring(1, uniquePath.length());
      setUniqueId(uniquePath);
    }
    setTitle(page.getTitle());
    setLink(LinkUtils.setupUrl(pagePath));
    setName(page.getName().replace(" ", ""));
    setIconClass((String)properties.get("iconClass", String.class));
    setContentPath(page.getPath() + "/_jcr_content/main");
    setShowInNav((String)properties.get("showNav", String.class));
    setSideNav((String)properties.get("sideNav",String.class));
    setColumn((String)properties.get("column", String.class));
  }
  
  public Page getPage()
  {
    return this.page;
  }
  
  public String getLink()
  {
    return this.link;
  }
  
  public String getName()
  {
    return this.name;
  }
  
  public String getIconClass()
  {
    return this.iconClass;
  }
  
  public List<FrontierWholesalePages> getChildList()
  {
    return this.childList;
  }
  
  public String getContentPath()
  {
    return this.contentPath;
  }
  
  public String getShowInNav()
  {
    return this.showInNav;
  }
  
  public String getHideInMobileNav()
  {
    return this.hideInMobileNav;
  }
  
  public String getColumn()
  {
    return this.column;
  }
  
  public List<List<FrontierWholesalePages>> getColumns()
  {
    return this.columns;
  }
  
  public void setPage(Page page)
  {
    this.page = page;
  }
  
  public void setLink(String link)
  {
    this.link = link;
  }
  
  public void setName(String name)
  {
    this.name = name;
  }
  
  public void setIconClass(String iconClass)
  {
    this.iconClass = iconClass;
  }
  
  public void setChildList(List<FrontierWholesalePages> childList)
  {
    this.childList = childList;
  }
  
  public void setContentPath(String contentPath)
  {
    this.contentPath = contentPath;
  }
  
  public void setShowInNav(String showInNav)
  {
    this.showInNav = showInNav;
  }
  
  public String getTitle()
  {
    return this.title;
  }
  
  public void setTitle(String title)
  {
    this.title = title;
  }
  
  public String getUniqueId()
  {
    return this.uniqueId;
  }
  
  public void setUniqueId(String uniqueId)
  {
    this.uniqueId = uniqueId;
  }
  
  public void setHideInMobileNav(String hideInMobileNav)
  {
    this.hideInMobileNav = hideInMobileNav;
  }
  
  public void setColumn(String column)
  {
    this.column = column;
  }
  
  public void setColumns(List<List<FrontierWholesalePages>> columns)
  {
    this.columns = columns;
  }
  
  public String getSideNav() {
		return sideNav;
	}

	public void setSideNav(String sideNav) {
		this.sideNav = sideNav;
	}
}
