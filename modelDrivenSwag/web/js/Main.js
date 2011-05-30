require(["jquery.bind", "jquery.ui"], function() {

	$.swag = {};
	$.swag.Main = function() {
		jQuery.fx.interval=60;
		
		var needed = [
			"swag/Menu"
		];
		
		if ($.browser.msie) {
			this.warning();
		} else {
			// push additional js-classes that only work in not ie-browsers
			//needed.push("jquery.svg.pack");
		}
		
		require(needed, this.allLoaded.bind(this));
	};
	
	$.extend($.swag.Main.prototype, {
		
		allLoaded: function() {
			$(window).resize(this.resize.bind(this));
			
			this.initMenu();
		},
		
		/**
		 * called when user resizes window
		 */
		resize: function() {
			
		},
		
		/**
		 * called when user uses ie
		 */
		warning: function() {
			$("#iewarning").dialog({
				modal: true,
				width: "70%",
				height: 500,
				maxHeight: 500
			});
		},
		
		/**
		 * called after loading js/swag/Menu.js
		 */
		initMenu: function() {
			this.menu = new $.swag.Menu();
		}
	});
	
	$(document).ready(function() {
		$.swag.Main.INSTANCE = new $.swag.Main();
	});
});