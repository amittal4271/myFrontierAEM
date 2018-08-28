//namespace support
var Frontier = Frontier || {};
Frontier.SearchResults = Frontier.SearchResults || {};
//end namespace support

Frontier.SearchResults = new function() {
	
	function init() {
		if($(".searchResult").length > 0) {
			initProductSearchHandlbarFunctions();
			initListenersForProductButtons();
			
	       $(document).on('change','#itemPerPageSelect,#sortBy',function(){
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
	       
	}
	
	
	String.prototype.replaceAll = function(search, replacement) {
	    var target = this;
	    return target.split(search).join(replacement);
	};
	
	function updateResults(products) {
		
		if($(".searchResult").length > 0) {
			var filterGroups = [];
			
			var searchTerm;
			var brandIds = [];
						
			if(typeof Frontier.SearchFacets != "undefined" && typeof products.buckets != "undefined") {
				Frontier.SearchFacets.updateFilterOptions(products.buckets);
			}
			
			if(typeof products.search_criteria !== "undefined") {
				filterGroups = products.search_criteria.filter_groups;
				jQuery.each( filterGroups, function(i,filterGroup) {
				    jQuery.each( filterGroup, function(j,filterGroupAttributes) {
				    	jQuery.each(filterGroupAttributes, function(k,filterGroupAttribute) {
				    		
				    		if(!!Frontier.SearchFacets) {
				    			Frontier.SearchFacets.selectFilter(filterGroupAttribute.field,filterGroupAttribute.value);
				    		}
				    		
				    		if(filterGroupAttribute.field == "manufacturer") {
				    			brandIds.push(filterGroupAttribute.value);
				    		}
				    		
				    		if(filterGroupAttribute.field == "search_term") {
				    			searchTerm = filterGroupAttribute.value;
				    			searchTerm = searchTerm.replaceAll("%", "");
				    		}
				    		
				    	});
				    });
				});
			}
						
			if(!!searchTerm) {
				products.searchTerm = decodeURI(searchTerm);
				$(".search-input").val(searchTerm);
			} else if(brandIds.length > 0) {
				if(!!Frontier.SearchFacets) {
					products.searchTerm = "";
					$(brandIds).each(function(i, idValue) {
						if(i>0) {
							products.searchTerm += ", ";
						}
						
						var displayValue = Frontier.SearchFacets.getFilterDisplayText("manufacturer",idValue);
						products.searchTerm += displayValue.replaceAll("&#039;","'");
					});				
				}
			}
			
			console.log("search results",products);
			
			var template = $("#productlistTemplate").html();
		    
		    var html = Handlebars.compile(template);
		  
		    var processedHTML = html(products)
		   
		    $('#productlisttemplate').empty();
		    $('#productlisttemplate').html(processedHTML); 
		    
		    var pageSize = getParameterByName("searchCriteria[pageSize]");
		    
			$('#itemPerPageSelect option[value='+pageSize +']').prop('selected',true);

			//pull sortby from query string // could also save in session storage or otherwise
			var sortBy = getParameterByName("searchCriteria[sortOrders][0][field]");
			var sortDirection = getParameterByName("searchCriteria[sortOrders][0][direction]");
					
			if(sortBy == "price") {
				sortBy = sortDirection;
	        } else if(sortBy == "created_at") {
	        	sortBy = "newproduct";
	        } else if(sortBy == "featured") {
	        	sortBy = "featured";
	        } 
			
		    if(sortBy !== null){
	           $('#sortBy option[value='+ sortBy+']').prop('selected',true);
	        }
	       
	        var $el = $('#plp-search-header-holder');
	        scrollToElement($el);
	       
	       
	        setTimeout(function() {
	        	adjustHeight();
	        }, 500);
	       
			//enable/disable previous button - Pagination
//		    if(currentPage > 1){
//		          $('#previous').removeClass('disabled');
//			} else {
//		          $('#previous').addClass('disabled');
//			}
//	       
		
		}
		
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
