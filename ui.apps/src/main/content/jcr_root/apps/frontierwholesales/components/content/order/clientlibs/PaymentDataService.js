app.service('PaymentDataService', function($q,$http,$window,FactoryJsonData) {
console.log("PaymentDataService ");
    this.getShippingInfo = function(){
        var deferred = $q.defer();
		showLoadingScreen();
         var userToken = getUserToken();
        	$http({
                 url: $window.serverURL+"/rest/V1/carts/mine/shipping-information",
                 method: "post",
                data: FactoryJsonData.shippingInfoData,
                headers:{
					"content-Type":"application/json",

                    "Access-Control-Allow-Origin":$window.serverURL,
            		"Access-Control-Allow-Credentials":"true",

                    'Authorization':userToken
                }
            }).then(function successCallback(response) {
                console.log(response.data); 

				hideLoadingScreen();

                deferred.resolve(response.data);


          }, function errorCallback(response) {
            console.log('error'+response);
              hideLoadingScreen();
          });
        return deferred.promise;
     }

});