var FontierwholesalesSearch = {

	settings: {
		form: '#search-form',
		formInput: '#search-form :input[name="q"]',
		closeBtn: '.search-close',
		suggestions: '#search-form .suggestions',
		searchContainer: '[data-search-toggle] + div',
		verticalArrowKeyCodes: [38, 40]
	},

	init: function(settings) {

        settings = settings || {};
		this.settings = $.extend(this.settings, settings);
		this.bindUI();

	},

	bindUI: function() {

		var self = this,
			verticalArrowKeyCodes = this.settings.verticalArrowKeyCodes,
			suggestions = $(this.settings.suggestions),
			searchContainer = $(this.settings.searchContainer);


		// Search overlay
		$('[data-search-toggle]').on('click', function(e) {

			e.preventDefault();

			// open the overlay
			$(this).next('div').toggleClass('active');

			$('#q').focus();

			// add close button
			if (!$(self.closeBtn).length) {
				$('body').append('<span class="search-close" style="display:none;"></span>').find(self.closeBtn).fadeIn();
			}

		});

		// Close search overlay
		$('body').on('click', this.settings.closeBtn, function() {

			searchContainer.removeClass('active');

			$(this).fadeOut(function(){
				$(this).remove();
			});

		});

		// Close search popup
		$(document).keydown(function(e) {

			// Close stuff on escape key
			if (e.keyCode == 27) {
				// search overlay
				if (searchContainer.is(':visible')) $(self.closeBtn).trigger('click');
			}

		});

		// Autocomplete
		$('body').on('keyup', this.settings.formInput, function(e) {

			// Enter key
			if(e.which == 13) {
				var active = suggestions.find('li.active');

				if(active.length === 1) {
					window.location.href = active.find('a[href]:not([href="#"]):not([href^="javascript"]):first').attr('href');
				}

				return false;
			}

			// Ignore the following from removing
			if(verticalArrowKeyCodes.indexOf(e.which) >= 0) {
				return false;
			}
			suggestions.find('.active').removeClass('active');

			self.delay(function(){ self.autocomplete(); }, 150);

		});

		$('body').on('blur', this.settings.formInput, function(e) {
			suggestions.hide();
        });

        $('body').on('focus', this.settings.formInput, function(e) {
			suggestions.show();
        });
		
		// Results key navigation
		$('body').on('keydown', this.settings.formInput, function(e) {

			// Vertical arrows
			if(verticalArrowKeyCodes.indexOf(e.which) >= 0) {
				var active = suggestions.find('li.active'),
					items = suggestions.find('li').removeClass('active'),
					index = items.index(active),
					item = null;

				if(e.which == 38) { // down
					item = items.eq(Math.max(0, index - 1));
				} else { // up
					item = items.eq(Math.min(items.length - 1, index + 1));
				}

				// Make active
				item.addClass('active');
				return false;
			}

		});

	},

	autocomplete: function() {

		var self = this,
			form = $(this.settings.form),
			formAction = form.attr('action'),
			formInput = $(this.settings.formInput),
			suggestions = $(this.settings.suggestions);
			// template = _.template('<li><a href="' + form.attr('action') + '?q=%22<%= term_encoded %>%22"><%= term %></a></li>');

		if (self.hasOwnProperty('xhr') && self.xhr.readyState != 4) {
			self.xhr.abort();
		}
		self.xhr = $.ajax({
			type: 'GET',
			url: form.data('suggestions-url'),
			data: {
				'q': formInput.val(),
			},
			dataType: 'json',
			success: function (data) {

				suggestions.empty();
				if (data) {
					// Append results
					$.each(data, function(i, item) {
                        var suggest = $("<li><a href='#'>" + item.title + "</a></li>");
					    suggestions.append(suggest);
					    
					    (function(suggest){
					    	suggest.find("a").on('mousedown', function(event) {
                                event.preventDefault();
                            }).click(function(event){
						    	event.preventDefault();
						    	$(".search-input").val($(this).text());
						    	$(this).closest("form").submit();
						    });
				    	})(suggest);
					    
					    //limit to 10 suggestions
					    if(i >= 9) {
					    	return false;
					    }
					});
				}
				// Trigger event to enable custom callbacks
				form.trigger('search:suggestions', [data.results]);
			},
            error: function (data) {
				suggestions.empty();
            }
		});

	},

	delay: (function(){
		var timer = 0;
		return function(callback, ms){
			clearTimeout (timer);
			timer = setTimeout(callback, ms);
		};
	})(),
};

$(document).ready(function(){
	FontierwholesalesSearch.init();
});