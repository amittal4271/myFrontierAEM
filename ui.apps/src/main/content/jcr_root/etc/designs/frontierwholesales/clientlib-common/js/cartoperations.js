function getCartItems(templateId){
     var jsonData={};
    jsonData['action']='getCart';
    $.get("/services/cart",jsonData,function(){
        
    }).done(function(results){
          if(results.trim() !== 'Error in Cart'){
            var cart = JSON.parse(results);
       
           var template = $("#"+templateId).html();
              var cartTemplate = Handlebars.compile(template);
           var html = cartTemplate(cart.items.reverse());
           $('#minicarttemplate').html(html); 
          }
    });
}