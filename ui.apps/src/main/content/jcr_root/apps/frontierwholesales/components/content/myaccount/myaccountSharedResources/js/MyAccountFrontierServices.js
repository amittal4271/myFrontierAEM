//namespace support
var Frontier = Frontier || {};
Frontier.MyAccountServices = Frontier.MyAccountServices || {};
//end namespace support

Frontier.MyAccountServices = new function(serverURL) {
	var serviceCallTimeout = 60000,
	servicePath = "/rest/V1/",
	customerUrl = serverURL + servicePath + "customers/me",
	addAddressUrl = serverURL + servicePath + "addNewAddress";
	
	function getCustomer() {
		
		console.log("Getting Customer");
		
		//GET
		$.support.cors = true;
		
		//optional service params can be added here
		var serviceParams = {};

		var userToken = getUserToken();
		
		return $.ajax({
            url: customerUrl, 
            type: "GET",
            data: serviceParams,
            dataType: "json",
            headers: {"Content-Type": "application/x-www-form-urlencoded; charset=UTF-8", "Authorization": userToken},
            crossDomain: true,
            timeout: serviceCallTimeout
        });
	}
	
	function saveCustomer(customerObj) {
		console.log("Updating customer", customerObj);
		
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
	
	function addAddress(addressData) {
		console.log("Adding Customer Address", addressData);
		
		//GET
		$.support.cors = true;
		
		var serviceParams = {
			"address": {
				"street": ["5830 Pennant Ln", ""],
				"region": {
					"region_code": "GA",
					"region": "Georgia",
					"region_id": "19",
					"extension_attributes": {}
				},
				"region_id": "19",
				"country_id": "US",
				"defaultShipping": "true",
				"defaultBilling": "true",
				"firstname": "Adam",
				"lastname": "Testing",
				"postcode": "30024",
				"city": "Suwanee",
				"telephone": "4049343899",
				"company": "",
				"fax": "",
				"prefix": "",
				"suffix": "",
				"vat_id": 0,
				"customer_id": "300"
			}
		}
		
		console.log("Adding Customer Address", addressData);
		
		
		var userToken = getUserToken();
		
		return $.ajax({
            url: addAddressUrl, 
            type: "POST",
            data: serviceParams,
            dataType: "json",
            headers: {"Content-Type": "application/x-www-form-urlencoded; charset=UTF-8", "Authorization": userToken},
            crossDomain: true,
            timeout: serviceCallTimeout
        });
	}
	
	//expose public functions
	this.saveCustomer = saveCustomer;
	this.getCustomer = getCustomer;
}(serverURL);

