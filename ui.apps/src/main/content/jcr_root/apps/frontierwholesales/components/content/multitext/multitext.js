use(function () {

    var multiContent = {};   
   
    multiContent.title = properties.get('./jcr:title');
    if (multiContent.title) {
        multiContent.id = properties.get('./jcr:title').trim().replace(" ", "-").toLowerCase();
    	multiContent.id = multiContent.id.replace("(","");
        multiContent.id = multiContent.id.replace(")","");
    }


    // The HTML element name

    multiContent.text=properties.get('./text');

    
   
    return multiContent;

});

