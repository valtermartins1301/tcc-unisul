package br.com.unisul.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ProdutoController {
	@RequestMapping("/cadastrarProduto")
	public String execute() {
		return "cadastro/produtoCadastrar";
	}
}
