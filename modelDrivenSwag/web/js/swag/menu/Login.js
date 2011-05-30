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
	
	submit: function() {
		$('#'+this.mainId).find('input[name=hashed]').val(MD5($('#'+this.mainId).find('input[name=password]').val()));
	}
});