var Frontier = Frontier || {};
Frontier.confirmation =Frontier.confirmation || {};


Frontier.confirmation = new function(){

    function init(){
        var serverURL = window.serverURL;
        showLoadingScreen();
         getAdminToken().then(function(response){
           var adminToken = response;
             var confirmationNr = $('#confirmationNr').val();
            Frontier.MagentoServices.getConfirmationData(serverURL,adminToken,confirmationNr).done(function(data){
                console.log(data); 
                hideLoadingScreen();
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
   
    $(document).ready(init);
}();