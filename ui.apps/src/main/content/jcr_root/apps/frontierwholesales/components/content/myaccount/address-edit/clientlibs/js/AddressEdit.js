//namespace support
var Frontier = Frontier || {};
Frontier.AddressEdit = Frontier.AddressEdit || {};
//end namespace support

Frontier.AddressEdit = new function() {

	var customerObj,
	addressToEdit;

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
		
		Frontier.MagentoServices.getCustomerDetails(serverURL).success(function(getCustomerResponse){
			customerObj = getCustomerResponse;
			addressToEdit = findObjectByKey(customerObj.addresses, "id", addressIdToEdit);
			loadRegions("id_locality").then(function(){
				loadEditForm(addressToEdit);
			});
			$("#form-contact-information").submit(function(event) {
	    	    event.preventDefault();

	    	    var shippingName = $('#id_name').val();
	    	    var shippingNameSplit = shippingName.split(' ');
	    	    
	    	    var address = addressToEdit;
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
	    	    
	    	    updateAddress(customerObj, address);
	    	    
	    	    Frontier.MagentoServices.saveCustomerDetails(serverURL, customerObj).success(function(){
	    	    	window.location.href = '/content/frontierwholesales/en/myaccount/addresses.html'
				}).error(function(xhr, status, error){
		        	console.log("Error saving customer to service" + xhr.responseText, xhr);
			    });
			});
        }).error(function(xhr, status, error){
        	console.log("Issue with getAddress Service" + xhr.responseText, xhr);
        });
		
		
		
	}

	function findObjectByKey(array, key, value) {
	    for (var i = 0; i < array.length; i++) {
	        if (array[i][key] == value) {
	            return array[i];
	        }
	    }
	    return null;
	}
	
	function updateAddress(customerObject, address) {
		for (var i = 0; i < customerObject.addresses.length; i++) {
	        if (customerObject.addresses[i]["id"] === address.id) {
	        	customerObject.addresses[i] = address;
	        }
	    }
		
		return customerObject;
	}

	function loadEditForm(address) {
		$('#id_name').val(address.firstname+" "+address.lastname);
		$('#id_company').val(address.company);
		$('#id_city').val(address.city);
		$("#id_locality option[value='"+address.region.region_code+"']").prop('selected', true);
		$('#id_postal_code').val(address.postcode);
		$('#id_phone').val(address.telephone);
		$('#id_fax').val(address.fax);
		$('#id_address').val(address.street.length >= 1 ? address.street[0] : '');
		$('#id_address2').val(address.street.length == 2 ? address.street[1] : '');
	}
	
	$(document).ready(init);
}();
