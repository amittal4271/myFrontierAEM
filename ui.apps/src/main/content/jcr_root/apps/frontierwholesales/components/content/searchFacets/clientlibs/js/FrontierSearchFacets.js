//namespace support
var Frontier = Frontier || {};
Frontier.SearchFacets = Frontier.SearchFacets || {};
//end namespace support

Frontier.SearchFacets = new function() {

	var filters = [],
		facetSelector = ".filter-link.checkbox-link",
		templates = {
		        facetTemplate:
		        	'<div class="each-filter-section"> \
				        <h2 class="filter-heading"><label for="id_{{attribute_code}}">{{facetLabel}}</label></h2> \
				        <ul data-code="{{attribute_code}}" class="each-filters-list"> \
		        			{{#each options as |option|}} \
		        			<li class="filter-item {{#if option.isVisuallyHidden}}visuallyhidden{{/if}}"> \
								<a href="#" id="{{option.value}}" data-value="{{option.value}}" class="filter-link checkbox-link"><span class="text">{{option.name}}</span></a> \
		        			</li> \
		        			{{#if ../isMoreThanFive}} \
		        			{{#if ../isExpandable}} \
		        			{{#if @last}} \
		        			<li class="filter-item filter-item-expand"> \
								<button type="button" class="btn btn-view-more-filters"> \
								     <span class="view-more-text">View More</span> \
								</button> \
							</li> \
		        			{{/if}} \
				        	{{/if}} \
				        	{{/if}} \
		        			{{/each}} \
				        </ul> \
			        </div>'
		};
	
	function hydrateTemplateObject(buckets, bucketName, facetLabel, isExpandable) {		
		for(i=0 ; i<=buckets.bucket.length-1 ; i++) {
        	var bucket = buckets.bucket[i];
        	if(bucket.value != null) {
        		for(j=0 ; j<=bucket.value.length-1 ; j++) {
            		var option = bucket.value[j];
                	
            		if(j>=5) {
            			option.isVisuallyHidden = true;
            		}
            	}
            	
            	if(bucket.name == bucketName) {
            		var facetHydrate = {
            				attribute_code : bucket.name.replace("_bucket",""),
            				facetLabel : facetLabel,
            				options: bucket.value,
            				isExpandable: isExpandable,
            				isMoreThanFive: bucket.value != null && bucket.value.length >5 ? true : false
            		};
            		return facetHydrate;
            	}
        	}
		}
		return {};
	}
	
	function init() {			
		initListeners();
	}
	
	function initListeners() {
		if($(".searchResult").length > 0) {
			$(document).on('click',facetSelector,function(event){ 	
		        event.preventDefault();
				if(!$(this).hasClass('selected-filter')) {
		            $(this).addClass('selected-filter');
		        }else{
		            $(this).removeClass('selected-filter');
		            var value = $(this).attr("data-value");
		            var filterName = $(this).closest(".each-filters-list").attr("data-code");
		            clearFilter(filterName, value);
		        } 
				$("#mobile-filters-holder").hide();
				$("#mobile-filters-overlay").hide();
		        Frontier.SearchController.updateResults();
		    });
		}
	}
	
	function updateFilterOptions(buckets) {		
		if(typeof buckets.bucket !== "undefined" && buckets.bucket != null && buckets.bucket.length > 0) {
			for(i=0 ; i<=buckets.bucket.length-1 ; i++){
	        	var bucket = buckets.bucket[i];
			}
			
			var facetTemplate = Handlebars.compile(templates.facetTemplate);
			
			
			$("#plp-search-left-nav-filters").html("");
			
			var manufacturerFacetObject = hydrateTemplateObject(buckets, "manufacturer_bucket", "Brand", true);
			
			if(!!manufacturerFacetObject.options) {
				$("#plp-search-left-nav-filters").append(facetTemplate(manufacturerFacetObject));
			}
		
			var certificationsFacetObject = hydrateTemplateObject(buckets, "certifications_bucket", "Certifications", false);
			if(!!certificationsFacetObject.options) {
				$("#plp-search-left-nav-filters").append(facetTemplate(certificationsFacetObject));
			}		
		}
		
		$(".each-filter-section").removeClass("hide");
	}
	
	function addFilter(name, value) {
		var filter = {
				name: name,
				value: value,
				type: name != "certifications" ? 'OR' : 'AND'
		}
		
		var found = false;
		for(var i = 0; i < filters.length; i++) {
		    if (filters[i].name == name && filters[i].value === value) {
		        found = true;
		        break;
		    }
		}
		
		if(!found) {
			filters.push(filter);
		}
	}
	
	function getFilters() {
		filters = [];
		
		$.each($(facetSelector+".selected-filter"), function( key, filterElem ) {
			addFilter($(this).closest(".each-filters-list").attr("data-code"), $(this).attr("data-value"));
		});
				
		return filters;
	}
	
	function getFilterDisplayText(filtername, id) {
		
		var filterCheckboxes = $(".each-filters-list[data-code='"+filtername+"'] .checkbox-link[data-value='"+id+"']");
		
		var displayText = "";
		$.each(filterCheckboxes,function(){
			displayText = $(this).find(".text").text();
		});
		
		return displayText;
	}
	
	function selectFilter(filtername, id) {
		var filterCheckbox = $(".each-filters-list[data-code='"+filtername+"'] .checkbox-link[data-value='"+id+"']");
		filterCheckbox.addClass("selected-filter");
	}
	
	function clearFilter(filtername, id) {
		var filterCheckbox = $(".each-filters-list[data-code='"+filtername+"'] .checkbox-link[data-value='"+id+"']");
		filterCheckbox.removeClass("selected-filter");
	}

	this.getFilters = getFilters;
	this.getFilterDisplayText = getFilterDisplayText;
	this.selectFilter = selectFilter;
	this.updateFilterOptions = updateFilterOptions;
	
	$(document).ready(init);
}();
