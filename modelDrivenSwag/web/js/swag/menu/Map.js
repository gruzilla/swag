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
		$.get($.swag.Main.BASE+'/map/query', this.fillMap.bind(this));
	},
	
	fillMap: function(result) {
		$('#'+$.swag.Main.CONTENT_ID+' .map table').html('');
		for (var y = 0; y < 10; y++) {
			var line = '<tr>';
			for (var x = 0; x < 10; x++) {
				line += '<td>';
				for(var i = 0; i < result.length; i++) {
					var sqr = result[i];
					if(sqr.x == x && sqr.y == y) {
						if(sqr.squad) {
							line += 'squad : ' + sqr.squad;
						}
						else if(sqr.base) {
							line += 'base : ' + sqr.base;
						}
						else if(sqr.resource) {
							line += sqr.resource;
						}
					}
				}
				line += '</td>';
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