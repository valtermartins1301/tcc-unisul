package br.com.unisul.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.unisul.bean.Entregador;
import br.com.unisul.bean.LotePedido;
import br.com.unisul.bean.Pedido;
import br.com.unisul.dao.EntregadorDAO;
import br.com.unisul.dao.LotePedidoDAO;

@Controller
public class EntregadorController {
	
	@RequestMapping(value="adicionarEntregador", method = RequestMethod.POST)
	public @ResponseBody String adicionarEntregador(@RequestBody Entregador entregador) { 
		
		new EntregadorDAO().salvar(entregador);

		return "redirect:index";
	}

}
