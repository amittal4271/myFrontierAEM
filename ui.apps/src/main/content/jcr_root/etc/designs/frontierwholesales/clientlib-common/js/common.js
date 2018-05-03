
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