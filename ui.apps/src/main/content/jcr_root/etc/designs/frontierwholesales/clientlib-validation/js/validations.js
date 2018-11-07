
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
        	validator.errorList[0].element.focus();
        }
	}
});

$.validator.addMethod("fullName", function(value, element) {

	var trimedValue = value.trim();
	if(trimedValue.length > 0 && trimedValue.indexOf(' ') > 0) {
		return true;
	}
	
});

$.validator.addMethod("phoneno", function(value, element) {
	return this.optional(element)|| /^\(?\d{3}\)?[- ]?\d{3}[- ]?\d{4}$/.test(value);
});

$.validator.addMethod("notalpha", function(value, element) {
	var regEx = new RegExp("^[a-zA-Z ]*$");
    return !regEx.test(value)
});

$.validator.addMethod("containsOneOrMoreSpaces", function(value, element) {
    return value.trim().indexOf(" ") !== -1;
});

$.validator.addMethod("cvvValidate", function(value, element) {
	var regEx = new RegExp("^[0-9]{3,4}$");
    return regEx.test(value)
});


$.validator.addMethod("passwordValidate",function(value,element){
    var regEx = new RegExp("^(?=.*[a-z])(?=.*[0-9])(?=.*[!@#\$%\^&\*])(?=.{8,})");
    return regEx.test(value)
},"Please enter valid password, must contain a capital letter, a number, and a symbol")

$.validator.addMethod("passwordMatch", function(value, element) {

	return $('#id_membership-password').val() == $('#id_membership-password_confirm').val()
},"Password did not match.");

$.validator.addMethod("ssntaxid", function(value, element) {
	return this.optional(element) || /^(\d{3})-?\d{2}-?\d{4}$/i.test(value) || /^(\d{2})-?\d{7}$/i.test(value)
}, "Please enter valid SSN/Tax ID");

$.validator.addMethod("urlText", function(value, element) {
console.log("method is being called here...");
	var btnRadio = $('.radio-checkbox-section-holder input:radio:checked').attr('id');
    var checked = $('#id_account-buying_club').is(':checked');
   if( value.trim().length > 0){
        if(checked){
            return false;
        }else if(undefined == btnRadio && !checked){
             this.settings.messages[element.name]['urlText'] =  'Please choose site';
            return false;
        }
        return true;
    }else{
		 if(undefined != btnRadio && !checked){
             this.settings.messages[element.name]['urlText'] = "This field is required";
            return false;
        }
        return true;
    }
});

$.validator.addMethod('validUrl', function(value, element) {
    var url = $.validator.methods.url.bind(this);
    return url(value, element) || url('http://' + value, element);
}, 'Please enter a valid URL');

