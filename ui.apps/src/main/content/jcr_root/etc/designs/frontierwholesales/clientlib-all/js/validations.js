
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
    }else{
        return true;
    }
});

$.validator.addMethod("expiryMonth",function(value,element){
   var dt = new Date();
   var currentMonth =  dt.getMonth();
    if(value > currentMonth){
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
		},

        registrationGeneralForm: function($formId) {
            $($formId).validate({
                rules: {
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
                        
                    },"account-url":{
                        url: true
                    }
                },messages :{
                     "shipping-name":{
                       
                         fullName: 'Please enter full name'
                        
                    },"shipping-phone":{
                        
                          phoneno: 'Enter a valid phone number'
                        
                    },"membership-name":{
                       
                        fullName: 'Please enter full name'
                        
                    },"membership-password_confirm":{
                        
                        equalTo: 'Password did not match'
                        
                    },"account-url":{
                        url: "This field is required"
                    }
                }
            });
        }

	};

})(jQuery);