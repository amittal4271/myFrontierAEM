var filterGroups;
var filterIds=[];
var facetsquery='';
$(document).ready(function(){
console.log("product list page..."+facetsquery);
    
    getProductListByCategory(1,28,'featured',facetsquery);
    
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
        getProductListByCategory(currentPage,recsPerPage,sortBy,facetsquery);
       
    });
    
    $(document).on('click','.pagination-next.pagination-arrow',function(e){
        e.preventDefault();
        var currentPage = parseInt($('#currentPage').val());
      
        var pageTotal = parseInt($('#totalPage').val());
        var sortBy = $('#sortBy').val();
        if(currentPage < pageTotal){
             currentPage = currentPage + 1;
            var recsPerPage = $('#itemPerPageSelect').val();
            getProductListByCategory(currentPage,recsPerPage,sortBy,facetsquery);
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
                getProductListByCategory(prevPage,recsPerPage,sortBy,facetsquery);

             }
         }
       
       
    });
    
    $(document).on('change','#sortBy',function(){
        var sortBy = $(this).val();
       
        var currentPage=1;
        var recsPerPage = $('#itemPerPageSelect').val();
        if(undefined == recsPerPage){
            recsPerPage = $('#totalcount').val();
        }
        getProductListByCategory(currentPage,recsPerPage,sortBy,facetsquery);
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
   
    
    $(document).on('click','.filter-link.checkbox-link',function(){ 
        filterIds=[];
        var page = $('#currentPage').val();
        if(undefined == page){
            page = 1;
        }
        var currentPage = parseInt(page);
        var recsPerPage = $('#itemPerPageSelect').val();
        
        if(undefined == recsPerPage){
            recsPerPage = $('#totalcount').val();
        }
        
        var sortBy = $('#sortBy').val();
        if(!$(this).hasClass('selected-filter')) {
            $(this).addClass('selected-filter');
           
        }else{
            $(this).removeClass('selected-filter'); 
          
            
        } 
        var searchString = constructFilterConditions();
        getProductListByCategory(currentPage,recsPerPage,sortBy,searchString);
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

function getProductListByCategory(currentPage,recsPerPage,sortBy,searchString){
    var jsonData={};
    showLoadingScreen();
    
    jsonData['facetQuery']=searchString;
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
         initHandlbarFunctions();

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

        filterGroups = productList.search_criteria.filter_groups;
        
        //retain checkbox selections
        retainFilterChkboxSelections();
        
    }).fail(function(error){
         hideLoadingScreen();
        enableErrorMsg(error.status);
    });
   
}

function initHandlbarFunctions(){
    
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

}

function retainFilterChkboxSelections(){
    $.each(filterIds,function(index,data){
        $('#'+data).addClass('selected-filter');
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


function constructFilterConditions(){
    var jsonData=[];
    var temp='';
    var group={};
    var filterValues=[];
    var jsonData={};
    var groupIdx = 1;
    var index = 0;
    var queryString='';
    $('#plp-search-left-nav-filters').find('.selected-filter').each(function(i,data){
       var code = $(this).parent().parent().data('code'); 
        var data = $(this).data('value').toString();
         filterIds.push($(this).attr('id'));

        if(temp != code){
			groupIdx++;
			index=0;
        }else{
			index++;
        }
         queryString+=getFilterParam(groupIdx,index,code,data,''); 
        temp = code;


    });
    facetsquery = queryString;
    return queryString;
}


function getFilterParam(group_index, index, field, value, type) {
		var searchCriteria ="&searchCriteria[filter_groups][" + group_index + "][filters][" + index + "][field]=" + field + "&" +
			   "searchCriteria[filter_groups][" + group_index + "][filters][" + index + "][value]=" + value  ;
            
        if( type != ''){
           searchCriteria = searchCriteria +"&searchCriteria[filter_groups][" + group_index + "][filters][" + index + "][condition_type]=" + type;
        }
    return searchCriteria;
}