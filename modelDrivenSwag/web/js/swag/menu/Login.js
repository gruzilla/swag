$.swag.menu.Login = function() {
	this.templateId = 'menu-content-login';
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
			$('#'+$.swag.Main.CONTENT_ID).html(this.loginFormTemplate);
			$('#'+$.swag.Main.CONTENT_ID).find('button').click(this.submitLogin.bind(this));
		} else if (this.showTemplate == 'register') {
			$('#'+$.swag.Main.CONTENT_ID).html(this.registerFormTemplate);
			$('#'+$.swag.Main.CONTENT_ID).find('input[name=username]').change(this.checkUsername.bind(this));
			$('#'+$.swag.Main.CONTENT_ID).find('button').click(this.submitRegister.bind(this));
		} else if (this.showTemplate == 'renew-password'){
			$('#'+$.swag.Main.CONTENT_ID).html(this.renewPasswordTemplate);
			$('#'+$.swag.Main.CONTENT_ID).find('button').click(this.submitRenewPassword.bind(this));
		}
		$.swag.Main.INSTANCE.updateLayout();
	},
	
	/**
	 * checks if the user is currently logged in
	 */
	checkLogin: function(data) {
		if (data.status) {
			$('#'+$.swag.Main.CONTENT_ID).append("You area already logged in!");
		} else {
			this.show();
		}
	},
	
	/**
	 * hashes the pw locally and sends it to the server, to check authentication
	 */
	submitLogin: function(data) {
		$('#'+$.swag.Main.CONTENT_ID).find('input[name=hashed]').val(MD5($('#'+$.swag.Main.CONTENT_ID).find('input[name=password]').val()));
		$('#'+$.swag.Main.CONTENT_ID+" .error").hide();
		$.post($.swag.Main.BASE+'/auth/login', {
			username: $('#'+$.swag.Main.CONTENT_ID).find('input[name=username]').val()
		,	hashed: $('#'+$.swag.Main.CONTENT_ID).find('input[name=hashed]').val()
		}, this.checkResult.bind(this));
	},
	
	submitRegister: function(data) {
		var formArray = $('#'+$.swag.Main.CONTENT_ID).find('form').serializeArray();
		var formData = {};
		for (var entry in formArray) {
			formData[formArray[entry].name] = formArray[entry].value.trim();
		}
		if (!formData.username) {
			alert("Please enter a username!");
			return;
		} else {
			if (!this.lastUsernameOk) {
				alert("Please enter a valid username!");
				return;
			}
		}
		if (!formData.email) {
			alert("Please enter a email address!");
			return;
		}
		if (!formData.name) {
			alert("Please enter your name!");
			return;
		}
		if (!formData.address) {
			alert("Please enter your home address!");
			return;
		}
		if (formData.timezone == "") {
			alert("Please select a timezone!");
			return;
		} else {
			formData.timezone = formData.timezone * 1;
		}
		
		$.post($.swag.Main.BASE+'/auth/register', formData, this.registrationComplete.bind(this));
	},
	
	registrationComplete: function(data) {
		$(document.body).append('<span style="display:none" id="registrationComplete">Registration complete, please check your email for login.</span>');
		$('#registrationComplete').dialog($.swag.Main.DIALOG_DEFAULT, {title:'Registration complete'});
		window.history.pushState(null, '', '#login');
	},
	
	checkUsername: function() {
		$('#unique-username').html("");
		$.post($.swag.Main.BASE+'/auth/checkusername', {
			username: $('#'+$.swag.Main.CONTENT_ID).find('input[name=username]').val()
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
		if (data.sessionId) {
			$.cookie("sessionId", data.sessionId);
			sessionStorage.setItem("authenticated", data.sessionId);
			window.location.reload();
		} else {
			$('#'+$.swag.Main.CONTENT_ID+" .error").show();
		}
	}
});