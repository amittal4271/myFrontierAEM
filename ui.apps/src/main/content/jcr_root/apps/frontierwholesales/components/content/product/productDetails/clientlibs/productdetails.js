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
    
      $(document).on('click','.btn-wishlist',function(e){
        e.preventDefault();
       var skuId = $(this).data('skuid');
       
        
        addItemToWishList(skuId);
        
    });
    
     $(document).on('click','.requisition-list-select',function(e){
       e.preventDefault(); 
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
         HandlebarsIntl.registerWith(Handlebars);
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
        
        
         Handlebars.registerHelper("ifEquals",function(attrib,options){
			 var fnTrue = options.fn,
        	fnFalse = options.inverse;
           return (attrib !== undefined && attrib.trim() !== '0')?fnTrue():fnFalse();
              
        });
        
        Handlebars.registerHelper("moreImages",function(index,options){
			 var fnTrue = options.fn,
        	fnFalse = options.inverse;
           return (index < 6)?fnTrue():fnFalse();
              
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
    

   /* $.get("/services/cart",jsonData,function(){

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

    });*/
}

function addItemToWishList(sku){
     var jsonData={};
    jsonData['itemDetails']={};
    var itemDetails={};
    
    itemDetails['product_sku']=sku;
    itemDetails['store_id']='1';
    itemDetails['website_id']='1';
    showLoadingScreen();
    
        itemDetails['customer_id']=getCustomerIdFromCookie();
         jsonData['itemDetails']=itemDetails;         
         Frontier.MagentoServices.addItemToWishList(jsonData).done(function(wishlist){
             hideLoadingScreen();
             console.log("added to whishlist");
              $('#message-'+sku).html("Item added to your WishList!");
              $('#message-'+sku).fadeIn('fast').delay(3000).fadeOut('fast');
         }).fail(function(error){
              console.log("error "+error);
              hideLoadingScreen();
            enableErrorMsg(error.status);
         });
     
}

function retrieveRequisitionList(thisObj){
     Frontier.MagentoServices.getRequisitionList(serverURL).done(function(list){
         $.each(list,function(key,value){
              thisObj.append($('<option/>',
                                    {'value':value.id,'text':value.name}));
         });
        
     }).fail(function(error){
             hideLoadingScreen();
            enableErrorMsg(error.status);
    });
}

function addRequisitionList(reqid,prodid,qty,sku){
    var jsonData={};
   
    var reqItems={};
    reqItems['reqlistId']=reqid;
    reqItems['skuId']=prodid;
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