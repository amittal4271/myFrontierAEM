$(document).ready(function(){
    console.log('shipping methods...');
    // Get US States and fill drop down values
    getRegions();
    
    var carrierCode="";
    var methodCode="";
    $(document).on('click','.delivery-methods',function(){
        var $this = $(this);
        deliveryMethodsCalc($this);
        carrierCode = $this.data('carriercode');
        methodCode = $this.data('methodcode');
    });
    validation.shippingAddressForm('#shipping-address-form');
    $(document).on('click','.btn-cart-checkout',function(e){
        e.preventDefault();
        if(carrierCode !=='' && methodCode !== '' ){
           
         if($("#shipping-address-form").valid()){
             $('#global-server-side-message-holder').css('display','none');
           callShippingInfo(carrierCode,methodCode);
         }else{
             $('#global-server-side-message-holder').css('display','block');
         }
        }else{
             $('#global-server-side-message-holder').css('display','block');
        }
    });
});

function deliveryMethodsCalc(obj){
    var shippingPrice = obj.siblings().text().split('-');
    var price = shippingPrice[1].trim().substr(1,shippingPrice[1].length);
     price = parseFloat(price).toFixed(2);
    $('#id_shipping-amount').text("$"+price);
    
    var subtotal = $('#id_subtotal-price').text();
    subtotal = parseFloat(subtotal.substr(1,subtotal.length)).toFixed(2);
    var shPrice = parseFloat(price.substr(1,price.length)).toFixed(2);
    var total = parseFloat(subtotal) + parseFloat(price);
    total = "$"+parseFloat(total).toFixed(2);
    $('#id_grand-total').text(total);
}

function callShippingInfo(carrierCode,methodCode){
    var shippingJsonData={};
      shippingJsonData['addressInformation']={};
  var jsonData ={};
    var shippingAddressJsonData={};
   var street=[];
    street.push($('#id_shipping-address').val());
    street.push($('#id_shipping-address2').val());
    var shippingAddress={};
    var nameSplit = $('#id_shipping-name').val().split(' ');
    shippingAddress['region']=$('#id_shipping-locality option:selected').text();
    shippingAddress['region_id']=$('#id_shipping-locality option:selected').attr('data-attr-id');
    shippingAddress['region_code']=$('#id_shipping-locality option:selected').val();
    shippingAddress['country_id']='US';
    shippingAddress['street']=street;
    shippingAddress['telephone']=$('#id_shipping-phone').val();
    shippingAddress['postcode']=$('#id_shipping-postal_code').val();
    shippingAddress['city']=$('#id_shipping-city').val();
    shippingAddress['firstname']=nameSplit[0];
    shippingAddress['lastname']=nameSplit[1];
    shippingAddress['email']=$('#emailId').val();
    var shippingMethods={};
    shippingMethods['shipping_method_code']=methodCode;
    shippingMethods['shipping_carrier_code']=carrierCode;
    shippingMethods['shipping_address']=shippingAddress;
  
     shippingJsonData['addressInformation']=shippingMethods;
  
   jsonData['address']=JSON.stringify(shippingJsonData);
   
    $.ajax({
        url:"/services/payment",
        method: "get",
        data:jsonData
    }).done(function(results){
        window.location=Granite.HTTP.externalize("/content/frontierwholesales/en/payment.html");
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
                      $('#id_shipping-locality').append($('<option/>',
                                                          {'data-attr-id':regions[val].id,'value':regions[val].code,'text':regions[val].name})); 
                  });
              }
          });
        },error:function(error){
            console.log(error);
        }
    });
}