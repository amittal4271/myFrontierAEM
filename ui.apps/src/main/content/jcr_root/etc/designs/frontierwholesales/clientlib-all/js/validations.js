$.validator.addMethod("fullName", function(value, element) {

	var trimedValue = value.trim();
	if(trimedValue.length > 0 && trimedValue.indexOf(' ') > 0) {
		return true;
	}
	
});

$.validator.addMethod("phoneno", function(value, element) {

	return this.optional(element)|| /^\d{10}$/.test(value);
});

$.validator.addMethod("passwordMatch", function(value, element) {

	return $('#id_membership-password').val() == $('#id_membership-password_confirm').val()
},"Password did not match.");

$.validator.addMethod("ssntaxid", function(value, element) {
	return this.optional(element) || /^(\d{3})-?\d{2}-?\d{4}$/i.test(value) || /^(\d{2})-?\d{7}$/i.test(value)
}, "Please enter valid SSN/Tax ID");

$.validator.addMethod("url", function(value, element) {

	var btnRadio = $('.radio-checkbox-section-holder input:radio:checked').attr('id');
    if(undefined !== btnRadio){
        if( value.trim().length > 0){
            return true;
        }
    }
});

$.validator.addMethod("expiryMonth",function(value,element){
   var dt = new Date();
   var currentMonth =  dt.getMonth();
    if(value > currentMonth){
        return true;
    }
});

;(function($) {

   console.log('validation part...');
	window.validation = {
		
		registrationLifetimeForm: function($formId) {
			$($formId).validate({
		        rules: {
		            "mailing-name": {
		            	required: true,
		            	maxlength: 70,
                        fullName: true
		            },
		            "account-company_name": {
		            	required: true,
		            	maxlength: 70
		            },
                    "account-taxpayer_id":{
                        required: true,
		            	maxlength: 11,
                        ssntaxid: true
                    },
                    "mailing-address":{
                        required: true,
		            	maxlength: 150
                        
                    },"mailing-city":{
                        required: true,
		            	maxlength: 70
                        
                    },"mailing-postal_code":{
                        required: true,
                        minlength: 5,
		            	maxlength: 12
                        
                    },"shipping-name":{
                        required: true,
		            	maxlength: 70,
                        fullName: true
                        
                    },"shipping-company":{
                        required: true,
		            	maxlength: 70
                        
                    },"shipping-address":{
                        required: true,
		            	maxlength: 150
                        
                    },"shipping-city":{
                        required: true,
		            	maxlength: 70
                        
                    },"shipping-postal_code":{
                        required: true,
                        minlength: 5,
		            	maxlength: 12
                        
                    },"shipping-phone":{
                        required: true,
		            	maxlength: 10,
                        phoneno: true
                        
                    },"account-signature":{
                        required: true,
		            	maxlength: 70
                        
                    },"membership-name":{
                        required: true,
		            	maxlength: 70,
                        fullName: true
                        
                    },"membership-email":{
                        required: true,
		            	maxlength: 150,
                        email: true
                        
                    },"membership-password":{
                        required: true,
                        minlength: 8,
		            	maxlength: 50
                        
                        
                    },"membership-password_confirm":{
                        required: true,
                        minlength: 8,
                        maxlength: 50,
                        equalTo: '#id_membership-password'
                        
                    },"billing-name":{
                        required: true,
		            	maxlength: 70,
                        fullName: true
                        
                    },"billing-address":{
                        required: true,
		            	maxlength: 150
                        
                    },"billing-city":{
                        required: true,
		            	maxlength: 70
                        
                    },"billing-postal_code":{
                        required: true,
                        minlength: 5,
		            	maxlength: 12
                    
                    },"billing-number":{
                        required: true,
		            	maxlength: 16,
                        creditcard: true
                        
                    },"billing-cvv":{
                        required: true,
		            	maxlength: 4
                        
                    },"billing-exp_month":{
                        required: true,
		            	maxlength: 2,
                         expiryMonth: true
                        
                    },"billing-exp_year":{
                        required: true,
		            	maxlength: 4
                        
                    },"billing-company":{
                        required: true,
                        maxlength: 70
                    },"account-url":{
                        url: true
                    }
                },		        
		        messages: {
		            "mailing-name": {
		                
                        fullName: 'Please enter full name'
		            },
		           "shipping-name":{
                       
                         fullName: 'Please enter full name'
                        
                    },"shipping-phone":{
                        
                          phoneno: 'Enter a valid phone number'
                        
                    },"membership-name":{
                       
                        fullName: 'Please enter full name'
                        
                    },"membership-password_confirm":{
                        
                        equalTo: 'Password did not match'
                        
                    },"billing-name":{
                      
                       fullName: 'Please enter full name'
                        
                    },"account-url":{
                        url: "This field is required"
                    },"billing-exp_month":{
                        expiryMonth: "Please enter a valid expiration date!"
                    }
		        }
		    });
		}
	};

})(jQuery);