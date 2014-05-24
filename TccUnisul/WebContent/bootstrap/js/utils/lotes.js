//**************************************************************************************
//  Data Tables - inicializa��o e internacionaliza��o das strings
//*************************************************************************************
$(document).ready(function() {
	$('#datatables_Lotes').dataTable( {
			"oLanguage": {
				"sProcessing":   "Processando...",
			    "sLengthMenu":   "Mostrar _MENU_ registros",
			    "sZeroRecords":  "N�o foram encontrados resultados",
			    "sInfo":         "Mostrando de _START_ at� _END_ de _TOTAL_ registros",
			    "sInfoEmpty":    "Mostrando de 0 at� 0 de 0 registros",
			    "sInfoFiltered": "(filtrado de _MAX_ registros no total)",
			    "sInfoPostFix":  "",
			    "sSearch":       "Buscar:",
			    "sUrl":          "",
			    "oPaginate": {
			        "sFirst":    "Primeiro",
			        "sPrevious": "Anterior",
			        "sNext":     "Seguinte",
			        "sLast":     "�ltimo"
			    }
			}
		} );
	} );


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

function editarLote(id){
	$.get("carregarLoteId/" + id, function(data){
		if(data == null){
			 return;
		}else{
			$('#novo_pedido_idLotePedido').val(data.idLotePedido);
			 for(var j=0; j<data.pedidos.length; j++){
				var pedidos = data.pedidos;
				var listaPedidos = document.getElementById("modal_lista_lotePedidos");
				var numeroPedido = pedidos[j].idPedido;
				var nome = pedidos[j].cliente.nome;
				var telefone = pedidos[j].cliente.telefone;
				var entregador = data.entregador.nome;
			 
				var tr = document.createElement("TR");
				tr.id = numeroPedido;
				var td = document.createElement("TD");
				td.innerText = numeroPedido;
				tr.appendChild(td);
				
				var td = document.createElement("TD");
				td.innerText = nome;
				tr.appendChild(td);
				
				var td = document.createElement("TD");
				td.innerText = telefone;
				tr.appendChild(td);
				
				var td = document.createElement("TD");
				td.innerText = entregador;
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
