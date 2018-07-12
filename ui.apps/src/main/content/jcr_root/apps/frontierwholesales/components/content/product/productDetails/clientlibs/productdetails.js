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

function getAttributeDataFromTxtBox(id){
    var attribute = $('#'+id).val();
    var splitArray = attribute.split(",");
    return splitArray;
}

function getProductDetails(){
    var jsonData={};
    showLoadingScreen();
   
   var summaryAttribute =  getAttributeDataFromTxtBox('summaryAttribute');
    var infoAttribute = getAttributeDataFromTxtBox('infoAttribute');
    var additionalAttribute = getAttributeDataFromTxtBox('additionalAttribute');

    jsonData['sku']=$('#productId').val();
    jsonData['currentPagePath'] = $('#currentPagePath').val();

    $.ajax({
        url: "/services/pdp",
        data:jsonData
    }).done(function(results){
        hideLoadingScreen();
        var productDetails = JSON.parse(results);
       
        var template = $("#productDetailsTemplate").html();
        var bCount=0;
        Handlebars.registerHelper("getAttrValues",function(attCode,value,count,options){
           
            var idx = $.inArray(attCode,summaryAttribute);
            if(idx != -1){
                if(parseInt(value) > 0){
                    bCount++;
                    return  options.fn(this);
                }
            }
           if(bCount == 14){
                return options.inverse(this);
           }
           return options.inverse(this);
              
        });
        
         Handlebars.registerHelper("getInfoAttrValues",function(attCode,value,options){
            var idx = $.inArray(attCode,infoAttribute);
            if(idx != -1){
                if(value.trim() !="0"){
                return  options.fn(this);
                }
                
            }
           return options.inverse(this);
              
        });
        
         Handlebars.registerHelper("additionalAttrValues",function(value1,value2,value3,value4,value5,value6,options){
          if(undefined == value1 && undefined == value2  && undefined ==value3 && undefined == value4 && undefined == value5 && undefined == value6 ){
             return options.inverse(this);
          }
              return options.fn(this);
           
              
        });
        

        var html = Handlebars.compile(template);

        var processedHTML = html(productDetails)
       

        $('#product-detail').empty();
        $('#product-detail').html(processedHTML);
        if ($('#product-detail').length > 0) {
    		bindPdpAffixEvents();
    	}
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