<br/>
<br/>
<div class="well">
	<br/>
	<legend align="center">Produtos</legend>
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
									<td><a class="edit_and_exclude" onclick="editProduto(${produto.idProduto})">Editar</a>/<a class="edit_and_exclude" href="removeProduto?id=${produto.idProduto}">Excluir</a></td>
								</tr>
							</c:forEach>
                         </tbody>
                  	  </table>
                    </div>
                 </div>
            </div>
      </div>
</div>