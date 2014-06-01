//**************************************************************************************
//-------------------------------Tela Listar Cliente------------------------------------
//**************************************************************************************
 
//**************************************************************************************
//							Exibir (Modal) Cadastrar Cliente									
//**************************************************************************************


 function exibirCadastrarCliente(){
	 $('#editar_cliente').modal('show');
 }
 
//**************************************************************************************
//									 Editar Cliente									
//**************************************************************************************
 function cadastrarEditarCliente(){
	 var cliente = $("form").serialize();
	 
	 $.ajax({ 
		 url: "adicionaEditaCliente",    
		 type:"POST", 
		 data: cliente,
		 success: function(response){
			 if(response == "sucesso"){
				 $('#editar_cliente').modal('hide');
				 bootbox.alert("Cliente salvo com sucesso", function() {
						 window.location = "clienteListar";
				 	}
				 );    		 
			 }        	 
		 }         
	 });	 
 }
 
//**************************************************************************************
//								Exibir Editar Cliente									
//**************************************************************************************
 
 function editarCliente(id){
	 $.get("carregarClienteId/" + id, function(data){
		 	$('#editar_cliente_nome').val(data.nome);
			$('#editar_cliente_telefone').val(data.telefone);
			if(data.endereco != null){
				$('#editar_cliente_cep').val(data.endereco.cep);
				$('#editar_cliente_rua').val(data.endereco.rua);
				$('#editar_cliente_numero').val(data.endereco.numero);
				$('#editar_cliente_bairro').val(data.endereco.bairro);
				$('#editar_cliente_cidade').val(data.endereco.cidade);
				$('#editar_cliente_complemento').val(data.endereco.complemento);
				$('#editar_cliente_idEndereco').val(data.endereco.idEndereco);
			}			
			$('#editar_cliente_idCliente').val(data.idCliente);
			$('#editar_cliente').modal('show');
	 });
 }
 
//**************************************************************************************
//									Limpar Campos Cliente									
//**************************************************************************************
 
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
//									Excluir Cliente								
//**************************************************************************************
 

 function excluirCliente(id) {
	 bootbox.confirm("Realmente deseja excluir?",function(result){
		 if (result) {
			$.ajax({
				type : "POST",
				url : "excluirCliente",
				data : "idCliente=" + id,
				success : function(response) {
					if (response == "sucesso") {
						bootbox.alert("Registro excluído com sucesso!",function() {
								 window.location = "clienteListar";
						 	}
						 );    		 
					}
				}
			});
		}
	});
}
 
