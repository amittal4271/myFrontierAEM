$(document).ready(function(){
console.log("product list page...");
    
    getProductListByCategory(1,28,'featured');
    
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
    
    $(document).on('change','#itemPerPageSelect',function(){
        var recsPerPage = $(this).val();
          var currentPage = 1;
        var sortBy = $('#sortBy').val();
        getProductListByCategory(currentPage,recsPerPage,sortBy);
       
    });
    
    $(document).on('click','.pagination-next.pagination-arrow',function(e){
        e.preventDefault();
        var currentPage = parseInt($('#currentPage').val());
      
        var pageTotal = parseInt($('#totalPage').val());
        var sortBy = $('#sortBy').val();
        if(currentPage < pageTotal){
             currentPage = currentPage + 1;
            var recsPerPage = $('#itemPerPageSelect').val();
            getProductListByCategory(currentPage,recsPerPage,sortBy);
        }
       
       
    });
    
     $(document).on('click','.pagination-previous.pagination-arrow',function(e){
         e.preventDefault();
        var disabled = $(this).hasClass('disabled');
         if(!disabled){
            var currentPage = parseInt($('#currentPage').val());
             if(currentPage > 1){
             var prevPage = currentPage - 1;

                  var pageTotal = parseInt($('#totalPage').val());
                 var recsPerPage = $('#itemPerPageSelect').val();
                  var sortBy = $('#sortBy').val();
                getProductListByCategory(prevPage,recsPerPage,sortBy);

             }
         }
       
       
    });
    
    $(document).on('change','#sortBy',function(){
        var sortBy = $(this).val();
       
        var currentPage=1;
        var recsPerPage = $('#itemPerPageSelect').val();
        getProductListByCategory(currentPage,recsPerPage,sortBy);
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

function getProductListByCategory(currentPage,recsPerPage,sortBy){
    var jsonData={};
    showLoadingScreen();
    
    
    jsonData['currentPage']=currentPage;
    jsonData['categoryId']=$('#categoryId').val();
    jsonData['noOfRecsPerPage']=recsPerPage;
   if(sortBy !== undefined && sortBy !== ''){
       if(sortBy == "featured"){
           jsonData['sortByFeatured']="DESC";
       }else if(sortBy == "newproduct" ){
           jsonData['newProduct']="DESC";
       }else{
       jsonData['sortByPrice']=sortBy;
       }
   }
    
    
    Frontier.MagentoServices.getProductListByCategory(jsonData).done(function(productList){
       
     
       
             hideLoadingScreen();
           
             var template = $("#productlistTemplate").html();
        
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
           return (name !== undefined && name !== "Category")?fnTrue():fnFalse();
        });

        
       var html = Handlebars.compile(template);
      
      
        var processedHTML = html(productList)
     
       
        $('#productlisttemplate').empty();
        $('#productlisttemplate').html(processedHTML); 
       
        $('#itemPerPageSelect option[value='+recsPerPage +']').prop('selected',true);
         if(sortBy !== undefined && sortBy !== ''){
            $('#sortBy option[value='+ sortBy+']').prop('selected',true);
         }
        
         var $el = $('#plp-search-header-holder');
        scrollToElement($el);
        
        
        setTimeout(function() {
                   adjustHeight();
               }, 500);
        
    //enable/disable previous button - Pagination
     if(currentPage > 1){
           $('#previous').removeClass('disabled');
       }else{
           $('#previous').addClass('disabled');
       }
       
        
    }).fail(function(error){
         hideLoadingScreen();
        enableErrorMsg(error.status);
    });
   
}



function adjustHeight(){
    var byRow = $('#product-grid').hasClass('match-height');
   $('#product-grid').each(function() {
       $(this).children('.product-grid-item').matchHeight({
           byRow: byRow
       });
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