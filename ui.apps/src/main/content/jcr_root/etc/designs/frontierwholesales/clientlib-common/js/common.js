$(document).ready(function(){
    if (!!window.performance && window.performance.navigation.type === 2) {
        console.log('browser back button...');
        location.reload();
    }
    $(document).on('click','#signOut',function(){
       localStorage.removeItem('ConfirmationNr');
      
          document.cookie="CustomerData=;Max-Age=-99999999;path=/;";
        window.location.href="/content/frontierwholesales/en/home.html";
    });

});
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
        // jsonData['id']=customerData.id;
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
   /* if($.isArray(data)){
        jsonData['id'] = data[0].customer_id;
    }*/
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

function initCartHandlbarFunctions(){	
	Handlebars.registerHelper("getCartUrl",function(){
        try {
        	var magentoCartUrl = serverURL;
        	if (magentoCartUrl.slice(-1) != '/') {
        		magentoCartUrl += "/";
        	}
        	magentoCartUrl = magentoCartUrl + "checkout/cart";
        	return magentoCartUrl;
        }
        catch(error){
        	console.log("error getting server cart url");
        	return '#';
        }
    });
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

function addItemToWishList(sku){
     var jsonData={};
    jsonData['itemDetails']={};
    var itemDetails={};
    
    itemDetails['product_sku']=sku;
    itemDetails['store_id']='1';
    itemDetails['website_id']='1';
    showLoadingScreen();
    Frontier.MagentoServices.getUserRole(serverURL).done(function(customerData){
         itemDetails['customer_id']=customerData.frontier_customer.id;
         jsonData['itemDetails']=itemDetails;         
         Frontier.MagentoServices.addItemToWishList(serverURL,jsonData).done(function(wishlist){
             hideLoadingScreen();
             console.log("added to whishlist");
              $('#message-'+sku).html("Item added to your WishList!");
              $('#message-'+sku).fadeIn('fast').delay(3000).fadeOut('fast');
         }).fail(function(error){
              console.log("error "+error);
              hideLoadingScreen();
            enableErrorMsg(error.status);
         });
    }).fail(function(error){
              console.log("error "+error);
              hideLoadingScreen();
            enableErrorMsg(error.status);
    });  
       
     
}


function retrieveRequisitionList(thisObj){
     Frontier.MagentoServices.getRequisitionList(serverURL).done(function(list){
         if(list.length > 0){
             thisObj.find('option').not(':first').remove();
             $.each(list,function(key,value){
                  thisObj.append($('<option/>',
                                        {'value':value.id,'text':value.name}));
				  thisObj.attr("size", list.length + 1);
             });
             if(($( window ).width()) < 480) {
				thisObj.delay(2000).focus().click();

             }
         }else{
             thisObj.append($('<option/>',
                                    {'text':'Not available'}));
         }
        
     }).fail(function(error){
             hideLoadingScreen();
            enableErrorMsg(error.status);
    });
}

function addRequisitionList(reqid,prodid,qty,sku){
    var jsonData={};
   
    var reqItems={};
    reqItems['reqlistId']=reqid;
    reqItems['skuId']=sku;
    reqItems['qty']=qty;
    var reqData = [];
    reqData.push(reqItems);
    jsonData['reqitems']=reqData;
    showLoadingScreen();
    Frontier.MagentoServices.addRequisitionList(serverURL,jsonData).done(function(response){
            hideLoadingScreen();
            $('#message-'+sku).html("Item added to your Shelves!");
            $('#message-'+sku).fadeIn('fast').delay(3000).fadeOut('fast');
    }).fail(function(error){
        hideLoadingScreen();
        enableErrorMsg(error.status);
    });
}

function addItemToCart(sku,qty){
	   var jsonData={};
	    var cartItems={};
	    var cartData={};
	   showLoadingScreen();
	    cartItems['sku']=sku;
	    cartItems['qty']=qty;
	   
	    cartData['cartItem']=cartItems;
	    jsonData['items']=JSON.stringify(cartData);
	    jsonData['action']='add';
	    
	    Frontier.MagentoServices.addItemToCart(jsonData).done(function(cart){
	        console.log("result is "+cart);
	        hideLoadingScreen();
	        
	        
	        $('#cartMessage-'+sku).fadeIn('fast').delay(3000).fadeOut('fast');
	        var template = $("#minicartTemplate").html();
	        var cartTemplate = Handlebars.compile(template);
	        var html = cartTemplate(cart,cart.items.reverse());
	        $('#minicarttemplate').html(html); 
	        
	    }).fail(function(error){
	        console.log("error is "+error);
	         hideLoadingScreen();
	        enableErrorMsg(error.status);
	    });
	   
	}

function initListenersForProductButtons() {
	$(document).on('click','.btn.btn-light-green.btn-grid-add-to-cart',function(){
       console.log("cart has been added");
       var sku = $(this).attr('id');
       var qty='';
       $(this).parent().find(":input").each(function(i,data) { 
           var className=data.getAttribute('class');
           if(className == 'grid-product-qty-input qty-input-field'){
               qty = data.value;
           }
          
       });
       addItemToCart(sku,qty);
        
    });
	
	$(document).on('click','.btn-wishlist',function(e){
        e.preventDefault();
       var skuId = $(this).data('skuid');
       
        
        addItemToWishList(skuId);

    });

    $(document).on('focus','.requisition-list-select',function(e){
        e.preventDefault(); 
        $(this).removeClass('closed');
        var length = $(this).find("option").length;
        if(length == 1){           
          var $thisObj = $(this);
          retrieveRequisitionList($thisObj);
        }
    });

    $(document).on('change','.requisition-list-select',function(e){
        e.preventDefault(); 
        var id = $(this).data('prodid');
        var sku = $(this).data('wishsku');
        var qty = $(this).parent().parent().siblings().children().find('input').val();
        if(qty !== undefined && qty !== ''){
            var reqid = $(this).val();
            addRequisitionList(reqid,id,qty,sku);
            $(this).addClass('closed').blur();
        }
        
    });

    //  close when clicked outside of shelves dropdown
    $(document).mouseup(function(e) {
        var shelvesDropdown = $(".requisition-list-select");
        var activeshelvf = $(".requisition-list-select").find('option');
        if (!shelvesDropdown.is(e.target) && shelvesDropdown.has(e.target).length === 0) {
            shelvesDropdown.addClass('closed');
        }
	});


}

function showProdErrorMessage(msg){
     $('.global-server-side-message-holder').css('display','block');
             $el = $('.global-server-side-message-holder');
            $('.global-server-side-message-holder').children().text(msg);
            scrollToElement($el);
}

