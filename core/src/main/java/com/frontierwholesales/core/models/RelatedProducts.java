package com.frontierwholesales.core.models;

import java.util.Collection;

import javax.annotation.Nonnull;

import org.osgi.annotation.versioning.ConsumerType;

import com.frontierwholesales.core.beans.FrontierWholesalesProducts;

/**
 * Defines the {@code List} Sling Model used for the {@code /apps/frontierwholesales/components/structure/products/related-products} component. 
 *
 * @since 
 */
@ConsumerType
public interface RelatedProducts {

	/**
     * Returns the list's items collection, as {@link FrontierWholesalesProducts}s elements.
     *
     * @return {@link Collection} of {@link FrontierWholesalesProducts}s
     * @since com.adobe.cq.wcm.core.components.models 12.2.0
     */
    @Nonnull
    default Collection<FrontierWholesalesProducts> getRelatedProductsList() {
        throw new UnsupportedOperationException();
    }
    
	/**
	 * Returns the heading text of the section (component).
	 * 
	 * @return the text for the section heading
	 */
	default String getHeading() {
		throw new UnsupportedOperationException();
	}
	
	/**
	 * Returns the maximum number of products to display before the carousel/slideshow takes effect.
	 * 
	 * @return returns the maximum number of products to display before turning into a carousel or slideshow
	 */
	default int getMaxItems() {
		throw new UnsupportedOperationException();
	}
	
	/**
	 * Returns the default text displayed if there are no related products.
	 * 
	 * @return the text to display if there are no related products
	 */
	default String getDefaultText() {
		throw new UnsupportedOperationException();
	}
}
