$.swag.menu.Chat = function() {
	this.init();
};

$.extend($.swag.menu.Chat.prototype, {
	init: function() {},
	
	handle: function() {
		alert("chat");
	}
	
	/**
	 * is called by Menu.js when the user changes the menu point
	 */
	,destruct: function() {
		alert("destructing chat");
	}
});