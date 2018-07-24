//namespace support
var Frontier = Frontier || {};
Frontier.SearchController = Frontier.SearchController || {};
//end namespace support

Frontier.SearchController = new function() {

	var filters = [];

	function init() {
		console.log("Frontier Search Controller init");
		$("document").bind("facet-changed", function() { console.log( "facet was changed " + getQueryString()  ); });
	}
	
	function updateResults() {
		console.log( "Refreshing Search... " + getQueryString());
	}

	function getQueryString() {
		var queryString = "";
		
		var filters = Frontier.SearchFacets.getFilters();
		$.each(filters, function( key, filter ) {
			if(!!filter.value) {
				queryString += filter.name + "=" + filter.value +"&";
			}
		});
		
		queryString = queryString.slice(0, -1);  // remove trailing &
		
		return queryString;
	}
	
	this.getQueryString = getQueryString;
	this.updateResults = updateResults;
	
	$(document).ready(init);
}();
