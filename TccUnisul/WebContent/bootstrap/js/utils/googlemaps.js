var geocoder;
var map;
var marker;
var gmarkers = new Array();
//**************************************************************************************
//
//**************************************************************************************
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
	});
}
//**************************************************************************************
//
//**************************************************************************************
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
//**************************************************************************************
//
//**************************************************************************************
function marcarNoMapa(data) {
		
            	for(var i=0; i<data.listapedidos.length;i++)
				{
            		var endereco = data.listapedidos[i].cliente.endereco;
            		var latLng = new google.maps.LatLng(endereco.latitude, endereco.longitude); 
            		var produtoList = '<div>'+
				    	                 '<div class="table-responsive">'+
				    	                 	 '<legend align="center">Produtos</legend>'+
				    	                     '<table class="table table-striped table-bordered table-hover" id="datatables_Lista">'+
				    	                         '<thead>'+
				    	                             '<tr>'+
				    	                                 '<th>Nome</th>'+
				    	                                 '<th>Quantidade</th>'+                                   
				    	                             '</tr>'+
				    	                         '</thead>'+
				    	                         '<tbody>';
            		
            		var produtos = '';
            		for (var j= 0; j < data.listapedidos[i].produtoPedidoList.length; j++)
            		{
            			produtos += 
            					'<tr>'+
            						'<td>'+ data.listapedidos[i].produtoPedidoList[j].produto.nomeProduto +
            						'</td>'+
            						'<td>'+ data.listapedidos[i].produtoPedidoList[j].quantidade +
            						'</td>'+
            					'</tr>'; 	
            		}
            		
            		produtoList += produtos+'</tbody></table></div></div>';
            		
            		var contentString = 
            			'<div id="content" style="width:250px; height:250px">'+
		            			'<ul id="tabs" class="nav nav-tabs" data-tabs="tabs">'+
			            			'<li class="active"><a class="edit_and_exclude" href="#Pedido" data-toggle="tab">Pedido</a></li>'+
			            			'<li><a class="edit_and_exclude" href="#Cliente" data-toggle="tab">Cliente</a></li>'+
			            			'<li><a class="edit_and_exclude" href="#Produto" data-toggle="tab">Produto</a></li>'+
			            		'</ul>'+
			            		'<div id="my-tab-content" class="tab-content">'+
			            			'<br/>'+
			            			'<div class="tab-pane active" id="Pedido">'+
			            				'<legend align="center">Pedido N�'+data.listapedidos[i].idPedido+'</legend>'+
			            				'<span> <b>Data: </b>'+data.listapedidos[i].data+'</span><br/>'+
			            				'<span> <b>Status: </b> '+data.listapedidos[i].statusPedido.descricao+'</span><br/>'+
			            				'<span> <b>Retirada no local: </b> '+(data.listapedidos[i].retiradoLocal?"sim":"n�o")+'</span><br/>'+
			            				'<span> <b>Valor: </b> '+data.listapedidos[i].valorTotalPedido+'</span><br/>'+
			            				'<textarea type="text" class="form-control" readonly="true" style="max-width: 230px; overflow: auto;">'+data.listapedidos[i].observacao+'</textarea>'+
			            			'</div>'+
			            			'<div class="tab-pane" id="Cliente">'+
			            				'<legend align="center">Cliente: '+data.listapedidos[i].cliente.nome+'</legend>'+
			            				'<span> <b>Telefone: </b>'+data.listapedidos[i].cliente.telefone+'</span><br/>'+
			            				'<span> <b>Rua: </b>'+data.listapedidos[i].cliente.endereco.rua+'</span><br/>'+
			            				'<span> <b>N�: </b>'+data.listapedidos[i].cliente.endereco.numero+'</span><br/>'+
			            				'<span> <b>Bairro: </b>'+data.listapedidos[i].cliente.endereco.bairro+'</span><br/>'+
		            				'</div>'+
		            				'<div class="tab-pane" id="Produto">'+
		            					'<div>'+  produtoList +
		            					'</div>'+
		            				'</div>'+
		            			'</div>'+
		            	'</div>';
            		
     	            var myinfowindow = new google.maps.InfoWindow({
     	                content: contentString,
     	                maxWidth: 800,
     	                maxHeight: 800
     	            });
    				var marker = new google.maps.Marker({
		                position: latLng,
		                title: "Pedido n�: "+ data.listapedidos[i].idPedido,
		                id: "Pedido"+ data.listapedidos[i].idPedido,
		                status: data.listapedidos[i].statusPedido.idStatusPedido,
		                map: map,
		                infowindow: myinfowindow
            		});
    				bindInfoWindow(marker, map, myinfowindow,data.listapedidos[i].idPedido);
    				
				}         
}

