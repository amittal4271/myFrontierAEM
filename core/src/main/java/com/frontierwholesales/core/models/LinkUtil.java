package com.frontierwholesales.core.models;

import com.frontierwholesales.core.services.constants.FrontierWholesalesConstants;

public class LinkUtil {

	

	private LinkUtil() {
		// prevent instantiation
	}

	/**
	 * getPathfieldURL
	 *
	 * Takes the value from pathfield and performs logic s.t. it will always be
	 * a valid URL.
	 *
	 * @param path
	 *            - Input value from pathfield
	 * @return pageURL - the modified path
	 */
	public static String getPathfieldURL(String path) {
		StringBuilder pageURL = new StringBuilder(path);
		if (pageURL.toString().startsWith("/content") ) {
			final int index = pageURL.lastIndexOf(".");
			if(index < 0) {
				pageURL.append(FrontierWholesalesConstants.CONSTANT_EXTENSION_HTML);
			}
		} else if (!"".equalsIgnoreCase(pageURL.toString())
				&& !(pageURL.toString().startsWith("http://") || pageURL.toString().startsWith("https://"))) {

			if (pageURL.toString().startsWith("//")) {
				pageURL.replace(0, 2, "");
			} else if (pageURL.toString().startsWith("/")) {
				pageURL.insert(0, "http:/");
			} else if (!"#".equals(pageURL.toString())) {
				pageURL.insert(0, "http://");
			}
		}
		return pageURL.toString();
	}

}