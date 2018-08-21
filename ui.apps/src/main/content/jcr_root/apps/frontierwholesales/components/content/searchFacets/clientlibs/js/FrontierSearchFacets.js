//namespace support
var Frontier = Frontier || {};
Frontier.SearchFacets = Frontier.SearchFacets || {};
//end namespace support

Frontier.SearchFacets = new function() {

	var filters = [],
		facetSelector = ".searchResult #plp-search-left-nav-filters .checkbox-link";
	
	function init() {			
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
	
	function getFilterDisplayText(filtername, id) {
		return $(".each-filters-list[data-code='"+filtername+"'] .checkbox-link[data-value='"+id+"']").find(".text").text();
	}
	
	function selectFilter(filtername, id) {
		var filterCheckbox = $(".each-filters-list[data-code='"+filtername+"'] .checkbox-link[data-value='"+id+"']");
		if(!filterCheckbox.hasClass("selected-filter")){
			filterCheckbox.addClass("selected-filter");
		}
	}

	this.getFilters = getFilters;
	this.getFilterDisplayText = getFilterDisplayText;
	this.selectFilter = selectFilter;
	
	$(document).ready(init);
}();
