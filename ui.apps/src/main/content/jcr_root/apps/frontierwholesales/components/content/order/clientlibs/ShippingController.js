app.controller('ShippingController', function($scope,$location,$http,$window,$q,shippingData,FactoryJsonData) {

$scope.JsonData = shippingData;
     console.log("Shipping Controller "+FactoryJsonData);

    $scope.carrierCode="";
    $scope.methodCode="";
 validation.shippingAddressForm('#shipping-address-form');
   $(document).on('click','.btn-shipping',function(){
        //shipping information process
       
       
        var $validFlag = $("#shipping-address-form").valid();
		
       if($validFlag){
           $('.global-server-side-message-holder').css('display','none');
        $scope.getShippingAddress();
           
       }else{
           //enable global error log
           $('.global-server-side-message-holder').css('display','block');
       }
    });

     $(document).on('click','.delivery-methods',function(){
        var $this = $(this);
        $scope.deliveryMethodsCalc($this);
        $scope.carrierCode = $this.data('carriercode');
        $scope.methodCode = $this.data('methodcode');
    });


	$scope.deliveryMethodsCalc = function(obj){
        var shippingPrice = obj.siblings().text().split('-');
        var price = shippingPrice[1].trim().substr(1,shippingPrice[1].length);
         price = parseFloat(price).toFixed(2);
        $('#id_shipping-amount').text("$"+price);
        
        var subtotal = $('#id_subtotal-price').text();
        subtotal = parseFloat(subtotal.substr(1,subtotal.length)).toFixed(2);
        
        var taxAmount = $('#id_tax-amount').text();
        taxAmount = taxAmount.substr(1,taxAmount.length);
    
        var discount = $('#id_discount-amount').text();
        discount = discount.substr(1,discount.length);
    
        var total = parseFloat(subtotal) + parseFloat(price)+parseFloat(taxAmount)+parseFloat(discount);
        total = "$"+parseFloat(total).toFixed(2);
        $('#id_grand-total').text(total);
	}


     $scope.getShippingAddress = function(){
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
    shippingAddress['region_id']=$('#id_shipping-locality option:selected').attr('data-id');
    shippingAddress['region_code']=$('#id_shipping-locality option:selected').val();
    shippingAddress['country_id']='US';
    shippingAddress['street']=street;
    shippingAddress['telephone']=$('#id_shipping-phone').val().replace(/-/g, "");
    shippingAddress['postcode']=$('#id_shipping-postal_code').val();
    shippingAddress['city']=$('#id_shipping-city').val();
    shippingAddress['firstname']=nameSplit[0];
    shippingAddress['lastname']=nameSplit[1];
    shippingAddress['email']=FactoryJsonData.email;
    var shippingMethods={};
    shippingMethods['shipping_method_code']=$scope.methodCode;
    shippingMethods['shipping_carrier_code']=$scope.carrierCode;
    shippingMethods['shipping_address']=shippingAddress;
         shippingMethods['billing_address']=JSON.parse(FactoryJsonData.data);

     shippingJsonData['addressInformation']=shippingMethods;
	FactoryJsonData.shippingInfoData=shippingJsonData;
	$location.path("/paymenthome");
   
    }


});