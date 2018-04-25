$(document).ready(function(){
    
   console.log('user registration...'); 
    
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
    })
    
    $('#btn-general-registration').on('click',function(e){
        e.preventDefault();
       collectUserDetails(); 
    });
});

function requiredField(obj,className,msg){
   if(undefined === obj || obj.trim().length == 0){
       assignError(className,msg);
       return false;
   } 
    return true;
}

function fullNameValidation(obj,className,msg){
    if(undefined !== obj && obj.trim().length > 0 && obj.trim().indexOf(' ') == -1){
        assignError(className,msg);
        return false;
    }
    return true;
}

function validateFields(){
    var bReturn = true;
    
    var shippingName = $('#id_shipping-name').val();
    if(!requiredField(shippingName,'shipping-name-text','This field is required.')){
        bReturn = false;
    }
    
    if(!fullNameValidation(shippingName,'shipping-name-text','Please enter full name')){
        bReturn = false;
    }
    
    
    var companyName=$('#id_shipping-company').val();
    
     if(!requiredField(companyName,'company-text','This field is required.')){
        bReturn = false;
    }
    
   
    
    var address = $('#id_shipping-address').val();
    if(!requiredField(address,'address1-text','This field is required.')){
        bReturn = false;
    }
   
    var city = $('#id_shipping-city').val();
      
    if(!requiredField(city,'city-text','This field is required.')){
        bReturn = false;
    }
   
    
    var postalCode = $('#id_shipping-postal_code').val();
    
    if(!requiredField(postalCode,'zip-text','This field is required.')){
        bReturn = false;
    }
    
    
    
    var phone = $('#id_shipping-phone').val();
    
     if(!requiredField(phone,'phone-text','This field is required.')){
        bReturn = false;
    }
     
    
    if(!validatePhonenumber(phone)){
        assignError('phone-text','Enter a valid phone number');
         bReturn = false;
    }
    
    var name =  $('#id_membership-name').val();
    
    
     if(!requiredField(name,'name-text','Name is required.')){
        bReturn = false;
    }
     
    
    
    if(undefined !== name && name.trim().length > 0 && name.trim().indexOf(' ') == -1){
         $('.name-text').css('display','block');
        $('.name-text').html('Please enter full name');
         bReturn = false;
    }
    
    var email = $('#id_membership-email').val()
    if(!validateEmail(email)){
        $('.email-text').css('display','block');
        $('.email-text').html('Enter a valid email address.');
         bReturn = false;
        
    }
   
     var pwd = $('#id_membership-password').val();
    
    var confirmPwd = $('#id_membership-password_confirm').val();
    
    if(!requiredField(pwd,'password-text','This field is required.')){
        bReturn = false;
    }
    
   
    
    if(undefined !== pwd && (pwd.trim().length > 0 && pwd.trim().length < 8)){
         $('.password-text').css('display','block');
        $('.password-text').html('Ensure this value has at least 8 characters (it has 7).');
         bReturn = false;
    }
    
   
    
    if(pwd !== confirmPwd){
        assignError('password-text','Password did not match.');
       // $('.password-text').css('display','block');
        //$('.password-text').html('Passwords did not match.')
         bReturn = false;
       
    }
    return bReturn;
    
}

function assignError(className,errorMsg){
    $('.'+className).css('display','block');
     $('.'+className).html(errorMsg);
}

function validateEmail(mail) 
{
    if(undefined !== mail && mail.trim().length > 0){
  var emailFormat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
    if(mail.match(emailFormat)){
        return true;
    }else{
        return false;
    }
    }else{
        return false;
    }
  
}

function validatePhonenumber(inputtxt)
{
    if(undefined !== inputtxt && inputtxt.trim().length > 0){
     var phoneno = /^\d{10}$/;
    
    if(phoneno.test(inputtxt)){
        return true;
    }else{
        return false;
    }
    }else{
        return false;
    }
}

function clearFields(){
    $('.name-text').css('display','none');
     $('.email-text').css('display','none');
     $('.password-text').css('display','none');
    $('.shipping-name-text').css('display','none');
    $('.company-text').css('display','none');
   $('.address1-text').css('display','none');
    $('.city-text').css('display','none');
   $('.zip-text').css('display','none');
   $('.phone-text').css('display','none');
}
function collectUserDetails(){
   
  
    var customer={};
     var pwd = $('#id_membership-password').val();
     customer['customer']={};
    // customer json data
    var customerJsonData={};
    customerJsonData['addresses']=[];
    customerJsonData['email'] = $('#id_membership-email').val();
    customerJsonData['firstname'] = $('#id_membership-name').val();
    customerJsonData['lastname']='last name';
    customerJsonData['website_id']='1';
   
    var addressData=[];
    addressData['region']={};
    addressData['street']=[];
    addressData.push('defaultShipping','true');
    addressData.push('defaultBilling','true');
    addressData.push('firstname',$('#id_shipping-name').val());
    addressData.push('lastname',' ');
    addressData.push('postcode',$('#id_shipping-postal_code').val());
    addressData.push('city',$('#id_shipping-city').val());
    addressData.push('telephone',$('#id_shipping-phone').val());
    addressData.push('countryId',"US");
   
    var regionData={};
    regionData['regionCode']='NJ';
    regionData['regionId']='32';
    regionData['region']='New Jersey';
    addressData.push('region',regionData);
    var streetData=[];
    
    streetData.push($('#id_shipping-address').val());
    streetData.push($('#id_shipping-address2').val());
    addressData.push('street',streetData);
    
    customerJsonData['addresses'].push(addressData);
    
    customer['customer']=customerJsonData;
    console.log("consumer data "+customer);
   // if(validateFields()){
        
    
    $.ajax({
        url:"/services/registration",
        data:{customer:JSON.stringify(customer)},
        method: "POST",
         headers:{

                'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8',
                'Authorization':'Basic '+btoa(pwd)
            },
        success:function(data){
            console.log(data);
        },error:function(error){
            console.log(error);
        }
    });
   // }else{
     //   console.log("error in validation");
    //}
    
   
    
}
