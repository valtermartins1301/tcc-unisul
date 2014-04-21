
//****************************************************
// Novo Pedido - Insere produto selecionado na tabela
//****************************************************
function insertOnTable()
{
	var Produto = document.getElementById("novo_pedido_poduto_nome").value;
	var Qtd		= document.getElementById("novo_pedido_poduto_quantidade").value;
	
	if(!Produto || !Qtd)
		return;
	
	var ListaProdutos = document.getElementById("novo_pedido_lista_produtos");
	
	var tr = document.createElement("TR");
	var td = document.createElement("TD");
	td.innerText = Produto;
	tr.appendChild(td);
	
	var td = document.createElement("TD");
	td.innerText = "R$ 00,00";
	tr.appendChild(td);
	
	var td = document.createElement("TD");
	td.innerText = Qtd;
	tr.appendChild(td);
	
	var td = document.createElement("TD");
	var a  = document.createElement("A");
	var span = document.createElement("SPAN");
	span.className = "glyphicon glyphicon-remove";
	span.style     = "align=center;";
	a.appendChild(span);
	a.onclick = function () {
	    tr.parentElement.removeChild(tr);
	};
	td.appendChild(a);
	tr.appendChild(td);
	
	ListaProdutos.appendChild(tr);
}


//**************************************************************************************
//  Data Tables - inicialização e internacionalização das strings
//*************************************************************************************
$(document).ready(function() {
	$('#lista_pedidos').dataTable( {
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



