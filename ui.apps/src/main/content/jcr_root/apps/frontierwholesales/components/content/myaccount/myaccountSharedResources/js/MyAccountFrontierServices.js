//namespace support
var Frontier = Frontier || {};
Frontier.MyAccountServices = Frontier.MyAccountServices || {};
//end namespace support

Frontier.MyAccountServices = new function(serverURL) {
	var serviceCallTimeout = 60000,
	servicePath = "/rest/V1/",
	getCustomerUrl = serverURL + servicePath + "customers/me";
	
	function getCustomer() {
		
		console.log("Getting Customer");
		
		//GET
		$.support.cors = true;
		
		//optional service params can be added here
		var serviceParams = {};

		var userToken = getUserToken();
		
		return $.ajax({
            url: getCustomerUrl, 
            type: "GET",
            data: serviceParams,
            dataType: "json",
            headers: {"Content-Type": "application/x-www-form-urlencoded; charset=UTF-8", "Authorization": userToken},
            crossDomain: true,
            timeout: serviceCallTimeout
        });
	}
	
	//expose public functions
	this.getCustomer = getCustomer;
}(serverURL);

