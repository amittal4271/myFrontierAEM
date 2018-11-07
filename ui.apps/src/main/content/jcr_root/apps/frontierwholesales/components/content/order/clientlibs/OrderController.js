

app.controller('OrderController', function($scope,$location,$http,$window,$q,orderData) {
   console.log("Order Controller ");
$scope.JsonData = orderData;

$scope.fixDate = function(date){
    return new Date(date);
};

    $scope.submitForm = function(){
        $location.path("/shippinghome");
    }
  
});


