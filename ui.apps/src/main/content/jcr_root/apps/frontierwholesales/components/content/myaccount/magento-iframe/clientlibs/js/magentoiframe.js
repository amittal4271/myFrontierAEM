$(document).ready(function(){
    if( $('#magento-myaccount-iframe').length > 0){
        var userToken = getUserToken();
        if(userToken.startsWith('Bearer') && userToken.trim().length > 0){
            userToken = userToken.replace('Bearer ','');
            var pathName = location.pathname;
           var path = pathName.substr(pathName.lastIndexOf("/")+1);
            if(path == 'addressbook'){
                //update with address url
                $('#magento-myaccount-iframe').prop('src',serverURL+'/customer/address?token='+userToken);
            }else if(path == 'orders'){
                //wish list url
                 $('#magento-myaccount-iframe').prop('src',serverURL+'/sales/order/history?token='+userToken);
            }else if(path == 'wishlist'){
                //orders url
                 $('#magento-myaccount-iframe').prop('src',serverURL+'/wishlist?token='+userToken);
            }else if(path == 'pendingorders'){
                 $('#magento-myaccount-iframe').prop('src',serverURL+'/savecart/customer/cartlist?token='+userToken); 
            }else if(path == 'myquote'){
                $('#magento-myaccount-iframe').prop('src',serverURL+'/negotiable_quote/quote?token='+userToken);   
            }else if(path == 'myshelves'){
                $('#magento-myaccount-iframe').prop('src',serverURL+'/requisition_list/requisition/index?token='+userToken); 
            }else if(path == 'mywishlist'){
                 $('#magento-myaccount-iframe').prop('src',serverURL+'/wishlist?token='+userToken);
            }else if(path == 'accountinfo'){
                 $('#magento-myaccount-iframe').prop('src',serverURL+'/customer/account/edit?token='+userToken);
            }else if(path == 'users'){
                 $('#magento-myaccount-iframe').prop('src',serverURL+'/company/users?token='+userToken);
            }else if(path == 'rewardpoints'){
                 $('#magento-myaccount-iframe').prop('src',serverURL+'/reward/customer/info?token='+userToken);
            }else if(path == 'giftregistry'){
                 $('#magento-myaccount-iframe').prop('src',serverURL+'/giftregistry?token='+userToken);
            }else if(path == 'subscription'){
                 $('#magento-myaccount-iframe').prop('src',serverURL+'/customersubscription/manage?token='+userToken);
            }else if(pathName.search('/vieworder/') > 0){
                 $('#magento-myaccount-iframe').prop('src',serverURL+'/sales/order/view/order_id/'+path);
            }else{
                $('#magento-myaccount-iframe').prop('src',serverURL+'/customer/account?token='+userToken);
            }
        }else{
            location.href="/content/frontierwholesales/en/login.html";
        }
    }  


    function iframeURLChange(iframe, callback) {
        var unloadHandler = function () {
            // Timeout needed because the URL changes immediately after
            // the `unload` event is dispatched.
            setTimeout(function () {
                callback(iframe.contentWindow.location.href);
            }, 0);
        };

        function attachUnload() {
            // Remove the unloadHandler in case it was already attached.
            // Otherwise, the change will be dispatched twice.
            iframe.contentWindow.removeEventListener("unload", unloadHandler);
            iframe.contentWindow.addEventListener("unload", unloadHandler);
        }

        iframe.addEventListener("load", attachUnload);
        attachUnload();
    }

    if ($('#magento-myaccount-iframe').length > 0) {
		iframeURLChange(document.getElementById("magento-myaccount-iframe"), function (newURL) {
            if(newURL.indexOf("/shop/") == -1){
              window.location = newURL;
            }
        });
    }
});
