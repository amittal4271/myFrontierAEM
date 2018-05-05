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
	//console.log('in bindPdpAffixEvents');

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

function showMiniCart () {
	var $this = $(this);
	var $miniCartFlyout = $this.children('.mini-cart-holder');
	$miniCartFlyout.fadeIn("fast");
}

function hideMiniCart () {
	var $this = $(this);
	var $miniCartFlyout = $this.children('.mini-cart-holder');
	$miniCartFlyout.fadeOut("fast");
}


$(document).ready(function() {

	$("#header").hoverIntent({
		over: showMiniCart,
		out: hideMiniCart,
		selector: '#cart-holder'
	});

	//console.log('main.js doc ready');

	
	$(document).on( "click", "#mobile-search-icon", function(e) {
		var $this = $(this);
		var $searchHeaderHolder = $('#search-header-holder');
		var $searchOverlay = $('#search-overlay');
		var $icon = $this.children('.glyphicon');

		if ($this.hasClass('open')) {
			$this.removeClass('open');
			$searchHeaderHolder.removeClass('open');
			$icon.removeClass('glyphicon-remove').addClass('glyphicon-search');

		} else {
			$this.addClass('open');
			$searchHeaderHolder.addClass('open');
			$icon.removeClass('glyphicon-search').addClass('glyphicon-remove');
		}
	});
	

	$(document).on( "click", ".btn-filters-plp-search", function(e) {
		var $mobileFilterHolder = $('#mobile-filters-holder');
		var $mobileFilterOverlay = $('#mobile-filters-overlay');

		var $htmlFilterToCopy = $('#plp-search-left-nav-filters').html();

		$('body').addClass('mobile-nav-overflow');

		$mobileFilterOverlay.show().addClass('mobile-filter-overlay-open');
		//append filter html to mobile holder
		$('#mobile-filters').html($htmlFilterToCopy);
		//show mobile filter container
		$mobileFilterHolder.show().addClass('mobile-filter-open');
	});

	$(document).on( "click", "#close-filters-plp-search", function(e) {
		var $mobileFilterHolder = $('#mobile-filters-holder');
		var $mobileFilterOverlay = $('#mobile-filters-overlay');

		$('body').removeClass('mobile-nav-overflow');
		$('#mobile-filters').html('');

		$mobileFilterOverlay.hide().removeClass('mobile-filter-overlay-open');
		$mobileFilterHolder.hide().removeClass('mobile-filter-open');
	});

	

	$(document).on( "click", ".btn-view-more-filters", function() {
		var $this = $(this);
		var $buttonText = $this.children('.view-more-text');
		var $eachFilterList = $this.parents('.each-filters-list');
		var $initiallyHiddenFilters = $eachFilterList.children('.filter-item.visuallyhidden');
		var $wasHiddenFilters = $eachFilterList.children('.filter-item.filter-was-hidden');

		if ($this.hasClass('show-additional')) {
			$this.removeClass('show-additional');
			$buttonText.html('View More');
			$wasHiddenFilters.removeClass('filter-was-hidden').addClass('visuallyhidden');
		} else {
			$this.addClass('show-additional');
			$buttonText.html('View Less');
			$initiallyHiddenFilters.removeClass('visuallyhidden').addClass('filter-was-hidden');
		}
	});

	

	$(document).on( "click", "#mobile-nav-overlay", function() {
		var $this = $(this);

		var $mobileNav = $('#mobile-nav');
		var $mobileNavOverlay = $('#mobile-nav-overlay');

		$('body').removeClass('mobile-nav-overflow');

		$mobileNavOverlay.hide().removeClass('mobile-overlay-open');
		$mobileNav.hide().removeClass('mobile-nav-open');
		
	});

	
	//mobile nav click events
	$(document).on( "click", ".mobile-top-level-btn", function() {
		var $this = $(this);
		var $icon = $this.children('.glyphicon');
		var $liTopLevelCategory = $this.parent('.mobile-top-level-category');
		var $subNavMobileFirstLevel = $liTopLevelCategory.children('.sub-nav-mobile.first-level');

		if ($this.hasClass('open')) {
			$this.addClass('closed').removeClass('open');
			$icon.addClass('rotate').removeClass('glyphicon-minus').addClass('glyphicon-plus');
			$liTopLevelCategory.removeClass('open');
			$subNavMobileFirstLevel.slideUp("fast");
		} else {
			$this.removeClass('closed').addClass('open');
			$icon.removeClass('rotate').removeClass('glyphicon-plus').addClass('glyphicon-minus');
			$liTopLevelCategory.addClass('open');
			$subNavMobileFirstLevel.slideDown("fast");
		}
	});

	$(document).on( "click", ".mobile-second-level-btn", function() {
		var $this = $(this);
		var $icon = $this.children('.glyphicon');
		var $liTopLevelCategory = $this.parent('.mobile-first-level-item');
		var $subNavMobileSecondLevel = $liTopLevelCategory.children('.sub-nav-mobile.second-level');

		if ($this.hasClass('open')) {
			$this.addClass('closed').removeClass('open');
			$icon.addClass('rotate').removeClass('glyphicon-minus').addClass('glyphicon-plus');
			$liTopLevelCategory.removeClass('open');
			$subNavMobileSecondLevel.slideUp("fast");
		} else {
			$this.removeClass('closed').addClass('open');
			$icon.removeClass('rotate').removeClass('glyphicon-plus').addClass('glyphicon-minus');
			$liTopLevelCategory.addClass('open');
			$subNavMobileSecondLevel.slideDown("fast");
		}
	});

	$(document).on( "click", ".mobile-third-level-btn", function() {
		var $this = $(this);
		var $icon = $this.children('.glyphicon');
		var $liTopLevelCategory = $this.parent('.mobile-second-level-item');
		var $subNavMobileSecondLevel = $liTopLevelCategory.children('.sub-nav-mobile.third-level');

		if ($this.hasClass('open')) {
			$this.addClass('closed').removeClass('open');
			$icon.addClass('rotate').removeClass('glyphicon-minus').addClass('glyphicon-plus');
			$liTopLevelCategory.removeClass('open');
			$subNavMobileSecondLevel.slideUp("fast");
		} else {
			$this.removeClass('closed').addClass('open');
			$icon.removeClass('rotate').removeClass('glyphicon-plus').addClass('glyphicon-minus');
			$liTopLevelCategory.addClass('open');
			$subNavMobileSecondLevel.slideDown("fast");
		}
	});

	

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
		//console.log('click in btn');
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
		//console.log('existing shipping radio change');
		var $this = $(this);
		var $newShippingAddressHolder = $('#shipping-new-address-holder');

		if ($this.hasClass('new-address-input')) {
			$newShippingAddressHolder.slideDown("fast");
		} else {
			$newShippingAddressHolder.slideUp("fast");
		}
	});

	$(document).on( "change", ".existing-billing-address-input", function(e) {
		//console.log('existing billing radio change');
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
