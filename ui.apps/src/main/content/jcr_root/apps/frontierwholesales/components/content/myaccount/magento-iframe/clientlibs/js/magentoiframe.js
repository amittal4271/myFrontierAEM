$(document).ready(function(){
    if( $('#magento-myaccount-iframe').length > 0){
        var userToken = getUserToken();
        if(userToken.startsWith('Bearer') && userToken.trim().length > 0){
            userToken = userToken.replace('Bearer ','');
            var path = location.pathname;
            if(path.endsWith('addressbook')){
                //update with address url
                $('#magento-myaccount-iframe').prop('src',serverURL+'/customer/address?token='+userToken);
            }else if(path.endsWith('orders')){
                //wish list url
                 $('#magento-myaccount-iframe').prop('src',serverURL+'/sales/order/history?token='+userToken);
            }else if(path.endsWith('wishlist')){
                //orders url
                 $('#magento-myaccount-iframe').prop('src',serverURL+'/wishlist?token='+userToken);
            }else if(path.endsWith('pendingorders')){
                 $('#magento-myaccount-iframe').prop('src',serverURL+'/savecart/customer/cartlist?token='+userToken); 
            }else{
                $('#magento-myaccount-iframe').prop('src',serverURL+'/customer/account?token='+userToken);
            }
        }else{
            location.href="/content/frontierwholesales/en/login.html";
        }
    }  

	counter = 0;

    function getIframeHeight() {
        if( $('#magento-myaccount-iframe').length > 0 && counter < 10) {
            if($('#maincontent').length > 0) {
                var iframeHeight = document.getElementById("maincontent").offsetHeight;
                console.log(iframeHeight);
                $('#magento-myaccount-iframe').css('height',iframeHeight);
            } else {
                setTimeout(getIframeHeight, 1000);
                counter ++;
            }
        }
    }
	getIframeHeight();

});