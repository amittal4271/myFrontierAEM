$(document).ready(function(){
    
    getCartItem();
    $('.cart-qty-input.form-control').on('change',function(){ 
        var $this = $(this);
        var qty = $this.val();
        updateShoppingCart(qty); 
    });
    
    $(document).on('click', '.btn.btn-only-green-icon', function() {
           console.log('button is clicked...');
         var $this = $(this);
        var itemId = $this.data('itemid');
        var deleteCartItemModal = $('#modalDeleteCartItem');
        var modalRemoveItemButton = deleteCartItemModal.find('.btn-cart-remove-item');
        modalRemoveItemButton.data('itemid', itemId);
    });
    
    $(document).on('click','.btn-cart-remove-item',function(){
       
        var $this = $(this);
        var itemId = $this.data('itemid');
        cartItemRemove(itemId);
    });
    
    $(document).on('click','.btn-cart-checkout',function(){
       window.location=Granite.HTTP.externalize($('#pageURL').data('pageurl')); 
    });
    
});

function updateCart(obj){
    var jsonData={};
    jsonData['qty']=obj.value;
    $.put("/services/cart",jsonData,function(){
        
    }).done(function(result){
       console.log(result); 
    });
}



function getCartItem(){
    showLoadingScreen();
    var jsonData={};
    jsonData['action']='getCart';
     $.ajax({
       url: "/services/cart" ,
        method: "get",
       data: jsonData
        
    }).done(function(cart){
         hideLoadingScreen();
         if(cart.trim() !== 'Error in Cart'){
        cart = JSON.parse(cart);
        var template = $("#cartTemplate").html();
         var processedHTML = Handlebars.compile(template);
        var html = processedHTML(cart);
        $('#carttemplate').html(html);
         }
    });
}



function cartItemRemove(itemId){
   
     var jsonData={};
   
    jsonData['itemId']=itemId;
    jsonData['action']='remove';
    $.ajax({
       url: "/services/cart",
        method: "get",
        data: jsonData
        
    }).done(function(cart){
         if(cart.trim() !== 'Error in Cart'){
        $('#modalDeleteCartItem').modal('hide');
        cart = JSON.parse(cart);
        var template = $("#cartTemplate").html();
        var processedHTML = Handlebars.compile(template);
        var html = processedHTML(cart);
        $('#carttemplate').html(html);
         }
    });
   
}