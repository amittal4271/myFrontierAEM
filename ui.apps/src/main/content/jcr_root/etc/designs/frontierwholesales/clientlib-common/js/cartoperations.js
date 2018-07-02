function getCartItems(templateId,jsonData,cartType){
     
    $.get(
        url:"/services/cart",
        data:jsonData,
        headers:{

                'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8',
                'Authorization':getUserToken()
            },
      beforeSend:function(xhr){
          xhr.overrideMimeType("application/json");
      }
        
    }).done(function(cart){
         if(cartType == 'cart'){
             loadCart();
         }else{
             loadMiniCart();
         }
          
    }).fail(function(error){
        //call error method here...
         if(cartType == 'cart'){
             errorCart();
         }else{
             errorMiniCart();
         }
        
    });
}

function loadMiniCart(){
    var template = $("#minicarttemplate").html();
    var cartTemplate = Handlebars.compile(template);
    var html = cartTemplate(cart.items.reverse());
    $('#minicarttemplate').html(html); 
}

function loadCart(){
     var template = $("#cartTemplate").html();
     var processedHTML = Handlebars.compile(template);
    var html = processedHTML(cart);
    $('#carttemplate').html(html);
}

function errorMiniCart(){
    
    var template = $("#minicarttemplate").html();
    var processedHTML =  Handlebars.compile(template);
    var html = processedHTML('');
    $('#minicarttemplate').html(html); 
    
}


function errorCart(){
    var template = $("#carttemplate").html();
    var processedHTML =  Handlebars.compile(template);
    var html = processedHTML('');
    $('#carttemplate').html(html); 
}

