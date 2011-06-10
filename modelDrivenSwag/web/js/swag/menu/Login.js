$.swag.menu.Login = function() {
	this.templateId = 'menu-content-login';
	this.mainId = 'content';
	this.loginFormTemplate = null;
	this.renewPasswordTemplate = null;
	this.registerFormTemplate = null;
	this.showTemplate = 'login';
	this.init();
};

$.extend($.swag.menu.Login.prototype, {
	init: function() {
		if ($('#'+this.templateId+' .login-form'))
			this.loginFormTemplate = $('#'+this.templateId+' .login-form').detach();
		if ($('#'+this.templateId+' .register-form'))
			this.registerFormTemplate = $('#'+this.templateId+' .register-form').detach();
		if ($('#'+this.templateId+' .renew-password-form'))
			this.renewPasswordTemplate = $('#'+this.templateId+' .renew-password-form').detach();
		$('#'+this.templateId).remove();
		$.get($.swag.Main.BASE+'/auth/isloggedin', this.checkLogin.bind(this));
		
		// if hash changes, update view
		window.addEventListener('hashchange', this.update.bind(this), false);
	},
	
	handle: function() {
		// nothing, we wait for checking the login status
	},
	
	update: function() {
		if (location.hash.length > 0) {
			this.showTemplate = location.hash.substr(1);
		} else {
			this.showTemplate = 'login';
		}
		this.show();
	},
	
	show: function() {
		if (this.showTemplate  == 'login') {
			$('#'+this.mainId).html(this.loginFormTemplate);
		} else if (this.showTemplate == 'register') {
			$('#'+this.mainId).html(this.registerFormTemplate);
		} else if (this.showTemplate == 'renew-password'){
			$('#'+this.mainId).html(this.renewPasswordTemplate);
		}
		$('#'+this.mainId).find('button').click(this.submit.bind(this));
	},
	
	/**
	 * checks if the user is currently logged in
	 */
	checkLogin: function(data) {
		if (data.status) {
			$('#'+this.mainId).append("You area already logged in!");
		} else {
			this.show();
		}
	},
	
	/**
	 * hashes the pw locally and sends it to the server, to check authentication
	 */
	submit: function(data) {
		$('#'+this.mainId).find('input[name=hashed]').val(MD5($('#'+this.mainId).find('input[name=password]').val()));
		$('#'+this.mainId+" .error").hide();
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
		if (data.id) {
			sessionStorage.setItem("authenticated", data);
			window.location.reload();
		} else {
			$('#'+this.mainId+" .error").show();
		}
	}
});