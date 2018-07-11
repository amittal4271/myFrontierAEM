//namespace support
var Frontier = Frontier || {};
Frontier.AddressAdd = Frontier.AddressAdd || {};
//end namespace support

Frontier.AddressAdd = new function() {

	var customerObj;

	function init() {
		loadRegions("id_locality");
		
		Frontier.MagentoServices.getCustomerDetails(serverURL).success(function(getCustomerResponse){
			customerObj = getCustomerResponse;
			console.log("setting cusomer object", getCustomerResponse);
        }).error(function(xhr, status, error){
        	console.log("Issue with getAddress Service" + xhr.responseText, xhr);
        });
		
		$("#form-contact-information").submit(function(event) {
    	    // get txn id from current table row
    	    console.log("Create new address!!");
    	    console.log("redirect to address list page");
    	    console.log("customer object to save", customerObj);
    	    event.preventDefault();
    	    
    	    var newAddress = JSON.parse(JSON.stringify(customerObj.addresses[1]));
    	    newAddress.lastname = "Testing";
    	    delete newAddress.id
    	    customerObj.addresses.push(newAddress);
    	    
    	    Frontier.MagentoServices.saveCustomerDetails(serverURL, customerObj).success(function(){
    	    	window.location.href = '/content/frontierwholesales/en/myaccount/addresses.html'
			}).error(function(xhr, status, error){
	        	console.log("Error saving customer to service" + xhr.responseText, xhr);
		    });
		});
		
	}

	$(document).ready(init);
}();
