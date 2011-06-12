$.swag.menu.Chat = function() {
	this.userList = null;
	this.activeChats = [];
	this.chat = null;
	this.sendMessage = null;
	this.tabs = null;
	this.timeout = null;
	this.init();
};

$.swag.menu.Chat.REFRESH_INTERVAL = 1000;

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
		if (!$.isArray(users)) users = [users];
		
		
		var select = $('#'+$.swag.Main.CONTENT_ID+' .user-list select')[0];
		
		for (var i = 0; i < select.options.length; i++) {
			select.options[0] = null;
		}
		
		$('#'+$.swag.Main.CONTENT_ID+' .user-list select').attr('size', users.length+1);
		select.options[select.options.length] = new Option("Select user", "0", true, true);
		
		for (var userIndex in users) {
			var user = users[userIndex];
			select.options[select.options.length] = new Option(user.username, user.id, false, false);
		}
	},
	
	selectUser: function(event) {
		var chatWith = $('#'+$.swag.Main.CONTENT_ID+' .user-list select').val();
		if (chatWith == 0) return;
		
		var userName = $($('#'+$.swag.Main.CONTENT_ID+' .user-list select')[0].options[
			$('#'+$.swag.Main.CONTENT_ID+' .user-list select')[0].selectedIndex
		]).html();
		this.activeChats.push(chatWith);
		var lis = $('#'+$.swag.Main.CONTENT_ID+' .chat-list ul li');
		var found = false;
		var index = 0;
		
		for (var i = 0; i < lis.length; i++) {
			var li = lis[i];
			if ($(li).html() == userName) {
				found = true;
				break;
			}
			index++;
		}
		if (!found) {
			this.addChat(chatWith, userName);
		}
	},
	
	addChat: function(userId, userName) {
		$('#'+$.swag.Main.CONTENT_ID+' .chat-list ul').append('<li data-user-id="'+userId+'"><a href="#chat_'+userId+'"><span>'+userName+'</span></a></li>');
		var div = this.chat.clone();
		$(div).attr('id', 'chat_'+userId);
		$(div).addClass('ui-tabs-hide');
		$(div).css({'padding':'0px'});
		$(div).find('textarea').attr('id', 'ta_'+userId);
		$('#'+$.swag.Main.CONTENT_ID+' .chat-list').append(div);
		
		$('#chat_'+userId+' button').click({userId: userId}, this.sendMsg.bind(this));
		
		this.tabs = $('#'+$.swag.Main.CONTENT_ID+' .chat-list').tabs({
			select: this.selectChat.bind(this)
		});
		
		
		$.post($.swag.Main.BASE+'/chat/initialize', {
			userId: userId
		}, this.refreshMessages.bind(this));
	},
	
	refreshMessages: function(event) {
		$.post($.swag.Main.BASE+'/chat/messages', {
			activeChats: this.activeChats.join(',')
			,timeStamp: (new Date()).getTime() - $.swag.menu.Chat.REFRESH_INTERVAL
		}, this.messagesReceived.bind(this));
		this.timeout = window.setTimeout(this.refreshMessages.bind(this), $.swag.menu.Chat.REFRESH_INTERVAL);
	},
	
	messagesReceived: function(data) {
		for (var userId in data) {
			for (var msg in data[userId]) {
				$('#ta_'+userId).val(data[userId][msg]+"\n"+$('#ta_'+userId).val());
			}
		}
	},
	
	sendMsg: function(event) {
		var message = $('#chat_' + event.data.userId+' input').val();
		var to = event.data.userId;
		//$('#chat_' + event.data.userId+' textarea').val($('#chat_' + event.data.userId+' textarea').val()+"\n"+message);
		$.post($.swag.Main.BASE+'/chat/add', {
				message: message,
				to: to,
				timeStamp: (new Date()).getTime()
			}, 
			function() {}
		);
		$('#chat_' + event.data.userId+' input').val('');
	},
	
	selectChat: function(event, ui) {
		console.log(ui);
	},
	
	/**
	 * is called by Menu.js when the user changes the menu point
	 */
	destruct: function() {
		window.clearTimeout(this.timeout);
		$('#'+$.swag.Main.CONTENT_ID).html('');
		/*
		this.userList = $('#'+$.swag.Main.CONTENT_ID+' .user-list').detach();
		this.chatList = $('#'+$.swag.Main.CONTENT_ID+' .chat-list').detach();
		console.log(this.userList);
		console.log(this.chatList);
		*/
	}
});