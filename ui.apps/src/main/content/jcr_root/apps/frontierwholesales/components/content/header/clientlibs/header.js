$(document).ready(function(){
    var pageTitle = $("#pageTitle").data("pagetitle");
    console.log(pageTitle);
    //getCartItems('minicartTemplate');
    getCartItems();
    
    $(document).on('click','#signOut',function(){
       localStorage.removeItem('ConfirmationNr');
       document.cookie = "MagentoUserToken=;expires=0;path=/;";
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
    $.get("/services/cart",jsonData,function(){
        
    }).done(function(results){
        var cart='';
          if(results.trim() !== 'Error in Cart'){
             cart = JSON.parse(results);
          }
           var template = $("#minicartTemplate").html();
            var processedHTML =  Handlebars.compile(template);
        var html = '';
        if(cart != ''){
          html  = processedHTML(cart,cart.items.reverse());
        }else{
            html = processedHTML(cart);
        }
           $('#minicarttemplate').html(html); 
          
    });
}

function removeCartItem(itemId){
    
    var jsonData={};
   
    jsonData['itemId']=itemId;
    jsonData['action']='remove';
    $.ajax({
       url: "/services/cart",
        method: "get",
        data: jsonData
       
    }).done(function(cart){
        if(cart.trim() !== 'Error in Cart'){
           
             cart = JSON.parse(cart);
             var template = $("#minicartTemplate").html();
             var processedHTML =  Handlebars.compile(template);
             var html = processedHTML( cart,cart.items.reverse());
             $('#minicarttemplate').html(html);
             $('.mini-cart-holder').show();
            
        }
    });
}

