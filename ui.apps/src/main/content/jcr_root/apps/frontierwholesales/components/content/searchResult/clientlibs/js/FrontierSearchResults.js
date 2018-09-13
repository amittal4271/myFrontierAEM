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
				
		if(products != null && !!products.search_criteria) {
			products.search_criteria.current_page = products.search_criteria.current_page + 1;
		}
		
		if(products == null) {
			products = {};
		}
		
		if($(".searchResult").length > 0) {
			var filterGroups = [];
			
			var filterCount = 0;
			var searchTerm;
			var brandIds = [];
			var certIds = [];
			
			if(typeof Frontier.SearchFacets != "undefined" && products != null && typeof products.buckets != "undefined") {
				Frontier.SearchFacets.updateFilterOptions(products.buckets);
			}
			
			if(products != null && typeof products.search_criteria !== "undefined") {
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
				    		
				    		if(filterGroupAttribute.field == "certifications") {
				    			certIds.push(filterGroupAttribute.value);
				    		}
				    		
				    		if(filterGroupAttribute.field == "search_term") {
				    			searchTerm = filterGroupAttribute.value;
				    			searchTerm = searchTerm.replaceAll("%", "");
				    		}
				    		
				    		if(filterGroupAttribute.field == "name") {
				    			searchTerm = filterGroupAttribute.value;
				    			searchTerm = searchTerm.replaceAll("%", "");
				    		}
				    		
				    	});
				    });
				});
			}
						
			if(!!searchTerm) {
				products.searchTerm = decodeURI(searchTerm);
				filterCount++;
				$(".search-input").val(searchTerm);
			} else {
				products.searchTerm = "";
			}
			
			if(brandIds.length > 0) {
				if(!!Frontier.SearchFacets) {
					$(brandIds).each(function(i, idValue) {
						if(filterCount > 0) {
							products.searchTerm += ", ";
						}
						
						var displayValue = Frontier.SearchFacets.getFilterDisplayText("manufacturer",idValue);
						products.searchTerm += displayValue.replaceAll("&#039;","'");
						filterCount++;
					});				
				}
			}
			
			if(certIds.length > 0) {
				if(!!Frontier.SearchFacets) {
					$(certIds).each(function(i, idValue) {
						if(filterCount > 0) {
							products.searchTerm += ", ";
						}
						
						var displayValue = Frontier.SearchFacets.getFilterDisplayText("certifications",idValue);
						products.searchTerm += displayValue.replaceAll("&#039;","'");
						filterCount++;
					});				
				}
			}
			
			console.log("search results",products);
			
			var template = $("#productlistTemplate").html();
		    
		    var html = Handlebars.compile(template);
		  
		    var processedHTML = html(products)
		   
		    $('#productlisttemplate').empty();
		    $('#productlisttemplate').html(processedHTML); 
		    
		    var pageSize = products.search_criteria.page_size;
		    
			$('#itemPerPageSelect option[value='+pageSize +']').prop('selected',true);

	       
	        // var $el = $('#plp-search-header-holder');
	        // scrollToElement($el);


			// preserve scroll position on back from pdp to clp
            var pathName = document.location.pathname;
    
            $(document).click(function(e) {
    
              if ( $(e.target).hasClass('grid-item-link') || ($(e.target).parents('.grid-item-link').length)) {
                // hide menu here
                  var scrollPosition = $(e.target).offset().top;
                  sessionStorage.setItem("scrollPosition_" + pathName, scrollPosition.toString());
              } else {
                sessionStorage.setItem("scrollPosition_" + pathName, '0');
              }
    
            });
    
            $( document ).ready(function() {
    
                setTimeout(function() {
                    if (sessionStorage["scrollPosition_" + pathName]) {
                     $(document).scrollTop(sessionStorage.getItem("scrollPosition_" + pathName));
                    }
    
                 }, 800);
            });


	       
	        setTimeout(function() {
	        	adjustHeight();
	        }, 500);

	        var currentPage = parseInt($('#currentPage').val());
	        
//			enable/disable previous button - Pagination
		    if(currentPage > 1){
		          $('#previous').removeClass('disabled');
			} else {
		          $('#previous').addClass('disabled');
			}
	       
		
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
