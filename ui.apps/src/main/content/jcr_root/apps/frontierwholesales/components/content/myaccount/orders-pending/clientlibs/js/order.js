var Frontier = Frontier || {};
Frontier.orderOperations =Frontier.orderOperations || {};
Frontier.orderOperations.customerId='';

Frontier.orderOperations = new function(){
    function init(){
        var serverURL = window.serverURL;
        showLoadingScreen();
        Frontier.MagentoServices.getCustomerDetails(serverURL).done(function(data){
            console.log(data.id);
            Frontier.orderOperations.customerId=data.id;
			loadOrdersList(serverURL,data.id);
        }).error(function(error){
           console.log(error); 
             hideLoadingScreen();
            enableErrorMsg(error.status);
            
        });

    }
    
    function loadOrdersList(serverURL,customerId){
        console.log('loadorders list...');
        getAdminToken().then(function(response){  
        Frontier.MagentoServices.getOrders(serverURL,response,customerId).done(function(order){
            hideLoadingScreen();
            console.log(order);
           var template = $("#orderTemplate").html();
            var processedHTML = Handlebars.compile(template);
            HandlebarsIntl.registerWith(Handlebars);
            var html = processedHTML(order);
            $('#ordertemplate').html(html);
            }).fail(function(error){
                console.log(error);
                hideLoadingScreen();
                enableErrorMsg(error.status);
            });
        });
    }
    $(document).ready(function(){
        init();
        
        $(document).on('click','.btn-cart-checkout',function(){
            console.log(Frontier.orderOperations.customerId);
           window.location.href=window.serverURL+"/company/customer/validate/id/"+Frontier.orderOperations.customerId; 
        });
    });
}();


