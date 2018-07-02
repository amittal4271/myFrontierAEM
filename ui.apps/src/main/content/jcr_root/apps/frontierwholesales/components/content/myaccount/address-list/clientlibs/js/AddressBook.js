//namespace support
var Frontier = Frontier || {};
Frontier.AddressBook = Frontier.AddressBook || {};
//end namespace support

Frontier.AddressBook = new function() {

	var aaaaa = '',
	savedAddressesList = [],
	templates = {
		test:
            '<div class="panel panel-default"> \
	            <h4 class="panel-title">TESTING HANDLEBARS{{testVar}}</h4> \
            </div>',
        addressItem:
        	'<div class="each-address-holder"> \
			    <div class="each-address"> \
			         <span class="name">{{firstname}} {{lastname}}</span><br> \
			         <span class="company">{{company}}</span><br> \
			         <span>{{street}}</span><br> \
			         <span>TODO: add street 2</span><br> \
			         <span>{{city}}</span>, <span>{{region.region_code}}</span> \
			         <span>{{postcode}}</span><br> \
			         <span>{{country_id}}</span> \
			    </div> \
			    <div class="address-options"> \
			         <a href="/account/addresses/edit/302507/" class="address-link"> \
			              <span class="address-link-text">{{uiConfig.editActionText}}</span> \
			         </a> \
			         <a href="/account/addresses/delete/302507/" class="address-link remove-address-link"> \
			              <span class="address-link-text">{{uiConfig.deleteActionText}}</span> \
			              <span class="glyphicon glyphicon-remove"></span> \
			         </a> \
			    </div> \
			</div>'
	},
	target = "",
	uiConfig = {},
	addresses = [];

	function init() {
		$componentContainer = $(".address-list");
		var $uiConfigElem = $componentContainer.find(".ui-config");
		console.log("UI Config element ", $uiConfigElem);
		uiConfig = {
			editActionText : $uiConfigElem.attr("data-editActionText"),
			deleteActionText : $uiConfigElem.attr("data-deleteActionText")
		}
		
		
		loadUserAddressesAndDisplay();
		console.log("initializing address book");
	}

	function runNoAddressesExperience() {
		console.log("no addresses found");
	}

	function loadUserAddressesAndDisplay() {
		// reset any display, remove all addresses

		var addressItemTemplate = Handlebars.compile(templates.addressItem);
		
		Frontier.MyAccountServices.getCustomer().success(function(getCustomerResponse){
        	if(!!getCustomerResponse.addresses && getCustomerResponse.addresses.length > 0) {
	            for(i=0 ; i<=getCustomerResponse.addresses.length-1 ; i++){
	            	var address = getCustomerResponse.addresses[i];
					savedAddressesList[address.id] = address;
					
					address.uiConfig = uiConfig;
					
					//TODO: render address
		            console.log("address found ", address);
		            
		            $(".account-information-holder").append(addressItemTemplate(address));
		            
	            }
        	} else {
        		console.log("No Addresses", getCustomerResponse);
        	}

        }).error(function(xhr, status, error){
        	console.log("Issue with getAddress Service" + xhr.responseText, xhr);
        });

	}


	$(document).ready(init);
}();
