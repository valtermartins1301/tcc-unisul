<br/>
<table>
 	<tr>
 	<td width="25"></td>
 		<td>
			<form role="form" class="well well-small"  style="width: 400px">
			  <legend>Novo Pedido</legend>
				  <!-- Cliente -->
				  <input type="hidden" id="novo_pedido_idCliente" name="idCliente" />
				  <input type="hidden" id="novo_pedido_idEndereco" name="idEndereco" />
				  <input type="hidden" id="novo_pedido_idPedido" name="idPedido" />
				  <input type="hidden" id="novo_pedido_idLotePedido" name="idPedido" />
				  <input type="hidden" id="novo_pedido_idStatusPedido" name="idStatusPedido" />
				  <div class="form-group control-group form-inline">
				    <input type="text" class="form-control" name="nome" id="novo_pedido_nome" placeholder="Nome do cliente" required>
				    <input type="text" class="form-control" name="telefone" id="novo_pedido_telefone" onblur="carregarCliente()" placeholder="Telefone" required>
				  </div>
				  <div class="form-group control-group">
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
   					 	 <option value="${produto.idProduto}_${produto.preco}">${produto.nomeProduto}</option>
					   </c:forEach>
					</select>
				    <input type="number" class="form-control" name="quantidade" id="novo_pedido_poduto_quantidade" placeholder="Qtd" size="4" required>	
				    <i onclick="insertOnTable()" class="edit_and_exclude glyphicon glyphicon-plus-sign"></i>		  
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
				  <div>
				  
				  <!-- Valor Total -->
				  <div class="control-group form-inline text-center">
  				    <br/>
				    <span style="color:rgb(208,0,0)">R$: <i id="novo_pedido_valor_total"> 0.0</i></span>
				  </div>
				  
				  <!-- Observações -->
				  <div class="form-group">
				    <textarea type="text" class="form-control" name="observacoes" id="novo_pedido_observacoes" placeholder="Observações"></textarea>
				  </div>
				  
				  <input type="hidden" id="novo_pedido_latitude" name="latitude" />
	              <input type="hidden" id="novo_pedido_longitude" name="longitude" />
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
				  	<input type="button" class="btn btn-default btn-lg" onclick="limparCampos()" style="font-weight: bold;font-size:13pt;" value="Limpar"/>
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
                 	 <legend align="center">Pedidos</legend>
                     <table class="table table-striped table-bordered table-hover" id="datatables_Lista">
                         <thead>
                             <tr>
                             	 <th><input type="button" class="btn btn-success" value="Criar Lote" onclick="criarLote();"></th>
                                 <th>Nº</th>
                                 <th>Cliente</th>
                                 <th>(Quant) Produto</th>
                                 <th>Obs:</th>
                                 <th>Telefone</th>
                                 <th>Lote</th>
                                 <th>Data/Hora</th>
                                 <th>Preço Total</th>
                                 <th>Status</th>
                                 <th></th>                                     
                             </tr>
                         </thead>
                         <tbody id="tbody_lista_pedidos">
                             <c:forEach items="${pedidos}" var="pedidos">
								<tr class="table_listaPedidos" id="${pedidos.idPedido}">
									<td>
										<label>
      										<input type="checkbox" name="${pedidos.idPedido}" class="checkboxLotePedido">
    									</label>	
    								</td>
    								<td>${pedidos.idPedido}</td>
									<td>${pedidos.cliente.nome}</td>
									<td>
										<c:forEach items="${pedidos.produtoPedidoList}" var="produtoPedidoList">
											(${produtoPedidoList.quantidade}) 
											${produtoPedidoList.produto.nomeProduto} | 
							            </c:forEach> 
							        </td>
									<td>${pedidos.observacao}</td>
									<td>${pedidos.cliente.telefone}</td>
									<td>${pedidos.lotePedido.idLotePedido}</td>
									<td><fmt:formatDate value="${pedidos.data}" type="both" pattern="dd/MM/yyyy HH:mm" /></td>
									<td>${pedidos.valorTotalPedido}</td>
									<td>
										<select class="form-control" name="statusPedido" id="novo_pedido_status_${pedidos.idPedido}" onchange="alterarStatusPedido(${pedidos.idPedido})">
					  						<c:forEach var="statusPedido" items="${statusPedido}">
					  							<c:choose>
													<c:when test="${pedidos.statusPedido.idStatusPedido == statusPedido.idStatusPedido}">
														<option selected value="${pedidos.idPedido}_${statusPedido.idStatusPedido}">${statusPedido.descricao}</option>
													 </c:when>
													<c:otherwise>
														<option value="${pedidos.idPedido}_${statusPedido.idStatusPedido}">${statusPedido.descricao}</option>
													</c:otherwise>
												</c:choose>      					 							 
   					 						</c:forEach>
										</select>
									</td>
									<td><a class="edit_and_exclude" onclick="editarPedido(${pedidos.idPedido})">Editar</a>/<a class="edit_and_exclude" onclick="excluirPedido(${pedidos.idPedido})">Excluir</a></td>
								</tr>
							</c:forEach>
                         </tbody>
                  	  </table>
                    </div>
                 </div>
            </div>
      </div>
