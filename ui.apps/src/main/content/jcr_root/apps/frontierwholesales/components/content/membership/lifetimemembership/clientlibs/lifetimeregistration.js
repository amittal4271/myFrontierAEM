var emailWithId={};
emailWithId['list']=[];
var $buttonObj;
var $el;
$(document).ready(function(){
    
   console.log(' lifetime registration...'); 
    
    $.ajax({
        url:"/services/registration",
        method:"GET",
        dataType: "json",
        success:function(usRegions){
           
          usRegions.forEach(function(key,val){ 
              if(usRegions[val].id == "US"){ 
                  
                  var regions = usRegions[val].available_regions; 
                  regions.forEach(function(key,val){ 
                      $('#id_mailing-locality').append($('<option/>',
                                                          {'data-attr-id':regions[val].id,'value':regions[val].code,'text':regions[val].name})); 
                      $('#id_shipping-locality').append($('<option/>',
                                                          {'data-attr-id':regions[val].id,'value':regions[val].code,'text':regions[val].name})); 
                      $('#id_billing-locality').append($('<option/>',
                                                          {'data-attr-id':regions[val].id,'value':regions[val].code,'text':regions[val].name}));
                  });
              }
          });
        },error:function(error){
            console.log(error);
             var errorText="The site is currently unavailable and unable to process your request.  Please check back later.";
             $('.global-server-side-message-holder').css('display','block');
            $('.global-server-side-message-holder').children().text(errorText);
        }
    })
    
    validation.registrationLifetimeForm('#lifetime-membership-form');
    showCurrentMonth();
    showExpYear();
    
    $('#btn-lifetime-registration').on('click',function(e){
        e.preventDefault();
        $buttonObj = $(this);
        $el = $('.global-server-side-message-holder');
         $validFlag = $("#lifetime-membership-form").valid();
          emailWithId['list']=[];
        if($validFlag){
             disableAjaxFormButton($buttonObj); 
            collectUserDetails(); 
        }else{
            console.log('error...');
              $('.global-server-side-message-holder').css('display','block');
             enableAjaxFormButton($buttonObj);
             scrollToElement($el);
        }
    });

    $('#id_shipping-shipping_same').on('change',function(e){
        e.preventDefault();
        var $this = $(this);
        setTaxAddressToBillingAddress($this);
    });
});

function showExpYear(){
   var date = new Date().getFullYear();
    var length = date + 10;
    for(var i=date;i<length;i++){  
        $('#id_billing-exp_year').append($('<option/>',{'value':i,'text':i} )); 
    }
}

function showCurrentMonth(){
    var date = new Date();
    var currentMonth = date.getMonth();

    $('#id_billing-exp_month').val(currentMonth+1);
}

function setTaxAddressToBillingAddress($this){
    if ($this.is(':checked')) {
           var stateVal = $('#id_mailing-locality option:selected').val();
            $('#id_shipping-name').val($('#id_mailing-name').val());
            $('#id_shipping-company').val($('#id_account-company_name').val());
            $('#id_shipping-address').val($('#id_mailing-address').val());
            $('#id_shipping-address2').val($('#id_mailing-address2').val());
            $('#id_shipping-city').val($('#id_mailing-city').val());
            $('#id_shipping-locality option[value='+stateVal+']').prop('selected',true);
            $('#id_shipping-postal_code').val($('#id_mailing-postal_code').val());
        }else{
            $('#id_shipping-name').val('');
            $('#id_shipping-company').val('');
            $('#id_shipping-address').val('');
            $('#id_shipping-address2').val('');
            $('#id_shipping-city').val('');
            $('#id_shipping-locality').prop('selectedIndex',0);
            $('#id_shipping-postal_code').val('');
        }
}

