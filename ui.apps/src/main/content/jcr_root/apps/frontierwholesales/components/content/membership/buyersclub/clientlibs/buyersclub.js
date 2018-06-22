var $el;
var $buttonObj;
$(document).ready(function(){
    $el =$('.global-server-side-message-holder');
    getRegions();
     validation.buyersClubRegistrationForm('#buyersclub-membership-form');
    
    $(document).on('click','.btn-account-form',function(e){
         e.preventDefault();
        $buttonObj = $(this);
        buyersClubRegistration();
    });
});

function getRegions(){
     $.ajax({
        url:"/services/registration",
        method:"GET",
        dataType: "json",
        success:function(results){
            if(results !== "Error"){                
              
              results.forEach(function(key,val){ 
                  if(results[val].id == "US"){ 

                      var regions = results[val].available_regions; 
                      regions.forEach(function(key,val){ 
                          $('#id_shipping-locality').append($('<option/>',
                                                              {'data-attr-id':regions[val].id,'value':regions[val].code,'text':regions[val].name})); 
                      });
                  }

              });
            }else{
                console.log(results);
            }
        },error:function(error){
            console.log(error);
            var errorText="The site is currently unavailable and unable to process your request.  Please check back later.";
             $('.global-server-side-message-holder').css('display','block');
            $('.global-server-side-message-holder').children().text(errorText);
        }
    });
}

function buyersClubRegistration(){
    var $validFlag = $('#buyersclub-membership-form').valid();
    if($validFlag){
         disableAjaxFormButton($buttonObj); 
        callResetPassword();
    }else{
        //The site is currently unavailable and unable to process your request.  Please check back later.
        console.log('error in validation');
        enableAjaxFormButton($buttonObj);
        scrollToElement($el);
    }
}

function callResetPassword(){
    var jsonData={};
    var resetPwd={};
    jsonData['email']=$('#id_membership-email').val();
    jsonData['resetToken']='';
    jsonData['resetPasssword']='';
   
    
    var memberName = $('#id_membership-name').val();
    var memberNameSplit = memberName.split(' ');

    var shippingName = $('#id_shipping-name').val();
    var shippingNameSplit = shippingName.split(' ');

    
    var customer={};
     var pwd = $('#id_membership-password').val();
     customer['customer']={};
    
    var customerJsonData={};
    customerJsonData['email']=$('#current-email-id').val();
    customerJsonData['firstname'] = memberNameSplit[0];
    customerJsonData['lastname']=memberNameSplit[1];
    customerJsonData['website_id']=1;
    customer['customer']=customerJsonData;
    
    var address={};
    address['address']={};
    var addressJsonData={};
    addressJsonData['id']=0;
    addressJsonData['customer_id']=$('#customer-id').val();
    
    
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
    addressJsonData['telephone']=$('#id_shipping-phone').val();
    addressJsonData['company']='';
    addressJsonData['fax']='';
    addressJsonData['prefix']='';
    addressJsonData['suffic']='';
    addressJsonData['vat_id']=''
    
    address['address']=addressJsonData;
    
    
   
    var pwd = $('#id_membership-password').val();
    $.ajax({
        url: "/services/registration",
        method: "POST",
        data: {resetPwd:JSON.stringify(jsonData),action:'buyersClub',customer:JSON.stringify(customer),address:JSON.stringify(address)},
         headers:{

                'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8',
                'Authorization':'Basic '+btoa(pwd)
            }, success:function(data){
            
            var object = JSON.parse(data);             
            
               window.location.href=getRedirectPath();
          
        },error:function(error){
            console.log(error);
            
           
            var errorText="The site is currently unavailable and unable to process your request.  Please check back later.";
            
            $('.global-server-side-message-holder').css('display','block');
            $('.global-server-side-message-holder').children().text(errorText);
            scrollToElement($el);
        }
    })
    
}