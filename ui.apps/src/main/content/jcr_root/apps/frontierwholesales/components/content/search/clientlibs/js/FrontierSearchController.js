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
		console.log("Frontier Search Controller init");
		if(typeof Frontier.SearchResults !== 'undefined') {
			if(window.location.search != "") {
				
				console.log("doing initial search from incoming page load query string");
				
				// Get saved data from sessionStorage
				var searchTerm = sessionStorage.getItem('Frontier.searchTerm');
				if(!!searchTerm) {
					searchTerm = searchTerm.replaceAll("%", "");
					$(".search-input").val(searchTerm);
				}
				// Remove saved data from sessionStorage
				sessionStorage.removeItem('Frontier.searchTerm');
				
				if(!!Frontier.SearchResults){
					showLoadingScreen();
					Frontier.MagentoServices.searchProducts(window.location.search.slice(1, window.location.search.length)).done(function(productList){
						Frontier.SearchResults.updateResults(productList);
						hideLoadingScreen();
					});
				}			
			} else {
				showLoadingScreen();
				Frontier.MagentoServices.searchProducts(getQueryString()).done(function(productList){
					Frontier.SearchResults.updateResults(productList);
					hideLoadingScreen();
				});
			}
		}
		
		$(".search-form").submit(function(event) {
			event.preventDefault();
			// Save data to sessionStorage
			sessionStorage.setItem('Frontier.searchTerm', $(".search-input").val());
			window.location = "/content/frontierwholesales/en/search.html?"+getQueryString(null, true);
		});
	}
	
	function updateResults(pageNum) {
		showLoadingScreen();
		var queryStringForSearch = getQueryString(pageNum);
		console.log( "Refreshing Search... " + queryStringForSearch);
		Frontier.MagentoServices.searchProducts(queryStringForSearch).done(function(productList){
			if (history.pushState) {
			    var newurl = window.location.protocol + "//" + window.location.host + window.location.pathname + "?" + queryStringForSearch;
			    window.history.pushState({path:newurl},'',newurl);
			}
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
		var queryString = "";
		
		var searchTerm = $(".search-input").val();
		queryString += getFilterParam(0,0,"botanicalname",encodeURIComponent("%"+searchTerm+"%"), "like");
		queryString += "&"+ getFilterParam(0,0,"name",encodeURIComponent("%"+searchTerm+"%"), "like");
		
		var filtersQueryString = "";
		
		if(!!Frontier.SearchFacets && !searchTermOnly) {
			var filters = Frontier.SearchFacets.getFilters();
			var groupIndex = 1;
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
		if(!searchTermOnly) {
			recsPerPage = $('#itemPerPageSelect').val();
		}

		if(typeof recsPerPage === 'undefined') {
			recsPerPage = 28;
		}
		
		if(typeof pageNum === 'undefined' || pageNum == '' || pageNum == null || pageNum < 1 || searchTermOnly) {
			pageNum = 1;
		}
		
		var sortBy;
		if(!searchTermOnly) {
			sortBy = $("#sortBy").val();
		}
		
		featured ="&searchCriteria[sortOrders][0][field]=featured&searchCriteria[sortOrders][0][direction]=DESC";
		newProduct="&searchCriteria[sortOrders][0][field]=created_at&searchCriteria[sortOrders][0][direction]=DESC";
		
		if(sortBy == "asc" || sortBy == "desc") {
			queryString += "&searchCriteria[sortOrders][0][field]=price&searchCriteria[sortOrders][0][direction]="+sortBy;
        } else if(sortBy == "featured") {
        	queryString += featured;
        } else if(sortBy == "newproduct") {
        	queryString += newProduct;
        } else {
        	queryString += featured; //default
        }
		
		queryString += "&searchCriteria[currentPage]="+pageNum;
		queryString += "&searchCriteria[pageSize]="+recsPerPage;
		
		return queryString;
	}
	
	this.getQueryString = getQueryString;
	this.updateResults = updateResults;
	
	$(document).ready(init);
}();
