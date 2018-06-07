$(document).ready(function(){
    console.log('loading payment-info...')
    showExpYear();
    getRegions();
    
    $(document).on('click','.btn-cart-checkout',function(e){
        e.preventDefault();
        submitPayment();
    })
});

function showExpYear(){
   var date = new Date().getFullYear();
    var length = date + 10;
    for(var i=date;i<length;i++){  
        $('#id_billing-exp_year').append($('<option/>',{'value':i,'text':i} )); 
    }
}

function submitPayment(){
    var jsonData={};
    var billingAddress={};
    var nameSplit=$('#id_billing-name').val().split(' ');
    
    var street=[];
    street.push($('#id_billing-address').val());
    street.push($('#id_billing-address2').val());
    
     billingAddress['city']=$('#id_billing-city').val();
    billingAddress['email']=$('#EmailId').val();
    billingAddress['country_id']='US';
    billingAddress['firstname']=nameSplit[0];
    billingAddress['lastname']=nameSplit[1];
    billingAddress['postcode']=$('#id_billing-postal_code').val();
    billingAddress['region']=$('#id_billing-locality option:selected').text();
    billingAddress['region_code']=$('#id_billing-locality option:selected').val();
    billingAddress['region_id']=$('#id_billing-locality option:selected').attr('data-attr-id');;
    billingAddress['same_as_billing']=0;
    billingAddress['save_in_address_book']=0;
    billingAddress['street']=street;
    billingAddress['telephone']=$('#id_billing-phone').val();
    
    var paymentMethod={};
    paymentMethod['method']='checkmo';
    
    var paymentJson={};
    paymentJson['billing_address']=billingAddress;
    
    paymentJson['paymentMethod']=paymentMethod;
    paymentJson['email']=$('#EmailId').val();
    
    jsonData['payment']=JSON.stringify(paymentJson);
    
    $.post("/services/payment",jsonData,function(){
        
    }).done(function(results){
        if(results !== "Error" && results !== null){
           window.location=Granite.HTTP.externalize("/content/frontierwholesales/en/confirmation.html?confirmationNr="+results);
        }else{
            $('.global-server-side-message-holder').css('display','block');
        }
    });
}

function getRegions(){
     $.ajax({
        url:"/services/registration",
        method:"GET",
        success:function(results){
            var usRegions = JSON.parse(results);
          usRegions.forEach(function(key,val){ 
              if(usRegions[val].id == "US"){ 
                  
                  var regions = usRegions[val].available_regions; 
                  regions.forEach(function(key,val){ 
                      $('#id_billing-locality').append($('<option/>',
                                                          {'data-attr-id':regions[val].id,'value':regions[val].code,'text':regions[val].name})); 
                  });
              }
          });
        },error:function(error){
            console.log(error);
        }
    });
}