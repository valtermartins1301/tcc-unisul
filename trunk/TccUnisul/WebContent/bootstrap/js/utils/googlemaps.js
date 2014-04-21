google.maps.event.addDomListener(window, 'load', initialize);

function initialize()
{
	var mapProp = {
	  center:new google.maps.LatLng(-27.591903,-48.565934),
	  zoom: 13,
	  mapTypeId:google.maps.MapTypeId.ROADMAP
	  };
	var map=new google.maps.Map(document.getElementById("map_canvas")
	  ,mapProp);
}
