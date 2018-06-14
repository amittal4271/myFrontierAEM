app.controller('PaymentController', function($scope,$location,$http,$window,$q,paymentData,FactoryJsonData) {
    $scope.shippingInfoData = paymentData;
    $scope.usRegions = FactoryJsonData.usRegions;
    $scope.shippingAddress = FactoryJsonData.shippingInfoData;
console.log("payment controller ");
 validation.billingAddressForm('#billing-address-form');

    $scope.submitForm = function(){
         $validFlag = $("#billing-address-form").valid();
		
       if($validFlag){
            $('.global-server-side-message-holder').css('display','none');
            $scope.collectPaymentDetails();
       }else{
            $('.global-server-side-message-holder').css('display','block');
           return false;
       }
    }
    $(document).on('click','.btn-payment-checkout',function(){
        $validFlag = $("#billing-address-form").valid();
		
       if($validFlag){
            $('.global-server-side-message-holder').css('display','none');
        $scope.collectPaymentDetails();
       }else{
            $('.global-server-side-message-holder').css('display','block');
       }
       
    });


     $scope.collectPaymentDetails = function(){
		 var jsonData={};
    var billingAddress={};
    var nameSplit=$('#id_billing-name').val().split(' ');
    
    var street=[];
    street.push($('#id_billing-address').val());
    street.push($('#id_billing-address2').val());

     billingAddress['city']=$('#id_billing-city').val();
    billingAddress['email']=FactoryJsonData.email;
    billingAddress['country_id']='US';
    billingAddress['firstname']=nameSplit[0];
    billingAddress['lastname']=nameSplit[1];
    billingAddress['postcode']=$('#id_billing-postal_code').val();
    billingAddress['region']=$('#id_billing-locality option:selected').text();
    billingAddress['region_code']=$('#id_billing-locality option:selected').val();
    billingAddress['region_id']=$('#id_billing-locality option:selected').attr('data-id');;
    billingAddress['same_as_billing']=0;
    billingAddress['save_in_address_book']=0;
    billingAddress['street']=street;
    billingAddress['telephone']=$('#id_billing-phone').val();
    
    var paymentMethod={};
    paymentMethod['method']='checkmo';
    
    var paymentJson={};
    paymentJson['billing_address']=billingAddress;
    
    paymentJson['paymentMethod']=paymentMethod;
    paymentJson['email']=FactoryJsonData.email;
        //FactoryJsonData.paymentJsonData = paymentJson;
        $scope.submitPayment(paymentJson);

        //$location.path("/confirmation");

    }

$scope.submitPayment = function(paymentJson){
	showLoadingScreen();
		var userToken = getUserToken();
		$http({
        url: $window.serverURL+"/rest/V1/carts/mine/payment-information",
        method: "post",
            data: paymentJson,
         headers:{
					"content-Type":"application/json",

                    "Access-Control-Allow-Origin":$window.serverURL,
            		"Access-Control-Allow-Credentials":"true",

                    'Authorization':userToken
            }
    }).then(function successCallback(response) {
        console.log(response.data); 
            localStorage.setItem("ConfirmationNr",response.data);
         $window.location.href="/content/frontierwholesales/en/confirmation.html";
      // $scope.getConfirmation(response.data);
  }, function errorCallback(response) {
    console.log('error'+response);
      hideLoadingScreen();
  });
    }

  $scope.getConfirmation=function(confirmNr){

    var adminToken=getAdminToken();
    $http({
        url: $window.serverURL+"/rest/V1/orders/"+confirmNr,
        method: "get",
         headers:{

                'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8',
                'Authorization':adminToken
            }
    }).then(function successCallback(response) {
        console.log(response.data); 
        FactoryJsonData.confirmationJsonData=response.data;
		hideLoadingScreen();
       // $location.path("/confirmation");
        $window.location.href="/content/frontierwholesales/en/confirmation.html";
  }, function errorCallback(response) {
    console.log('error'+response);
      hideLoadingScreen();
  });

}


 });