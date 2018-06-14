
$(document).on( "click", ".btn-heading .btn-faq-section", function() {
		console.log('click in btn');
		var el = $(this);
		slideUpDownSection(el);
	});	
function getRedirectPath(){
        return "/content/frontierwholesales/en/myaccount.html"; 

    }

  function slideUpDownSection(el) {		
	var closestSlideSection = el.parent().next('.faq-section-expand-collapse');
	if (el.hasClass('open')) {
		el.removeClass('open');
		el.find('.glyphicon').removeClass('glyphicon-minus').addClass('glyphicon-plus');
		closestSlideSection.removeClass('open').slideUp("fast");
	} else {
		el.addClass('open');
		closestSlideSection.addClass('open').slideDown("fast");
		el.find('.glyphicon').removeClass('glyphicon-plus').addClass('glyphicon-minus'); 
	}
}

function getUserToken(){
		var cookieValue = document.cookie;
        var cookieSplit=cookieValue.split(";");
        var userToken='';
        for(var i=0;i<cookieSplit.length;i++){
            var token = cookieSplit[i].trim().split("=");
            if(token[0].startsWith("MagentoUserToken")){
                userToken = token[1];
            }
        }
        var regx=new RegExp("\"","g");
        userToken=userToken.replace(regx,"");
        return userToken;
    }

function getAdminToken(){
     var d = $.Deferred();

    $.ajax({
        url: "/services/admintoken",
        method:"get",
        success:function(response){
             var regx=new RegExp("\"","g");
            var token = JSON.parse(response);
            var adminToken=token.Token.replace(regx,"");
           d.resolve(adminToken);
    },error:function(error){
           d.resolve(error);
    }
        
    });
   return  d.promise();
}
