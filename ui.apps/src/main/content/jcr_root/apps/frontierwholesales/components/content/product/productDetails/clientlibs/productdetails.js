$(document).ready(function(){
console.log("product details page...");
    
  getProductDetails();

    $(document).on('click','.btn.btn-light-green.btn-add-to-cart',function(){
       console.log("Add to card invoked");
       var sku = $(this).attr('id');
       var qty=''; 
       qty = $('#product-qty-input').val();
       if(qty > 0){
           addItemToCart(sku,qty);
			console.log("cart has been added");
       }
       else {
			console.log("quantity is 0");
       }

    });  


});

function getProductDetails(){
    var jsonData={};
    showLoadingScreen();
    

    jsonData['sku']=$('#productId').val();

    $.ajax({
        url: "/services/pdp",
        data:jsonData
    }).done(function(results){
       console.log(results); 
        hideLoadingScreen();
        var productDetails = JSON.parse(results);
       
        var template = $("#productDetailsTemplate").html();
     

        var html = Handlebars.compile(template);

        var processedHTML = html(productDetails)
       

        $('#product-detail').empty();
        $('#product-detail').html(processedHTML); 
    });
}


function addItemToCart(sku,qty){
   console.log("in addItemToCart("+sku+", "+qty+")");
   var jsonData={};
   var cartItems={};
   var cartData={};
   showLoadingScreen();
    cartItems['sku']=sku;
    cartItems['qty']=qty;
   
    cartData['cartItem']=cartItems;
    jsonData['items']=JSON.stringify(cartData);
    console.log(jsonData);
    jsonData['action']='add';
    

    $.get("/services/cart",jsonData,function(){

    }).done(function(result){
        console.log("result is "+result);
        hideLoadingScreen();
        if(result.trim() !== 'Error in Cart'){
        var cart = JSON.parse(result);
        $('#cartMessage-'+sku).fadeIn('fast').delay(3000).fadeOut('fast');
        var template = $("#minicartTemplate").html();
        var cartTemplate = Handlebars.compile(template);
        var html = cartTemplate(cart,cart.items.reverse());
        $('#minicarttemplate').html(html); 
        }

    });
}