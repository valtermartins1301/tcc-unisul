//****************************************************
// Novo Pedido - Atualiza valor do pedido
//****************************************************
function valorTotalPedido(Valor,Quantidade,Operacao,Tag)
{
	var valorTotal = document.getElementById("novo_pedido_valor_total");
	
	var Total = new Number(valorTotal.innerText);
	Valor = new Number(Valor);
	Quantidade = new Number(Quantidade);
	
	var obj = new Object();
	obj.valorTotal = Total;
	obj.valor 	   = Valor;
	obj.quantidade = Quantidade;
	
	var url = "";
	if(Operacao == "somar")
		url = "somaValorTotalPedido";
	else if(Operacao == "subtrair")
		url = "diminuiValorTotalPedido";
		
	var dataJson = JSON.stringify(obj);
	$.ajax({ 
        url: url,    
        type:"POST", 
        contentType: "application/json; charset=utf-8",
        data: dataJson, //Stringified Json Object
        async: false,    //Cross-domain requests and dataType: "jsonp" requests do not support synchronous operation
        cache: false,    //This will force requested pages not to be cached by the browser          
        processData:false, //To avoid making query String instead of JSON
        success: function(Resultado) {
        	if(Resultado.status == "OK")
        	{
        		var jsonResult = JSON.parse(Resultado.data);
        		var result = jsonResult.resultado;
        		valorTotal.innerText = result.toFixed(2);
        	}
        	else if(Tag)
        	{	
        		Tag.parentElement.removeChild(Tag);
        	}
		   }
	});
}

//****************************************************
// Novo Pedido - Insere produto selecionado na tabela
//****************************************************
function insertOnTable(produto)
{
	var List = document.getElementById("novo_pedido_poduto_nome");
	var id_valor = List[List.selectedIndex].value;
	
	var splitIt = id_valor.split('_'); 
	var ProdutoId = splitIt[0]
	var ProdutoValor = splitIt[1];
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
	tr.id = ProdutoId;
	var td = document.createElement("TD");
	td.innerText = ProdutoValor;
	tr.appendChild(td);
	
	var td = document.createElement("TD");
	td.innerText = Qtd;
	tr.appendChild(td);
	
	var td = document.createElement("TD");
	var i = document.createElement("i");
	i.className = "edit_and_exclude glyphicon glyphicon-plus-sign";
	i.style     = "align=center;";
	i.onclick = function () {
	    tr.parentElement.removeChild(tr);
	    valorTotalPedido(ProdutoValor,Qtd,"subtrair",tr);
	};
	td.appendChild(i);
	tr.appendChild(td);
	
	ListaProdutos.appendChild(tr);
	valorTotalPedido(ProdutoValor,Qtd,"somar");
}

