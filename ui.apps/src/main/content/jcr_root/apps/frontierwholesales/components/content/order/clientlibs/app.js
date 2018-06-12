var app = angular.module('frontierApp', ['ngRoute']);

app.config(function($routeProvider,$locationProvider) {
    console.log("router ");
  $routeProvider

  .when('/order', {
      templateUrl : '/orderhome.html',controller:'OrderController',
      resolve:{
          orderData : ['$route', 'OrderDataService', function ($route, orderDataService) { 
                              return orderDataService.getCustomer(); }] 
      }

  })

  .when('/shippinghome', {
    templateUrl : '/shippinghome.html',
    controller  : 'ShippingController',
	resolve:{
          shippingData : ['$route', 'ShippingDataService', function ($route, shippingDataService) { 
              return shippingDataService.getCustomer();}] 
      }
  })

  .when('/paymenthome', {
    templateUrl : '/paymenthome.html',
    controller  : 'PaymentController',
	resolve:{
          paymentData : ['$route', 'PaymentDataService', function ($route, paymentDataService) { 
              return paymentDataService.getShippingInfo();}] 
      }
  })

  .when('/confirmation', {
    templateUrl : '/confirmation.html',
	controller  : 'ConfirmationController'


  })


  .otherwise({redirectTo: '/order'});
    
    $locationProvider.html5Mode(true);

});






