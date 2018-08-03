//namespace support
var Frontier = Frontier || {};
Frontier.SearchFacets = Frontier.SearchFacets || {};
//end namespace support

Frontier.SearchFacets = new function() {

	var filters = [],
		facetSelector = "#plp-search-left-nav-filters .checkbox-link";
	
	function init() {
		console.log("Frontier Search Facets init");
			
		$(document).on('click',facetSelector,function(event){ 
	        event.preventDefault();
			if(!$(this).hasClass('selected-filter')) {
	            $(this).addClass('selected-filter');
	        }else{
	            $(this).removeClass('selected-filter');
	        } 
	        Frontier.SearchController.updateResults();
	    });
	}
	
	function addFilter(name, value) {
		var filter = {
				name: name,
				value: value,
				type: name != "certifications" ? 'OR' : 'AND'
		}
		
		filters.push(filter);
	}
	
	function getFilters() {
		filters = [];
		
		$.each($(facetSelector+".selected-filter"), function( key, filterElem ) {
			addFilter($(this).closest(".each-filters-list").attr("data-code"), $(this).attr("data-value"));
		});
				
		return filters;
	}

	this.getFilters = getFilters;
	
	$(document).ready(init);
}();
