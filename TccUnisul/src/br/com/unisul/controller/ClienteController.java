package br.com.unisul.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.unisul.bean.Cliente;
import br.com.unisul.bean.Endereco;
import br.com.unisul.dao.ClienteDAO;
import br.com.unisul.dao.EnderecoDAO;

@Controller
public class ClienteController {
	
	@RequestMapping("/clienteListar")
	public String execute(Model model) {
		ClienteDAO dao = new ClienteDAO();
		List<Cliente> clientes = dao.listAll();
		model.addAttribute("clientes", clientes);
		return "cadastro/clienteListar";
	}
	
	@RequestMapping(value = "adicionaEditaCliente", method= RequestMethod.POST)
	public @ResponseBody String adicionaEditaCliente(@ModelAttribute(value="cliente") Cliente cliente, @ModelAttribute(value="endereco") Endereco endereco, BindingResult result) {	
		if(!result.hasErrors()){
			if(cliente.getIdCliente() != null){						
				cliente.setEndereco(endereco);
				new ClienteDAO().editar(cliente);
			}else{	
				cliente.setEndereco(endereco);
				new ClienteDAO().salvar(cliente);
			}
		}
		return "sucesso";
	}
	

	@RequestMapping("novoCliente")
	public String form() {
		return "cadastro/clienteCadastrar";
	}

	@RequestMapping("listaClientes")
	public String lista(Model model) {
		ClienteDAO dao = new ClienteDAO();
		List<Cliente> clientes = dao.listAll();
		model.addAttribute("clientes", clientes);
		return "cadastro/clienteListar";
	}
	
	@RequestMapping(value = "carregarClienteTelefone/{telefone}", method= RequestMethod.GET)
	public @ResponseBody List<Cliente> getCliente(@PathVariable String telefone, Model model) {
		List<Cliente> cliente = new ClienteDAO().buscarClientePeloTelefone(telefone);
		return cliente;
	}
	
	@RequestMapping(value = "carregarClienteId/{id}", method= RequestMethod.GET)
	public @ResponseBody Cliente getCliente(@PathVariable Long id, Model model) {
		Cliente cliente = new ClienteDAO().buscarClientePeloId(id);
		return cliente;
	}

	@RequestMapping("removeCliente")
	public String remove(Long id) 
	{
		ClienteDAO dao = new ClienteDAO();
		dao.excluir(id);
		return "redirect:clienteListar";
	}
    
	
 	/*@RequestMapping("alteraTarefa")
	public String altera(Tarefa tarefa) {
		TarefaDAO dao = new TarefaDAO();
		dao.merge(tarefa);
		return "redirect:listaTarefas";
	}
	*/
}

