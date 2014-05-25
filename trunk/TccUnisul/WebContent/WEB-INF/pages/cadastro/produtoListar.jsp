<br/>
<br/>
<div class="modal fade" id="editar_produto" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="false" onclick="limparCamposProduto()">&times;</button>
        <h4 class="modal-title" id="myModalLabel">Produto</h4>
      </div>
      <div align="center" class="modal-body">
        	<form role="form" class="well well-small"  style="width: 400px" >
	    			  <!-- Produto -->
					  <div class="form-group control-group form-inline">
					    <input type="text" class="form-control" name="nomeProduto" id="editar_produto_nomeProduto" placeholder="Nome do produto">
					    <input type="text" class="form-control" name="preco" id="editar_produto_preco" placeholder="Preco">
					  </div>
					  <div class="form-group">
					     <textarea type="text" class="form-control" name="descricao" id="editar_produto_descricao" placeholder="Descrição"></textarea>				      	  
					  </div>						  				  
					  <input type="hidden" id="editar_produto_idProduto" name="idProduto" value=""/>
					  
			</form>			
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal" onclick="limparCamposProduto()">Cancelar</button>
        <button type="button" class="btn btn-primary" onclick="cadastrarEditarProduto()">Salvar</button>
      </div>
    </div>
  </div>
</div>
<div class="well">
	<br/>
	<legend align="center">Produtos</legend>
	<div class="control-group">
		&nbsp;&nbsp;<a style="width: 10%" class="btn btn-success" onclick="exibirCadastrarProduto()">Novo</a>
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
                                 <th>Descrição</th>
                                 <th>Preço</th>
                                 <th></th>                                     
                             </tr>
                         </thead>
                         <tbody>
                             <c:forEach items="${produtos}" var="produto">
								<tr>
									<td>${produto.nomeProduto}</td>
									<td>${produto.descricao}</td>
									<td>${produto.preco}</td>
									<td><a class="edit_and_exclude" onclick="editarProduto(${produto.idProduto})">Editar</a>/<a class="edit_and_exclude" onclick="excluirProduto(${produto.idProduto})">Excluir</a></td>
								</tr>
							</c:forEach>
                         </tbody>
                  	  </table>
                    </div>
                 </div>
            </div>
      </div>
</div>