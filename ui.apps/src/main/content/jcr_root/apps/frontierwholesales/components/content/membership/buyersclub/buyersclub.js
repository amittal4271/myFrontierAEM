use(function () {
  var param = this.params;
    var paramSplit = param.split('&');
    for(var i=0;i<paramSplit.length;i++){
    	var emailSplit = paramSplit[i].split('=');

        if(emailSplit[0].indexOf('email') ==0){ 

        	return { email:emailSplit[1]
               };
    	}
    }

    return "";
});