$.swag.menu = {};
$.swag.Menu = function(handler, menuId) {
	this.handler = handler;
	this.pointHandlers = {};
	this.menuId = typeof menuId == "undefined" ? "menu" : menuId;
	this.init();
};

$.extend($.swag.Menu.prototype, {
	init: function() {
		this.update();
	},

	update: function() {
		var points = $('#'+this.menuId+' li');
		for (var i = 0; i < points.length; i++) {
			this.checkMenuPoint(points[i]);
		}
	},
	
	checkMenuPoint: function(point) {
		point = $(point);
		if (point.attr('data-show') == 'authenticated' && !sessionStorage.getItem("authenticated")
		|| point.attr('data-show') == 'not-authenticated' && sessionStorage.getItem("authenticated")) {
			point.hide();
		} else {
			point.show();
		}
		
		point.click(this.clickHandler.bind(this));
	},
	
	/**
	 * if menu-point-controller has not been loaded yet, load it.
	 * if loaded, call handle-function on it
	 */
	clickHandler: function(event) {
		var controller = $(event.target).attr('data-js-controller');
		if (controller && this.pointHandlers[controller] == undefined) {
			var className = controller.replace(/\./g, "/");
			var ref = this;
			require([className], function() {
				ref.initClickController(controller);
			});
		} else {
			this.handle(controller);
		}
	},
	
	/**
	 * after menu-point-controller has been loaded call handle-function on it
	 */
	initClickController: function(controller) {
		for (var cont in this.pointHandlers) {
			if (cont != controller && this.pointHandlers[cont] && this.pointHandlers[cont].destruct) {
				this.pointHandlers[cont].destruct();
				this.pointHandlers[cont] = null;
			}
		}
		this.pointHandlers[controller] = new (eval("$."+controller))();
		this.handle(controller);
	},
	
	handle: function(controller) {
		this.pointHandlers[controller].handle();
		$.swag.Main.INSTANCE.updateLayout();
	}
});
