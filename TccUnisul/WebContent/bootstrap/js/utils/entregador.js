//**************************************************************************************
//-------------------------------Tela Listar Entregador------------------------------------
//**************************************************************************************

//**************************************************************************************
//							Exibir (Modal) Cadastrar Cliente									
//**************************************************************************************

function exibirCadastrarEntregador(){
	 $('#editar_entregador').modal('show');
 }


//**************************************************************************************
//									Editar Entregador								
//**************************************************************************************
function cadastrarEditarEntregador() {
	var entregador = $("form").serialize();

	$.ajax({
		url : "adicionaEditaEntregador",
		type : "POST",
		data : entregador,
		success : function(response) {
			if (response == "sucesso") {
				$('#editar_entregador').modal('hide');
				bootbox.alert("Entregador salvo com sucesso",function(){
						window.location = "entregadorListar";
					}	
				);
			}
		}
	});
}

//**************************************************************************************
//							Exibir (Modal) Editar Entregador									
//**************************************************************************************

function editarEntregador(id) {
	$.get("carregarEntregadorId/" + id, function(entregador) {
		$('#editar_entregador_nome').val(entregador.nomeEntregador);
		$('#editar_entregador_telefone').val(entregador.telefone);
		if(entregador.enderecoEntregador != null){
			$('#editar_entregador_cep').val(entregador.enderecoEntregador.cep);
			$('#editar_entregador_rua').val(entregador.enderecoEntregador.rua);
			$('#editar_entregador_numero').val(entregador.enderecoEntregador.numero);
			$('#editar_entregador_bairro').val(entregador.enderecoEntregador.bairro);
			$('#editar_entregador_cidade').val(entregador.enderecoEntregador.cidade);
			$('#editar_entregador_complemento').val(entregador.enderecoEntregador.complemento);
			$('#editar_entregador_idEndereco').val(entregador.enderecoEntregador.idEndereco);
		}
		$('#editar_entregador_capacidadeEntrega').val(entregador.capacidadeEntrega);
		$('#editar_entregador_idEntregador').val(entregador.idEntregador);
		$('#editar_entregador').modal('show');
	});
}

//**************************************************************************************
//								Limpar Campos Entregador								
//**************************************************************************************

function limparCamposEntregador() {
	$('#editar_entregador_nome').val("");
	$('#editar_entregador_telefone').val("");
	$('#editar_entregador_cep').val("");
	$('#editar_entregador_rua').val("");
	$('#editar_entregador_numero').val("");
	$('#editar_entregador_bairro').val("");
	$('#editar_entregador_cidade').val("");
	$('#editar_entregador_complemento').val("");
	$('#editar_entregador_idEntregador').val("");
	$('#editar_entregador_idEndereco').val("");
	$('#editar_entregador_capacidadeEntrega').val("");
}


//**************************************************************************************
//									Excluir Entregador								
//**************************************************************************************


function excluirEntregador(id) {
	 bootbox.confirm("Realmente deseja excluir?",function(result){
		if (result) {
			$.ajax({
				type : "POST",
				url : "excluirEntregador",
				data : "idEntregador=" + id,
				success : function(response) {
					if (response == "sucesso") {
						bootbox.alert("Registro excluído com sucesso!",function(){
								window.location = "entregadorListar";
							}	
						);
					}
				}
			});
		}
	 });
}