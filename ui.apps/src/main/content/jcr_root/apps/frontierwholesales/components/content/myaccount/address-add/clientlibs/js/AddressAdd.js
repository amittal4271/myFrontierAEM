//namespace support
var Frontier = Frontier || {};
Frontier.AddressAdd = Frontier.AddressAdd || {};
//end namespace support

Frontier.AddressAdd = new function() {

	var customerObj;

	function getParameterByName(name, url) {
	    if (!url) url = window.location.href;
	    name = name.replace(/[\[\]]/g, '\\$&');
	    var regex = new RegExp('[?&]' + name + '(=([^&#]*)|&|#|$)'),
	        results = regex.exec(url);
	    if (!results) return null;
	    if (!results[2]) return '';
	    return decodeURIComponent(results[2].replace(/\+/g, ' '));
	}
	
	function init() {		
		// check if there is an edit address param 
		var addressIdToEdit = getParameterByName('address_id');
		
		loadRegions("id_locality");
		
		Frontier.MagentoServices.getCustomerDetails(serverURL).success(function(getCustomerResponse){
			customerObj = getCustomerResponse;
        }).error(function(xhr, status, error){
        	console.log("Issue with getAddress Service" + xhr.responseText, xhr);
        });
		
		$("#form-contact-information").submit(function(event) {
    	    // get txn id from current table row
    	    event.preventDefault();

    	    var shippingName = $('#id_name').val();
    	    var shippingNameSplit = shippingName.split(' ');
    	    
    	    var address={};
    	    address['region']={};
    	    address['street']=[];
    	    
    	    address['defaultShipping']='false';
    	    address['defaultBilling']='false';
    	    
    	    address['firstname']=shippingNameSplit[0];
    	    address['lastname']=shippingNameSplit[1];
    	    
    	    address['company']=$('#id_company').val();
    	    
    	    address['city']=$('#id_city').val();
    	    
    	    var regionData={};
    	    regionData['regionCode']=$('#id_locality option:selected').val();
    	    regionData['regionId']=$('#id_locality option:selected').attr('data-attr-id');
    	    regionData['region']=$('#id_locality option:selected').text();
    	    address['region']=regionData;
    	    
    	    address['postcode']=$('#id_postal_code').val();
    	    
    	    address['countryId']="US";
    	   
    	    address['telephone']=$('#id_phone').val();
    	    
    	    address['fax']=$('#id_fax').val();
    	    
    	    var streetData=[];
    	    
    	    streetData.push($('#id_address').val());
    	    streetData.push($('#id_address2').val());
    	    address['street']=streetData;
    	        	    
    	    customerObj.addresses.push(address);
    	    
    	    Frontier.MagentoServices.saveCustomerDetails(serverURL, customerObj).success(function(){
    	    	window.location.href = '/content/frontierwholesales/en/myaccount/addresses.html'
			}).error(function(xhr, status, error){
	        	console.log("Error saving customer to service" + xhr.responseText, xhr);
		    });
		});
		
	}

	$(document).ready(init);
}();
