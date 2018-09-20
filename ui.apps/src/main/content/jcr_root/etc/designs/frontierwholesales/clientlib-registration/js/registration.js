var emailWithId={};
emailWithId['list']=[];
var $buttonObj;
var $el;
$(document).ready(function(){
  
    loadRegions('id_shipping-locality');

     validation.registrationGeneralForm('#general-membership-form');
    
     $(document).on('click','.other-than-buying-club-input',function(e){ 
              
        var val = $(this).val();
        $('#webaddress-data').val('');
        if(val == 'web'){
            var visible = $('#webaddress-data').is(':visible');
            if(!visible){
                $('#webaddress-data').css('display','block');
                
            }
        }else{
             $('#webaddress-data').css('display','none');
        }
    });
    
    $('#btn-general-registration').on('click',function(e){
        e.preventDefault();
        $buttonObj = $(this);
       
         $validFlag = $("#general-membership-form").valid();
        
        var buyerClubChecked = $('#id_account-buying_club').is(":checked");
        var btSelected = $('.radio-checkbox-section-holder input:radio:checked').attr('id');
        var bCheck = false;
        if(!buyerClubChecked && undefined == btSelected){
              bCheck=false;
             
             $('.alert-danger').html("Please select one of the business type");
           
        }else{
           
            if($validFlag){
                  disableAjaxFormButton($buttonObj);            
                 emailWithId['list']=[];
                 if(findDuplicateEmailId()){
                    collectUserDetails(); 
                     bCheck = true;
                 }else{
                     bCheck=false;
                     $('.alert-danger').html(" There is an error with your application. Please fix the field(s) with the red error message.");
                 }
            }else{
                console.log('error in validation');
                bCheck = false;
                  $('.alert-danger').html("There is an error with your application. Please fix the field(s) with the red error message.");
             
            }
        }
        
        if(!bCheck){
            $('.global-server-side-message-holder').css('display','block');           
            $el = $('.global-server-side-message-holder');
            enableAjaxFormButton($buttonObj);
            scrollToElement($el);
        }
    });
    
    
    $('#id_membership-email').on('onchange',function(e){
        e.preventDefault();
        emailValidation();
    });
   
});



function findDuplicateEmailId(){
    var arr=[];
    var bReturn = true;
    var checked = $('#id_account-buying_club').is(":checked");
    if(checked){
    $('#buyer-club-group-holder input').each(function(idx,data){ 
        var id = data.id; 
        if(id.endsWith('email')){ 
            
            var $sameValue = data.value;  
            if (arr.indexOf($sameValue) == -1){
                arr.push($sameValue);
            }
            else {
                if($('#'+data.id+'-error').length > 0){
                    $('#'+data.id+'-error').remove();
                }
                $('#'+data.id).after("<span id='"+ data.id+"'-error class='validate-error'>Duplicate email id</span>")
               
                bReturn = false;
            }
        } 
    });
    }
    return bReturn;
}


