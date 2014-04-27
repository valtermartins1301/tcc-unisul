<br/>
<br/>
<div class="modal fade" id="editar_cliente" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h4 class="modal-title" id="myModalLabel">Cliente</h4>
      </div>
      <div align="center" class="modal-body">
        	<c:forEach items="${editCliente}" var="editCliente">
	        	<form role="form" class="well well-small"  style="width: 400px" >
	    			  <!-- Cliente -->
					  <div class="form-group">
					    <input type="text" class="form-control" name="nome" id="editar_cliente_nome" placeholder="Nome do cliente" value="${editCliente.nome}">
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
					  </div>		  				  
				</form>
			</c:forEach>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary">Save changes</button>
      </div>
    </div>
  </div>
</div>
<div class="well">
	<br/>
	<legend align="center">Clientes</legend>
	<div class="control-group">
		&nbsp;&nbsp;<a style="width: 10%" class="btn btn-success" href="novoCliente">Novo</a>
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
									<td><a class="edit_and_exclude" onclick="editCliente(${cliente.idCliente})">Editar</a>/<a class="edit_and_exclude" href="removeCliente?id=${cliente.idCliente}">Excluir</a></td>
								</tr>
							</c:forEach>
                         </tbody>
                  	  </table>
                    </div>
                 </div>
            </div>
      </div>
</div>