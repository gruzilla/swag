$.swag.menu.Chat = function() {
	this.userList = null;
	this.activeChats = [];
	this.chat = null;
	this.sendMessage = null;
	this.init();
};

$.extend($.swag.menu.Chat.prototype, {
	init: function() {
		console.log('initializing chat');
		this.userList = $('#chat-template .user-list').detach();
		this.chatList = $('#chat-template .chat-list').detach();
		this.chat = $('#chat-template .chat-template').detach();
		this.sendMessage = $('#chat-template .chat-sender').detach();
	},
	
	handle: function() {
		$('#'+$.swag.Main.CONTENT_ID).html('lol');
		console.log(this.userList);
		$('#'+$.swag.Main.CONTENT_ID).append(this.userList);
		$('#'+$.swag.Main.CONTENT_ID).append(this.chatList);
		$('#'+$.swag.Main.CONTENT_ID+' .user-list select').change(this.selectUser.bind(this));
		$.get($.swag.Main.BASE+'/user/list', this.updateUserList.bind(this));
	},
	
	updateUserList: function(users) {
		users = users.user;
		
		console.log(users);
		
		$('#'+$.swag.Main.CONTENT_ID+' .user-list select').attr('size', users.length);
		
		var select = $('#'+$.swag.Main.CONTENT_ID+' .user-list select')[0];
		
		for (var userIndex in users) {
			var user = users[userIndex];
			select.options[select.options.length] = new Option(user.username, user.id, false, false);
		}
	},
	
	selectUser: function(event) {
		var chatWith = $('#'+$.swag.Main.CONTENT_ID+' .user-list select').val();
		this.activeChats.push(chatWith);
		this.showChat(chatWith);
	},
	
	showChat: function(userId) {
		
	},
	
	/**
	 * is called by Menu.js when the user changes the menu point
	 */
	destruct: function() {
		$('#'+$.swag.Main.CONTENT_ID).html('');
		/*
		this.userList = $('#'+$.swag.Main.CONTENT_ID+' .user-list').detach();
		this.chatList = $('#'+$.swag.Main.CONTENT_ID+' .chat-list').detach();
		console.log(this.userList);
		console.log(this.chatList);
		*/
	}
});