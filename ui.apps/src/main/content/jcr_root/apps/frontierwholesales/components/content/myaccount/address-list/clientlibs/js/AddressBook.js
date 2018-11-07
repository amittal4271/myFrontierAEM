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
			         <a href="/content/frontierwholesales/en/myaccount/addresses/edit.html?address_id={{id}}" class="address-link"> \
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
		var $componentContainer = $(".address-list");
		var $uiConfigElem = $componentContainer.find(".ui-config");
		uiConfig = {
			editActionText : $uiConfigElem.attr("data-editActionText"),
			deleteActionText : $uiConfigElem.attr("data-deleteActionText"),
			deleteModalHeading : $uiConfigElem.attr("data-deleteModalHeading"),
			deleteModalText : $uiConfigElem.attr("data-deleteModalText"),
			deleteModalCancelButtonText : $uiConfigElem.attr("data-deleteModalCancelButtonText"),
			deleteModalConfirmButtonText : $uiConfigElem.attr("data-deleteModalConfirmButtonText")
		}
		
		loadUserAddressesAndDisplay();
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
        		
        		
	            for(var i=0 ; i<=getCustomerResponse.addresses.length-1 ; i++){
	            	var address = getCustomerResponse.addresses[i];
					savedAddressesList[address.id] = address;
					
					//create a separate object for display, so as not to disturb the original from the server
					var addressToDisplay = JSON.parse(JSON.stringify(address));
					addressToDisplay.uiConfig = uiConfig;
					
		            
		            $addressListContainer.append(addressItemTemplate(addressToDisplay));
	            }
	            
	            $addressListContainer.find(".remove-address-link").click(function(event) {

	            	var id = $(this).data('address-id');

	        	    var callback = function(confirmModal) {
	        	      	        	      	        	      
	        	      	var listToDelete = [id];
	        	      
	        	      	var newAddressesArray = customerObject.addresses.filter(function(obj) {
	        	    	    return listToDelete.indexOf(obj.id) === -1;
	        	    	});
	        	      
	        	      	customerObject.addresses = newAddressesArray;
	        	      	        	      
	        	      	Frontier.MagentoServices.saveCustomerDetails(serverURL, customerObject).success(function(){
	        	    	    loadUserAddressesAndDisplay();
	        	    	    confirmModal.modal('hide');
						}).error(function(xhr, status, error){
				        	console.log("Error saving customer to service" + xhr.responseText, xhr);
					    });
	        	    };

	        	    confirm(uiConfig.deleteModalHeading, uiConfig.deleteModalText, uiConfig.deleteModalCancelButtonText, uiConfig.deleteModalConfirmButtonText, callback);

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
		
		var confirmModal = $(confirmModalTemplate(confirmModalConfig));
		
	    confirmModal.find('.btn-delete-address').click(function(event) {
	      callback(confirmModal);
	    });
	
	    confirmModal.modal('show');     
	};

	$(document).ready(init);
}();
