package br.com.unisul.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.unisul.bean.Cliente;
import br.com.unisul.dao.ClienteDAO;

@Controller
public class ClienteController {
	
	@RequestMapping("/clienteListar")
	public String execute(Model model) {
		ClienteDAO dao = new ClienteDAO();
		List<Cliente> clientes = dao.listAll();
		model.addAttribute("clientes", clientes);
		return "cadastro/clienteListar";
	}
	
	@RequestMapping("adicionaCliente")
	public String adiciona(Cliente cliente) {
		ClienteDAO dao = new ClienteDAO();
		dao.salvar(cliente);
		return "cadastro/clienteListar";
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
	
	@RequestMapping(value = "carregarCliente/{telefone}", method= RequestMethod.GET)
	public @ResponseBody List<Cliente> getCliente(@PathVariable String telefone, Model model) {
		List<Cliente> cliente = new ClienteDAO().buscarClientePeloTelefone(telefone);
		return cliente;
	}
	
	@RequestMapping(value = "carregarCliente/{id}", method= RequestMethod.GET)
	public @ResponseBody List<Cliente> getCliente(@PathVariable Long id, Model model) {
		List<Cliente> cliente = new ClienteDAO().buscarClientePeloId(id);
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

