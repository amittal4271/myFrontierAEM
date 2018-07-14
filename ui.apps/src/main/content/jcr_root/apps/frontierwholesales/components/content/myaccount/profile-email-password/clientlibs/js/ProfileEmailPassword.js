//namespace support
var Frontier = Frontier || {};
Frontier.ProfileEmailPassword = Frontier.ProfileEmailPassword || {};
//end namespace support

Frontier.ProfileEmailPassword = new function() {

	var customerObj,
	addressToEdit;

	function init() {
		Frontier.MagentoServices.getCustomerDetails(serverURL).success(function(getCustomerResponse){
			customerObj = getCustomerResponse;
			$("#id_email").val(customerObj.email);
			$("#form-contact-information").submit(function(event) {
	    	    event.preventDefault();

	    	    var email = $('#id_email').val();
	    	    
	    	    var currentPassword = $("#id_current_password").val();
	    	    
	    	    var newPassword = $("#id_password").val();
	    	    
	    	    var confirmPassword = $("#id_password_confirm").val();
	    	    
	    	    console.log("currentPassword = " + currentPassword);
	    	    console.log("newPassword = " + newPassword);
	    	    console.log("confirmPassword = " + confirmPassword);
	    	    
	    	    //confirm newpass and confirm pass are equal
	    	    var savePass = false;
	    	    if(!!currentPassword && !!newPassword && !!confirmPassword) {
	    	    	if(newPassword === confirmPassword) {
	    	    		savePass = true;
	    	    	}
	    	    }
	    	    
	    	    customerObj.email = email;
	    	    showLoadingScreen();
	    	    Frontier.MagentoServices.saveCustomerDetails(serverURL, customerObj).success(function(){
	    	    	if(savePass) {
	    	    		console.log("save password");
	    	    		var passwordObj = {
	    	    				  "currentPassword": currentPassword,
	    	    				  "newPassword": newPassword
	    	    				};
	    	    		
	    	    		Frontier.MagentoServices.updateCustomerPassword(serverURL, passwordObj).success(function(){
	    	    	    		window.location.reload(true);
	    				}).error(function(xhr, status, error){
	    					hideLoadingScreen();
	    		        	console.log("Error saving customer to service" + xhr.responseText, xhr);
	    			    });
	    	    		
	    	    	} else {
	    	    		window.location.reload(true);
	    	    	}
				}).error(function(xhr, status, error){
					hideLoadingScreen();
		        	console.log("Error saving customer to service" + xhr.responseText, xhr);
			    });
			});
        }).error(function(xhr, status, error){
        	console.log("Issue with getCustomerDetails Service" + xhr.responseText, xhr);
        });
	}
	
	$(document).ready(init);
}();
