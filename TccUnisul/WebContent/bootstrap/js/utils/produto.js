//**************************************************************************************
//-------------------------------Tela Listar Produto------------------------------------
//**************************************************************************************
 
//**************************************************************************************
//								Exibir Cadastrar Produto									
//**************************************************************************************
 function exibirCadastrarProduto(){
	 $('#editar_produto').modal('show');
 }
 
//**************************************************************************************
//									Editar Produto									
//**************************************************************************************
 
 function cadastrarEditarProduto(){
	 var produto = $("form").serialize();
	
	 $.ajax({ 
		 url: "adicionaEditaProduto",    
		 type:"POST", 
		 data: produto,
		 success: function(response){
			 if(response == "sucesso"){
				 $('#editar_produto').modal('hide');
				 bootbox.alert("Produto salvo com sucesso",function(){
					 window.location = "produtoListar";  
				 	}	 
				 );
			 }        	 
		 }         
	 }); 
 }
 
//**************************************************************************************
//								Exibir Editar Produto								
//**************************************************************************************
 function editarProduto(id){
	 $.get("carregarProdutoId/" + id, function(data){
		 	$('#editar_produto_nomeProduto').val(data.nomeProduto);
			$('#editar_produto_preco').val(data.preco);
			$('#editar_produto_descricao').val(data.descricao);
			$('#editar_produto_idProduto').val(data.idProduto);
			$('#editar_produto').modal('show');
	 });
 }
 
//**************************************************************************************
//								Limpar Campos Produto									
//**************************************************************************************
 
 function limparCamposProduto(){
	 $('#editar_produto_nomeProduto').val("");
	 $('#editar_produto_preco').val("");
	 $('#editar_produto_descricao').val("");
	 $('#editar_produto_idProduto').val("");	 
 }
 
//*************************************************************************************
//									Excluir Produto
//*************************************************************************************

function excluirProduto(id) {
	bootbox.confirm("Realmente deseja excluir?",function(result){
		if (result) {
			$.ajax({
				type : "POST",
				url : "excluirProduto",
				data : "idProduto=" + id,
				success : function(response) {
					if (response == "sucesso") {
						bootbox.alert("Registro exclu�do com sucesso!",function(){
							 window.location = "produtoListar";  
						 	}	 
						 );
					}
				}
			});
		}
	});
}