//**************************************************************************************
//  Data Tables - inicializa��o e internacionaliza��o das strings
//*************************************************************************************
$(document).ready(function() {
	$('#datatables_lista').dataTable( {
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

//**************************************************************************************
// Novo Pedido - adiciona pedido no banco
//*************************************************************************************
function adicionarPedido(){			
	var endereco = new Object();	
	endereco.idEndereco = $('#novo_pedido_idEndereco').val();
	endereco.rua = $('#novo_pedido_rua').val();
	endereco.bairro = $('#novo_pedido_bairro').val();
	endereco.numero = $('#novo_pedido_numero').val();
	endereco.cidade = $('#novo_pedido_cidade').val();
	endereco.cep = $('#novo_pedido_cep').val();
	endereco.complemento = $('#novo_pedido_complemento').val();
	endereco.latitude = $('#novo_pedido_latitude').val();
	endereco.longitude = $('#novo_pedido_longitude').val();
	
	var cliente = new Object();	
	cliente.idCliente = $('#novo_pedido_idCliente').val();
	cliente.nome = $('#novo_pedido_nome').val();
	cliente.telefone = $('#novo_pedido_telefone').val();
	cliente.endereco = endereco;
	
	var statusPedido = Object();
	var idStatusPedido = $('#novo_pedido_idStatusPedido').val();
	if(idStatusPedido == ""){
		statusPedido.idStatusPedido = 1;
	}else{
		statusPedido.idStatusPedido = idStatusPedido;
	}
	
	var pedido = new Object();
	pedido.idPedido = $('#novo_pedido_idPedido').val();
	pedido.observacao = $('#novo_pedido_observacoes').val();
	pedido.valorTotalPedido = document.getElementById("novo_pedido_valor_total").value;
	pedido.retiradoLocal = document.getElementById("novo_pedido_retiradoLocal").checked;
	pedido.statusPedido = statusPedido;
	pedido.cliente = cliente;
	
	listaProduto = new Array();
	var trs = document.getElementById('novo_pedido_lista_produtos').rows;
	var tds = null;
  	for (var i = 0; i < trs.length; i++)
  	{
 		tds = trs[i].cells;		
 		var produto = new Object();
 		produto.idProduto = trs[i].id;
 		produto.nomeProduto = tds[0].innerHTML;
 		produto.preco = tds[1].innerHTML;
 		quantidade = tds[2].innerHTML;
 		
 		var produtoPedido = new Object();
 		produtoPedido.produto = produto;
 		produtoPedido.quantidade = tds[2].innerHTML;
 		
 		listaProduto.push(produtoPedido);		 		
  	}		
	
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
        	   alert("Pedido adicionado com sucesso!");
        	   limparCampos();
		   }
           
    });     	    
 }; 
 
 function carregarCliente(){
	 var telefone = $('#novo_pedido_telefone').val();
	 
	 $.get("carregarClienteTelefone/" + telefone, function(data){
		
		 if(data.length == 0){
			 return;
		 }else{
			 for(var j=0; j<data.length; j++){
				 var listaClientes = document.getElementById("modal_lista_clientes");
				var nome = data[j].nome;
				var telefone = data[j].telefone;
				var email = data[j].email;
			 
				var tr = document.createElement("TR");
				tr.id = data[j].idCliente;
				var td = document.createElement("TD");
				td.innerText = nome;
				tr.appendChild(td);
				
				var td = document.createElement("TD");
				td.innerText = telefone;
				tr.appendChild(td);
				
				var td = document.createElement("TD");
				td.innerText = email;
				tr.appendChild(td);
									
				var td = document.createElement("TD");				
				var i = document.createElement("a");
				i.className = "edit_and_exclude glyphicon glyphicon-remove";
				i.style     = "align=center;";
				i.id = j;
				i.onclick = function(){ populaCadastroPedido(this.id, data);};
				td.appendChild(i);
				tr.appendChild(td);
				
				listaClientes.appendChild(tr);
			 }	
		 $('#myModal').modal('show');
		 }			 
	 });
 }
 
 
 function editarPedido(id){
	 $.get("carregarPedidoId/" + id, function(pedido){
	 if(pedido != null){
		 	limparCampos();
		 	$('#novo_pedido_idPedido').val(pedido.idPedido);
		 	$('#novo_pedido_idCliente').val(pedido.cliente.idCliente);
		 	$('#novo_pedido_idEndereco').val(pedido.cliente.endereco.idEndereco);
		 	$('#novo_pedido_idStatusPedido').val(pedido.statusPedido.idStatusPedido);
			$('#novo_pedido_rua').val(pedido.cliente.endereco.rua);
			$('#novo_pedido_bairro').val(pedido.cliente.endereco.bairro);
			$('#novo_pedido_numero').val(pedido.cliente.endereco.numero);
			$('#novo_pedido_cidade').val(pedido.cliente.endereco.cidade);
			$('#novo_pedido_cep').val(pedido.cliente.endereco.cep);
			$('#novo_pedido_complemento').val(pedido.cliente.endereco.complemento);
			$('#novo_pedido_latitude').val(pedido.cliente.endereco.latitude);
			$('#novo_pedido_longitude').val(pedido.cliente.endereco.longitude);
			
			$('#novo_pedido_nome').val(pedido.cliente.nome);
			$('#novo_pedido_telefone').val(pedido.cliente.telefone);
			
			$('#novo_pedido_observacoes').val(pedido.observacao);
			$('#novo_pedido_valor_total').val(pedido.valorTotalPedido);
			
			for(var i=0; i<pedido.produtoPedidoList.length; i++){
				produto = pedido.produtoPedidoList[i].produto.nomeProduto;
				quantidade = pedido.produtoPedidoList[i].quantidade;				
				$( "#novo_pedido_poduto_nome option:selected" ).text(produto);
				$('#novo_pedido_poduto_quantidade').val(quantidade);
				insertOnTable();
			}
		}else{
			alert("Nenhum pedido encontrado!");
		}	
	 });
 }
 
 
//**************************************************************************************
//
//**************************************************************************************
 function populaCadastroPedido(idLinha, data){	
	 		idLinha = parseInt(idLinha);
	 		var idCliente = data[idLinha].idCliente;
	 		var idEndereco = data[idLinha].endereco.idEndereco;
			var nome = data[idLinha].nome;
			var rua = data[idLinha].endereco.rua;
			var bairro = data[idLinha].endereco.bairro;
			var numero = data[idLinha].endereco.numero;
			var cidade = data[idLinha].endereco.cidade;
			var cep = data[idLinha].endereco.cep;
			var complemento = data[idLinha].endereco.complemento;
			$('#novo_pedido_idCliente').val(idCliente);
			$('#novo_pedido_idEndereco').val(idEndereco);
			$('#novo_pedido_nome').val(nome);
			$('#novo_pedido_rua').val(rua);
			$('#novo_pedido_bairro').val(bairro);
			$('#novo_pedido_numero').val(numero);
			$('#novo_pedido_cidade').val(cidade);
			$('#novo_pedido_cep').val(cep);
			$('#novo_pedido_complemento').val(complemento);
			$('#myModal').modal('hide');
			document.getElementById("modal_lista_clientes").innerHTML = "";
 }
 
//**************************************************************************************
//-------------------------------Tela Listar Cliente------------------------------------
//**************************************************************************************
 function exibirCadastrarCliente(){
	 $('#editar_cliente').modal('show');
 }
 
 function cadastrarEditarCliente(){
	 var cliente = $("form").serialize();
	 
	 $.ajax({ 
		 url: "adicionaEditaCliente",    
		 type:"POST", 
		 data: cliente,
		 success: function(response){
			 if(response == "sucesso"){
				 alert("Cliente salvo com sucesso");
				 $('#editar_cliente').modal('hide');
				 window.location = "clienteListar";    		 
			 }        	 
		 }         
	 });
	 
	 
 }
 
 function editarCliente(id){
	 $.get("carregarClienteId/" + id, function(data){
		 	$('#editar_cliente_nome').val(data.nome);
			$('#editar_cliente_telefone').val(data.telefone);
			$('#editar_cliente_cep').val(data.endereco.cep);
			$('#editar_cliente_rua').val(data.endereco.rua);
			$('#editar_cliente_numero').val(data.endereco.numero);
			$('#editar_cliente_bairro').val(data.endereco.bairro);
			$('#editar_cliente_cidade').val(data.endereco.cidade);
			$('#editar_cliente_complemento').val(data.endereco.complemento);
			$('#editar_cliente_idCliente').val(data.idCliente);
			$('#editar_cliente_idEndereco').val(data.endereco.idEndereco);
			$('#editar_cliente').modal('show');
	 });
 }
 
 function limparCamposCliente(){
	 $('#editar_cliente_nome').val("");
	 $('#editar_cliente_telefone').val("");
	 $('#editar_cliente_cep').val("");
	 $('#editar_cliente_rua').val("");
	 $('#editar_cliente_numero').val("");
	 $('#editar_cliente_bairro').val("");
	 $('#editar_cliente_cidade').val("");
	 $('#editar_cliente_complemento').val("");
	 $('#editar_cliente_idCliente').val("");
	 $('#editar_cliente_idEndereco').val("");	 
 }
 
//**************************************************************************************
//-------------------------------Tela Listar Produto------------------------------------
//**************************************************************************************
 
 function exibirCadastrarProduto(){
	 $('#editar_produto').modal('show');
 }
 
 function cadastrarEditarProduto(){
	 var produto = $("form").serialize();
	
	 $.ajax({ 
		 url: "adicionaEditaProduto",    
		 type:"POST", 
		 data: produto,
		 success: function(response){
			 if(response == "sucesso"){
				 alert("Produto salvo com sucesso");
				 $('#editar_produto').modal('hide');
				 window.location = "produtoListar";    		 
			 }        	 
		 }         
	 }); 
 }
 
 function editarProduto(id){
	 $.get("carregarProdutoId/" + id, function(data){
		 	$('#editar_produto_nomeProduto').val(data.nomeProduto);
			$('#editar_produto_preco').val(data.preco);
			$('#editar_produto_descricao').val(data.descricao);
			$('#editar_produto_idProduto').val(data.idProduto);
			$('#editar_produto').modal('show');
	 });
 }
 
 function limparCamposProduto(){
	 $('#editar_produto_nomeProduto').val("");
	 $('#editar_produto_preco').val("");
	 $('#editar_produto_descricao').val("");
	 $('#editar_produto_idProduto').val("");	 
 }
 

//**************************************************************************************
//-------------------------------Limpar Campos Tela Pedido------------------------------
//**************************************************************************************
 
 function limparCampos(){
	 	$('#novo_pedido_idCliente').val("");
	 	$('#novo_pedido_idEndereco').val("");
	 	$('#novo_pedido_idPedido').val("");
	 	$('#novo_pedido_idStatusPedido').val("");
	 	$('#novo_pedido_nome').val("");
		$('#novo_pedido_rua').val("");
		$('#novo_pedido_telefone').val("");
		$('#novo_pedido_bairro').val("");
		$('#novo_pedido_numero').val("");
		$('#novo_pedido_cidade').val("");
		$('#novo_pedido_cep').val("");
		$('#novo_pedido_complemento').val("");
		$('#novo_pedido_retiradoLocal').val("");
		$('#novo_pedido_poduto_quantidade').val("");
		$('#novo_pedido_latitude').val("");
		$('#novo_pedido_longitude').val("");
		$('#novo_pedido_observacoes').val("");
		$('#novo_pedido_valor_total').val("");
		document.getElementById("novo_pedido_valor_total").innerHTML = "0.0";
		document.getElementById("modal_lista_clientes").innerHTML = "";
		document.getElementById("novo_pedido_lista_produtos").innerHTML = "";
		$('#myModal').modal('hide');
 }
 
 function limparModalCliente(){
		document.getElementById("modal_lista_clientes").innerHTML = "";
 }
 
 

 
 



