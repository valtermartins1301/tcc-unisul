<br/>
<br/>
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
                         	<tr class="odd gradeX">
                                 <td>Nome</td>
                                 <td>Rua 1</td>
                                 <td>Bairro 1</td>
                                 <td>Nº</td>
                                 <td>Cidade</td>
                                 <td>Telefone</td>
                                 <td>CEP</td>
                                 <td>Editar/Excluir</td>                                  
                             </tr>
                             <c:forEach items="${clientes}" var="cliente">
								<tr>
									<td>${cliente.nome}</td>
									<td>${cliente.Rua}</td>
									<td>${cliente.Bairro}</td>
									<td>${cliente.Numero}</td>
									<td>${cliente.Cidade}</td>
									<td>${cliente.Telefone}</td>
									<td>${cliente.cep}</td>
									<td><a href="mostraTarefa?id=${tarefa.id}">Editar</a><a href="removeTarefa?id=${tarefa.id}">Excluir</a></td>
								</tr>
							</c:forEach>
                         </tbody>
                  	  </table>
                    </div>
                 </div>
            </div>
      </div>
</div>