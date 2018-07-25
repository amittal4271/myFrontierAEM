var Frontier = Frontier || {};
Frontier.MagentoServices = Frontier.MagentoServices || {};

Frontier.MagentoServices = new function(){
    var serviceCallTimeout = 60000;

    function getCustomerDetails(serverURL){
       
        var url = serverURL+"/rest/V1/customers/me";
        
		console.log("Getting Customer");
		
		//GET
		$.support.cors = true;
		
		

		var userToken = getUserToken();
		
		return $.ajax({
            url: url, 
            type: "GET",

            dataType: "json",
            headers: {"Content-Type": "application/x-www-form-urlencoded; charset=UTF-8", "Authorization": userToken},
            crossDomain: true,
            timeout: serviceCallTimeout
        });
    }
    
    function saveCustomerDetails(serverUrl, customerObj) {
		console.log("Updating customer object", customerObj);
		
		var customerUrl = serverURL+"/rest/V1/customers/me";
		
		//GET
		$.support.cors = true;
		
		//optional service params can be added here
		var serviceParams = {
				customer: customerObj
		};

		console.log("Saving Customer JSON", JSON.stringify(serviceParams));
		
		var userToken = getUserToken();
		
		return $.ajax({
            url: customerUrl, 
            type: "PUT",
            data: JSON.stringify(serviceParams),
            headers: {"Content-Type": "application/json", "Authorization": userToken},
            crossDomain: true,
            timeout: serviceCallTimeout
        });
	}

    function inviteCustomer(serverUrl, email, firstname, lastname) {		
		var customerUrl = serverURL+"/rest/V1/customers";
		
		//GET
		$.support.cors = true;
		
		
		//optional service params can be added here
		var serviceParams = {
				customer: {
					email : email,
					firstname : firstname,
					lastname : lastname,
					website_id : 1,
					extension_attributes : {
				      company_attributes : {
				        company_id : 1,
				        status : 0
				      }
				    }
				}
		};

		console.log("Inviting Customer JSON", JSON.stringify(serviceParams));
		
		var userToken = getUserToken();
		
		return $.ajax({
            url: customerUrl, 
            type: "POST",
            data: JSON.stringify(serviceParams),
            headers: {"Content-Type": "application/json", "Authorization": userToken},
            crossDomain: true,
            timeout: serviceCallTimeout
        });
	}
    
    function updateCustomerPassword(serverUrl, passwordObj) {
		console.log("Updating customer password", passwordObj);
		
		var customerUrl = serverURL+"/rest/V1/customers/me/password";
		
		//GET
		$.support.cors = true;
		
		//optional service params can be added here
		var serviceParams = passwordObj;

		console.log("Updating Customer Password", JSON.stringify(serviceParams));
		
		var userToken = getUserToken();
		
		return $.ajax({
            url: customerUrl, 
            type: "PUT",
            data: JSON.stringify(serviceParams),
            headers: {"Content-Type": "application/json", "Authorization": userToken},
            crossDomain: true,
            timeout: serviceCallTimeout
        });
	}
    
    function getOrders(serverURL,adminToken,customerId){
     var orders=serverURL+"/rest/V1/orders?searchCriteria[pageSize]=150"
            + "&searchCriteria[currentPage]=1&searchCriteria[filterGroups][0][filters][0][value]="+customerId
            + "&searchCriteria[filterGroups][0][filters][0][field]=customer_id";

        //GET
		$.support.cors = true;
		
        return $.ajax({
            url: orders, 
            type: "GET",
           
            dataType: "json",
            headers: {"Content-Type": "application/x-www-form-urlencoded; charset=UTF-8", 
                      "Authorization": adminToken,
                       "Access-Control-Allow-Origin":serverURL,
                        "Access-Control-Allow-Credentials":"true"
                     },
            crossDomain: true,
            timeout: serviceCallTimeout
        });

    }
    
    function magentoLogin(serverURL,userName,password){
         var loginURL = serverURL+'/rest/V1/integration/customer/token';
        var jsonData = {};
        
        jsonData['username']=userName;
        jsonData['password']=password;
        return $.ajax({
            url: loginURL,
            method: 'POST',
            data:JSON.stringify(jsonData),
            crossDomain: "true",
             timeout: serviceCallTimeout,
            headers:{
                "content-Type":"application/json",
                "Access-Control-Allow-Origin":serverURL,
                "Access-Control-Allow-Credentials":"true"
                }
            });
    }
    
    function retrieveCartItems(){
    
    var jsonData={};
    jsonData['action']='getCart';
        
     return $.ajax({
       url: "/services/cart" ,
        method: "get",
         data:jsonData,
        headers:{

                'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8',
                'Authorization':getUserToken()
            },
          beforeSend:function(xhr){
              xhr.overrideMimeType("application/json");
          }
        
        });
    }
    
    function addItemToCart(jsonData){
       clearErrorMsg();
        return $.ajax({
            url:"/services/cart",
            data:jsonData,
            headers:{

                    'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8',
                    'Authorization':getUserToken()
                },
            beforeSend:function(xhr){
              xhr.overrideMimeType("application/json");
          }

        });
    }
    
    function getProductListByCategory(jsonData){
        clearErrorMsg();
        return $.ajax({
            url: "/services/productlist",
            data:jsonData,
            beforeSend:function(xhr){
              xhr.overrideMimeType("application/json");
          }
        });
    }
    
    function searchProducts(searchParams){
    	console.log("Searching Products with queryString", searchParams);
        return $.ajax({
            url: "/bin/product/search?"+searchParams,
            beforeSend:function(xhr){
              xhr.overrideMimeType("application/json");
          }
        });
    }
    
    function getProductFacets(){
        clearErrorMsg();
        return $.ajax({
            url: "/services/facetlist",
            beforeSend:function(xhr){
              xhr.overrideMimeType("application/json");
          }
        });
    }
    
    function getConfirmationData(serverURL,adminToken,confirmationNr){
         return $.ajax({
                url: serverURL+"/rest/V1/orders/"+confirmationNr,
                method: "get",
                 headers:{

                        'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8',
                        'Authorization':adminToken,
                        "Access-Control-Allow-Origin":serverurl,
                        "Access-Control-Allow-Credentials":"true"
                    },
                beforeSend:function(xhr){
                  xhr.overrideMimeType("application/json");
              }
            });
       
    }
    
    
    
    /**
    * Verify email including buyers club if selected
    * if email exists in system then don't allow registration
    */
    function emailValidation(serverurl,jsonData){
       
         
        return  $.ajax({
           url:serverurl+"/rest/all/V1/buyingroups/areEmailsAvailable",
           method:"POST",
            crossDomain: "true",
            headers:{
                "content-Type":"application/json",
                "Access-Control-Allow-Origin":serverurl,
                "Access-Control-Allow-Credentials":"true"
            },
           
           data:JSON.stringify(jsonData)
        });     

    }
    
    /**
    * Get productdetails based on sku
    */
    function getProductDetails(serverurl,sku){
        return  $.ajax({
           url:serverurl+"/rest/V1/products/"+sku,
           method:"GET",
            crossDomain: "true",
            headers:{

                        'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8',
                        'Authorization':adminToken,
                        "Access-Control-Allow-Origin":serverurl,
                        "Access-Control-Allow-Credentials":"true"
                    },
            
                beforeSend:function(xhr){
                  xhr.overrideMimeType("application/json");
              }
         
        });    
    }
    
    function addItemToWishList(jsonData){
       // clearErrorMsg();
        return $.ajax({
            url: "/services/productlist",
            data:{wishlist:JSON.stringify(jsonData),action:"wishlist"},
            method: "POST",
            beforeSend:function(xhr){
              xhr.overrideMimeType("application/json");
          }
        });
    }
    
    function getUserRole(serverurl){
        clearErrorMsg();
        return $.ajax({
            url: serverurl+"/rest/default/V1/fc/customers/me",
            method: "GET",
             timeout: serviceCallTimeout,
          
            headers:{

                        'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8',
                        'Authorization':getUserToken(),
                        "Access-Control-Allow-Origin":serverurl,
                        "Access-Control-Allow-Credentials":"true"
                    },
             beforeSend:function(xhr){
                
                  xhr.overrideMimeType("application/json");
            }
            
        });
    }
    
    function getRequisitionList(serverurl){
        return $.ajax({
            url: serverurl+"/rest/default/V1/frontier/customer/requisitionlist",
            method: "GET",
             timeout: serviceCallTimeout,
          
            headers:{

                        'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8',
                        'Authorization':getUserToken(),
                        "Access-Control-Allow-Origin":serverurl,
                        "Access-Control-Allow-Credentials":"true"
                    },
             beforeSend:function(xhr){
                
                  xhr.overrideMimeType("application/json");
            }
            
        });
    }
    
    function addRequisitionList(serverurl,jsonData){
       // clearErrorMsg();
        return $.ajax({
            url: serverurl+"/rest/default/V1/frontier/customer/requisitionlistitem",
            method: "POST",
             timeout: serviceCallTimeout,
          data:JSON.stringify(jsonData),
            headers:{
                        'Content-Type': 'application/json',
                        'Authorization':getUserToken(),
                        "Access-Control-Allow-Origin":serverurl,
                        "Access-Control-Allow-Credentials":"true"
                    },
             beforeSend:function(xhr){
                
                  xhr.overrideMimeType("application/json");
            }
            
        });
    }
    
    this.getCustomerDetails = getCustomerDetails;
    this.saveCustomerDetails = saveCustomerDetails;
    this.inviteCustomer = inviteCustomer;
    this.updateCustomerPassword = updateCustomerPassword;
    this.getOrders = getOrders;
    this.magentoLogin = magentoLogin;
    this.retrieveCartItems = retrieveCartItems;
    this.addItemToCart = addItemToCart;
    this.getProductListByCategory = getProductListByCategory;
    this.getConfirmationData = getConfirmationData;
    this.emailValidation = emailValidation;
    this.searchProducts = searchProducts;
    this.addItemToWishList = addItemToWishList;
    this.getUserRole = getUserRole;
    this.getRequisitionList = getRequisitionList;
    this.addRequisitionList = addRequisitionList;
}();