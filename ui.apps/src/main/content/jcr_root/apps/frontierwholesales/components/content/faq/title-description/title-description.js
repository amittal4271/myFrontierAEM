"use strict";

/**
 * Title foundation component JS backing script
 */
use(function () {

    // TODO: change currentStyle to wcm.currentStyle

    var CONST = {
        PROP_TITLE: "./text",
        PROP_PAGE_TITLE: "pageTitle",
        PROP_TYPE: "type",
        PROP_DEFAULT_TYPE: "defaultType"
    }

    var titletext = {};

    titletext.title = properties.get('./jcr:title');
    // The actual titletext content
    //titletext.text = granite.resource.properties[CONST.PROP_TITLE]
    titletext.text = properties.get(CONST.PROP_TITLE)
            || resourcePage.getProperties().get(CONST.PROP_TITLE)
            || resourcePage.getProperties().get(CONST.PROP_PAGE_TITLE)
            || granite.resource.properties["fieldDescription"]
            || "Insert text here";


            
    // The HTML element name
    titletext.element = granite.resource.properties[CONST.PROP_TYPE]
            || currentStyle.get(CONST.PROP_DEFAULT_TYPE, "");

    // Adding the constants to the exposed API
    titletext.CONST = CONST;

    return titletext;

});