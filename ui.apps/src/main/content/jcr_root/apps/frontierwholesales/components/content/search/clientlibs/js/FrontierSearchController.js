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
		var queryStringForSearch = getQueryString();
		console.log( "Refreshing Search... " + queryStringForSearch);
		Frontier.MagentoServices.searchProducts(queryStringForSearch).done(function(productList){
			Frontier.SearchResults.updateResults(productList);
		});
	}

	function getFilterParam(group_index, index, field, value, type) {
		return "searchCriteria[filter_groups][" + group_index + "][filters][" + index + "][field]=" + field + "&" +
			   "searchCriteria[filter_groups][" + group_index + "][filters][" + index + "][value]=" + value + "&" + 
			   "searchCriteria[filter_groups][" + group_index + "][filters][" + index + "][condition_type]=" + type;
	}
	
	function getQueryString() {
		var queryString = "";
		
		var searchTerm = $(".search-input").val();
		queryString += getFilterParam(0,0,"botanicalname",encodeURIComponent("%"+searchTerm+"%"), "like");
		queryString += "&"+ getFilterParam(0,0,"name",encodeURIComponent("%"+searchTerm+"%"), "like");
		
		queryString += "&";
		
		var filters = Frontier.SearchFacets.getFilters();
		var groupIndex = 1;
		$.each(filters, function( key, filter ) {
			if(!!filter.value) {
				queryString += getFilterParam(groupIndex, 0, filter.name, filter.value, "eq") +"&";
				groupIndex++;
			}
		});
		
		//queryString = queryString.slice(0, -1);  // remove trailing &
		
		queryString += "searchCriteria[currentPage]=1";
		queryString += "&searchCriteria[pageSize]=100";
		
		return queryString;
	}
	
	this.getQueryString = getQueryString;
	this.updateResults = updateResults;
	
	$(document).ready(init);
}();
