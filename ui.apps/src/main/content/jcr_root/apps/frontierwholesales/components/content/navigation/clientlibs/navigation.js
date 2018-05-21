$(document).ready(function(){
    console.log('navigation here');
    getCategories();
});

function getCategories(){
    $.get("/services/products",function(){
        
    }).done(function(categories){
       console.log(categories); 
    });
}