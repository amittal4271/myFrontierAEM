$(document).ready(function(){
console.log("product list page...");
    
    getProductListByCategory(1,28,'');
    
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
        var sortByPrice = $('#sortByPrice').val();
        getProductListByCategory(currentPage,recsPerPage,sortByPrice);
       
    });
    
    $(document).on('click','.pagination-next.pagination-arrow',function(){
        var currentPage = parseInt($('#currentPage').val());
       
        var pageTotal = parseInt($('#totalPage').val());
        var sortByPrice = $('#sortByPrice').val();
        if(currentPage < pageTotal){
             currentPage = currentPage + 1;
            var recsPerPage = $('#itemPerPageSelect').val();
            getProductListByCategory(currentPage,recsPerPage,sortByPrice);
        }
       
    });
    
     $(document).on('click','.pagination-previous.pagination-arrow',function(){
        var currentPage = parseInt($('#currentPage').val());
         if(currentPage > 1){
         var prevPage = currentPage - 1;
              
              var pageTotal = parseInt($('#totalPage').val());
             var recsPerPage = $('#itemPerPageSelect').val();
              var sortByPrice = $('#sortByPrice').val();
            getProductListByCategory(prevPage,recsPerPage,sortByPrice);
             
         }
       
       
    });
    
    $(document).on('change','#sortByPrice',function(){
        var sortByPrice = $(this).val();
        var currentPage = parseInt($('#currentPage').val());
        var recsPerPage = $('#itemPerPageSelect').val();
        getProductListByCategory(currentPage,recsPerPage,sortByPrice);
    });
   
});

function getProductListByCategory(currentPage,recsPerPage,sortByPrice){
    var jsonData={};
    showLoadingScreen();
    jsonData['currentPage']=currentPage;
    jsonData['categoryId']=15;
    jsonData['noOfRecsPerPage']=recsPerPage;
   if(sortByPrice !== undefined && sortByPrice !== ''){
       jsonData['sortByPrice']=sortByPrice;
   }
    $.ajax({
        url: "/services/productlist",
        data:jsonData
    }).done(function(results){
       console.log(results); 
        hideLoadingScreen();
        var productList = JSON.parse(results);
       
        var template = $("#productlistTemplate").html();
      var categoryTemplate = $('#categoriesTemplate').html();
       
        Handlebars.registerHelper("recordsPerPage",function(recsPerPage,page){
            var recordsPerPage = recsPerPage * page;
            return recordsPerPage;
        });
        
       var html = Handlebars.compile(template);
        var categoryHTML = Handlebars.compile(categoryTemplate);
        
       var catProcessedHTML = categoryHTML(productList);
        var processedHTML = html(productList)
       
        $('#categorytemplate').html(catProcessedHTML);
        $('#plp-search-product-grid').html(processedHTML); 
        $('#itemPerPageSelect option[value='+recsPerPage +']').prop('selected',true);
         if(sortByPrice !== undefined && sortByPrice !== ''){
            $('#sortByPrice option[value='+ sortByPrice+']').prop('selected',true);
         }
       
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