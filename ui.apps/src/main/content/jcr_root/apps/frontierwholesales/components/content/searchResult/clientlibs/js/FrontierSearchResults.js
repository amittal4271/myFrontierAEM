//namespace support
var Frontier = Frontier || {};
Frontier.SearchResults = Frontier.SearchResults || {};
//end namespace support

Frontier.SearchResults = new function() {
	
	function init() {
		console.log("Frontier Search Results init");
		if(typeof embeddedFrontierSearchResults !== 'undefined') {
			updateResults(embeddedFrontierSearchResults);
		}
	}
	
	function updateResults(products) {
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
	    
//	    $('#itemPerPageSelect option[value='+recsPerPage +']').prop('selected',true);
//        if(sortBy !== undefined && sortBy !== ''){
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
