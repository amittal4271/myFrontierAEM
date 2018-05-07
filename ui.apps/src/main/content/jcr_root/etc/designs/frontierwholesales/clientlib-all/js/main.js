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

function bindPdpAffixEvents() {
	console.log('in bindPdpAffixEvents');

	var pdpOffsetProductImages = $('#product-images').offset();
	//console.log(pdpOffsetProductImages);

	$('#product-images').affix({
		offset: {
			top: pdpOffsetProductImages.top,
			bottom: function () {
				var footerOuterHeight = $('#footer').outerHeight(true);
				var relatedProductsPdpHeight = $('#related-products-holder-pdp').height();
				//console.log(footerOuterHeight);
				var newFooterHeight = footerOuterHeight + (relatedProductsPdpHeight+160);
				//console.log('added height: '+newFooterHeight);
				return (this.bottom = newFooterHeight)
			}
		}
	});

	$("#product-images").on('affixed-bottom.bs.affix', function(){
		
	});
}

function showTopLevelNavigation() {
	var $this = $(this);
	var $subNavElement = $this.find('.sub-nav.first-level');
	$subNavElement.show();
	$this.addClass('hovering');
}
function hideTopLevelNavigation() {
	var $this = $(this);
	var $subNavElement = $this.find('.sub-nav.first-level');
	$subNavElement.hide();
	$this.removeClass('hovering');
}

function showSecondLevelNavigation() {
	var $this = $(this);
	var $subNavElement = $this.find('.sub-nav.second-level');
	$subNavElement.show();
	$this.addClass('hovering');
}
function hideSecondLevelNavigation() {
	var $this = $(this);
	var $subNavElement = $this.find('.sub-nav.second-level');
	$subNavElement.hide();
	$this.removeClass('hovering');
}

function showThirdLevelNavigation() {
	var $this = $(this);
	var $subNavElement = $this.find('.sub-nav.third-level');
	$subNavElement.show();
	$this.addClass('hovering');
}
function hideThirdLevelNavigation() {
	var $this = $(this);
	var $subNavElement = $this.find('.sub-nav.third-level');
	$subNavElement.hide();
	$this.removeClass('hovering');
}

function showHeaderFlyout () {
	var $this = $(this);
	var $childFlyout = $this.children('.header-flyout-list');
	if ($this.find('.header-flyout-list').length > 0) {
		$childFlyout.show();
	}
}
function hideHeaderFlyout () {
	var $this = $(this);
	var $childFlyout = $this.children('.header-flyout-list');
	if ($this.find('.header-flyout-list').length > 0) {
		$childFlyout.hide();
	}
}