function collectUserDetails(){
    //hide top level error
    $('.global-server-side-message-holder').css('display','none');
    var memberName = $('#id_membership-name').val();
    var memberNameSplit = memberName.split(' ');

     var shippingName = $('#id_shipping-name').val();
    var shippingNameSplit = shippingName.split(' ');
    var buyerClubType='';
    if($('#id_account-buying_club').is(":checked")){
         buyerClubType = $('#id_account-buying_club').data('buyerclub');
    }else{
        buyerClubType = $('.radio-checkbox-section-holder input:radio:checked').attr('id');
       
    }
    
     var url=$('#id_account-url').val();
        if(buyerClubType !== ''){
            buyerClubType = buyerClubType.substr(buyerClubType.length-2,buyerClubType.length);

        }
  
    var customer={};
    var jsonData={};
     var pwd = $('#id_membership-password').val();
     customer['customer']={};
    // customer json data
    var customerJsonData={};
    customerJsonData['addresses']=[];
    customerJsonData['email'] = $('#id_membership-email').val();
    customerJsonData['firstname'] = memberName.substr(0,memberName.indexOf(' '));
    customerJsonData['lastname']=memberName.substr(memberName.indexOf(' ')+1);
    customerJsonData['website_id']='1';
   
    var addressData=[];
    
    var address={};
    address['region']={};
    address['street']=[];
    
    
   // address['defaultShipping']='false';
    //address['defaultBilling']='true';
    address['firstname']=shippingName.substr(0,shippingName.indexOf(' '));
    address['lastname']=shippingName.substr(shippingName.indexOf(' ')+1);
    address['postcode']=$('#id_shipping-postal_code').val();
    address['city']=$('#id_shipping-city').val();
    address['telephone']=$('#id_shipping-phone').val().replace(/-/g, "");
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
    
    jsonData['customer']=customerJsonData;
   

    var companyJsonData={};
 
    companyJsonData['company_name']=$('#id_shipping-company').val();
   
    companyJsonData['city']=$('#id_shipping-city').val();
    companyJsonData['country_id']='US';
    companyJsonData['region']=$('#id_shipping-locality option:selected').text();
    companyJsonData['region_id']=$('#id_shipping-locality option:selected').attr('data-attr-id');
    
    companyJsonData['postcode']=$('#id_shipping-postal_code').val();
    companyJsonData['telephone']=$('#id_shipping-phone').val().replace(/-/g, "");
   
    companyJsonData['street']=[];
    companyJsonData['street']=streetData;
    
    var businessType={};
     businessType['business_type']=buyerClubType;
    businessType['web_address']=url;
    jsonData['businessType']=businessType;
    jsonData['signature']=$('#id_account-signature').val();
    jsonData['password']='';
    
  
    jsonData['company']=companyJsonData;
    var buyersClub = getBuyersClubDetails();
    if(buyersClub.email.length > 0){
     jsonData['buyingGroups'] = buyersClub;
    }
    
    console.log('company details '+jsonData);  
    userRegistrationService(jsonData,$('#id_membership-password').val());
  
}

function validateEmailAndRegisterUser(jsonBuyersEmailList,customer,company,pwd){
    
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
     
    
    Frontier.MagentoServices.emailValidation(serverurl,jsonData).done(function(data){
          var bValid = true;
       if($.isArray(data)){
                
                // true means email doesn't exists
                $(data).each(function(i,data){ 
                    if (data.status == false){
                        bValid = false;

                        var id = getEmailInputIdFromObj(data.email);

                       $('#'+id).after($('<span/>',{'class':'validate-error','text':'Email address already has an account'}));


                    }

                });

            if(bValid){
                userRegistrationService(customer,company,pwd);
            }
       }else{
           bValid=false;
          
       }         
            
       if(!bValid){
           enableAjaxFormButton($buttonObj);    
           enableErrorMsg(data);
       }
    }).fail(function(error){
          
       enableAjaxFormButton($buttonObj);    
           enableErrorMsg(error);
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

function userRegistrationService(jsonData,pwd){
    var email =  $('#id_membership-email').val();
    
     $.ajax({
        url:"/services/registration",
        data:{registration:JSON.stringify(jsonData),action:'registration',email: email},
        method: "POST",
         headers:{

                'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8',
                'Authorization':'Basic '+btoa(pwd)
            },beforeSend:function(xhr){
                xhr.overrideMimeType('application/json');
            }
     }).done(function(data){
         if(undefined == data.Fail){
            if(addCustomerDataToCookie(data)){

                window.location.href=getRedirectPath();
            }else{
                console.log("error in storing cookie values");
                enableAjaxFormButton($buttonObj); 
            }
         }else{
             enableAjaxFormButton($buttonObj);  
            showProdErrorMessage(data.Fail);   
         }
     }).fail(function(error){
            enableAjaxFormButton($buttonObj);         
            enableErrorMsg(error.status);
          
           
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
      var d = $.Deferred();
   
    
    Frontier.MagentoServices.emailValidation(serverurl,jsonData).done(function(response){
         d.resolve(response);
    }).fail(function(error){
          
        d.resolve(error);
    });
    
   
   return d.promise();
  
}
