package br.com.unisul.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.unisul.bean.Entregador;
import br.com.unisul.bean.Endereco;
import br.com.unisul.dao.EntregadorDAO;

@Controller
public class EntregadorController {
	
	@RequestMapping("/entregadorListar")
	public String execute(Model model) {
		EntregadorDAO dao = new EntregadorDAO();
		List<Entregador> entregadores = dao.listAll();
		model.addAttribute("entregadores", entregadores);
		return "cadastro/entregadorListar";
	}
	
	@RequestMapping("listaEntregadores")
	public String lista(Model model) {
		EntregadorDAO dao = new EntregadorDAO();
		List<Entregador> entregadores = dao.listAll();
		model.addAttribute("entregadores", entregadores);
		return "cadastro/entregadorListar";
	}
	
	
	@RequestMapping(value = "adicionaEditaEntregador", method= RequestMethod.POST)
	public @ResponseBody String adicionaEditaEntregador(@ModelAttribute(value="entregador") Entregador entregador, @ModelAttribute(value="endereco") Endereco endereco, BindingResult result) {	
		if(!result.hasErrors()){
			if(entregador.getIdEntregador() != null){						
				entregador.setEnderecoEntregador(endereco);
				new EntregadorDAO().editar(entregador);
			}else{	
				entregador.setEnderecoEntregador(endereco);
				new EntregadorDAO().salvar(entregador);
			}
		}
		return "sucesso";
	}
	
	@RequestMapping(value = "carregarEntregadorId/{id}", method= RequestMethod.GET)
	public @ResponseBody Entregador getEntregador(@PathVariable Long id, Model model) {
		Entregador entregador = new EntregadorDAO().buscarEntregadorPeloId(id);
		return entregador;
	}
	
	@RequestMapping(value="excluirEntregador", method = RequestMethod.POST)
	public @ResponseBody String excluirCliente(@RequestParam("idEntregador") Long idEntregador) { 
		
		new EntregadorDAO().excluir(idEntregador);			

		return "sucesso";
	}

}
