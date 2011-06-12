$.swag.menu.Logout = function() {
};

$.extend($.swag.menu.Logout.prototype, {
	init: function() {
	},
	
	handle: function() {
		$.get($.swag.Main.BASE+'/auth/logout', this.logoutComplete.bind(this));
	},
	
	logoutComplete: function(data) {
		$.cookie('JSESSIONID', null);
		sessionStorage.removeItem("authenticated");
		window.location.reload();
	}
});