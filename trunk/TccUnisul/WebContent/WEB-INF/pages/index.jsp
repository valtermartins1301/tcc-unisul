<script type="text/javascript">
function adicionar() {
	  
  	  var nome = $('#novo_pedido_nome').val();
	  var telefone = $('#novo_pedido_telefone').val();
	  var rua = $('#novo_pedido_rua').val();
	  var bairro = $('#novo_pedido_bairro').val();
	  var numero = $('#novo_pedido_numero').val();
	  var cidade = $('#novo_pedido_cidade').val();
	  var cep = $('#novo_pedido_cep').val();
	  var complemento = $('#novo_pedido_complemento').val();
	  var observacoes = $('#novo_pedido_observacoes').val();
	  var nomeProduto;
	  var preco;
	  var quantidade;
	  
	 
	  var teste = $("form").serialize();
		 
	  $.ajax({  
		    type: "POST",  
		    url: "adicionaPedido",  
		    data: teste,  
		    success: function(response){
		      if (response == "sucesso") {
			      alert("Registro salvo com sucesso!");
		      }
		    }  
	  });
};	

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
		  	for (var i = 0; i < trs.length; i++){
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
		pedido.retiradoLocal = $(novo_pedido_retiradoLocal).checked;
		pedido.statusPedido = statusPedido;
		pedido.cliente = cliente;
		pedido.produtoPedidoList = listaProduto;
		
		var dataJson = JSON.stringify(pedido);
		var dataJsonTrue = JSON.parse(dataJson);
		
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
	 
	

	
</script>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<br/>
<table>
 	<tr>
 	<td width="25"></td>
 		<td>
			<form role="form" class="well well-small"  style="width: 400px">
			  <legend>Novo Pedido</legend>
				  <!-- Cliente -->
				  <div class="form-group control-group form-inline">
				    <input type="text" class="form-control" name="nome" id="novo_pedido_nome" placeholder="Nome do cliente">
				    <input type="text" class="form-control" name="telefone" id="novo_pedido_telefone" onblur="carregarCliente()" placeholder="Telefone">
				  </div>
				  <div class="form-group">
				    <input type="text" class="form-control" name="rua" id="novo_pedido_rua" placeholder="Rua">
				  </div>
				  <div class="form-group control-group form-inline">
				    <input type="text" class="form-control" name="bairro" id="novo_pedido_bairro" placeholder="Bairro">
				    <input type="text" class="form-control" name="numero" id="novo_pedido_numero" placeholder="Número">			  
				  </div>
				  <div class="form-group control-group form-inline">
				    <input type="text" class="form-control" name="cidade" id="novo_pedido_cidade" placeholder="Cidade">
				    <input type="text" class="form-control" name="cep" id="novo_pedido_cep" placeholder="CEP">			  
				  </div>
				  <div class="form-group">
				    <input type="text" class="form-control" name="complemento" id="novo_pedido_complemento" placeholder="Complemento">
				  </div>
				  
				  <!-- Produto -->
				  <div class="form-group control-group form-inline">
				    <select class="form-control" name="produto" id="novo_pedido_poduto_nome">
					   <c:forEach var="produto" items="${produtos}">
					   			
					 	 <option>${produto.nomeProduto}</option>
					   </c:forEach>
					</select>
				    <input type="number" class="form-control" name="quantidade" id="novo_pedido_poduto_quantidade" placeholder="Qtd" size="4">	
				    <a onclick="insertOnTable()"><i class="glyphicon glyphicon-plus-sign"></i></a>		  
				  </div>
				  <div style="height:100px; overflow:auto;">
					  <table class="table table-bordered">
						<thead>
							<th>
								Produto
							</th>
							<th>
								Valor
							</th>
							<th>
								Qtd
							</th>
							<th>
							</th>					
						</thead>
						<tbody id="novo_pedido_lista_produtos">
						
						</tbody>
					  </table>
				  </div>
				  
				  <!-- Observações -->
				  <div class="form-group">
				    <br/>
				    <textarea type="text" class="form-control" name="observacoes" id="novo_pedido_observacoes" placeholder="Observações"></textarea>
				  </div>
				  
				  <!-- Checkbox -->
				  <div class="control-group form-inline">
				    <label class="checkbox-inline">
	  					<input name="retiradoLocal" id="novo_pedido_retiradoLocal" type="checkbox">Retirada no local
					</label>
				    <label class="checkbox-inline pull-right">
				      <input type="checkbox">Salvar cliente
				    </label>
				    <br/>
				  </div>
				  
				  <div class="control-group form-inline text-center">
				  	<br/>
				  	<input type="button" class="btn btn-success btn-lg"  onclick="adicionarPedido()" style="font-weight: bold;font-size:13pt;" value="Salvar"/>
				  	&nbsp;&nbsp;&nbsp; 
				  	<input type="button" class="btn btn-default btn-lg" style="font-weight: bold;font-size:13pt;" value="Limpar"/>
				  </div>
			</form>
 		</td>
 		<td width="25"></td>
 		<td width="900px" height="700px">
 			<div class="well well-small" style='width:100%;height:100%' >
      			<div id="map_canvas" style='width:100%;height:100%'></div>
      		</div>
    	</td>
 		<td width="25"></td>
 	</tr>
</table>
<div>
     <div style="width: 100%">
         <div class="panel panel-default">
             <!-- /.panel-heading -->
             <div class="panel-body">
                 <div class="table-responsive">
                     <table class="table table-striped table-bordered table-hover" id="datatables_lista">
                         <thead>
                             <tr>
                                 <th>Nº</th>
                                 <th>Cliente</th>
                                 <th>Produtos</th>
                                 <th>Obs:</th>
                                 <th>Telefone</th>
                                 <th>Lote</th>
                                 <th>Data/Hora</th>
                                 <th>Status</th>
                                 <th></th>                                     
                             </tr>
                         </thead>
                         <tbody>
                         	<tr class="odd gradeX">
                                 <td>1</td>
                                 <td>Cliente1</td>
                                 <td>Produto1; Produto2</td>
                                 <td>Observação</td>
                                 <td>32584215</td>
                                 <td>Lote 1</td>
                                 <td>13/04/2014 23:25</td>
                                 <td>Cancelado</td>
                                 <td>Editar/Excluir</td>                                  
                             </tr>
                             <tr class="even gradeC">
                                 <td>1</td>
                                 <td>Cliente2</td>
                                 <td>Produto1; Produto2</td>
                                 <td>Observação</td>
                                 <td>32584215</td>
                                 <td>Lote1</td>
                                 <td>13/04/2014 23:25</td>
                                 <td>Finalizado</td>
                                 <td>Editar/Excluir</td>   
                             </tr>
                             <tr class="odd gradeA">
                                 <td>1</td>
                                 <td>Cliente3</td>
                                 <td>Produto1; Produto 2</td>
                                 <td>Observação</td>
                                 <td>32584215</td>
                                 <td>Lote3</td>
                                 <td>13/04/2014 23:25</td>
                                 <td>Pendente</td>
                                 <td>Editar/Excluir</td>  
                             </tr>
                             <tr class="even gradeA">
                                 <td>1</td>
                                 <td>Cliente4</td>
                                 <td>Produto1; Produto2</td>
                                 <td>Observação</td>
                                 <td>32584215</td>
                                 <td>Lote1</td>
                                 <td>13/04/2014 23:25</td>
                                 <td>Pendente</td>
                                 <td>Editar/Excluir</td>  
                             </tr>
                         </tbody>
                  	  </table>
                    </div>
                 </div>
            </div>
      </div>
</div>

<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h4 class="modal-title" id="myModalLabel">Cliente cadastrado com esse número de telefone.</h4>
      </div>
      <div class="modal-body">
       Testeeeeeeeeeeeeeeeeeeeeee
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary">Save changes</button>
      </div>
    </div>
  </div>
</div>