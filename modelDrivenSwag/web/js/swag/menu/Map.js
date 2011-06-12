$.swag.menu.Map = function() {
	this.map = null;
	this.id = null;
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
				line += '<td';
				var found = false;
				for(var i = 0; i < result.length; i++) {
					var sqr = result[i];
					if(sqr.x == x && sqr.y == y) {
						if(sqr.squad) {
							line += '>squad : ' + sqr.squad;
							found = true;
						}
						else if(sqr.base) {
							line += ' mapObjectType="base" baseId="' + sqr.baseid + '"> base : ' + sqr.base;
							found = true;
						}
						else if(sqr.resource) {
							line += '>' + sqr.resource;
							found = true;
						}
					}
				}
				if(found == false) {
					line += '>';
				}
				line += '</td>';
			}
			line += '</tr>';
			console.log(line);	
			$('#'+$.swag.Main.CONTENT_ID+' .map table').append(line);
		}
		$('#'+$.swag.Main.CONTENT_ID+' .map table').click(this.clickDetect.bind(this));
	},
	
	clickDetect : function(event) {
		if($(event.target).attr('mapObjectType') == 'base') {
			this.id = $(event.target).attr('baseId');
			$.get($.swag.Main.BASE+'/base/buildings/' + this.id, this.loadBuildings.bind(this));
		}
	},
	
	loadBuildings : function(result) {
		$('#'+$.swag.Main.CONTENT_ID+' .map table').html('');
		
		var buildings = result.buildings;
		$('#'+$.swag.Main.CONTENT_ID+' .map table').append('<tr><td>Buildings</td></tr>');
		for(var i = 0; i < buildings.length; i++) {
			var building = buildings[i];
			var line = '<tr><td>';
			line += building.name;
			line += '</td></tr>';
			$('#'+$.swag.Main.CONTENT_ID+' .map table').append(line);
		}
		//$.get($.swag.Main.BASE+'/base/squads/' + this.id, this.loadSquads.bind(this));
	},
	
	loadSquads : function(result) {		
		var squads = result.squads;
		$('#'+$.swag.Main.CONTENT_ID+' .map table').append('<tr><td>Squads</td></tr>');
		for(var i = 0; i < squads.length; i++) {
			var squad = squads[i];
			var line = '<tr><td>';
			line += squad.name;
			line += '</td></tr>';
			$('#'+$.swag.Main.CONTENT_ID+' .map table').append(line);
		}
		$.get($.swag.Main.BASE+'/buildableTroops/', this.loadBuildableTroops.bind(this));
	},
	
	loadBuildableTroops : function(result) {
		var troops = result.troops;
		$('#'+$.swag.Main.CONTENT_ID+' .map table').append('<tr><td>Build Troop</td></tr>');
		for(var i = 0; i < troops.length; i++) {
			var troop = troops[i];
			var line = '<tr><td>';
			line += troop.name;
			line += '</td></tr>';
			$('#'+$.swag.Main.CONTENT_ID+' .map table').append(line);
		}
	},
	
	/**
	 * is called by Menu.js when the user changes the menu point
	 */
	destruct: function() {
		$('#'+$.swag.Main.CONTENT_ID).html('');
		//this.map = $('#'+$.swag.Main.CONTENT_ID+' .map').detach();
	}
});