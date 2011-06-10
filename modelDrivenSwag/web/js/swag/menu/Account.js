$.swag.menu.Account = function() {
	this.init();
};

$.extend($.swag.menu.Account.prototype, {
	init: function() {},
	
	handle: function() {
		alert("account");
	}
	
	/**
	 * is called by Menu.js when the user changes the menu point
	 */
	,destruct: function() {
		alert("destructing account");
	}
});