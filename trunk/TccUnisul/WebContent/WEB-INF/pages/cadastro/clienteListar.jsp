<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<br/>
<br/>
<div class="modal fade" id="editar_cliente" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="false" onclick="limparCamposCliente()">&times;</button>
        <h4 class="modal-title" id="myModalLabel">Cliente</h4>
      </div>
      <div align="center" class="modal-body">
        	<form role="form" class="well well-small"  style="width: 400px" >
	    			  <!-- Cliente -->
					  <div class="form-group">
					    <input type="text" class="form-control" name="nome" id="editar_cliente_nome" placeholder="Nome do cliente">
					  </div>
					  <div class="form-group control-group form-inline">
					    <input type="text" class="form-control" name="telefone" id="editar_cliente_telefone" placeholder="Telefone">				  
					    <input type="text" class="form-control" name="cep" id="editar_cliente_cep" placeholder="CEP" >			  
					  </div>
					  <div class="form-group control-group form-inline">
					    <input type="text" class="form-control" name="rua" id="editar_cliente_rua" placeholder="Rua"  style="width: 72%">&nbsp;&nbsp;&nbsp;
					    <input type="text" class="form-control" name="numero" id="editar_cliente_numero" placeholder="Número" style="width: 22%">					  
					  </div>
					  
					  <div class="form-group control-group form-inline">
	 				    <input type="text" class="form-control" name="bairro" id="editar_cliente_bairro" placeholder="Bairro">
	 				    <input type="text" class="form-control" name="cidade" id="editar_cliente_cidade" placeholder="Cidade">
					  </div>
					  <div class="form-group">
					    <input type="text" class="form-control" name="complemento" id="editar_cliente_complemento" placeholder="Complemento">
					    <input type="hidden" id="editar_cliente_idCliente" name="idCliente" value=""/>
            			<input type="hidden" id="editar_cliente_idEndereco" name="idEndereco" value=""/>
					  </div>		  				  
			</form>
			
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal" onclick="limparCamposCliente()">Cancelar</button>
        <button type="button" class="btn btn-primary" onclick="cadastrarEditarCliente()">Salvar</button>
      </div>
    </div>
  </div>
</div>
<div class="well">
	<br/>
	<legend align="center">Clientes</legend>
	<div class="control-group">
		&nbsp;&nbsp;<a style="width: 10%" class="btn btn-success" onclick="exibirCadastrarCliente()">Novo</a>
		<!-- <input type="button" class="btn btn-success"  onclick="exibirCadastrarCliente()"  style="width: 5%;font-size:13pt;" value="Novo"/>
				  	&nbsp;&nbsp;&nbsp;  -->
	</div>
    <div style="width: 100%">
         <div class="panel panel-default">
             <!-- /.panel-heading -->
             <div class="panel-body">
                 <div class="table-responsive">
                     <table class="table table-striped table-bordered table-hover" id="datatables_lista">
                         <thead>
                             <tr>
                                 <th>Nome</th>
                                 <th>Rua</th>
                                 <th>Bairro</th>
                                 <th>Nº</th>
                                 <th>Cidade</th>
                                 <th>Telefone</th>
                                 <th>CEP</th>
                                 <th></th>                                     
                             </tr>
                         </thead>
                         <tbody>
                             <c:forEach items="${clientes}" var="cliente">
								<tr>
									<td>${cliente.nome}</td>
									<td>${cliente.endereco.rua}</td>
									<td>${cliente.endereco.bairro}</td>
									<td>${cliente.endereco.numero}</td>
									<td>${cliente.endereco.cidade}</td>
									<td>${cliente.telefone}</td>
									<td>${cliente.endereco.cep}</td>
									<td><a class="edit_and_exclude" onclick="editarCliente(${cliente.idCliente})">Editar</a>/<a class="edit_and_exclude" href="removeCliente?id=${cliente.idCliente}">Excluir</a></td>
								</tr>
							</c:forEach>
                         </tbody>
                  	  </table>
                    </div>
                 </div>
            </div>
      </div>
</div>