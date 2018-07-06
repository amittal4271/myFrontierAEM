var Frontier = Frontier || {};
Frontier.confirmation =Frontier.confirmation || {};


Frontier.confirmation = new function(){

    function init(){
        var serverURL = window.serverURL;
        showLoadingScreen();
         getAdminToken().then(function(response){
           var adminToken = response;
            Frontier.MagentoServices.getConfirmationData(serverURL,adminToken).done(function(data){
                console.log(data); 
                HandlebarsIntl.registerWith(Handlebars);
                 var template = $("#confirmationTemplate").html();
                 var processedHTML =  Handlebars.compile(template);
                 var html = processedHTML( data);
                $("#confirmationpage").html(html);
            }).error(function(error){
                hideLoadingScreen();
                enableErrorMsg(error.status);
            });
         });
    }
        
        
   /*function getConfirmation(){
       var confirmationNr = localStorage.getItem("ConfirmationNr");
   getAdminToken().then(function(response){
       var adminToken = response;
    $.ajax({
        url: window.serverURL+"/rest/V1/orders/"+confirmationNr,
        method: "get",
         headers:{

                'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8',
                'Authorization':adminToken
            }
    }).done(function(response) {
        console.log(response); 
            HandlebarsIntl.registerWith(Handlebars);
             var template = $("#confirmationTemplate").html();
             var processedHTML =  Handlebars.compile(template);
             var html = processedHTML( response);
       $("#confirmationpage").html(html);
  });
   });


}*/
    $(document).ready(init);
}();