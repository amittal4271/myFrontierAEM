$(document).ready(function(){
	
	initCartHandlbarFunctions();
	
    var pageTitle = $("#pageTitle").data("pagetitle");
    console.log(pageTitle);
    //getCartItems('minicartTemplate');
    getCartItems();
    
    $(document).on('click','#signOut',function(){
       localStorage.removeItem('ConfirmationNr');
      
          document.cookie="CustomerData=;Max-Age=-99999999;path=/;";
        window.location.href="/content/frontierwholesales/en/home.html";
    });
    
    $(document).on('click','.btn-mini-cart-remove',function(){
       var $this = $(this);
        var parentMiniCartItem = $this.parents('.each-mini-cart-item');
        var itemId = $this.data('itemid');
       
        var html = '';
         html += '<div class="mini-cart-item-remove-confirmation">';
             html += '<button type="button" data-itemid="'+itemId +'" class="btn btn-smaller btn-confirm-remove-mini-cart-item">Remove Item</button>';
             html += '<button type="button" class="btn btn-smaller btn-white-green btn-cancel-remove-mini-cart-item">Cancel</button>';
         html += '</div>';
        parentMiniCartItem.append(html);
        
    });
    
     $(document).on('click','.btn-confirm-remove-mini-cart-item',function(){
          
         var $this = $(this);
        var itemId = $this.data('itemid');
         removeCartItem(itemId);
     });
    
    $(document).on('click', '.btn-cancel-remove-mini-cart-item', function() {
        var $this = $(this);
        var removeHolder = $this.parent('.mini-cart-item-remove-confirmation');
        removeHolder.remove();
    });
  
});

function getCartItems(){
    var jsonData={};
    jsonData['action']='getCart';
    
  $.ajax({
        url:"/services/cart",
     data: jsonData,
       headers:{

                'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8',
                'Authorization':getUserToken()
            },
      beforeSend:function(xhr){
          xhr.overrideMimeType("application/json");
      }, 
        success:function(cart){
            
           var template = $("#minicartTemplate").html();
            var processedHTML =  Handlebars.compile(template);
        var html = '';
        if(!$.isEmptyObject(cart)){
          html  = processedHTML(cart,cart.items.reverse());
        }else{
            html = processedHTML(cart);
        }
           $('#minicarttemplate').html(html); 
        },error:function(error){
			console.log("error");
            if(error.status == 401){
                enableErrorMsg(error.status);
            }else{
             var template = $("#minicartTemplate").html();
            var processedHTML =  Handlebars.compile(template);
			var html = processedHTML('');
            $('#minicarttemplate').html(html); 
            }
        }

    });

}
function removeCartItem(itemId){
    
    var jsonData={};
   
    jsonData['itemId']=itemId;
    jsonData['action']='remove';
    $.ajax({
       url: "/services/cart",
        method: "get",
        data: jsonData,
         headers:{

                'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8',
                'Authorization':getUserToken()
            },
         beforeSend:function(xhr){
          xhr.overrideMimeType("application/json");
      },
       
    }).done(function(cart){
        if(cart !== 'Error in Cart'){
           
             var template = $("#minicartTemplate").html();
             var processedHTML =  Handlebars.compile(template);
             var html = processedHTML( cart,cart.items.reverse());
             $('#minicarttemplate').html(html);
             $('.mini-cart-holder').show();
            
        }else{
             if(error.status == 401){
                enableErrorMsg(error.status);
            }
        }
    });
}

