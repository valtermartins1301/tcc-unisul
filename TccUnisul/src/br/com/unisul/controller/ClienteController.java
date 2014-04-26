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
	
	@RequestMapping("/olaMundoSpring")
	public String execute() {
		System.out.println("Executando a lógica com Spring MVC");
		return "ok";
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
	public String lista() {
		//ClienteDAO dao = new ClienteDAO();
		//model.addAttribute("tarefas", dao.listAll());
		return "cadastro/clienteListar";
	}
	
	@RequestMapping(value = "carregarCliente/{telefone}", method= RequestMethod.GET)
	public @ResponseBody List<Cliente> getCliente(@PathVariable String telefone, Model model) {
		List<Cliente> cliente = new ClienteDAO().buscarClientePeloTelefone(telefone);
		return cliente;
	}
	
	

	/*@RequestMapping("removeTarefa")
	public String remove(Tarefa tarefa) {
		TarefaDAO dao = new TarefaDAO();
		dao.remove(tarefa);
		return "redirect:listaTarefas";
	}
    */
	
	/*@RequestMapping("mostraTarefa")
	public String mostra(Long id, Model model) {
		TarefaDAO dao = new TarefaDAO();
		model.addAttribute("tarefas", dao.buscarTarefaPeloId(id));
		return "tarefa/mostra";
	}
    */
	
 	/*@RequestMapping("alteraTarefa")
	public String altera(Tarefa tarefa) {
		TarefaDAO dao = new TarefaDAO();
		dao.merge(tarefa);
		return "redirect:listaTarefas";
	}
	*/
}

