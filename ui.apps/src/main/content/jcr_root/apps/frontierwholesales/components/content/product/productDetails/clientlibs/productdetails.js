$(document).ready(function(){
console.log("product details page...");
  if($('#product-detail').length > 0){
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

     $(document).on('focus','.requisition-list-select',function(e){
       e.preventDefault(); 
        $(this).removeClass('closed');
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
            $(this).addClass('closed').blur();
        }
        
    });
    //  close when clicked outside of shelves dropdown
    $(document).mouseup(function(e) {
        var shelfDropdown = $(".requisition-list-select");
        if (!shelfDropdown.is(e.target) && shelfDropdown.has(e.target).length === 0) {
            shelfDropdown.addClass('closed');
        }
	});
  }

});

function getAttributeDataFromTxtBox(id){
    var attribute = $('#'+id).val();
    var splitArray;
    if(attribute !== undefined){
     splitArray = attribute.split(",");
    }
    return splitArray;
}

function getProductDetails(){
    var jsonData={};
    showLoadingScreen();
   
   var summaryAttribute =  getAttributeDataFromTxtBox('summaryAttribute');
    var infoAttribute = getAttributeDataFromTxtBox('infoAttribute');
    var additionalAttribute = getAttributeDataFromTxtBox('additionalAttribute');
    var summaryLabel = $('#summaryAttributeLabel').val();
     summaryLabel = $.parseJSON(summaryLabel);
if(summaryAttribute !== undefined && infoAttribute !== undefined && additionalAttribute !== undefined){
     var sku = $('#productId').val();
    if(sku == undefined || sku == ''){
        return false;
    }
    sku = sku.split('/');
    
    sku = sku[sku.length-1];
    jsonData['sku']=sku;
    jsonData['currentPagePath'] = $('#currentPagePath').val();
   
    $.ajax({
        url: "/services/pdp",
        data:jsonData,
        headers:{
            'Authorization':getUserToken()
        }
    }).done(function(results){
        hideLoadingScreen();
        if(!results.startsWith("Error")){
        var productDetails = JSON.parse(results);
       
        var template = $("#productDetailsTemplate").html();
       
         HandlebarsIntl.registerWith(Handlebars);
       
        registerHandleBarHelpers(summaryAttribute,infoAttribute,additionalAttribute,summaryLabel);

        var html = Handlebars.compile(template);

        var processedHTML = html(productDetails)
       

        $('#product-detail').empty();
        $('#product-detail').html(processedHTML);
        if ($('#product-detail').length > 0) {
    		bindPdpAffixEvents();
    	}
        }else{
             hideLoadingScreen();
            enableErrorMsg('');
        }
    }).fail(function(error){
         hideLoadingScreen();
        enableErrorMsg(error.status);
    });
}
}

function registerHandleBarHelpers(summaryAttribute,infoAttribute,additionalAttribute,summaryLabel){
    var bCount = 0;
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
    
       Handlebars.registerHelper("getSummaryAttrLabel",function(attCode){
           var label="";
          
           $.map(summaryLabel,function(value,key){ 
               if(key == attCode){ 
                   label = value;
                } 
           });
           
            return label;
                                         
        });
         Handlebars.registerHelper("getInfoAttrValues",function(attCode,value,options){
            var idx = $.inArray(attCode,infoAttribute);
            if(idx != -1){
                if(value.trim() !=""){
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
        
        Handlebars.registerHelper("priceCheck",function(price,options){
                 var fnTrue = options.fn,
                fnFalse = options.inverse;
               return (price !== undefined && price >=0 )?fnTrue():fnFalse();
            });
}