</div>

<div>
     <div style="width: 100%">
         <div class="panel panel-default">
             <!-- /.panel-heading -->
             <div class="panel-body">
                 <div class="table-responsive">
	                 <legend align="center">Lotes</legend>
                     <table class="table table-striped table-bordered table-hover" id="datatables_Lotes">
                         <thead>
                             <tr>
                                 <th>NºLote</th>
                                 <th>Pedidos</th>
                                 <th>Entregador</th>
                                 <th>Status</th>
                                 <th></th>                                     
                             </tr>
                         </thead>
                         <tbody>
                            <c:forEach var="lotes" items="${lotePedido}">
								<tr>
    								<td>${lotes.idLotePedido}</td>
									<td>
										<c:forEach items="${lotes.pedidos}" var="pedidos">
											(${pedidos.idPedido}) 
											${pedidos.cliente.nome}  
							            </c:forEach> 
							        </td>
									<td>${lotes.entregador.nomeEntregador}</td>
									<td>
										<select class="form-control" name="statusLote" id="novo_lote_status_${lotes.idLotePedido}" onchange="alterarStatusLote(${lotes.idLotePedido})">
											 <c:forEach var="statusLote" items="${statusLote}">
												<c:choose>
													<c:when test="${lotes.statusLote.idStatusLote == statusLote.idStatusLote}">
														<option selected value="${lotes.idLotePedido}_${statusLote.idStatusLote}">${statusLote.descricao}</option>
													 </c:when>
													<c:otherwise>
														<option value="${lotes.idLotePedido}_${statusLote.idStatusLote}">${statusLote.descricao}</option>
													</c:otherwise>
												</c:choose>   					 							 
					  						 </c:forEach>
										</select>
									</td>
									<td><a class="edit_and_exclude" onclick="editarLote(${lotes.idLotePedido})">Editar</a>/<a class="edit_and_exclude" onclick="excluirLote(${lotes.idLotePedido})">Excluir</a></td>
								</tr>
							</c:forEach> 
                         </tbody>
                  	  </table>
                    </div>
                 </div>
            </div>
      </div>
</div>

<!-- Modal Pedidos -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true" onclick="limparCampos()">&times;</button>
        <h4 class="modal-title" id="myModalLabel">Clientes cadastrados com esse número de telefone.</h4>
      </div>
      <div class="modal-body">
      <table class="table table-bordered">
			 <thead>
                <tr>
                    <th>Nome</th>
                    <th>Telefone</th>
                    <th>Email</th>
                    <th>Selecionar</th>                                 
                </tr>
            </thead>
			 <tbody id="modal_lista_clientes">
						
			</tbody>
	  </table>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal" onclick="limparModalCliente()">Sair</button>
      </div>
    </div>
  </div>
</div>


<!-- Modal Lotes -->
<div class="modal fade" id="lotesModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true" onclick="limparCampos()">&times;</button>
        <h4 class="modal-title" id="lotesModalLabel"> Lote Nº </h4>
      </div>
      <div class="modal-body">
      <div>
      	<div class="input-group">
			<input type="text" class="form-control" data-placement="bottom" placeholder="Nº pedido" id="modal_lista_lotepedido_adicionar_pedido_id" name="idPedido_lotes" required>
			<div class="input-group-btn">
				<button class="btn btn-success" type="submit"  onclick="adionaPedidoLote()"><i class="glyphicon glyphicon-plus"></i></button>
			</div>
		</div>
      </div>
      <input type="hidden" id="modal_lista_lotepedido_idLotePedido" required/>
      <br/>
      <div style="height:250px; overflow:auto;">
	      <table class="table table-bordered">
				 <thead>
	                <tr>
	                    <th>NºPedido</th>
	                    <th>Cliente</th>
	                    <th>Produtos</th>
	                    <th>Obs:</th>
	                    <th>Telefone</th>
	                    <th>Data/Hora</th>
	                    <th></th>                                  
	                </tr>
	            </thead>
				 <tbody id="modal_lista_lotePedidos">
							
				</tbody>
		  </table>
	  </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal" onclick="limparModalLote()">Sair</button>
        <button type="button" class="btn btn-default" data-dismiss="modal" onclick="salvaLoteEditado()">Salvar</button>
      </div>
    </div>
  </div>
</div>