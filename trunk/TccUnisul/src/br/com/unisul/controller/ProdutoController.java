package br.com.unisul.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.unisul.bean.Produto;
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
	
	@RequestMapping("adicionaProduto")
	public String adiciona(Produto produto) {
		ProdutoDAO dao = new ProdutoDAO();
		dao.salvar(produto);
		return "cadastro/produtoListar";
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
	
	@RequestMapping(value = "carregarProduto/{id}", method= RequestMethod.GET)
	public @ResponseBody Produto getProduto(@PathVariable Long id, Model model) {
		Produto produto = new ProdutoDAO().buscarProdutoPeloId(id);
		return produto;
	}

	@RequestMapping("removeProduto")
	public String remove(Long id) 
	{
		ProdutoDAO dao = new ProdutoDAO();
		dao.excluir(id);
		return "redirect:produtoListar";
	}
}

