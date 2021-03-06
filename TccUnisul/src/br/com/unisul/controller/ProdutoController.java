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

import br.com.unisul.bean.Cliente;
import br.com.unisul.bean.Endereco;
import br.com.unisul.bean.Produto;
import br.com.unisul.dao.ClienteDAO;
import br.com.unisul.dao.EnderecoDAO;
import br.com.unisul.dao.ProdutoDAO;

@Controller
public class ProdutoController {
	@RequestMapping("/cadastrarProduto")
	public String execute() {
		return "cadastro/produtoCadastrar";
	}
	
	@RequestMapping("/produtoListar")
	public String execute(Model model) {
		ProdutoDAO dao = new ProdutoDAO();
		List<Produto> produto = dao.listAll();
		model.addAttribute("produtos", produto);
		return "cadastro/produtoListar";
	}
	
	@RequestMapping(value = "adicionaEditaProduto", method= RequestMethod.POST)
	public @ResponseBody String adicionaEditaProduto(@ModelAttribute(value="produto") Produto produto, BindingResult result) {	
		if(!result.hasErrors()){
			if(produto.getIdProduto() != null){
				new ProdutoDAO().editar(produto);
			}else{
				new ProdutoDAO().salvar(produto);			
			}
		}
		return "sucesso";
	}

	@RequestMapping("novoProduto")
	public String form() {
		return "cadastro/produtoCadastrar";
	}

	@RequestMapping("listaProdutos")
	public String lista(Model model) {
		ProdutoDAO dao = new ProdutoDAO();
		List<Produto> produtos = dao.listAll();
		model.addAttribute("produtos", produtos);
		return "cadastro/produtoListar";
	}
	
	@RequestMapping(value = "carregarProdutoId/{id}", method= RequestMethod.GET)
	public @ResponseBody Produto getProduto(@PathVariable Long id, Model model) {
		Produto produto = new ProdutoDAO().buscarProdutoPeloId(id);
		produto.setProdutoPedidoList(null);
		return produto;
	}

	@RequestMapping(value="excluirProduto", method = RequestMethod.POST)
	public @ResponseBody String excluirProduto(@RequestParam("idProduto") Long idProduto) { 
		
		new ProdutoDAO().excluir(idProduto);			

		return "sucesso";
	}
}

