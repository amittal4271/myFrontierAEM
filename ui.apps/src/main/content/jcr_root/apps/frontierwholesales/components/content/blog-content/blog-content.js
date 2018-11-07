"use strict";

/**
 * Title foundation component JS backing script
 */
use(function () {

    var blog = {};

    blog.date= ((properties.get("./blogDate") !=null)?properties.get("./blogDate"):"Enter date");
   
    blog.title = ((properties.get("./blogTitle") != null)?properties.get("./blogTitle"):"Enter title");
    
    blog.description = ((properties.get("./blogDesc") != null)?properties.get("./blogDesc"):"Enter description");
   

    return blog;

});