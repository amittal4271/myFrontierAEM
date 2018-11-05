app.factory('FactoryJsonData', function(){
    return { email:'',data: '',shippingInfoData:'',paymentJsonData:'',confirmationJsonData:'',usRegions:'' };
});
app.service('ShippingDataService', function($q,$http,$window,FactoryJsonData) {
	this.email="";
    this.address={};


     this.getCustomer = function(){
    	var deferred = $q.defer();
         console.log("server URL "+$window.serverURL);
		showLoadingScreen();
		var userToken = getUserToken();
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
               var email = customerJsonData.email;
                FactoryJsonData.email = email;
                 deferred.resolve(getCustomerShippingAddress(customerJsonData.id));

          }, function errorCallback(response) {
            console.log('error'+response);
			hideLoadingScreen();
          });
		return deferred.promise;
    }

    this.getCustomerShippingAddress = function(customerId){
		var deferred = $q.defer();

        getAdminToken().then(function(response){
            var adminToken = response;
            $http({
                url: $window.serverURL+"/rest/V1/customers/"+customerId+"/shippingAddress",
                method: "get",
                headers:{
                
                'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8',
                'Authorization':adminToken
                   }
                  }).then(function successCallback(response) {

                console.log(JSON.stringify(response.data)); 
                var address = parseAddressObjAndAddEmail(response.data,email);
                deferred.resolve(cartBillingAddress(address));



            }, function errorCallback(response) {
                console.log('error'+response);
                hideLoadingScreen();

            });
        });
		return deferred.promise;
     }


    this.parseAddressObjAndAddEmail = function(response){
        var addressJson={};
        addressJson["region"]=response.region.region;
		addressJson["region_id"]= response.region.region_id;
		addressJson["region_code"]= response.region.region_code;
		addressJson["country_id"]= response.country_id;
		addressJson["street"]= response.street;
		addressJson["telephone"]=response.telephone;
		addressJson["postcode"]= response.postcode;
		addressJson["city"]= response.city;
		addressJson["firstname"]= response.firstname;
		addressJson["lastname"]=response.lastname;
		addressJson["email"]= email;
		FactoryJsonData.data = JSON.stringify(addressJson);
        var jsonData={};
		jsonData["address"]= addressJson;
		jsonData["useForShipping"]=true;
        console.log("jsonData "+JSON.stringify(jsonData));

        return JSON.stringify(jsonData);
    }


    this.cartBillingAddress = function(address){
        var deferred = $q.defer();
		var userToken = getUserToken();
        	$http({
                 url: $window.serverURL+"/rest/V1/carts/mine/billing-address",
                 method: "post",
                data:address,
                headers:{

           "content-Type":"application/json",

                    "Access-Control-Allow-Origin":$window.serverURL,
            "Access-Control-Allow-Credentials":"true",

                    'Authorization':userToken
                }
            }).then(function successCallback(response) {
                console.log(response.data); 
                var customerJsonData = response.data;

                console.log("json data is "+customerJsonData);
                 deferred.resolve(shippingMethods());

          }, function errorCallback(response) {
            console.log('error'+response);
              hideLoadingScreen();

          });
		return deferred.promise;
    }

    this.shippingMethods = function(){
        var deferred = $q.defer();
		var userToken = getUserToken();
        	$http({
                 url: $window.serverURL+"/rest/V1/carts/mine/shipping-methods",
                 method: "get",

                headers:{
    
                    'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8',
                    'Authorization':userToken
                }
            }).then(function successCallback(response) {
                console.log(response.data); 
                var customerJsonData = response.data;

                console.log("json data is "+customerJsonData);

                 deferred.resolve(getRegions(response.data));

          }, function errorCallback(response) {
            console.log('error'+response);
              hideLoadingScreen();

          });
		return deferred.promise;
    }

    this.getRegions = function(paymentMethods){
		var deferred = $q.defer();
		 getAdminToken().then(function(response){
             var adminToken = response;
        	$http({
                 url: $window.serverURL+"/rest/all/V1/directory/countries",
                 method: "get",

                headers:{
    
                    'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8',
                    'Authorization':adminToken
                }
            }).then(function successCallback(response) {
                console.log(response.data); 
                var regions = response.data;
                var usRegions={};
                angular.forEach(regions,function(value,key){
                    if(value.id == "US"){
						usRegions = value.available_regions;
                    }
                });
                console.log("json data is "+usRegions);
                FactoryJsonData.usRegions = usRegions;
                 deferred.resolve(cartTotal(usRegions,paymentMethods));

          }, function errorCallback(response) {
            console.log('error'+response);
              hideLoadingScreen();

          });
         });
		return deferred.promise;
    }


   this.cartTotal = function(regions,paymentMethods){
		 var deferred = $q.defer();
		var userToken = getUserToken();
        	$http({
                 url: $window.serverURL+"/rest/V1/carts/mine/totals",
                 method: "get",

                headers:{

                    'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8',
                    'Authorization':userToken
                }
            }).then(function successCallback(response) {
				hideLoadingScreen();
                console.log(response.data); 
                var customerJsonData = response.data;
                 response.data.paymentMethods = paymentMethods;
                response.data.regions = regions;
                console.log("json data is "+customerJsonData);
                 deferred.resolve(response.data);

          }, function errorCallback(response) {
            console.log('error'+response);
               hideLoadingScreen();
          });
		return deferred.promise;

    }

    this.getEmail = function(){
        return email;
    }



});