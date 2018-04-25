$(document).ready(function(){
   
    
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
    
   
	$(".user-menu").hoverIntent({
		over: showHeaderFlyout,
		out: hideHeaderFlyout,
		selector: 'li'
	});
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