//**************************************************************************************
// ativa infowindow no mapa ao clicar em um pedido
//**************************************************************************************
function myclick(i) {
	  google.maps.event.trigger(gmarkers[i], "click");
	}

//**************************************************************************************
//Sets the map on all markers in the array.
//**************************************************************************************
function setAllMap(map) {
  for (var i = 0; i < gmarkers.length; i++) {
	  gmarkers[i].setMap(map);
  }
}

//**************************************************************************************
//Removes the markers from the map, but keeps them in the array.
//**************************************************************************************
function clearMarkers() {
  setAllMap(null);
}
//**************************************************************************************
//Shows any markers currently in the array.
//**************************************************************************************
function showMarkers() {
  setAllMap(map);
}

//**************************************************************************************
//
//**************************************************************************************
function bindInfoWindow(marker, map, infowindow,idPedido) {
	
	gmarkers.push(marker);
    google.maps.event.addListener(marker, 'click', function() {
    	for(var i=0; i < gmarkers.length;i++)
    		gmarkers[i].infowindow.close();
    	infowindow.open(map, marker);
    	$('.bs-example-tabs').tab('show');
    });
    
    google.maps.event.addListener(infowindow, 'domready', function (e) {
            $('.bs-example-tabs').tab();
   }); 
 
	var table_pedidos = $(".table_listaPedidos");
    var marker_num = gmarkers.length-1;
	for(var x=0; x < table_pedidos.length; x++)
	{
		if(table_pedidos[x].id != idPedido)
			continue;
		
		table_pedidos[x].onclick = function(){ myclick(marker_num);};
		break;
	}
}

//**************************************************************************************
//Filtra por status
//**************************************************************************************
function filtarPedidosPorStatus() {
	
	var idStatus = $("#filtrar_status").val();
	if(idStatus == 0)
	{	
		showMarkers();
		return;
	}
	
	clearMarkers();
	for (var i = 0; i < gmarkers.length; i++) {
	  
		if(gmarkers[i].status != idStatus)
			continue;
		gmarkers[i].setMap(map);
	}
}

//**************************************************************************************
//
//**************************************************************************************
$(document).ready(function () {

	initialize();
	carregaPedidosNoMapa();
	function CarregaEndereco(endereco) {
		geocoder.geocode({ 'address': endereco + ', Brasil', 'region': 'BR' }, function (results, status) {
			if (status == google.maps.GeocoderStatus.OK) {
				if (results[0]) {
					var latitude = results[0].geometry.location.lat();
					var longitude = results[0].geometry.location.lng();
		
					$('#editar_cliente_latitude').val(latitude);
                   	$('#editar_cliente_longitude').val(longitude);
                   	
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
                   	
               		$('#editar_cliente_rua').val(rua);
               		document.getElementById("editar_cliente_rua").innerHtml = rua;
               		$('#editar_cliente_bairro').val(bairro);
               		$('#editar_cliente_cidade').val(cidade);
                   	$('#editar_cliente_cep').val(CEP);
                   	$('#editar_cliente_numero').val(numero);
				}
			}
		});
	}
	
	function carregarNoMapa(endereco) {
		geocoder.geocode({ 'address': endereco + ', Brasil', 'region': 'BR' }, function (results, status) {
			if (status == google.maps.GeocoderStatus.OK) {
				if (results[0]) {
					var latitude = results[0].geometry.location.lat();
					var longitude = results[0].geometry.location.lng();
		
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
               		document.getElementById("novo_pedido_rua").innerHtml = rua;
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
	
	$("#novo_pedido_rua").blur(function() {
		if($(this).val() != "")
			carregarNoMapa($(this).val());
	});
	
	$("#editar_cliente_rua").blur(function() {
		if($(this).val() != "")
			CarregaEndereco($(this).val());
	});
	  
	$("#editar_cliente_rua").autocomplete({
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
			$("#editar_cliente_latitude").val(ui.item.latitude);
    		$("#editar_cliente_longitude").val(ui.item.longitude);
			var location = new google.maps.LatLng(ui.item.latitude, ui.item.longitude);
			marker.setPosition(location);
			map.setCenter(location);
			map.setZoom(16);
		}
	});
	
	$("#novo_pedido_rua").autocomplete({
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
	});

});

