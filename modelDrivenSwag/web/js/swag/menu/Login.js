$.swag.menu.Login = function() {
	this.templateId = 'menu-content-login';
	this.mainId = 'content';
	this.init();
};

$.extend($.swag.menu.Login.prototype, {
	init: function() {
		if ($('#'+this.templateId))
			this.template = $('#'+this.templateId).detach();
	},
	
	handle: function() {
		$('#'+this.mainId).append(this.template);
		$('#'+this.mainId).find('button').click(this.submit.bind(this));
	},
	
	/**
	 * hashes the pw locally and sends it to the server, to check authentication
	 */
	submit: function() {
		$('#'+this.mainId).find('input[name=hashed]').val(MD5($('#'+this.mainId).find('input[name=password]').val()));
		$.post($.swag.Main.BASE+'/auth/login', {
			username: $('#'+this.mainId).find('input[name=username]').val()
		,	hashed: $('#'+this.mainId).find('input[name=hashed]').val()
		}, this.checkResult.bind(this));
	},
	
	/**
	 * checks what the result from the server tells us.
	 * null means user is not found, pw is wrong or an exception was thrown
	 */
	checkResult: function(data) {
		console.log(data);
	}
});