/**
* redirect to my account
*/
function getRedirectPath(){
        return "/content/frontierwholesales/en/myaccount.html"; 

    }

function redirectToLogin(){
    return "/content/frontierwholesales/en/login.html";
}

function loadRegions(obj1){
    $.ajax({
        url:"/services/registration",
        method:"GET",
        timeout: getServiceTimeOut(),
      beforeSend: function(xhr){
        xhr.overrideMimeType("application/json");  
      }
    }).done(function(usRegions){
           
          usRegions.forEach(function(key,val){ 
              if(usRegions[val].id == "US"){ 
                  
                  var regions = usRegions[val].available_regions; 
                  regions.forEach(function(key,val){ 
                      $('#'+obj1).append($('<option/>',
                                                          {'data-attr-id':regions[val].id,'value':regions[val].code,'text':regions[val].name})); 
                      
                  });
              }
          });
        
    }).fail(function(error){
            console.log(error);
             var errorText="The site is currently unavailable and unable to process your request.  Please check back later.";
             $('.global-server-side-message-holder').css('display','block');
            $('.global-server-side-message-holder').children().text(errorText);
        });
}

function loadLifeTimeRegistrationRegions(){
     
    $.ajax({
        url:"/services/registration",
        method:"GET",
        timeout: getServiceTimeOut(),
        beforeSend: function(xhr){
        xhr.overrideMimeType("application/json");  
        }
      }).done(function(usRegions){
           
          usRegions.forEach(function(key,val){ 
              if(usRegions[val].id == "US"){ 
                  
                  var regions = usRegions[val].available_regions; 
                  regions.forEach(function(key,val){ 
                      $('#id_mailing-locality').append($('<option/>',
                                                          {'data-attr-id':regions[val].id,'value':regions[val].code,'text':regions[val].name})); 
                      $('#id_shipping-locality').append($('<option/>',
                                                          {'data-attr-id':regions[val].id,'value':regions[val].code,'text':regions[val].name})); 
                      $('#id_billing-locality').append($('<option/>',
                                                          {'data-attr-id':regions[val].id,'value':regions[val].code,'text':regions[val].name}));
                  });
              }
          });
        }).fail(function(error){
            console.log(error);
             var errorText="The site is currently unavailable and unable to process your request.  Please check back later.";
             $('.global-server-side-message-holder').css('display','block');
            $('.global-server-side-message-holder').children().text(errorText);
        });
    
}

function enableErrorMsg(errorCode){
    if(undefined !== errorCode && errorCode == '401'){
        window.location=redirectToLogin();
    }else{
        var errorText="The site is currently unavailable and unable to process your request.  Please check back later.";
            
            $('.global-server-side-message-holder').css('display','block');
             $el = $('.global-server-side-message-holder');
            $('.global-server-side-message-holder').children().text(errorText);
            scrollToElement($el);
    }
}

function getUserToken(){
		var cookieValue = document.cookie;
        var cookieSplit=cookieValue.split(";");
        var CustomerData={};
        for(var i=0;i<cookieSplit.length;i++){
            var token = cookieSplit[i].trim().split("=");
            if(token[0].startsWith("CustomerData")){
                CustomerData = JSON.parse(token[1]);
            }
        }
        
        var regx=new RegExp("\"","g");
        var userToken='';
        if(!$.isEmptyObject(CustomerData)){
            userToken = CustomerData.token.replace(regx,"");
        }
        return userToken;
    }

function getCustomerIdFromCookie(){
    var cookieValue = document.cookie;
        var cookieSplit=cookieValue.split(";");
        var CustomerData={};
        for(var i=0;i<cookieSplit.length;i++){
            var token = cookieSplit[i].trim().split("=");
            if(token[0].startsWith("CustomerData")){
                CustomerData = JSON.parse(token[1]);
            }
        }
    var customerId='';
    if(!$.isEmptyObject(CustomerData)){
        customerId = CustomerData.id;
    }
    return customerId;
}

function getAdminToken(){
     var d = $.Deferred();

    $.ajax({
        url: "/services/admintoken",
        method:"get",
        success:function(response){
             var regx=new RegExp("\"","g");
            var token = JSON.parse(response);
            var adminToken=token.Token.replace(regx,"");
           d.resolve(adminToken);
    },error:function(error){
        d.resolve(error);
        console.log("error "+error);
    }
        
    });
   return  d.promise();
}

function addCookie(value){
  
    document.cookie=value;
}

function getServiceTimeOut(){
    return '6000';
}

function tokenValidity(error){
    if(error.status == '401'){
        //token is expired then redirect to login page
        window.location.href=redirectToLogin();
    }
}

function addCustomerDataToCookie(data){
    var customerData = JSON.parse(data);  
     var customerId=customerData.id
     var jsonData={};
     jsonData['token']=data.UserToken;
     jsonData['email']=customerData.company_email;
     jsonData['id']=customerData.id;
     var cookieData = "CustomerData="+JSON.stringify(jsonData)+"; path=/";
    addCookie(cookieData);
}

function addBuyersClubDataToCookie(data,email,token){
   
     var jsonData={};
    if($.isArray(data)){
        jsonData['id'] = data[0].customer_id;
    }
     jsonData['token']=token;
     jsonData['email']=email;
     var cookieData = "CustomerData="+JSON.stringify(jsonData)+"; path=/";
    addCookie(cookieData);
}


