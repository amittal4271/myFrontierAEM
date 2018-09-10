var filterGroups;
var filterIds=[];
var facetsquery='';
$(document).ready(function(){
console.log("product list page..."+facetsquery);
  if($('.product-list-page').length > 0){  
    getProductListByCategory(1,28,'newproduct',facetsquery);
    
    initListenersForProductButtons();
    
    $(document).on('change','#itemPerPageSelect',function(){
        var recsPerPage = $(this).val();
          var currentPage = 1;
        var sortBy = $('#sortBy').val();
        getProductListByCategory(currentPage,recsPerPage,sortBy,facetsquery);
       
    });
    
    $(document).on('click','.pagination-next.pagination-arrow',function(e){
        e.preventDefault();
        var currentPage = parseInt($('#currentPage').val());

        var pageTotal = parseInt($('#totalPage').val());
        var sortBy = $('#sortBy').val();
        if(currentPage < pageTotal){
             currentPage = currentPage + 1;
            var recsPerPage = $('#itemPerPageSelect').val();
            getProductListByCategory(currentPage,recsPerPage,sortBy,facetsquery);
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
                getProductListByCategory(prevPage,recsPerPage,sortBy,facetsquery);

             }
         }
       
       
    });
    
    $(document).on('change','#sortBy',function(){
        var sortBy = $(this).val();
       
        var currentPage=1;
        var recsPerPage = $('#itemPerPageSelect').val();
        if(undefined == recsPerPage){
            recsPerPage = $('#totalcount').val();
        }
        getProductListByCategory(currentPage,recsPerPage,sortBy,facetsquery);
    });
    
    
   
    
    $(document).on('click','.filter-link.checkbox-link',function(){ 
        filterIds=[];
        var page = $('#currentPage').val();
        if(undefined == page){
            page = 1;
        }
        var currentPage = parseInt(page);
        var recsPerPage = $('#itemPerPageSelect').val();
        var itemPerPageVisible = $('#itemPerPageSelect').is(':visible');
        if(undefined == recsPerPage  && itemPerPageVisible){
            recsPerPage = $('#totalcount').val();
	   }else if(!itemPerPageVisible){
            recsPerPage='28';
        }
        
        var sortBy = $('#sortBy').val();
	   var sortByVisible = $('#sortBy').is(':visible');
        
        if(!sortByVisible){
            sortBy='newproduct';
        }
        if(!$(this).hasClass('selected-filter')) {
            $(this).addClass('selected-filter');
           
        }else{
            $(this).removeClass('selected-filter'); 
          
            
        } 
        var searchString = constructFilterConditions();
        getProductListByCategory(currentPage,recsPerPage,sortBy,searchString);
    });
  }
    
});



function getProductListByCategory(currentPage,recsPerPage,sortBy,searchString){
    var jsonData={};
    showLoadingScreen();
    
    jsonData['facetQuery']=searchString;
    jsonData['currentPage']=currentPage;
    var categoryId = $('#categoryId').val();
    if(categoryId == undefined || categoryId == ''){
       
         hideLoadingScreen();
        enableErrorMsg(error.status);
        return false;
    }
    categoryId = categoryId.split('/');
    
    categoryId = categoryId[categoryId.length-1];
    
    jsonData['categoryId']=categoryId;
    jsonData['noOfRecsPerPage']=recsPerPage;
   
   if(sortBy !== undefined && sortBy !== ''){
       if(sortBy == "newproduct" ){
           jsonData['newProduct']="DESC";
       }else{
           jsonData['sortByPrice']=sortBy;
       }
   }

    
    Frontier.MagentoServices.getProductListByCategory(jsonData).done(function(productList){
              
         hideLoadingScreen();

         var template = $("#productlistTemplate").html();
         initProductSearchHandlbarFunctions();

           var html = Handlebars.compile(template);


            var processedHTML = html(productList)


            $('#productlisttemplate').empty();
            $('#productlisttemplate').html(processedHTML); 

            $('#itemPerPageSelect option[value='+recsPerPage +']').prop('selected',true);
             if(sortBy !== undefined && sortBy !== ''){
                $('#sortBy option[value='+ sortBy+']').prop('selected',true);
             }


          //  var $el = $('#plp-search-header-holder');
          //  scrollToElement($el);

           
            setTimeout(function() {
                       adjustHeight();
                   }, 500);



$(function () {
            var pathName = document.location.pathname;
            window.onbeforeunload = function () {
                var scrollPosition = $(document).scrollTop();
                sessionStorage.setItem("scrollPosition_" + pathName, scrollPosition.toString());
            }
            if (sessionStorage["scrollPosition_" + pathName]) {
                $(document).scrollTop(sessionStorage.getItem("scrollPosition_" + pathName));
            }
        });



        //enable/disable previous button - Pagination
         if(currentPage > 1){
               $('#previous').removeClass('disabled');
           }else{
               $('#previous').addClass('disabled');
           }

        filterGroups = productList.search_criteria.filter_groups;
        
        //retain checkbox selections
        retainFilterChkboxSelections();
        
    }).fail(function(error){
         hideLoadingScreen();
        enableErrorMsg(error.status);
    });
   
}



function retainFilterChkboxSelections(){
   
    var bCheck = false;
    $.each(filterIds,function(index,data){
         $('#'+data).addClass('selected-filter');
       var number =  data.match(/(\d+)/g);
        if(number > 5){
            var title = data.match(/([a-z]+)/g);
            if(!bCheck){
            if(title == "manufacturer"){
                $('.Brand .btn-view-more-filters').trigger('click');
                bCheck = true;
            }else if(title == "certifications"){
                $('.Certifications .btn-view-more-filters').trigger('click');
                bCheck = true;
            }
            }
        }
    });
}

function adjustHeight(){
    var byRow = $('#product-grid').hasClass('match-height');
   $('#product-grid').each(function() {
       $(this).children('.product-grid-item').matchHeight({
           byRow: byRow
       });
   });
}




function constructFilterConditions(){
    var jsonData=[];
    var temp='';
    var group={};
    var filterValues=[];
    var jsonData={};
    var groupIdx = 1;
    var index = 0;
    var queryString='';
    var findLength = $('#mobile-filters').children().length;
    var findFilters = $('#plp-search-left-nav-filters');
    if(findLength > 0){
        findFilters =  $('#mobile-filters');
    }
   
    findFilters.find('.selected-filter').each(function(i,data){
       var code = $(this).parent().parent().data('code'); 
        var data = $(this).data('value').toString();
        var id = $(this).attr('id');
         filterIds.push(id);
	   
        if(temp != code){
			index = 0;
            groupIdx++;
        }else{
            index++;
        }
        queryString+=getFilterParam(groupIdx,index,code,data,'');
        temp = code;


    });
    facetsquery = queryString;
    return queryString;
}


function getFilterParam(group_index, index, field, value, type) {
		var searchCriteria ="&searchCriteria[filter_groups][" + group_index + "][filters][" + index + "][field]=" + field + "&" +
			   "searchCriteria[filter_groups][" + group_index + "][filters][" + index + "][value]=" + value  ;
            
        if( type != ''){
           searchCriteria = searchCriteria +"&searchCriteria[filter_groups][" + group_index + "][filters][" + index + "][condition_type]=" + type;
        }
    return searchCriteria;
}
