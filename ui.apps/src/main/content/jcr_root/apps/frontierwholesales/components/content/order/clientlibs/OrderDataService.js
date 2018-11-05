app.service('OrderDataService', function($q,$http,$window) {

 this.getUserToken = function(){
		var cookieValue = document.cookie;
        var cookieSplit=cookieValue.split(";");
        var userToken='';
        for(var i=0;i<cookieSplit.length;i++){
            var token = cookieSplit[i].trim().split("=");
            if(token[0].startsWith("MagentoUserToken")){
                userToken = token[1];
            }
        }
        var regx=new RegExp("\"","g");
        userToken=userToken.replace(regx,"");
        return userToken;
    }

 

     this.getCustomer = function(){
	showLoadingScreen();
         var deferred = $q.defer();
        var userToken=getUserToken();

        	$http({
                 url: $window.serverURL+"/rest/V1/customers/me",
                 method: "get",
                headers:{
    
                    'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8',
                    'Authorization':userToken
                }
            }).then(function successCallback(response) {
                console.log(response.data); 
                var customerJsonData = response.data;
    
                console.log("json data is "+customerJsonData.id);
                 deferred.resolve(getCustomerOrders(customerJsonData.id));

          }, function errorCallback(response) {
            console.log('error'+response);
              hideLoadingScreen();
          });
		return deferred.promise;
    }

    this.getCustomerOrders = function(customerId){
		var deferred = $q.defer();
        getAdminToken().then(function(response){
			adminToken = response;

            var orders="/rest/V1/orders?searchCriteria[pageSize]=50"
            + "&searchCriteria[currentPage]=1&searchCriteria[filterGroups][0][filters][0][value]="+customerId
            + "&searchCriteria[filterGroups][0][filters][0][field]=customer_id";

                $http({
                    url: $window.serverURL+orders,
                    method: "get",
                    headers:{

                    'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8',
                    'Authorization':adminToken
                       }
                      }).then(function successCallback(response) {
                    hideLoadingScreen();
                    console.log(JSON.stringify(response.data)); 
                    deferred.resolve(response.data);


                }, function errorCallback(response) {
                    console.log('error'+response);
                    hideLoadingScreen();
                });

             });
		return deferred.promise;
     }
});