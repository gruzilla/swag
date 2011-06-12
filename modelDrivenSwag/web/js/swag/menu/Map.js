$.swag.menu.Map = function() {
	this.map = null;
	this.init();
};

$.extend($.swag.menu.Map.prototype, {
	init: function() {
		this.map = $('#map-template .map').detach();
		$('#map-template').remove();
	},
	
	/**
	 * 
	 */
	handle: function() {
		$('#'+$.swag.Main.CONTENT_ID).html(this.map);
		
		// fill map
		this.fillMap();
	},
	
	fillMap: function() {
		$('#'+$.swag.Main.CONTENT_ID+' .map table').html('');
		for (var i = 0; i < 10; i++) {
			var line = '<tr>';
			for (var j = 0; j < 10; j++) {
				line += '<td></td>';
			}
			line += '</tr>';
			$('#'+$.swag.Main.CONTENT_ID+' .map table').append(line);
		}
	}
	
	/**
	 * is called by Menu.js when the user changes the menu point
	 */
	,destruct: function() {
		$('#'+$.swag.Main.CONTENT_ID).html('');
		//this.map = $('#'+$.swag.Main.CONTENT_ID+' .map').detach();
	}
});