$.validator.addMethod("emailValidation",function(value,element){
    var emailFormat = /^\w+([\+\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
    if(value.match(emailFormat)){
        return true;
    }
});

$.validator.addMethod("expiryMonth",function(value,element){
   var dt = new Date();
   var currentMonth =  dt.getMonth();
    currentMonth = currentMonth + 1;
   value = parseInt(value);
    var fullYear = parseInt($('#id_billing-exp_year').val());
    if(value < currentMonth && fullYear == dt.getFullYear()){
        return false;
    }else{
        return true;
    }
});

$.validator.addMethod("creditcardValidate",function(value,element){
   // first, sanitize the number by removing all non-digit characters.
var num = value.replace(/[^\d]/g, '');
// now test the number against some regexes to figure out the card type.
if (num.match(/^5[1-5]\d{14}$/)) {
 return true;
} else if (num.match(/^4\d{15}/) || num.match(/^4\d{12}/)) {
 return true;
} else if (num.match(/^3[47]\d{13}/)) {
 return true;
} else if (num.match(/^6011\d{12}/)) {
 return true;
}

});

function buyersClubCheckboxOperations(){
     $(document).on( "change", "#id_account-buying_club", function(e) {
                         console.log('buying club radio change');
                         var $this = $(this);

                         var $buyingClubInvitesHolder = $('#buying-club-additional-invites-holder');
                         
                         if ($buyingClubInvitesHolder.hasClass('show-invites-holder')) {
                              $buyingClubInvitesHolder.removeClass('show-invites-holder').slideUp("fast");
                              $this.removeClass('buying-club-selected');
                         } else {
                              $buyingClubInvitesHolder.addClass('show-invites-holder').slideDown("fast");
                              $this.addClass('buying-club-selected');
                              // de-select other business type options if buying club is selected
                              $('.other-than-buying-club-input').prop('checked', false);
                             $('#webaddress-data').css('display','none');
                         }
                    });

                    $(document).on( "change", ".other-than-buying-club-input", function(e) {
                         console.log('other than buying club change');
                         var $this = $(this);

                         var $buyingClubInvitesHolder = $('#buying-club-additional-invites-holder');
                         var $buyingClubInput = $('#id_account-buying_club');
                         
                         if ($buyingClubInvitesHolder.hasClass('show-invites-holder')) {
                              $buyingClubInvitesHolder.removeClass('show-invites-holder').slideUp("fast");
                              $buyingClubInput.removeClass('buying-club-selected');
                              // de-select buying club and online only if other options chosen
                              $buyingClubInput.prop('checked', false);
                         }
                    });

                    $(document).on( "click", ".btn-add-another-buyer-member", function(e) {
                         var $this = $(this);
                         // update current count 
                         validation.currentCount++;
                         // log
                         console.log(validation.currentCount);
                         // update input count val
                         validation.buyerMemberCountInput.val(validation.currentCount);

                         validation.addNewRowHtml();
                    });

                    $(document).on( "click", ".btn-remove-buyer-member", function(e) {
                         var $this = $(this);
                         var $btnEl = $this;
                         // update current count 
                         validation.currentCount--;
                         // log
                         console.log(validation.currentCount);
                         // update input count val
                         validation.buyerMemberCountInput.val(validation.currentCount);
                         // remove row
                         validation.removeRow($btnEl);
                    });
                    
}

;(function($) {

   console.log('validation part...');


   buyersClubCheckboxOperations();


	window.validation = {
		
             buyerMemberCountInput: $('#buyer-member-count-input'),
                         groupHolder: $('#buyer-club-group-holder'),
                         countMin: 4,
                         countMax: 100,
                         currentCount: 4,
             addNewRowHtml: function () {
                              var $newRowHtml = '';

                              $newRowHtml += '<div id="invite-group-section-'+validation.currentCount+'" class="each-invite-group-section">';
                                   $newRowHtml += '<div class="form-group name-group width-half first">';
                                        $newRowHtml += '<label for="id_invite-'+validation.currentCount+'-name">Name</label>';
                                        $newRowHtml += '<input id="id_invite-'+validation.currentCount+'-name" name="invite-'+validation.currentCount+'-name" placeholder="Name" type="text" class="form-control">';
                                   $newRowHtml += '</div>';
                                   $newRowHtml += '<div class="form-group email-group width-half">';
                                        $newRowHtml += '<label for="id_invite-'+validation.currentCount+'-email">Email</label>';
                                        $newRowHtml += '<input id="id_invite-'+validation.currentCount+'-email" name="id_invite-'+validation.currentCount+'-email" placeholder="Email" type="text" class="form-control">';
                                   $newRowHtml += '</div>';
                                   $newRowHtml += '<div class="form-group remove-buying-club-btn-holder">';
                                        $newRowHtml += '<button type="button" class="btn btn-link red-link btn-remove-buyer-member">';
                                             $newRowHtml += '<span class="glyphicon glyphicon-minus"></span>Remove Line Item';
                                        $newRowHtml += '</button>';
                                   $newRowHtml += '</div>';
                              $newRowHtml += '</div>';

                              validation.groupHolder.append($newRowHtml);
                 
                           $('#id_invite-'+validation.currentCount+'-email').rules( "add", {
                                  required: true,
                                  emailValidation: true,
                                  minlength: 2,
                                  maxlength: 120
                             });

                             $('#id_invite-'+validation.currentCount+'-name').rules( "add", {
                                  required: true,
                                  minlength: 2,
                                  maxlength: 40
                             });
                         },

                         removeRow: function ($btnEl) {
                              // find parent invite group holder
                              var $inviteGroupSectionRemoveHolder = $btnEl.parents('.each-invite-group-section');
                              // remove html
                              $inviteGroupSectionRemoveHolder.remove();
                              validation.updateLineItemCountsOnRemove();
                         },

                         updateLineItemCountsOnRemove: function () {
                              // we need to update numbering based on line item number that is removed
                              if (validation.currentCount > 4) {
                                   $('.each-invite-group-section').each(function (index, value) {
                                        var $this = $(this);
                                        var $nameGroup = $this.children('.name-group');
                                        var $emailGroup = $this.children('.email-group');
                                        console.log(index+1);
                                        // set index to match numbers starting at 1
                                        var $newIndex = index+1;
                                        // reset html that has numbers according to new index
                                        $this.attr('id', 'invite-group-section-'+$newIndex);

                                        // set new index values on labels and inputs for name and email
                                        var $nameLabel = $nameGroup.find('label');
                                        var $nameInput = $nameGroup.find('input');

                                        $nameLabel.attr('for', 'id_invite-'+$newIndex+'-name');
                                        $nameInput.attr('id', 'id_invite-'+$newIndex+'-name');
                                        $nameInput.attr('name', 'invite-'+$newIndex+'-name');

                                        var $emailLabel = $emailGroup.find('label');
                                        var $emailInput = $emailGroup.find('input');

                                        $emailLabel.attr('for', 'id_invite-'+$newIndex+'-email');
                                        $emailInput.attr('id', 'id_invite-'+$newIndex+'-email');
                                        $emailInput.attr('name', 'invite-'+$newIndex+'-email');
                                   });
                              }
                         },
        newletterForm: function($formId) {
            $($formId).validate({
                rules: {
                   
                    "email":{
                        required: true,
		            	maxlength: 150,
                        emailValidation: true
                        
                    }
                    
                },messages :{
                    "email":{
                         emailValidation:"Please enter a valid email"
                    }
                    
                }
            });
        },
        
        
        contactUsForm: function($formId){
             $($formId).validate({
                rules: {
                   "name":{
                        required: true,
		            	maxlength: 70,
                       
                       
                   },
                    "reason":{
                         required: true
                    },"message":{
                         required: true
                       
                        
                    },
                    "email":{
                        required: true,
		            	maxlength: 150,
                        emailValidation: true
                        
                    }
                    
                },messages :{
                    "email":{
                         emailValidation:"Please enter a valid email"
                    }
                    
                }
            });
        },
        
         buyersClubRegistrationForm: function($formId) {
            $($formId).validate({
                rules: {
                   
                    "membership-email":{
                        required: true,
		            	maxlength: 150,
                        emailValidation: true
                        
                    },"membership-password":{
                        required: true,
                        minlength: 8,
		            	maxlength: 50
                        
                        
                    },"membership-password_confirm":{
                        required: true,
                        minlength: 8,
                        maxlength: 50,
                        equalTo: '#id_membership-password'
                        
                    },
                    "shipping-name":{
                        required: true,
                        maxlength: 70,
                        fullName: true
                    },"shipping-address":{
                        required: true,
                        maxlength: 150
                    },"shipping-city":{
                        required: true,
                        maxlength: 70
                        
                    },"shipping-postal_code":{
                        required: true,
                        minlength: 5,
                        maxlength: 12,
                        notalpha: true
                        
                    },"shipping-phone":{
                        required: true,
                        phoneno: true
                        
                    }
                    
                },messages :{
                    "membership-email":{
                         emailValidation:"Please enter a valid email"
                    },
                     "shipping-name":{
                       
                         fullName: 'Please enter full name'
                        
                    },"shipping-postal_code":{
                        
                        notalpha: 'Enter a valid postal code'
                      
                    },"shipping-phone":{
                        
                          phoneno: 'Enter a valid phone number'
                        
                    },"membership-name":{
                       
                        fullName: 'Please enter full name'
                        
                    },"membership-password_confirm":{
                        
                        equalTo: 'Password did not match'
                        
                    }
                }
            });
        },
        
        billingAddressForm: function($formId){
             $($formId).validate({
                rules: {
                   
                    "billing-name":{
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
                        maxlength: 12,
                        notalpha: true
                        
                    },"billing-phone":{
                        required: true,
		            	phoneno: true
                        
                    },
                },messages :{
                    
                     "billing-name":{
                       
                         fullName: 'Please enter full name'
                        
                    },"billing-phone":{
                        
                          phoneno: 'Enter a valid phone number'
                        
                    },"billing-postal_code":{
                        
                        notalpha: 'Enter a valid postal code'
                      
                    }
                }
            });
        },
        shippingAddressForm: function($formId) {
            $($formId).validate({
                rules: {
                   
                    "shipping-name":{
                        required: true,
                        maxlength: 70,
                        fullName: true
                    },"shipping-address":{
                        required: true,
                        maxlength: 150
                    },"shipping-city":{
                        required: true,
                        maxlength: 70
                        
                    },"shipping-postal_code":{
                        required: true,
                        minlength: 5,
                        maxlength: 12,
                        notalpha: true
                        
                    },"shipping-phone":{
                        required: true,
		            	phoneno: true
                        
                    }
                    
                },messages :{
                    
                     "shipping-name":{
                       
                         fullName: 'Please enter full name'
                        
                    },"shipping-phone":{
                        
                          phoneno: 'Enter a valid phone number'
                        
                    },"shipping-postal_code":{
                        
                        notalpha: 'Enter a valid postal code'
                      
                    }
                }
            });
        },
        
		registrationLifetimeForm: function($formId) {
			$($formId).validate({
		        rules: {
                     "invite_1_name": {
                        required: "#id_account-buying_club:checked",
                          fullName: true
                    },
                    "invite_1_email": {
                        required: "#id_account-buying_club:checked",
                        emailValidation: true
                    },"invite_2_name": {
                        required: "#id_account-buying_club:checked",
                         fullName: true
                    },
                    "invite_2_email": {
                        required: "#id_account-buying_club:checked",
                        emailValidation: true
                    },
                     "invite_3_name": {
                        required: "#id_account-buying_club:checked",
                          fullName: true
                    },
                    "invite_3_email": {
                        required: "#id_account-buying_club:checked",
                        emailValidation: true
                    },
                     "invite_4_name": {
                        required: "#id_account-buying_club:checked",
                          fullName: true
                    },
                    "invite_4_email": {
                        required: "#id_account-buying_club:checked",
                        emailValidation: true
                    },
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
		            	maxlength: 12,
		            	notalpha: true
                        
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
		            	maxlength: 12,
		            	notalpha: true
                        
                    },"shipping-phone":{
                        required: true,
		            	phoneno: true
                        
                    },"account-signature":{
                        required: true,
		            	maxlength: 70,
		            	containsOneOrMoreSpaces: true
                        
                    },"membership-name":{
                        required: true,
		            	maxlength: 70,
                        fullName: true
                        
                    },"membership-email":{
                        required: true,
		            	maxlength: 150,
                        emailValidation: true
                        
                    },"membership-password":{
                        required: true,
                        minlength: 8,
		            	maxlength: 50,
                        passwordValidate: true
                        
                    },"membership-password_confirm":{
                        required: true,
                        minlength: 8,
                        maxlength: 50,
                        passwordValidate: true,
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
		            	maxlength: 12,
		            	notalpha: true
                    
                    },"billing-number":{
                        required: true,
		            	maxlength: 16,
                        creditcardValidate: true
                        
                    },"billing-cvv":{
                        required: true,
		            	maxlength: 4,
		            	cvvValidate: true
                        
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
                        urlText: true,
                        validUrl: true
                    }
                },		        
		        messages: {
		        	 "account-signature": {
		        		 containsOneOrMoreSpaces: 'Signature must contain a space.'
		        	 },
                     "invite_1_name": {
                          fullName: 'Please enter full name'
                     },"invite_1_email":{
                          emailValidation:"Please enter a valid email"
                     },
                    "invite_2_name": {
                          fullName: 'Please enter full name'
                     },"invite_2_email":{
                          emailValidation:"Please enter a valid email"
                     },
                    "invite_3_name": {
                          fullName: 'Please enter full name'
                     },"invite_3_email":{
                          emailValidation:"Please enter a valid email"
                     },
                    "invite_4_name": {
                          fullName: 'Please enter full name'
                     },"invite_4_email":{
                          emailValidation:"Please enter a valid email"
                     },
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
                        urlText: "This field is required"
                    },"billing-exp_month":{
                        expiryMonth: "Please enter a valid expiration date!"
                    },"membership-email":{
                        emailValidation:"Please enter a valid email"
                    },"billing-number":{
                        creditcardValidate: "Enter a valid card number!"
                    },"billing-cvv":{
                    	cvvValidate: "Please match the requested format."
                    },"shipping-postal_code":{
                        notalpha: 'Enter a valid postal code'
                    },"mailing-postal_code":{
                    	notalpha: 'Enter a valid postal code'
                    },"billing-postal_code":{
                    	notalpha: 'Enter a valid postal code'
                    }
		        }
		    });
		},

        registrationGeneralForm: function($formId) {
            $($formId).validate({
                rules: {
                    "invite_1_name": {
                        required: "#id_account-buying_club:checked",
                         fullName: true
                    },
                    "invite_1_email": {
                        required: "#id_account-buying_club:checked",
                        emailValidation: true
                    },"invite_2_name": {
                        required: "#id_account-buying_club:checked",
                         fullName: true
                    },
                    "invite_2_email": {
                        required: "#id_account-buying_club:checked",
                        emailValidation: true
                    },
                     "invite_3_name": {
                        required: "#id_account-buying_club:checked",
                          fullName: true
                    },
                    "invite_3_email": {
                        required: "#id_account-buying_club:checked",
                        emailValidation: true
                    },
                     "invite_4_name": {
                        required: "#id_account-buying_club:checked",
                          fullName: true
                    },
                    "invite_4_email": {
                        required: "#id_account-buying_club:checked",
                        emailValidation: true
                    },
                    "shipping-name":{
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
                        maxlength: 12,
                        notalpha: true
                        
                    },"shipping-phone":{
                        required: true,
                        phoneno: true
                        
                    },"account-signature":{
                        required: true,
                        maxlength: 70,
                        containsOneOrMoreSpaces: true
                    },"membership-name":{
                        required: true,
                        maxlength: 70,
                        fullName: true
                        
                    },"membership-email":{
                        required: true,
                        maxlength: 150,
                        emailValidation: true
                        
                    },"membership-password":{
                        required: true,
                        minlength: 8,
                        maxlength: 50,
                        passwordValidate: true
                        
                        
                    },"membership-password_confirm":{
                        required: true,
                        minlength: 8,
                        maxlength: 50,
                        passwordValidate: true,
                        equalTo: '#id_membership-password'
                        
                    },"account-url":{
                        urlText: true,
                        validUrl:true
                       
                    }
                },messages :{
                	 "account-signature": {
		        		 containsOneOrMoreSpaces: 'Signature must contain a space.'
		        	 },
                     "invite_1_name": {
                          fullName: 'Please enter full name'
                     },"invite_1_email":{
                          emailValidation:"Please enter a valid email"
                     },
                    "invite_2_name": {
                          fullName: 'Please enter full name'
                     },"invite_2_email":{
                          emailValidation:"Please enter a valid email"
                     },
                    "invite_3_name": {
                          fullName: 'Please enter full name'
                     },"invite_3_email":{
                          emailValidation:"Please enter a valid email"
                     },
                    "invite_4_name": {
                          fullName: 'Please enter full name'
                     },"invite_4_email":{
                          emailValidation:"Please enter a valid email"
                     },
                     "shipping-name":{
                       
                         fullName: 'Please enter full name'
                        
                    },"shipping-phone":{
                        
                          phoneno: 'Enter a valid phone number'
                        
                    },"membership-name":{
                       
                        fullName: 'Please enter full name'
                        
                    },"membership-password_confirm":{
                        
                        equalTo: 'Password did not match'
                        
                    },"account-url":{
                        urlText: "This field is required"
                    },"membership-email":{
                        emailValidation: "Please enter a valid email"
                    },"shipping-postal_code":{
                        notalpha: 'Enter a valid postal code'
                    }
                }
            });
        }        

	};

})(jQuery);