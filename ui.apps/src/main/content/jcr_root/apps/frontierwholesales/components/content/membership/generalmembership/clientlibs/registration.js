var emailWithId={};
emailWithId['list']=[];
var $buttonObj;
var $el;
$(document).ready(function(){
    
   console.log('user registration...'+$('#serverurl').val()); 
    
    $.ajax({
        url:"/services/registration",
        method:"GET",
        success:function(results){
            var usRegions = JSON.parse(results);
          usRegions.forEach(function(key,val){ 
              if(usRegions[val].id == "US"){ 
                  
                  var regions = usRegions[val].available_regions; 
                  regions.forEach(function(key,val){ 
                      $('#id_shipping-locality').append($('<option/>',
                                                          {'data-attr-id':regions[val].id,'value':regions[val].code,'text':regions[val].name})); 
                  });
              }
          });
        },error:function(error){
            console.log(error);
        }
    });

     validation.registrationGeneralForm('#general-membership-form');
    
    $('#btn-general-registration').on('click',function(e){
        e.preventDefault();
        $buttonObj = $(this);
        $el = $('.global-server-side-message-holder');
         $validFlag = $("#general-membership-form").valid();
         if($validFlag){
              disableAjaxFormButton($buttonObj);            
             emailWithId['list']=[];
          collectUserDetails(); 
        }else{
          console.log('error in validation');
          $('.global-server-side-message-holder').css('display','block');
            enableAjaxFormButton($buttonObj);
            scrollToElement($el);
        }
    });
    
    
    $('#id_membership-email').on('onchange',function(e){
        e.preventDefault();
        emailValidation();
    });
});






function collectUserDetails(){
    //hide top level error
    $('.global-server-side-message-holder').css('display','none');
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
  
    var customer={};
     var pwd = $('#id_membership-password').val();
     customer['customer']={};
    // customer json data
    var customerJsonData={};
    customerJsonData['addresses']=[];
    customerJsonData['email'] = $('#id_membership-email').val();
    customerJsonData['firstname'] = memberNameSplit[0];
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
    companyJsonData['company_name']=$('#id_shipping-company').val();
    companyJsonData['legal_name']=$('#id_mailing-name').val();
    companyJsonData['company_email']=$('#id_membership-email').val();
    // customer id will be passed after customer service call. This will happen in servlet class side.
    companyJsonData['super_user_id']=''
    companyJsonData['city']=$('#id_shipping-city').val();
    companyJsonData['country_id']='US';
    companyJsonData['region']=$('#id_shipping-locality option:selected').text();
    companyJsonData['region_id']=$('#id_shipping-locality option:selected').attr('data-attr-id');
    
    companyJsonData['postcode']=$('#id_shipping-postal_code').val();
    companyJsonData['telephone']=$('#id_shipping-phone').val();
    // 8 is for non member
    companyJsonData['customer_group_id']='8';
    
   
    companyJsonData['street']=[];
    companyJsonData['street']=streetData;
    
    var taxInfo={};
    taxInfo['tax_fullname']='';
    taxInfo['tax_companyname']='';
    taxInfo['tax_payerid']='';
    var businessType={};
    businessType['business_type']=web_retail;
    businessType['web_address']=url;
    var card_details={};
    card_details['full_name']='';
    card_details['company']='';
    card_details['address_line_one']='';
    card_details['address_line_two']='';
    card_details['city']='';
    card_details['state']='';
    card_details['zip']='';
    card_details['country']='US'
    card_details['credit_card_number']='';
    card_details['cvv_code']='';
    card_details['expiration_month']='';
    card_details['expiration_year']='';
    var extensionAttributes={};
    extensionAttributes['tax_info']={};
    extensionAttributes['business_type']={};
    extensionAttributes['card_details']={};
    extensionAttributes['buying_groups']={};
    
    
    extensionAttributes['buying_groups'] = getBuyersClubDetails();
    
    
    companyJsonData['extension_attributes']={};
    
    extensionAttributes['tax_info']=taxInfo;
    extensionAttributes['business_type']=businessType;
    extensionAttributes['card_details']=card_details;
    
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
            userRegistrationService(customer,company,pwd);
        }
           
            
       
    });
    
}

function getEmailInputIdFromObj(email){
    var emailid='';
    $(emailWithId.list).each(function(i,obj) {
    if(obj.value === email){
         emailid = obj.emailid; 
        emailWithId.list.splice(i,1);
        return false;
    }
     });
    return emailid;
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

function userRegistrationService(customer,company,pwd){
    
     $.ajax({
        url:"/services/registration",
        data:{customer:JSON.stringify(customer),company:JSON.stringify(company)},
        method: "POST",
         headers:{

                'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8',
                'Authorization':'Basic '+btoa(pwd)
            },
        success:function(data){
            
            var object = JSON.parse(data);             
            
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
