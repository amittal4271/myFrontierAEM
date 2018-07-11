//namespace support
var Frontier = Frontier || {};
Frontier.AddressBook = Frontier.AddressBook || {};
//end namespace support

Frontier.AddressBook = new function() {

	var savedAddressesList = [],
	customerObject,
	templates = {
        addressItem:
        	'<div class="each-address-holder"> \
			    <div class="each-address"> \
			         <span class="name">{{firstname}} {{lastname}}</span><br> \
			         <span class="company">{{company}}</span><br> \
			         <span>{{street}}</span><br> \
			         <span>{{city}}</span>, <span>{{region.region_code}}</span> \
			         <span>{{postcode}}</span><br> \
			         <span>{{country_id}}</span> \
			    </div> \
			    <div class="address-options"> \
			         <a href="/account/addresses/edit/302507/" class="address-link"> \
			              <span class="address-link-text">{{uiConfig.editActionText}}</span> \
			         </a> \
			         <a href="#" data-address-id="{{id}}" class="address-link remove-address-link"> \
			              <span class="address-link-text">{{uiConfig.deleteActionText}}</span> \
			              <span class="glyphicon glyphicon-remove"></span> \
			         </a> \
			    </div> \
			</div>',
			confirmModal:
				'<div class="modal fade" id="modalDeleteAddress" tabindex="-1" role="dialog" aria-labelledby="modalDeleteAddressLabel"> \
				    <div class="modal-dialog" role="document"> \
				         <div class="modal-content"> \
				              <div class="modal-header clearfix"> \
				                   <h4 class="modal-title" id="modalDeleteAddressLabel">{{heading}}</h4> \
				              </div> \
				              <div class="modal-body clearfix"> \
				                   <p>{{question}}</p> \
				              </div> \
				              <div class="modal-footer clearfix"> \
				                   <div class="modal-footer-btn-left-holder"> \
				                        <button type="button" class="btn btn-red btn-delete-address">{{okButtonTxt}}</button> \
				                   </div> \
				                   <div class="modal-footer-btn-right-holder"> \
				                        <button type="button" class="btn btn-link btn-link-close-modal" data-dismiss="modal">{{cancelButtonTxt}}</button> \
				                   </div> \
				              </div> \
				         </div> \
				    </div> \
				</div>'
	},
	target = "",
	uiConfig = {},
	selectedAddress,
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
		var $addressListContainer = $(".account-information-holder");
		$addressListContainer.empty();
		
		var addressItemTemplate = Handlebars.compile(templates.addressItem);
		
		Frontier.MagentoServices.getCustomerDetails(serverURL).success(function(getCustomerResponse){
			
			customerObject = getCustomerResponse;
			
        	if(!!getCustomerResponse.addresses && getCustomerResponse.addresses.length > 0) {
        		
        		
	            for(i=0 ; i<=getCustomerResponse.addresses.length-1 ; i++){
	            	var address = getCustomerResponse.addresses[i];
					savedAddressesList[address.id] = address;
					
					//create a separate object for display, so as not to disturb the original from the server
					var addressToDisplay = JSON.parse(JSON.stringify(address));
					addressToDisplay.uiConfig = uiConfig;
					
		            console.log("address found ", address);
		            
		            $addressListContainer.append(addressItemTemplate(addressToDisplay));
	            }
	            
	            $addressListContainer.find(".remove-address-link").click(function(event) {
	        	    // get txn id from current table row
	        	    var id = $(this).data('address-id');

	        	    console.log("Selected address id: "+id);
	        	    
	        	    var heading = 'Confirm Address Delete';
	        	    var question = 'Please confirm that you wish to delete Address ' + id + '.';
	        	    var cancelButtonTxt = 'Cancel';
	        	    var okButtonTxt = 'Confirm';

	        	    var callback = function(confirmModal) {
	        	      	        	      
	        	    	console.log("Deleting address for this customer",JSON.stringify(customerObject));
	        	      
	        	      	var listToDelete = [id];
	        	      
	        	      	var newAddressesArray = customerObject.addresses.filter(function(obj) {
	        	    	    return listToDelete.indexOf(obj.id) === -1;
	        	    	});
	        	      
	        	      	console.log("Updated Address Array", newAddressesArray);
	        	      	customerObject.addresses = newAddressesArray;
	        	      
	        	      	console.log("new customer object to send ", customerObject);
	        	      
	        	      	Frontier.MagentoServices.saveCustomerDetails(serverURL, customerObject).success(function(){
	        	    	    loadUserAddressesAndDisplay();
	        	    	    confirmModal.modal('hide');
						}).error(function(xhr, status, error){
				        	console.log("Error saving customer to service" + xhr.responseText, xhr);
					    });
	        	    };

	        	    confirm(heading, question, cancelButtonTxt, okButtonTxt, callback);

	        	  });
        	} else {
        		console.log("No Addresses", getCustomerResponse);
        	}

        }).error(function(xhr, status, error){
        	console.log("Issue with getAddress Service" + xhr.responseText, xhr);
        });

	}

	function confirm(heading, question, cancelButtonTxt, okButtonTxt, callback) {	
		var confirmModalTemplate = Handlebars.compile(templates.confirmModal);
		var confirmModalConfig = {
				"heading" : heading,
				"question" : question,
				"cancelButtonTxt" : cancelButtonTxt,
				"okButtonTxt": okButtonTxt
		};
		
		confirmModal = $(confirmModalTemplate(confirmModalConfig));
		
	    confirmModal.find('.btn-delete-address').click(function(event) {
	      callback(confirmModal);
	    });
	
	    confirmModal.modal('show');     
	};

	$(document).ready(init);
}();
