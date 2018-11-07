$(document).ready(function(){
    if($('#magento-quickorder-iframe').length > 0){
        var userToken = getUserToken();
        if(userToken.startsWith('Bearer')  && userToken.trim().length > 0){
            userToken = userToken.replace('Bearer ','');
             $('#magento-quickorder-iframe').prop('src',serverURL+'/quickorder?token='+userToken);
        }else{
            location.href="/content/frontierwholesales/en/login.html";
        }
    }
   
});