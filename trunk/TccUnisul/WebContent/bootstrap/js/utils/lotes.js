//**************************************************************************************
//  Data Tables - inicialização e internacionalização das strings
//*************************************************************************************
$(document).ready(function() {
	$('#datatables_Lotes').dataTable( {
			"oLanguage": {
				"sProcessing":   "Processando...",
			    "sLengthMenu":   "Mostrar _MENU_ registros",
			    "sZeroRecords":  "Não foram encontrados resultados",
			    "sInfo":         "Mostrando de _START_ até _END_ de _TOTAL_ registros",
			    "sInfoEmpty":    "Mostrando de 0 até 0 de 0 registros",
			    "sInfoFiltered": "(filtrado de _MAX_ registros no total)",
			    "sInfoPostFix":  "",
			    "sSearch":       "Buscar:",
			    "sUrl":          "",
			    "oPaginate": {
			        "sFirst":    "Primeiro",
			        "sPrevious": "Anterior",
			        "sNext":     "Seguinte",
			        "sLast":     "Último"
			    }
			}
		} );
	
	$('#lotesModal').on('hide.bs.modal', function() {
		limparModalLote();
	});
	
	} );

//*************************************************************************************
//                                     Criar Lote
//*************************************************************************************
function criarLote(){
	
	var pedidosController = new Array();
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
        		pedidosController = dataJson.listapedidos;
        	}
        }
	});
	
	if(!pedidosController.length)
		return;
	
	var data  = $('.checkboxLotePedido');
	var listaPedidos = new Array();
	for(var i=0; i<data.length ;i++)
	{
		if(!data[i].checked)
			continue;
		
		var result = $.grep(pedidosController, function(e){ return e.idPedido == data[i].name; });
		if(result == 0) //result[0].statusPedido.descricao != "Pronto para entrega"
		 continue;
		
		listaPedidos.push(result[0]);
	}
	
	if(!listaPedidos.length)
		return;
	var lotePedido = new Object();
	lotePedido.idLotePedido = $('#novo_pedido_idLotePedido').val();
	lotePedido.pedidos = listaPedidos;
	lotePedido.entregador;
	
	var dataJson = JSON.stringify(lotePedido);
    $.ajax({ 
           url:"adicionarLotePedido",    
           type:"POST", 
           contentType: "application/json; charset=utf-8",
           data: dataJson, //Stringified Json Object
           async: false,    //Cross-domain requests and dataType: "jsonp" requests do not support synchronous operation
           cache: false,    //This will force requested pages not to be cached by the browser          
           processData:false, //To avoid making query String instead of JSON
           success: function(resposeJsonObject){
        	   alert("Lote criado com sucesso!");
		   }
           
    });     	    
	
	
}

//*************************************************************************************
//										Editar Lote
//*************************************************************************************
function editarLote(id){
	$.get("carregarLoteId/" + id, function(data){
		if(data == null){
			 return;
		}else{
			 
			$("#modal_lista_lotepedido_idLotePedido").val(id);
			var listaPedidos = document.getElementById("modal_lista_lotePedidos");	
			
			if(data.entregador){
				$("modal_lista_lotepedido_entregador").val(data.entregador.nomeEntregador);					
			}	
				
			 for(var j=0; j<data.pedidos.length; j++){
				var pedidos = data.pedidos;
				
				var numeroPedido = pedidos[j].idPedido;
				var cliente 	 = pedidos[j].cliente.nome;
				var produtos 	 = pedidos[j].produtoPedidoList;
				var observacoes  = pedidos[j].observacao;
				var telefone 	 = pedidos[j].cliente.telefone;
				var data_hora    = pedidos[j].data;
				
				
				var tr = document.createElement("TR");
				
				tr.id = numeroPedido;
				var td = document.createElement("TD");
				td.innerText = numeroPedido;
				td.className = "modalLotePedidos_idPedido";
				tr.appendChild(td);
				
				var td = document.createElement("TD");
				td.innerText = cliente;
				tr.appendChild(td);
				
				var td = document.createElement("TD");
				td.innerText = "";
				for(var x=0; x < produtos.length; x++)
				{
					if(x!=0)
						td.innerText += ", ";
					td.innerText += "("+produtos[x].quantidade+") "+produtos[x].produto.nomeProduto;
				}
				tr.appendChild(td);
				
				var td = document.createElement("TD");
				td.innerText = observacoes;
				tr.appendChild(td);
								
				var td = document.createElement("TD");
				td.innerText = telefone;
				tr.appendChild(td);
				
				var td = document.createElement("TD");
				td.innerText = data_hora;
				tr.appendChild(td);
													
				var td = document.createElement("TD");
				var i = document.createElement("i");
				i.className = "edit_and_exclude glyphicon glyphicon-remove";
				i.style     = "align=center;";
				i.onclick = function () {
				    tr.parentElement.removeChild(tr);
				};
				td.appendChild(i);
				tr.appendChild(td);
				
				listaPedidos.appendChild(tr);
			 }	
		 $('#lotesModal').modal('show');			
		}	
	});	
}

