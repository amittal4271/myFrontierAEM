use(function () {
    var data={};
    var skuParam = this.param1;
    var skuSplit = skuParam.split('-');
   
   var newSession = Packages.javax.jcr.Session;
    var session = resource.getResourceResolver().adaptTo(newSession);
    var queryManager =  session.getWorkspace().getQueryManager();
var sqlStatement='SELECT * FROM [nt:unstructured] AS node WHERE ISDESCENDANTNODE(node, "/etc/commerce/products/we-retail") AND CONTAINS([jcr:title],"'+skuSplit[0]+'")';
    var query = queryManager.createQuery(sqlStatement,"JCR-SQL2");
    var result = query.execute();
    var nodeIter = result.getNodes();
    var path='';
     while ( nodeIter.hasNext() ) {
	    	var node = nodeIter.nextNode();
	    	
         if(skuSplit.length > 1){
	    	if(node.getName().equals(skuSplit[2])) {
	    		
	           path = node.getPath();
	    	}
         }else{
             path = node.getPath();
         }
	    }
   
    path = path +"/image";
    var imgProp = resource.getResourceResolver().getResource(path);
    data['path'] = imgProp.properties['fileReference'];
   

    var numberFormat = Packages.java.text.DecimalFormat("#0.00");
    data['unitPrice']=numberFormat.format(this.param2);
    data['rowPrice']=numberFormat.format(this.param3);
    data['totalPrice']=numberFormat.format(this.param4);

    return data;

});