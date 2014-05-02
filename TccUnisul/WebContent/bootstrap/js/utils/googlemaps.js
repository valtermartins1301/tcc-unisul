var geocoder;
var map;
var marker;

function initialize()
{
	var latlng = new google.maps.LatLng(-27.591903,-48.565934);
	var options = {
		zoom: 13,
		center: latlng,
		mapTypeId: google.maps.MapTypeId.ROADMAP
	};
	
	map = new google.maps.Map(document.getElementById("map_canvas"),options);
	
	geocoder = new google.maps.Geocoder();
	
	marker = new google.maps.Marker({
		map: map,
		draggable: true,
	});
	
	//marker.setPosition(latlng);
}

function carregaPedidosNoMapa() {
	
	var url = "getPedidos";
	$.ajax({ 
        url: url,    
        type:"GET", 
        contentType: "application/json; charset=utf-8",
        async: false,    //Cross-domain requests and dataType: "jsonp" requests do not support synchronous operation
        cache: false,    //This will force requested pages not to be cached by the browser          
        processData:false, //To avoid making query String instead of JSON
        success: function(Resultado) {
        	if(Resultado.status =="ok")
        	{	
        		var dataJson = JSON.parse(Resultado.data);
        		marcarNoMapa(dataJson);
        	}
        }
	});
}

function marcarNoMapa(data) {

            	for(var i=0; i<data.listapedidos.length;i++)
				{
            		var endereco = data.listapedidos[i].cliente.endereco;
            		var latLng = new google.maps.LatLng(endereco.latitude, endereco.longitude); 
            		var contentString = 
     	            	'<div id="content">'+
     	            		'<div id="marker_popup_id_pedido">Pedido nº: '+data.listapedidos[i].idpedido+'</div>'+
     	            		'<div id="marker_popup_id_pedido">Status: '+data.listapedidos[i].status+'</div>'+
     	            		'<div id="marker_popup_id_pedido">Cliente: '+data.listapedidos[i].cliente.nome +'</div>'+
     	            		'<div id="marker_popup_id_pedido">Telefone: '+data.listapedidos[i].cliente.telefone +'</div>'+
     	            		'<div id="marker_popup_id_pedido">Endereco: '+endereco.rua+','+endereco.numero+'</div>'+
     	            	'</div>';
     	            var myinfowindow = new google.maps.InfoWindow({
     	                content: contentString,
     	                maxWidth: 200,
     	                maxHeight: 200
     	            });
    				var marker = new google.maps.Marker({
		                position: latLng,
		                title: "Pedido nº: "+ data.listapedidos[i].idpedido,
		                map: map,
		                infowindow: myinfowindow
            		});
    				bindInfoWindow(marker, map, myinfowindow);
				}         
}

function bindInfoWindow(marker, map, infowindow) {
    google.maps.event.addListener(marker, 'click', function() {
        infowindow.open(map, marker);
    });
}
$(document).ready(function () {

	initialize();
	carregaPedidosNoMapa();
	
	function carregarNoMapa(endereco) {
		geocoder.geocode({ 'address': endereco + ', Brasil', 'region': 'BR' }, function (results, status) {
			if (status == google.maps.GeocoderStatus.OK) {
				if (results[0]) {
					var latitude = results[0].geometry.location.lat();
					var longitude = results[0].geometry.location.lng();
		
					$('#txtEndereco').val(results[0].formatted_address);
					$('#novo_pedido_latitude').val(latitude);
                   	$('#novo_pedido_longitude').val(longitude);
                   	
                   	var rua ="", numero="", bairro="", cidade="", CEP="";
                   	for(var i=0; i < results[0].address_components.length; i++)
                   	{                   	
                   		var type = results[0].address_components[i].types[0];
                   		switch(type)
                   		{
                   			case "street_number" : numero = results[0].address_components[i].short_name; break;
                   			case "route" :		   rua    = results[0].address_components[i].short_name; break;
                   			case "neighborhood" :  bairro = results[0].address_components[i].short_name; break;
                   			case "locality" :      cidade = results[0].address_components[i].short_name; break;
                   			case "postal_code" :   CEP    = results[0].address_components[i].short_name; break;
                   		}
                   			
                   	}
                   	
               		$('#novo_pedido_rua').val(rua);
               		$('#novo_pedido_bairro').val(bairro);
               		$('#novo_pedido_cidade').val(cidade);
                   	$('#novo_pedido_cep').val(CEP);
                   	$('#novo_pedido_numero').val(numero);
                   	
		
					var location = new google.maps.LatLng(latitude, longitude);
					marker.setPosition(location);
					map.setCenter(location);
					map.setZoom(16);
				}
			}
		});
	}
	
	$("#btnEndereco").click(function() {
		if($(this).val() != "")
			carregarNoMapa($("#txtEndereco").val());
	});
	
	$("#txtEndereco").blur(function() {
		if($(this).val() != "")
			carregarNoMapa($(this).val());
	});
	  
	$("#txtEndereco").autocomplete({
		source: function (request, response) {
			geocoder.geocode({ 'address': request.term + ', Brasil', 'region': 'BR' }, function (results, status) {
				response($.map(results, function (item) {
					return {
						label: item.formatted_address,
						value: item.formatted_address,
						latitude: item.geometry.location.lat(),
          				longitude: item.geometry.location.lng()
					}
				}));
			})
		},
		select: function (event, ui) {
			$("#txtLatitude").val(ui.item.latitude);
    		$("#txtLongitude").val(ui.item.longitude);
			var location = new google.maps.LatLng(ui.item.latitude, ui.item.longitude);
			marker.setPosition(location);
			map.setCenter(location);
			map.setZoom(16);
		}
	});
	
	$("form").submit(function(event) {
		event.preventDefault();
		
		var endereco = $("#txtEndereco").val();
		var latitude = $("#txtLatitude").val();
		var longitude = $("#txtLongitude").val();
		
		alert("EndereÃ§o: " + endereco + "\nLatitude: " + latitude + "\nLongitude: " + longitude);
	});

});