//*************************************************************************************
//										Salva Lote Editado
//*************************************************************************************
function salvaLoteEditado(){

	var idLotePedido = $('#modal_lista_lotepedido_idLotePedido').val();
	if(!idLotePedido)
		return console.log("Erro - salvaLoteEditado() lote sem id!");
	var pedidosController = new Array();
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
				pedidosController = dataJson.listapedidos;
			}
		}
	});

	if(!pedidosController.length)
	return;
	
	var data = $('.modalLotePedidos_idPedido');	
	var listaPedidos = new Array();
	
	for(var i=0; i<data.length ;i++)
	{
		var result = $.grep(pedidosController, function(e){ return e.idPedido == data[i].innerText; });
		if(result == 0) //result[0].statusPedido.descricao != "Pronto para entrega"
			continue;
		listaPedidos.push(result[0]);
	}
	
	if(!listaPedidos.length)
		return;
	
	var lotePedido = new Object();
	lotePedido.idLotePedido = idLotePedido;
	lotePedido.pedidos 	    = listaPedidos;
	lotePedido.entregador;
	
	var dataJson = JSON.stringify(lotePedido);
	$.ajax({ 
		url:"adicionarLotePedido",    
		type:"POST", 
		contentType: "application/json; charset=utf-8",
		data: dataJson, //Stringified Json Object
		async: false,    //Cross-domain requests and dataType: "jsonp" requests do not support synchronous operation
		cache: false,    //This will force requested pages not to be cached by the browser          
		processData:false, //To avoid making query String instead of JSON
		success: function(resposeJsonObject){
			alert("Lote criado com sucesso!");
		}
	});     	    
}

//*************************************************************************************
//									Excluir Lote
//*************************************************************************************
function excluirLote(id){
	if (confirm("Realmente deseja excluir?")) {
		  $.ajax({  
		    type: "POST",  
		    url: "excluirLotePedido",  
		    data: "idLotePedido=" + id,
		    success: function(response) {  
				if (response == "sucesso") {
			    	window.location = "index";
					alert("Registro excluído com sucesso!");
				}
		    }  
		  });
		}
}


//****************************************************************************************
//									Novo Pedido no lote
//****************************************************************************************
function adionaPedidoLote() {
	
	var id_pedido = document.getElementById("modal_lista_lotepedido_adicionar_pedido_id").value;
	if(!id_pedido)
		return;
	
	var data = $('.modalLotePedidos_idPedido');	
	
	for(var i=0; i<data.length ;i++)
	{
		if(idPedido == data[i].innerText)
			return;
	}
	
	$.get("carregarPedidoId/" + id_pedido, function(pedido) {
				if (pedido) //&& (!pedido.lotePedido.idLotePedido && pedido.statusPedido.idStatusPedido == 3)
				{
					var numeroPedido = pedido.idPedido;
					var cliente 	  = pedido.cliente.nome;
					var produtos 	  = pedido.produtoPedidoList;
					var observacoes  = pedido.observacao;
					var telefone 	  = pedido.cliente.telefone;
					var data_hora    = pedido.data;
					 
					 var listaPedidos = document.getElementById("modal_lista_lotePedidos");
						var tr = document.createElement("TR");
						tr.id = numeroPedido;
						
						var td = document.createElement("TD");
						td.innerText = numeroPedido;
						tr.appendChild(td);
						
						var td = document.createElement("TD");
						td.innerText = cliente;
						tr.appendChild(td);
						
						var td = document.createElement("TD");
						td.innerText = "";
						for(var x=0; x < produtos.length; x++)
						{
							if(x!=0)
								td.innerText += ", ";
							td.innerText += "("+produtos[x].quantidade+") "+produtos[x].produto.nomeProduto;
						}
						tr.appendChild(td);
						
						var td = document.createElement("TD");
						td.innerText = observacoes;
						tr.appendChild(td);
										
						var td = document.createElement("TD");
						td.innerText = telefone;
						tr.appendChild(td);
						
						var td = document.createElement("TD");
						td.innerText = data_hora;
						tr.appendChild(td);
															
						var td = document.createElement("TD");
						var i = document.createElement("i");
						i.className = "edit_and_exclude glyphicon glyphicon-remove";
						i.style     = "align=center;";
						i.onclick = function () {
						    tr.parentElement.removeChild(tr);
						};
						td.appendChild(i);
						tr.appendChild(td);
						
						listaPedidos.appendChild(tr);
				}
				else
					return;
			});
}

//**************************************************************************************
//	Limpar Modal Lotes
//**************************************************************************************
function limparModalLote() {
	document.getElementById("modal_lista_lotePedidos").innerHTML = "";
}