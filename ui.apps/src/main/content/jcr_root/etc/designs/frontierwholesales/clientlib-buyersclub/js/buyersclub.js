var $el;
var $buttonObj;
$(document).ready(function(){
   
    loadRegions('id_shipping-locality');
     validation.buyersClubRegistrationForm('#buyersclub-membership-form');
    
    $(document).on('click','.btn-account-form',function(e){
         e.preventDefault();
        $buttonObj = $(this);
        buyersClubRegistration();
    });
   
});



function buyersClubRegistration(){
    var $validFlag = $('#buyersclub-membership-form').valid();
    if($validFlag){
         disableAjaxFormButton($buttonObj); 
        callResetPassword();
    }else{
        //The site is currently unavailable and unable to process your request.  Please check back later.
        console.log('error in validation');
        enableAjaxFormButton($buttonObj);
        $el=$('#membership-holder');
        scrollToElement($el);
    }
}

function callResetPassword(){
    var jsonData={};
    var resetPwd={};
     var memberEmailId = $('#id_membership-email').val();
    var currentEmailId = $('#current-email-id').val();
    jsonData['email']=currentEmailId;
    jsonData['resetToken']=$('#customer-token').val();
    
   jsonData['customerId']=$('#customer-id').val();
    
    var memberName = $('#id_shipping-name').val();
    var memberNameSplit = memberName.split(' ');

    var shippingName = $('#id_shipping-name').val();
    var shippingNameSplit = shippingName.split(' ');

    
    var customer={};
     var pwd = $('#id_membership-password').val();
     customer['customer']={};
    
    var customerJsonData={};
    if(memberEmailId != currentEmailId){
        currentEmailId = memberEmailId;
    }
    
    customerJsonData['email']=currentEmailId;
    customerJsonData['firstname'] = memberNameSplit[0];
    customerJsonData['lastname']=memberNameSplit[1];
    customerJsonData['website_id']=1;
    customer['customer']=customerJsonData;
    
    var address={};
    address['address']={};
    var addressJsonData={};
    addressJsonData['id']=0;
    
    
    
    var streetData=[];
    
    streetData.push($('#id_shipping-address').val());
    streetData.push($('#id_shipping-address2').val());
    
    addressJsonData['street']=streetData;
    
     var region={};
    region['region_code']=$('#id_shipping-locality option:selected').val();
    region['region']=$('#id_shipping-locality option:selected').text();
    region['region_id']=$('#id_shipping-locality option:selected').attr('data-attr-id');
    region['extension_attributes']={};
    var extnAtt = {};
    
    addressJsonData['region']=region;
    addressJsonData['region_id']=$('#id_shipping-locality option:selected').attr('data-attr-id');    
    addressJsonData['country_id']="US";    
    addressJsonData['defaultShipping']='true';
    addressJsonData['defaultBilling']='true';
    addressJsonData['firstname']=shippingNameSplit[0];
    addressJsonData['lastname']=shippingNameSplit[1];
    addressJsonData['postcode']=$('#id_shipping-postal_code').val();
    addressJsonData['city']=$('#id_shipping-city').val();
    addressJsonData['telephone']=$('#id_shipping-phone').val().replace(/-/g, "");
    addressJsonData['company']='';
    addressJsonData['fax']='';
    addressJsonData['prefix']='';
    addressJsonData['suffix']='';
    addressJsonData['vat_id']=0;
    
    address['address']=addressJsonData;
    
    
   
    var pwd = $('#id_membership-password').val();
    $.ajax({
        url: "/services/registration",
        method: "POST",
        data: {customerId: $('#customer-id').val(),
               resetToken: $('#customer-token').val(),resetPwd:JSON.stringify(jsonData),action:'buyersClub',customer:JSON.stringify(customer),address:JSON.stringify(address),email: $('#id_membership-email').val()},
         headers:{

                'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8',
                'Authorization':'Basic '+btoa(pwd)
            },beforeSend:function(xhr){
                xhr.overrideMimeType('application/json');
            }
        }).done(function(data){
         if(undefined == data.Fail){
            var address = JSON.parse(data.BuyersAddress);
            
            addBuyersClubDataToCookie(address,currentEmailId,data.UserToken);
            window.location.href=getRedirectPath();
         }else{
             enableAjaxFormButton($buttonObj);  
            showProdErrorMessage(data.Fail);   
         }
          
        }).fail(function(error){
            console.log(error);
            enableAjaxFormButton($buttonObj);
            enableErrorMsg(error.status);
        });
    
    
}