function collectUserDetails(){
    //hide top level error
      $('.global-server-side-message-holder').css('display','none');
    var customer={};
     var pwd = $('#id_membership-password').val();
    var memberName = $('#id_membership-name').val();
    var memberNameSplit = memberName.split(' ');
    
    var shippingName = $('#id_shipping-name').val();
    var shippingNameSplit = shippingName.split(' ');
    
    var web_retail = $('.radio-checkbox-section-holder input:radio:checked').attr('id');
    var url=$('#id_account-url').val();
    if(undefined !== web_retail){
        web_retail = web_retail.substr(web_retail.length-1,web_retail.length);
       
    }else{
        web_retail='';
    }
    
     customer['customer']={};
    // customer json data
    var customerJsonData={};
    customerJsonData['addresses']=[];
    customerJsonData['email'] = $('#id_membership-email').val();
    customerJsonData['firstname'] =memberNameSplit[0] ;
    customerJsonData['lastname']=memberNameSplit[1];
    customerJsonData['website_id']='1';
   
    var addressData=[];
   
    var address={};
    address['region']={};
    address['street']=[];
    
    address['defaultShipping']='false';
    address['defaultBilling']='true';
    address['firstname']=shippingNameSplit[0];
    address['lastname']=shippingNameSplit[1];
    address['postcode']=$('#id_shipping-postal_code').val();
    address['city']=$('#id_shipping-city').val();
    address['telephone']=$('#id_shipping-phone').val();
    address['countryId']="US";
    
   
   
    var regionData={};
    regionData['regionCode']=$('#id_shipping-locality option:selected').val();
    regionData['regionId']=$('#id_shipping-locality option:selected').attr('data-attr-id');
    regionData['region']=$('#id_shipping-locality option:selected').text();
    address['region']=regionData;
    var streetData=[];
    
    streetData.push($('#id_shipping-address').val());
    streetData.push($('#id_shipping-address2').val());
    address['street']=streetData;
    
    addressData.push(address);
    customerJsonData['addresses']=addressData;
    
    customer['customer']=customerJsonData;
    console.log("consumer data "+customer);
    
    var company={};
    company['company']={};
    
    var companyJsonData={};
    companyJsonData['status']=1;
    companyJsonData['company_name']=$('#id_account-company_name').val();
    companyJsonData['legal_name']=$('#id_mailing-name').val();
    companyJsonData['company_email']=$('#id_membership-email').val();
    // customer id will be passed after customer service call. This will happen in servlet class side.
    companyJsonData['super_user_id']='';
    companyJsonData['city']=$('#id_mailing-city').val();
    companyJsonData['country_id']='US';
    companyJsonData['region']=$('#id_mailing-locality option:selected').text();
    companyJsonData['region_id']=$('#id_mailing-locality option:selected').attr('data-attr-id');
    
    companyJsonData['postcode']=$('#id_mailing-postal_code').val();
    companyJsonData['telephone']=$('#id_shipping-phone').val();
    // 10 is for lifetime member
    companyJsonData['customer_group_id']='10';
    
   
    companyJsonData['street']=[];
    companyJsonData['street']=streetData;
    
    var taxInfo={};
    taxInfo['tax_fullname']=$('#id_mailing-name').val();
    taxInfo['tax_companyname']=$('#id_account-company_name').val();
    taxInfo['tax_payerid']=$('#id_account-taxpayer_id').val();
    var businessType={};
    businessType['business_type']=web_retail;
    businessType['web_address']=url;
    var card_details={};
    card_details['full_name']=$('#id_billing-name').val();
    card_details['company']=$('#id_billing-company').val();
    card_details['address_line_one']=$('#id_billing-address').val();
    card_details['address_line_two']=$('#id_billing-address2').val();
    card_details['city']=$('#id_billing-city').val();
    card_details['state']=$('#id_billing-locality option:selected').text();
    card_details['zip']=$('#id_billing-postal_code').val();
    card_details['country']='US'
    card_details['credit_card_number']=$('#id_billing-number').val();
    card_details['cvv_code']=$('#id_billing-cvv').val();
    card_details['expiration_month']=$('#id_billing-exp_month option:selected').text();
    card_details['expiration_year']=$('#id_billing-exp_year option:selected').text();
    var extensionAttributes={};
    extensionAttributes['tax_info']={};
    extensionAttributes['business_type']={};
    extensionAttributes['card_details']={};
    companyJsonData['extension_attributes']={};
    
    extensionAttributes['tax_info']=taxInfo;
    extensionAttributes['business_type']=businessType;
    extensionAttributes['card_details']=card_details;
    
    extensionAttributes['buying_groups'] = getBuyersClubDetails();
    
    companyJsonData['extension_attributes']=extensionAttributes;
    company['company']=companyJsonData;
    
    console.log('company details '+company);
   
    
    validateEmailAndRegisterUser(extensionAttributes,customer,company,pwd);  
}


