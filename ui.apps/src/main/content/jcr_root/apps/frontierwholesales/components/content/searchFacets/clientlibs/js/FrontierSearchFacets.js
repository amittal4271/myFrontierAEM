//namespace support
var Frontier = Frontier || {};
Frontier.SearchFacets = Frontier.SearchFacets || {};
//end namespace support

Frontier.SearchFacets = new function() {

	var filters = [],
		elem = null;
	
	function init() {
		console.log("Frontier Search Facets init");
		
		elem = $("#plp-search-left-nav-filters");
		
		$("#plp-search-left-nav-filters select").change(function(event) {
			console.log("Filter Changed, new filter list... ", getFilters());
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
		
		$.each($("#plp-search-left-nav-filters select"), function( key, selectFilter ) {
			addFilter($(selectFilter).attr("name"), $(selectFilter).val());
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
	this.elem = elem;
	
	$(document).ready(init);
}();
