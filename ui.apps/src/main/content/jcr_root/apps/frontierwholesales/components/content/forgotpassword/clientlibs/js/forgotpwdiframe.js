$(document).ready(function(){
    if($('#magento-forgotpwd-iframe').length > 0){
        var userToken = getUserToken();
        if(userToken == ""  && userToken.trim().length == 0){
        
            $('#magento-forgotpwd-iframe').prop('src',serverURL+'/customer/account/forgotpassword/');
        }else{
            location.href="/content/frontierwholesales/en/home.html";
        }
    }
   
});