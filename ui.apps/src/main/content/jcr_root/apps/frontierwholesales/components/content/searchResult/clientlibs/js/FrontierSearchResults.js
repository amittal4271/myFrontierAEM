//namespace support
var Frontier = Frontier || {};
Frontier.SearchResults = Frontier.SearchResults || {};
//end namespace support

Frontier.SearchResults = new function() {
	
	function init() {
		console.log("Frontier Search Results init");
	}
	
	function updateResults(products) {
		
		$(".plp-search-text-heading").html('Search Results for "'+$(".search-input").val()+'"');
		var template = $("#productlistTemplate").html();
	    
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


	    
	   var html = Handlebars.compile(template);
	  
	  
	    var processedHTML = html(products)
	 
	   
	    $('#productlisttemplate').empty();
	    $('#productlisttemplate').html(processedHTML); 
	    
	    var pageSize = getParameterByName("searchCriteria[pageSize]");
		$('#itemPerPageSelect option[value='+pageSize +']').prop('selected',true);

//	    if(sortBy !== undefined && sortBy !== ''){
//           $('#sortBy option[value='+ sortBy+']').prop('selected',true);
//        }
       
        var $el = $('#plp-search-header-holder');
       scrollToElement($el);
       
       
       setTimeout(function() {
                  adjustHeight();
              }, 500);
       
   //enable/disable previous button - Pagination
//    if(currentPage > 1){
//          $('#previous').removeClass('disabled');
//      }else{
//          $('#previous').addClass('disabled');
//      }
       
       

       $(document).on('change','#itemPerPageSelect',function(){
    	   Frontier.SearchController.updateResults();
       });
       
       $(document).on('click','.pagination-next.pagination-arrow',function(e){
           e.preventDefault();
           var currentPage = parseInt($('#currentPage').val());
         
           var pageTotal = parseInt($('#totalPage').val());
           var sortBy = $('#sortBy').val();
           if(currentPage < pageTotal){
                currentPage = currentPage + 1;
               var recsPerPage = $('#itemPerPageSelect').val();
               Frontier.SearchController.updateResults(currentPage);
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
                     Frontier.SearchController.updateResults(prevPage);
                }
            }
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
	
	this.updateResults = updateResults;
	
	$(document).ready(init);
}();