$(document).ready(function(){

getConfirmation();
});




   function getConfirmation(){
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


}