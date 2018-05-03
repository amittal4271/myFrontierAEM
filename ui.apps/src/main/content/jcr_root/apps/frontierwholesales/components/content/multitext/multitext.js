use(function () {

    // TODO: change currentStyle to wcm.currentStyle

    var CONST = {
        PROP_TITLE: "jcr:titletext",
        PROP_PAGE_TITLE: "pageTitle",
        PROP_TYPE: "type",
        PROP_DEFAULT_TYPE: "defaultType"
    }

    var multiContent = {};


   
   
    multiContent.title = properties.get('./title');

    // The HTML element name

    multiContent.text=properties.get('./text');

    
   
    return multiContent;

});

