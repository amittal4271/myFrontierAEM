$(document).ready(function(){
    
    getCartItem();
    $('.cart-qty-input.form-control').on('change',function(){ 
        var $this = $(this);
        var qty = $this.val();
        updateShoppingCart(qty); 
    });
    
    $('.btn.btn-only-green-icon').on('click',function(){ 
       
        var itemId = $(this).parent().attr('data-itemid');
        removeCartItem(itemId);
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
        cart = JSON.parse(cart);
        var template = $("#cartTemplate").html();
        var html = Mustache.to_html(template, cart);
        $('#carttemplate').html(html);
    });
}



function cartItemRemove(obj){
   showLoadingScreen();
     var jsonData={};
   
    jsonData['itemId']=obj.parentNode.getAttribute('data-itemid');
    jsonData['action']='remove';
    $.ajax({
       url: "/services/cart",
        method: "get",
        data: jsonData
        
    }).done(function(cart){
        hideLoadingScreen();
        cart = JSON.parse(cart);
        var template = $("#cartTemplate").html();
        var html = Mustache.to_html(template, cart);
        $('#carttemplate').html(html);
    });
   
}