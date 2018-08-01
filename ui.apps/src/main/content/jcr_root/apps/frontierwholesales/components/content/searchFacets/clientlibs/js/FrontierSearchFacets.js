//namespace support
var Frontier = Frontier || {};
Frontier.SearchFacets = Frontier.SearchFacets || {};
//end namespace support

Frontier.SearchFacets = new function() {

	var filters = [],
		facetSelector = "#plp-search-left-nav-filters select, #plp-search-left-nav-filters input";
	
	function init() {
		console.log("Frontier Search Facets init");
				
		$(facetSelector).change(function(event) {
			console.log("Filter Changed, new filter list... ", getFilters());
			Frontier.SearchController.updateResults();
		});
		
		$(facetSelector).change(function(){
		    	Frontier.SearchController.updateResults();
	    });
	}
	
	function addFilter(name, value) {
		var filter = {
				name: name,
				value: value
		}
		
		filters.push(filter);
	}
	
	function getFilters() {
		filters = [];
		
		$.each($(facetSelector), function( key, filterElem ) {
			var value = "";
			
			if($(this).attr("type") == "checkbox") {
				if(this.checked) {
					value = "1";
				} else {
					value = "";
				}
			} else {
				value = $(filterElem).val()
			}
			
			if($.isArray(value)) {
				$.each(value, function(key, filterValue){
					addFilter($(filterElem).attr("name"), filterValue);
				});
			} else {
				addFilter($(filterElem).attr("name"), value);
			}
			
		});
		
		
		return filters;
	}
	//TODO
	/*
	 * Probably want a way to remember the selected filters and the value selected
	 * 
	 * 
	 */

	this.getFilters = getFilters;
	
	$(document).ready(init);
}();
