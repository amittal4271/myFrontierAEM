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
	var deferred = $.Deferred();
	
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
          
          deferred.resolve(usRegions);
        
    }).fail(function(error){
            console.log(error);
             var errorText="The site is currently unavailable and unable to process your request.  Please check back later.";
             $('.global-server-side-message-holder').css('display','block');
            $('.global-server-side-message-holder').children().text(errorText);
            deferred.resolve(error);
        });
	
	return deferred.promise();
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
    if(undefined !== errorCode && errorCode == '401'  && errorCode == '403'){
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


function getGroupIdFromCookie(){
    var cookieValue = document.cookie;
        var cookieSplit=cookieValue.split(";");
        var CustomerData={};
        for(var i=0;i<cookieSplit.length;i++){
            var token = cookieSplit[i].trim().split("=");
            if(token[0].startsWith("CustomerData")){
                CustomerData = JSON.parse(token[1]);
            }
        }
    var groupId='';
    if(!$.isEmptyObject(CustomerData)){
        groupId = CustomerData.group_id;
    }
    return groupId;
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
    try{
        var customerData = JSON.parse(data.CustomerData);
         var jsonData={};
         jsonData['token']=data.UserToken;
         jsonData['email']=customerData.company_email;
         jsonData['id']=customerData.id;
         var cookieData = "CustomerData="+JSON.stringify(jsonData)+"; path=/";
        addCookie(cookieData);
        return true;
    }catch(error){
        console.log("invalid json format "+error);
        enableErrorMsg('403');
        return false;
    }
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

function clearErrorMsg(){
    $('.global-server-side-message-holder').css('display','none');
    $('.global-server-side-message-holder > .alert-danger').html('');
}

function getParameterByName(name, url) {
    if (!url) url = window.location.href;
    name = name.replace(/[\[\]]/g, '\\$&');
    var regex = new RegExp('[?&]' + name + '(=([^&#]*)|&|#|$)'),
        results = regex.exec(url);
    if (!results) return null;
    if (!results[2]) return '';
    return decodeURIComponent(results[2].replace(/\+/g, ' '));
}

function initProductSearchHandlbarFunctions(){
    
    HandlebarsIntl.registerWith(Handlebars);
      Handlebars.registerHelper("recordsPerPage",function(recsPerPage,page,totalRecs){
          var recordsPerPage = recsPerPage * page;
          if( recordsPerPage > totalRecs){
              return totalRecs;
          }else{
            return recordsPerPage;
          }
      });

      Handlebars.registerHelper("gt",function(pageTotal,options){
          var fnTrue = options.fn,
      fnFalse = options.inverse;
         return (pageTotal > 28)?fnTrue():fnFalse();

      });

      Handlebars.registerHelper("moreCategories",function(index,options){
           var fnTrue = options.fn,
          fnFalse = options.inverse;
         return (index > 4)?fnTrue():fnFalse();

      });

       Handlebars.registerHelper("ifEquals",function(attrib,options){
           var fnTrue = options.fn,
          fnFalse = options.inverse;
         return (attrib !== undefined && attrib.trim() !== '0')?fnTrue():fnFalse();

      });

       Handlebars.registerHelper("viewMore",function(index,options){
           var fnTrue = options.fn,
          fnFalse = options.inverse;
         return (index > 5)?fnTrue():fnFalse();

      });

      Handlebars.registerHelper("noCatFilter",function(name,options){
           var fnTrue = options.fn,
          fnFalse = options.inverse;
         return (name !== undefined && name !== "Category" && name !== "Price")?fnTrue():fnFalse();
      });

  Handlebars.registerHelper("priceCheck",function(price,options){
           var fnTrue = options.fn,
          fnFalse = options.inverse;
         return (price !== undefined && price >=0 )?fnTrue():fnFalse();
      });

}
