$(document).ready(function(){
   $(document).on('click','.pagination-previous.pagination-arrow',function(e){
        var $this = $(this);
       goToPrevious($this)
       
   });
    
    $(document).on('click','.pagination-next.pagination-arrow',function(e){
       
        var $this = $(this);
        goToNext($this);
    });
});



function goToNext($this){
        var currentPage = parseInt($('#current-page').val())+1;
        var totalPages = parseInt($('#total-pages').val());
        if(currentPage <= totalPages){
          
            var url = window.currentPageURL+"/page/"+currentPage+".html/";
            location.href=url;
        }else{
             $this.addClass('disabled');
        }
}

function goToPrevious($this){
    
        var currentPage = parseInt($('#current-page').val())-1;
        if(currentPage >=1 ){
            var totalPages = parseInt($('#total-pages').val());

            var url = window.currentPageURL+"/page/"+currentPage+".html/";
            location.href=url;
        }else{
            $this.addClass('disabled');
        }
}