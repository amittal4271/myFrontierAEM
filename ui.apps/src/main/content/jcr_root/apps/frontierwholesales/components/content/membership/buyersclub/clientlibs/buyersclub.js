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
        success:function(results){
            if(results !== "Error"){
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
    
   
    var pwd = $('#id_membership-password').val();
    $.ajax({
        url: "/services/registration",
        method: "POST",
        data: {resetPwd:JSON.stringify(jsonData),action:'buyersClub',customer:JSON.stringify(customer)},
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