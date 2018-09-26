//namespace support
var Frontier = Frontier || {};
Frontier.SearchController = Frontier.SearchController || {};
//end namespace support

Frontier.SearchController = new function() {

	var filters = [],
		SORT_ORDER_ASC = "ASC",
		SORT_ORDER_DESC = "DESC";
	
	String.prototype.replaceAll = function(search, replacement) {
	    var target = this;
	    return target.split(search).join(replacement);
	};
	
	function init() {
		if(typeof Frontier.SearchResults !== 'undefined' && $(".searchResult").length > 0) {
			var searchTerm = getParameterByName("q");
			$(".search-input").val(searchTerm);
			
			var brandId = getParameterByName("brand");
			
			var searchQS ="";
			
			if(!!brandId) {
				searchQS = "searchCriteria[requestName]=quick_search_container&searchCriteria[filter_groups][1][filters][0][field]=manufacturer&searchCriteria[filter_groups][1][filters][0][value]="+brandId+"&searchCriteria[filter_groups][1][filters][0][condition_type]=eq&searchCriteria[filter_groups][2][filters][0][field]=status&searchCriteria[filter_groups][2][filters][0][value]=1&searchCriteria[filter_groups][2][filters][0][condition_type]=eq&searchCriteria[currentPage]=0&searchCriteria[pageSize]=28";
			} else {
				searchQS = getQueryString(1, true);
			}
			
			if(!!Frontier.SearchResults ){
				showLoadingScreen();
				Frontier.MagentoServices.searchProducts(searchQS).done(function(productList){
					Frontier.SearchResults.updateResults(productList);
					hideLoadingScreen();
				});
			}			
		}
	}
	
	function updateResults(pageNum) {
		showLoadingScreen();
		var queryStringForSearch = getQueryString(pageNum);
		Frontier.MagentoServices.searchProducts(queryStringForSearch).done(function(productList){
//			if (history.pushState) {
//			    var newurl = window.location.protocol + "//" + window.location.host + window.location.pathname + "?" + queryStringForSearch;
//			    window.history.pushState({path:newurl},'',newurl);
//			}
			
			Frontier.SearchResults.updateResults(productList);
			hideLoadingScreen();
		});
	}

	function getFilterParam(group_index, index, field, value, type) {
		return "searchCriteria[filter_groups][" + group_index + "][filters][" + index + "][field]=" + field + "&" +
			   "searchCriteria[filter_groups][" + group_index + "][filters][" + index + "][value]=" + value + "&" + 
			   "searchCriteria[filter_groups][" + group_index + "][filters][" + index + "][condition_type]=" + type;
	}
	
	function getQueryString(pageNum, searchTermOnly) {
		var queryString;
		
		if(!!Frontier.SearchFacets && !searchTermOnly) {
			var filters = Frontier.SearchFacets.getFilters();
			
			if(filters.length > 0) {
				searchTermOnly = false;
			} else {
				searchTermOnly = true;
			}
		}
		
		if(searchTermOnly) {
			queryString = "searchCriteria[requestName]=quick_search_container";
		} else {
			queryString = "searchCriteria[requestName]=advanced_search_container";
		}
		
		var searchTerm = $(".search-input").val();
		if(!!searchTerm) {
			if(searchTermOnly) {
				queryString += "&"+ getFilterParam(0,0,"search_term",encodeURIComponent(searchTerm), "like");
			} else {
				queryString += "&"+ getFilterParam(0,0,"botanicalName",encodeURIComponent("%"+searchTerm+"%"), "like");
				queryString += "&"+ getFilterParam(0,1,"name",encodeURIComponent("%"+searchTerm+"%"), "like");
			}
		}
		
		var filtersQueryString = "";
		var groupIndex = 0;
		if(!!Frontier.SearchFacets && !searchTermOnly) {
			
			var filters = Frontier.SearchFacets.getFilters();
			
			if(filters.length > 0) {
				groupIndex++;
			}
			
			var paramCount = 1;
			var groupItemIndex = 0;
			var previousFilterName = null;
			
			$.each(filters, function( key, filter ) {
				if(!!filter.value) {
					if(previousFilterName != null && previousFilterName != filter.name || (previousFilterName == filter.name && filter.type == "AND") ) {
						groupIndex++;
						groupItemIndex = 0;
					} 
					
					if(previousFilterName == filter.name && filter.type != "AND") {
						groupItemIndex++;
					}
					
					if(paramCount > 1) {
						filtersQueryString += "&";
					}
					filtersQueryString += getFilterParam(groupIndex, groupItemIndex, filter.name, filter.value, "eq");
					
					previousFilterName = filter.name;
					
					paramCount++;
				}
			});
			
			if(filtersQueryString.length > 0) {
				queryString += "&"+filtersQueryString ;
			} 
			
		} 
		
		groupIndex++;
		queryString += "&" + getFilterParam(groupIndex, 0, "status", "1", "eq");
		
		var recsPerPage;
		
		
		recsPerPage = $('#itemPerPageSelect').val();

		if(typeof recsPerPage === 'undefined') {
			recsPerPage = 28;
		}
		
		if(typeof pageNum === 'undefined' || pageNum == '' || pageNum == null || pageNum < 1) {
			pageNum = 1;
		}
				
		try {
			pageNum = pageNum - 1;
		} catch(error) {
			console.log("pagination error ", error);
		}
		
		queryString += "&searchCriteria[currentPage]="+pageNum;
		queryString += "&searchCriteria[pageSize]="+recsPerPage;
		
		return queryString;
	}
	
	this.getQueryString = getQueryString;
	this.updateResults = updateResults;
	
	$(document).ready(init);
}();
