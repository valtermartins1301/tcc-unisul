//****************************************************
// Novo Pedido - Atualiza valor do pedido
//****************************************************
function somaValorTotalPedido(Valor,Quantidade)
{
	var valorTotal = document.getElementById("novo_pedido_valor_total");
	
	var Total = new Number(valorTotal.innerText);
	Valor = new Number(Valor);
	Quantidade = new Number(Quantidade);
	
	var resultado = new Number(Total + (Valor*Quantidade));
	valorTotal.innerText = resultado.toPrecision(4);
}

//****************************************************
//Novo Pedido - Atualiza valor do pedido
//****************************************************
function diminuiValorTotalPedido(Valor,Quantidade)
{
	var valorTotal = document.getElementById("novo_pedido_valor_total");
	
	var Total = new Number(valorTotal.innerText);
	Valor = new Number(Valor);
	Quantidade = new Number(Quantidade);

	var resultado = new Number(Total - (Valor*Quantidade));
	valorTotal.innerText = resultado.toPrecision(4);
}

//****************************************************
// Novo Pedido - Insere produto selecionado na tabela
//****************************************************
function insertOnTable(produto)
{
	var List = document.getElementById("novo_pedido_poduto_nome");
	var ProdutoValor = List[List.selectedIndex].value;
	var Produto = List[List.selectedIndex].outerText;
	
	var Qtd		= document.getElementById("novo_pedido_poduto_quantidade").value;
	
	if(!Produto || !Qtd)
		return;
	
	var ListaProdutos = document.getElementById("novo_pedido_lista_produtos");
	
	
	var trs = ListaProdutos.rows;
	var tds = null;
  	for (var i = 0; i < trs.length; i++)
  	{
 		tds = trs[i].cells;		
 		if(Produto == tds[0].innerHTML)				//Verifica elementos duplicados pelo nome
 			return;
  	}
  	
	var tr = document.createElement("TR");
	var td = document.createElement("TD");
	td.innerText = Produto;
	tr.appendChild(td);
	
	var td = document.createElement("TD");
	td.innerText = ProdutoValor;
	tr.appendChild(td);
	
	var td = document.createElement("TD");
	td.innerText = Qtd;
	tr.appendChild(td);
	
	var td = document.createElement("TD");
	var i = document.createElement("i");
	i.className = "edit_and_exclude glyphicon glyphicon-remove";
	i.style     = "align=center;";
	i.onclick = function () {
	    tr.parentElement.removeChild(tr);
	    diminuiValorTotalPedido(ProdutoValor,Qtd);
	};
	td.appendChild(i);
	tr.appendChild(td);
	
	ListaProdutos.appendChild(tr);
	somaValorTotalPedido(ProdutoValor,Qtd);
}

//**************************************************************************************
//  Data Tables - inicialização e internacionalização das strings
//*************************************************************************************
$(document).ready(function() {
	$('#datatables_lista').dataTable( {
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

//**************************************************************************************
// Novo Pedido - adiciona pedido no banco
//*************************************************************************************
function adicionarPedido(){			
	var endereco = new Object();		
	endereco.rua = $('#novo_pedido_rua').val();
	endereco.bairro = $('#novo_pedido_bairro').val();
	endereco.numero = $('#novo_pedido_numero').val();
	endereco.cidade = $('#novo_pedido_cidade').val();
	endereco.cep = $('#novo_pedido_cep').val();
	endereco.complemento = $('#novo_pedido_complemento').val();
	
	var cliente = new Object();		
	cliente.nome = $('#novo_pedido_nome').val();
	cliente.telefone = $('#novo_pedido_telefone').val();
	cliente.endereco = endereco;
	
	listaProduto = new Array();
	var trs = document.getElementById('novo_pedido_lista_produtos').rows;
	var tds = null;
  	for (var i = 0; i < trs.length; i++)
  	{
 		tds = trs[i].cells;		
 		var produto = new Object();
 		produto.idProduto = (i+1).toString();
 		produto.nomeProduto = tds[0].innerHTML;
 		produto.preco = tds[1].innerHTML;
 		quantidade = tds[2].innerHTML;
 		
 		var produtoPedidoId = new Object();
 		produtoPedidoId.produto = produto;
 		var produtoPedido = new Object();
 		produtoPedido.idProdutoPedido = produtoPedidoId;
 		produtoPedido.quantidade = tds[2].innerHTML;
 		
 		listaProduto.push(produtoPedido);		 		
  	}	
	
	var statusPedido = Object();
	statusPedido.idStatusPedido = 1;
	
	var pedido = new Object();
	pedido.observacao = "Teste";
	pedido.valorTotalPedido = "";
	pedido.retiradoLocal = document.getElementById("novo_pedido_retiradoLocal").checked;
	pedido.statusPedido = statusPedido;
	pedido.cliente = cliente;
	pedido.produtoPedidoList = listaProduto;
	
	var dataJson = JSON.stringify(pedido);
	
    $.ajax({ 
           url:"adicionarPedido",    
           type:"POST", 
           contentType: "application/json; charset=utf-8",
           data: dataJson, //Stringified Json Object
           async: false,    //Cross-domain requests and dataType: "jsonp" requests do not support synchronous operation
           cache: false,    //This will force requested pages not to be cached by the browser          
           processData:false, //To avoid making query String instead of JSON
           success: function(resposeJsonObject){                           
		   }
           
    });     	    
 }; 
 
 function carregarCliente(){
	 var telefone = $('#novo_pedido_telefone').val();
	 
	 $.get("carregarCliente/" + telefone, function(data){
		 $('#myModal').modal('show');
			alert(data);
	 });
 }
 