function validateEmailAndRegisterUser(extensionAttributes,customer,company,pwd){
      var emailReq = emailValidation(extensionAttributes);
      emailReq.done(function(data){
      
       
            var bValid = true;
            // true means email doesn't exists
            $(data).each(function(i,data){ 
                if (data.status == false){
                    bValid = false;
                    
                    var id = getEmailInputIdFromObj(data.email);
                  
                   $('#'+id).after($('<span/>',{'class':'validate-error','text':'Email address already has an account'}));
                   
                   
                }
                
            });
        
        if(!bValid){
            console.log('error ');
             enableAjaxFormButton($buttonObj);  
             $('.global-server-side-message-holder').css('display','block');
             scrollToElement($el);
        }else{
            memberRegistration(customer,company,pwd);
        }
           
            
       
    });
    
}

function getBuyersClubDetails(){
    var jsonData={};
    jsonData['first_name']=[];
    jsonData['last_name']=[];
    jsonData['email']=[];
    
    var firstName = new Array();
    var lastName = new Array();
    var email = new Array();
     var checked = $('#id_account-buying_club').is(":checked");
    if(checked){
    $('#buyer-club-group-holder').find('input').each(function(i,data) { 
      
        if(data.id.endsWith('email')){
            email.push(data.value);
            var jsonData={};
            jsonData['emailid']=data.id;
            jsonData['value']=data.value;
            emailWithId['list'].push(jsonData);
        }else{
             var name = data.value;
             var nameSplit = name.split(' ');       
       
            firstName.push(nameSplit[0]);
            lastName.push(nameSplit[1]);
        }
        
    });
    }
     jsonData['first_name']=firstName;
    jsonData['last_name']=lastName;
    jsonData['email']=email;
    
    return jsonData;
    
}

function memberRegistration(customer,company,pwd){
     $.ajax({
        url:"/services/registration",
        data:{customer:JSON.stringify(customer),company:JSON.stringify(company),action:'registration'},
        method: "POST",
         headers:{

                'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8',
                'Authorization':'Basic '+btoa(pwd)
            },
        success:function(data){
            console.log(data);
            window.location.href=getRedirectPath();

        },error:function(error){
            console.log(error);
             enableAjaxFormButton($buttonObj);  
             $('.global-server-side-message-holder').css('display','block');
             scrollToElement($el);
        }
    });
}


function emailValidation(jsonBuyersEmailList){
    var serverurl = $('#serverurl').val();
    var validation = false;
    var jsonData={};
    jsonData['customerEmail']=[]
      
   $(jsonBuyersEmailList.buying_groups.email).each(function(i,data){
      if(undefined !== data && data !== ''){
        jsonData['customerEmail'].push(data);  
      } 
   });
    
    jsonData['customerEmail'].push($('#id_membership-email').val());
    jsonData['websiteId']=1;
    var emailData={};
    emailData['emailid']='id_membership-email';
    emailData['value']=$('#id_membership-email').val();
    emailWithId['list'].push(emailData);
    
    var emailRequest= $.ajax({
       url:serverurl+"/rest/all/V1/buyingroups/areEmailsAvailable",
       method:"POST",
        crossDomain: "true",
        headers:{
            "content-Type":"application/json",
            "Access-Control-Allow-Origin":serverurl,
            "Access-Control-Allow-Credentials":"true"
        },
       data:JSON.stringify(jsonData)
    });
    
    var success = function(response){
        console.log('success '+response);
        
    };
    
    var failure = function (error){
      console.log('error '+error);  
       
    };
   return emailRequest;
  
}

