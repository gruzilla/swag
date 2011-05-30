require(["jquery.bind", "jquery.ui", "md5"], function() {

	$.swag = {};
	
	// constructor
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
	
	// static attributes
	$.swag.Main.DIALOG_DEFAULT = {
		modal: true,
		buttons: [
			{
				text: "Ok",
				click: function() { $(this).dialog("close"); }
			}
		]
	};
	
	
	// methods
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
		},
		
		/**
		 * fixes all inputs for layouting
		 */
		updateLayout: function() {
			$('button').button();
			$('input:submit').button();
			$('a.button').button();
		}
	});
	
	$(document).ready(function() {
		$.swag.Main.INSTANCE = new $.swag.Main();
	});
});