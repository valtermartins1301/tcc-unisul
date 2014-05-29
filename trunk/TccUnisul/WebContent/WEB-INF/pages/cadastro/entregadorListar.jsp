<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<br/>
<br/>
<div class="modal fade" id="editar_entregador" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="false" onclick="limparCamposEntregador()">&times;</button>
        <h4 class="modal-title" id="myModalLabel">Entregador</h4>
      </div>
      <div align="center" class="modal-body">
        	<form role="form" class="well well-small"  style="width: 400px" >
	    			  <!-- Cliente -->
					  <div class="form-group">
					    <input type="text" class="form-control" name="nomeEntregador" id="editar_entregador_nome" placeholder="Nome do entregador">
					  </div>
					  <div class="form-group control-group form-inline">
					    <input type="text" class="form-control" name="telefone" id="editar_entregador_telefone" placeholder="Telefone">				  
					    <input type="text" class="form-control" name="cep" id="editar_entregador_cep" placeholder="CEP" >			  
					  </div>
					  <div class="form-group control-group">
			            <input type="text" class="form-control" name="rua" id="editar_entregador_rua" placeholder="Rua">
			    	  </div>
					  <div class="form-group">
					  	<input type="text" class="form-control" name="bairro" id="editar_entregador_bairro" placeholder="Bairro">
					  </div>
					  <div class="form-group control-group form-inline">
	 				    <input type="text" class="form-control" name="numero" id="editar_entregador_numero" placeholder="Número">		
	 				    <input type="text" class="form-control" name="cidade" id="editar_entregador_cidade" placeholder="Cidade">
					  </div>
					  <div class="form-group">
			            <input type="hidden" id="editar_entregador_latitude" name="latitude" />
		                <input type="hidden" id="editar_entregador_longitude" name="longitude" />
		                <input type="hidden" id="map_canvas" />
					    <input type="text" class="form-control" name="complemento" id="editar_entregador_complemento" placeholder="Complemento">
					    <input type="hidden" id="editar_entregador_idEntregador" name="idEntregador" value=""/>
            			<input type="hidden" id="editar_entregador_idEndereco" name="idEndereco" value=""/>
					  </div>	
					  <div class="form-group">
	 				    <input type="text" class="form-control" name="capacidadeEntrega" id="editar_entregador_capacidadeEntrega" placeholder="Capacidade de Entrega">
					  </div>	  				  
			</form>
			
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal" onclick="limparCamposEntregador()">Cancelar</button>
        <button type="button" class="btn btn-primary" onclick="cadastrarEditarEntregador()">Salvar</button>
      </div>
    </div>
  </div>
</div>
<div class="well">
	<br/>
	<legend align="center">Entregadores</legend>
	<div class="control-group">
		&nbsp;&nbsp;<a style="width: 10%" class="btn btn-success" onclick="exibirCadastrarEntregador()">Novo</a>
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
                                 <th>Capacidade</th>
                                 <th></th>                                     
                             </tr>
                         </thead>
                         <tbody>
                             <c:forEach items="${entregadores}" var="entregador">
								<tr>
									<td>${entregador.nomeEntregador}</td>
									<td>${entregador.enderecoEntregador.rua}</td>
									<td>${entregador.enderecoEntregador.bairro}</td>
									<td>${entregador.enderecoEntregador.numero}</td>
									<td>${entregador.enderecoEntregador.cidade}</td>
									<td>${entregador.telefone}</td>
									<td>${entregador.enderecoEntregador.cep}</td>
									<td>${entregador.capacidadeEntrega}</td>
									<td><a class="edit_and_exclude" onclick="editarEntregador(${entregador.idEntregador})">Editar</a>/<a class="edit_and_exclude" onclick="excluirEntregador(${entregador.idEntregador})">Excluir</a></td>
								</tr>
							</c:forEach>
                         </tbody>
                  	  </table>
                    </div>
                 </div>
            </div>
      </div>
</div>