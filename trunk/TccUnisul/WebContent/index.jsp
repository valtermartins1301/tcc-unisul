
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
				    <input type="text" class="form-control" name="telefone" id="novo_pedido_telefone" placeholder="Telefone">
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
					  <option>Produto 1</option>
	  				  <option>Produto 2</option>
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
	  					<input type="checkbox">Retirada no local
					</label>
				    <label class="checkbox-inline pull-right">
				      <input type="checkbox">Salvar cliente
				    </label>
				    <br/>
				  </div>
				  
				  <div class="control-group form-inline text-center">
				  	<br/>
				  	<button type="submit" class="btn btn-success btn-lg">Salvar</button>&nbsp;&nbsp;&nbsp; 
				  	<button type="submit" class="btn btn-default btn-lg">Limpar</button>
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