$(document).ready(function() {

	console.log('main.js doc ready');

	$(document).on( "click", ".account-nav-toggle", function() {
		var $this = $(this);
		var $icon = $this.children('.glyphicon');
		var $accountNav = $('.account-nav');

		if ($this.hasClass('open')) {
			$this.addClass('closed').removeClass('open');
			$icon.addClass('rotate');
			$accountNav.slideUp("fast");
		} else {
			$this.removeClass('closed').addClass('open');
			$icon.removeClass('rotate');
			$accountNav.slideDown("fast");
		}
	});

	$(document).on( "click", ".account-nav-sub-toggle", function(e) {
		e.preventDefault();
		var $this = $(this);
		var $icon = $this.children('.glyphicon');
		var $accountSubNav = $this.parent('.account-nav-item').children('.account-nav-sub');

		if ($this.hasClass('open')) {
			$this.addClass('closed').removeClass('open');
			$icon.removeClass('rotate');
			$accountSubNav.slideUp("fast");
		} else {
			$this.removeClass('closed').addClass('open');
			$icon.addClass('rotate');
			$accountSubNav.slideDown("fast");
		}
	});

	$(document).on( "click", ".help-nav-toggle", function() {
		var $this = $(this);
		var $icon = $this.children('.glyphicon');
		var $accountNav = $('.side-nav');

		if ($this.hasClass('open')) {
			$this.addClass('closed').removeClass('open');
			$icon.addClass('rotate');
			$accountNav.slideUp("fast");
		} else {
			$this.removeClass('closed').addClass('open');
			$icon.removeClass('rotate');
			$accountNav.slideDown("fast");
		}
	});

	$(document).on( "click", ".btn-heading .btn-faq-section", function() {
		console.log('click in btn');
		var el = $(this);
		slideUpDownSection(el);
	});	

	var swiper = new Swiper('#homepage-scroller .swiper-container', {
		pagination: {
			el: '#homepage-scroller .swiper-pagination',
			clickable: true,
		},
		navigation: {
			nextEl: '#homepage-scroller .swiper-button-next',
			prevEl: '#homepage-scroller .swiper-button-prev',
		},
    });


    var swiper = new Swiper('#pdp-scroller .swiper-container', {
		pagination: {
			el: '#pdp-scroller .swiper-pagination',
			clickable: true,
		},
		navigation: {
			nextEl: '#pdp-scroller .swiper-button-next',
			prevEl: '#pdp-scroller .swiper-button-prev',
		},
		breakpoints: {
		    480: {
	    		slidesPerView: 1,
	    		slidesPerGroup: 1,
	    		allowTouchMove: true
		    },
		    991: {
		    	slidesPerView: 3,
		    	slidesPerGroup: 3,
		    	allowTouchMove: true,
		    	spaceBetween: 25
		    },
		    9999: {
		    	slidesPerView: 5,
		    	allowTouchMove: true,
		    	spaceBetween: 35
		    }


		}
    });

    if ($('#product-detail').length > 0) {
    	bindPdpAffixEvents();
    }



    /* top navigation stuff */
    $("#main-navigtaion-list").hoverIntent({
		over: showTopLevelNavigation,
		out: hideTopLevelNavigation,
		selector: '.top-level-category'
	});

	$("#main-navigtaion-list").hoverIntent({
		over: showSecondLevelNavigation,
		out: hideSecondLevelNavigation,
		selector: '.category-nav-item'
	});

	$("#main-navigtaion-list").hoverIntent({
		over: showThirdLevelNavigation,
		out: hideThirdLevelNavigation,
		selector: '.category-nav-child-item'
	});

	$(document).on( "click", "#mobile-menu-icon", function(e) {
		var $mobileNav = $('#mobile-nav');
		var $mobileNavOverlay = $('#mobile-nav-overlay');

		$('body').addClass('mobile-nav-overflow');

		$mobileNavOverlay.show().addClass('mobile-overlay-open');
		$mobileNav.show().addClass('mobile-nav-open');
	});

	$(document).on( "click", "#close-mobile-nav", function(e) {
		var $mobileNav = $('#mobile-nav');
		var $mobileNavOverlay = $('#mobile-nav-overlay');

		$('body').removeClass('mobile-nav-overflow');

		$mobileNavOverlay.hide().removeClass('mobile-overlay-open');
		$mobileNav.hide().removeClass('mobile-nav-open');
	});

	$(".user-menu").hoverIntent({
		over: showHeaderFlyout,
		out: hideHeaderFlyout,
		selector: 'li'
	});

	$(document).on( "click", ".btn-toggle-cart-actions", function(e) {
		var $this = $(this);
		var $icon = $this.children('.glyphicon');
		var $toggleActionHolder = $('.toggle-action-holder');
		var $toggleSections = $('#cart-actions-grid .toggle-holder');

		if ($this.hasClass('btn-toggle-open')) {
			$this.removeClass('btn-toggle-open');
			$toggleActionHolder.removeClass('toggle-open');
			$icon.removeClass('rotate');
			$toggleSections.removeClass('toggle-open');
		} else {
			$this.addClass('btn-toggle-open');
			$toggleActionHolder.addClass('toggle-open');
			$icon.addClass('rotate');
			$toggleSections.addClass('toggle-open');
		}
	});

	$(document).on( "click", ".btn-toggle-order-details", function(e) {
		var $this = $(this);
		var $icon = $this.children('.glyphicon');
		var $closestDetailSection = $this.parents('.row-each-order').find('.order-details-holder');

		if ($this.hasClass('btn-toggle-detail-open')) {
			$this.removeClass('btn-toggle-detail-open');
			$closestDetailSection.slideUp("fast");
			$icon.removeClass('rotate').addClass('glyphicon-plus').removeClass('glyphicon-minus');
		} else {
			$this.addClass('btn-toggle-detail-open');
			$closestDetailSection.slideDown("fast");
			$icon.addClass('rotate').removeClass('glyphicon-plus').addClass('glyphicon-minus');
		}

	});
	
	$(document).on( "change", ".existing-shipping-address-input", function(e) {
		console.log('existing shipping radio change');
		var $this = $(this);
		var $newShippingAddressHolder = $('#shipping-new-address-holder');

		if ($this.hasClass('new-address-input')) {
			$newShippingAddressHolder.slideDown("fast");
		} else {
			$newShippingAddressHolder.slideUp("fast");
		}
	});

	$(document).on( "change", ".existing-billing-address-input", function(e) {
		console.log('existing billing radio change');
		var $this = $(this);
		var $newShippingAddressHolder = $('#billing-new-address-holder');
		
		if ($this.hasClass('new-address-input')) {
			$newShippingAddressHolder.slideDown("fast");
		} else {
			$newShippingAddressHolder.slideUp("fast");
		}
	});


});

$(window).resize(function() {

	if ($('#product-detail').length > 0) {
		$('#product-images').affix('checkPosition');
		bindPdpAffixEvents();
	}

});

$(window).scroll(function() {
	if ($('#product-detail').length > 0) {
		$('#product-images').affix('checkPosition');
	}
});
