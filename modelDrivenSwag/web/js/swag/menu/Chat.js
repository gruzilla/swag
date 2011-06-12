$.swag.menu.Chat = function() {
	this.userList = null;
	this.activeChats = [];
	this.chat = null;
	this.sendMessage = null;
	this.tabs = null;
	this.init();
};

$.extend($.swag.menu.Chat.prototype, {
	init: function() {
		this.userList = $('#chat-template .user-list').detach();
		this.chatList = $('#chat-template .chat-list').detach();
		this.chat = $('#chat-template .chat').detach();
		this.sendMessage = $('#chat-template .chat-sender').detach();
	},
	
	handle: function() {
		$('#'+$.swag.Main.CONTENT_ID).html(this.userList);
		$('#'+$.swag.Main.CONTENT_ID).append(this.chatList);
		$('#'+$.swag.Main.CONTENT_ID+' .user-list select').change(this.selectUser.bind(this));
		$.get($.swag.Main.BASE+'/user/list', this.updateUserList.bind(this));
	},
	
	updateUserList: function(users) {
		users = users.user;
		
		$('#'+$.swag.Main.CONTENT_ID+' .user-list select').attr('size', users.length);
		
		var select = $('#'+$.swag.Main.CONTENT_ID+' .user-list select')[0];
		
		for (var userIndex in users) {
			var user = users[userIndex];
			select.options[select.options.length] = new Option(user.username, user.id, false, false);
		}
	},
	
	selectUser: function(event) {
		var chatWith = $('#'+$.swag.Main.CONTENT_ID+' .user-list select').val();
		var userName = $($('#'+$.swag.Main.CONTENT_ID+' .user-list select')[0].options[
			$('#'+$.swag.Main.CONTENT_ID+' .user-list select')[0].selectedIndex
		]).html();
		this.activeChats.push(chatWith);
		this.showChat(chatWith, userName);
	},
	
	showChat: function(userId, userName) {
		var lis = $('#'+$.swag.Main.CONTENT_ID+' .chat-list ul li');
		var found = false;
		var index = 0;
		
		for (var i = 0; i < lis.length; i++) {
			var li = lis[i];
			if ($(li).html() == userName) {
				console.log($(li).html()+' = '+userName);
				found = true;
				break;
			}
			index++;
		}
		if (!found) {
			$('#'+$.swag.Main.CONTENT_ID+' .chat-list ul').append('<li data-user-id="'+userId+'"><a href="#'+userName+'"><span>'+userName+'</span></a></li>');
			var div = this.chat.clone();
			$(div).attr('id', userName);
			$(div).addClass('ui-tabs-hide');
			$(div).css({'padding':'0px'});
			$('#'+$.swag.Main.CONTENT_ID+' .chat-list').append(div);
		}
		
		this.tabs = $('#'+$.swag.Main.CONTENT_ID+' .chat-list').tabs({
			select: this.selectChat.bind(this)
		});
	},
	
	selectChat: function(event, ui) {
		console.log(ui);
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