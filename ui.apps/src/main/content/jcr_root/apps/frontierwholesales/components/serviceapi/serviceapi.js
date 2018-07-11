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
            headers: {"Content-Type": "application/x-www-form-urlencoded; charset=UTF-8", "Authorization": adminToken},
            crossDomain: true,
            timeout: serviceCallTimeout
        });

    }
    
    function magentoLogin(serverURL,username,password){
         var loginURL = serverURL+'/rest/V1/integration/customer/token';
        var jsonData = {};
        var bReturn = false;
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
    
    function addItemToCart(sku,qty){
       var jsonData={};
        var cartItems={};
        var cartData={};
     
        cartItems['sku']=sku;
        cartItems['qty']=qty;

        cartData['cartItem']=cartItems;
        jsonData['items']=JSON.stringify(cartData);
        jsonData['action']='add';

    
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
    
    function getProductListByCategory(currentPage,recsPerPage,sortBy){
        var jsonData={};
   
    
    
        jsonData['currentPage']=currentPage;
        jsonData['categoryId']=$('#categoryId').val();
        jsonData['noOfRecsPerPage']=recsPerPage;
       if(sortBy !== undefined && sortBy !== ''){
           if(sortBy == "featured"){
               jsonData['sortByFeatured']="DESC";
           }else if(sortBy == "newproduct" ){
               jsonData['newProduct']="DESC";
           }else{
           jsonData['sortByPrice']=sortBy;
           }
       }
        return $.ajax({
            url: "/services/productlist",
            data:jsonData,
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
                        'Authorization':adminToken
                    },
                beforeSend:function(xhr){
                  xhr.overrideMimeType("application/json");
              }
            });
       
    }
    
    this.getCustomerDetails = getCustomerDetails;
    this.saveCustomerDetails = saveCustomerDetails;
    this.getOrders = getOrders;
    this.magentoLogin = magentoLogin;
    this.retrieveCartItems = retrieveCartItems
    this.addItemToCart = addItemToCart;
    this.getConfirmationData = getConfirmationData;
}();