$(document).ready(function(){
    
   if($('#magento-cart-iframe').length > 0){
       $('#minicarttemplate').css('display','none');
        var userToken = getUserToken();
        if(userToken.startsWith('Bearer')  && userToken.trim().length > 0){
            userToken = userToken.replace('Bearer ','');
             $('#magento-cart-iframe').prop('src',serverURL+'/checkout/cart?token='+userToken);
        }else{
            $('#minicarttemplate').css('display','block');
            location.href="/content/frontierwholesales/en/login.html";
        }
    }
    
});

