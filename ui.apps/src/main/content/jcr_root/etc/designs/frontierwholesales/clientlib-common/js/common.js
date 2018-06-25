
function getRedirectPath(){
        return "/content/frontierwholesales/en/myaccount.html"; 

    }

function loadRegions(obj1){
    $.ajax({
        url:"/services/registration",
        method:"GET",
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

function getUserToken(){
		var cookieValue = document.cookie;
        var cookieSplit=cookieValue.split(";");
        var userToken='';
        for(var i=0;i<cookieSplit.length;i++){
            var token = cookieSplit[i].trim().split("=");
            if(token[0].startsWith("MagentoUserToken")){
                userToken = token[1];
            }
        }
        var regx=new RegExp("\"","g");
        userToken=userToken.replace(regx,"");
        return userToken;
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
    }
        
    });
   return  d.promise();
}
