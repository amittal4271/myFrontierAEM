// setting up defaults for validator plugin for error handling
$.validator.setDefaults({
    onfocusout: function (element) {
        // uncomment for showing error on focus out of input field
        //$(element).valid();
    },
    //focusCleanup: true,
    errorElement: "span",
    errorClass: 'validate-error',
    errorPlacement: function (error, element) {
        // check for type of radio if so move error message placement
        if (element.hasClass('error-outside')) {
            error.appendTo(element.parent().parent());
        } else {
            error.insertAfter(element);
        } 
    },
    highlight: function (element, errorClass) {
        $(element).addClass('input-form-error');
    },
    unhighlight: function (element, errorClass) {
        $(element).removeClass('input-form-error');
    },
    invalidHandler: function(event, validator) {
    	// do other things for a valid form
    	var errors = validator.numberOfInvalids();
    	if (errors) {
        	//validator.errorList[0].element.focus();
        	var $el = validator.errorList[0].element;
			scrollToElement($el);
        	
        }
	}
});

$.validator.addMethod("dateMMDDYYYY", function(value, element) {
    return this.optional(element) || /^(0[1-9]|1[0-2])\/(0[1-9]|1\d|2\d|3[01])\/(19|20)\d{2}$/.test(value);
}, "The date must follow mm/dd/yyyy");

$.validator.addMethod("newemail", function(value, element) {
	$(element).val($(element).val().trim());
    return this.optional(element) || /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/.test($(element).val());
}, "Please enter a valid email address");

$.validator.addMethod("nowhitespace", function(value, element) {
	// make sure it's only white spaces 
	var trimedValue = value.trim().length;
	if(trimedValue > 0) {
		return true
	}
	else {
    	return this.optional(element) || /^\S+$/i.test(value);
	}
});

$.validator.addMethod("phoneNumber", function(value, element) {
	$(element).val($(element).val().trim());
    return this.optional(element) || /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/.test($(element).val());
}, "Please enter a valid phone number");








// Set up Validation namespace to reference for all form validation
;(function($) {

	window.validation = {
		
		registrationLifetimeForm: function($formId) {
			$($formId).validate({
		        rules: {
		            "mailing-name": {
		            	required: true,
		            	maxlength: 240
		            },
		            "account-company_name": {
		            	required: true,
		            	maxlength: 240
		            },
		            invite_1_name: {
						required: "#id_account-buying_club:checked"
					},
					invite_1_email: {
						required: "#id_account-buying_club:checked",
						email: true
					},
					invite_2_name: {
						required: "#id_account-buying_club:checked"
					},
					invite_2_email: {
						required: "#id_account-buying_club:checked",
						email: true
					},
					invite_3_name: {
						required: "#id_account-buying_club:checked"
					},
					invite_3_email: {
						required: "#id_account-buying_club:checked",
						email: true
					},
					invite_4_name: {
						required: "#id_account-buying_club:checked"
					},
					invite_4_email: {
						required: "#id_account-buying_club:checked",
						email: true
					}
				    
		        },
		        messages: {
		            "mailing-name": {
		                required: 'Please enter your Full Name'
		            },
		            "account-company_name": {
		            	required: 'Please enter a valid company name'
		            }
		        }
		    });
		}
	};

})(jQuery);