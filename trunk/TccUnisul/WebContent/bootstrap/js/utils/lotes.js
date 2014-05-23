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
	} );



function editarLote(id){
	$.get("carregarLoteId/" + id, function(data){
		if(data == null){
			 return;
		}else{
			 
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
