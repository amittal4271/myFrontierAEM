package com.frontierwholesales.core.models;

import java.util.List;
import java.util.Map;

import org.osgi.annotation.versioning.ConsumerType;

import com.frontierwholesales.core.beans.Link;

/**
 * Defines the {@code List} Sling Model used for the {@code /apps/frontierwholesales/components/content/shop-brand} component. 
 *
 * @since 
 */
@ConsumerType
public interface ShopBrand {
	/**
	 * Returns the heading text of the section (component).
	 * 
	 * @return the text for the section heading
	 */
	default String getHeading() {
		throw new UnsupportedOperationException();
	}
	
	/**
	 * Returns the path to the brand page whose image is to be displayed under the section heading.
	 * 
	 * @return returns the path to the brand page to retrieve the page image from the DAM
	 */
	default String getBrandPage1() {
		throw new UnsupportedOperationException();
	}
	
	/**
	 * Returns the path to the brand page whose image is to be displayed under the section heading.
	 * 
	 * @return returns the path to the brand page to retrieve the page image from the DAM
	 */
	default String getBrandPage2() {
		throw new UnsupportedOperationException();
	}
	
	/**
	 * Returns the path to the brand page whose image is to be displayed under the section heading.
	 * 
	 * @return returns the path to the brand page to retrieve the page image from the DAM
	 */
	default String getBrandPage3() {
		throw new UnsupportedOperationException();
	}
	
	/**
	 * Returns the object representing the brand page, including the path to the image to be displayed under the section heading.
	 * 
	 * @return returns the <code>Link</code> object representing the brand page
	 */
	default Link getBrandPageLink1() {
		throw new UnsupportedOperationException();
	}
	
	/**
	 * Returns the object representing the brand page, including the path to the image to be displayed under the section heading.
	 * 
	 * @return returns the <code>Link</code> object representing the brand page
	 */
	default Link getBrandPageLink2() {
		throw new UnsupportedOperationException();
	}
	
	/**
	 * Returns the object representing the brand page, including the path to the image to be displayed under the section heading.
	 * 
	 * @return returns the <code>Link</code> object representing the brand page
	 */
	default Link getBrandPageLink3() {
		throw new UnsupportedOperationException();
	}
	
	/**
	 * Returns the map that contains the list of brands.
	 * 
	 * @return
	 */
	default Map<String, List<Link>> getBrandsMap() {
		throw new UnsupportedOperationException();
	}
}
