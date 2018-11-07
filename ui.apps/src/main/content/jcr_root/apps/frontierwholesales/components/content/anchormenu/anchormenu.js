
use( function () {

    var linksList = [];
    var linkArray = [];
    linkArray = properties.get("anchormenu");  
    if (linkArray != null){
        for(var i = 0; i < linkArray.length; i++) {
            var singleObj = {};
            var itemObject =  JSON.parse(linkArray[i]);
            singleObj['title'] = itemObject.anchorTitle;
            singleObj['path'] = itemObject.anchorId;
			linksList.push(singleObj);
    	}
    } 

   return {
       linksList: linksList
   }
});