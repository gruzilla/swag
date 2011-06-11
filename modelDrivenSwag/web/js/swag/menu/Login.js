$.swag.menu.Login = function() {
	this.templateId = 'menu-content-login';
	this.mainId = 'content';
	this.loginFormTemplate = null;
	this.renewPasswordTemplate = null;
	this.registerFormTemplate = null;
	this.showTemplate = location.hash ? location.hash.substr(1) : 'login';
	this.init();
	this.lastUsernameOk = false;
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
			$('#'+this.mainId).find('button').click(this.submitLogin.bind(this));
		} else if (this.showTemplate == 'register') {
			$('#'+this.mainId).html(this.registerFormTemplate);
			$('#'+this.mainId).find('input[name=username]').change(this.checkUsername.bind(this));
			$('#'+this.mainId).find('button').click(this.submitRegister.bind(this));
		} else if (this.showTemplate == 'renew-password'){
			$('#'+this.mainId).html(this.renewPasswordTemplate);
			$('#'+this.mainId).find('button').click(this.submitRenewPassword.bind(this));
		}
		$.swag.Main.INSTANCE.updateLayout();
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
	submitLogin: function(data) {
		$('#'+this.mainId).find('input[name=hashed]').val(MD5($('#'+this.mainId).find('input[name=password]').val()));
		$('#'+this.mainId+" .error").hide();
		$.post($.swag.Main.BASE+'/auth/login', {
			username: $('#'+this.mainId).find('input[name=username]').val()
		,	hashed: $('#'+this.mainId).find('input[name=hashed]').val()
		}, this.checkResult.bind(this));
	},
	
	submitRegister: function(data) {
		var formdata = $('#'+this.mainId).find('form').serializeArray();
		console.log(formdata);
		if (!formdata.username) {
			alert("Please enter a username!");
			return;
		} else {
			if (!this.lastUsernameOk) {
				alert("Please enter a valid username!");
				return;
			}
		}
		if (!formdata.email) {
			alert("Please enter a email address!");
			return;
		}
		if (!formdata.name) {
			alert("Please enter your name!");
			return;
		}
		if (!formdata.address) {
			alert("Please enter your home address!");
			return;
		}
		if (!formdata.timezone) {
			alert("Please select a timezone!");
			return;
		}
		
		$.post($.swag.Main.BASE+'/auth/register', formdata, this.registrationComplete.bind(this));
	},
	
	registrationComplete: function(data) {
		$(document).append('<span style="display:none" id="registrationComplete">Registration complete, please check your email for login.</span>');
		$('#registrationComplete').dialog($.swag.Main.DIALOG_DEFAULT, {title:'Registration complete'});
		window.history.pushState(null, '', '#login');
	},
	
	checkUsername: function() {
		$('#unique-username').html("");
		$.post($.swag.Main.BASE+'/auth/checkusername', {
			username: $('#'+this.mainId).find('input[name=username]').val()
		}, this.checkUsernameResult.bind(this));
	},
	
	checkUsernameResult: function(data) {
		if (data.status) {
			$('#unique-username').html("Username exists!");
			$('#unique-username').css({
				color: '#CC0000'
			});
			this.lastUsernameOk = false;
		} else {
			$('#unique-username').html("Username ok!");
			$('#unique-username').css({
				color: '#00CC00'
			});
			this.lastUsernameOk = true;
		}
	},
	
	submitRenewPassword: function(data) {
		
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