package br.com.unisul.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.unisul.bean.LotePedido;
import br.com.unisul.bean.Pedido;
import br.com.unisul.bean.ProdutoPedido;
import br.com.unisul.dao.LotePedidoDAO;
import br.com.unisul.dao.PedidoDAO;

@Controller
public class LotePedidoController {
	
	@RequestMapping(value="adicionarLotePedido", method = RequestMethod.POST)
	public @ResponseBody String adicionarLotePedido(@RequestBody LotePedido lote) { 
		
		new LotePedidoDAO().salvar(lote);			

		return "redirect:index";
	}
	
	@RequestMapping(value="excluirLotePedido", method = RequestMethod.POST)
	public @ResponseBody String excluirLotePedido(@RequestParam("idLotePedido") Long idLotePedido) { 
		
		new LotePedidoDAO().excluir(idLotePedido);			

		return "sucesso";
	}
	
	
	@RequestMapping(value = "carregarLoteId/{id}", method= RequestMethod.GET)
	public @ResponseBody LotePedido getPedido(@PathVariable Long id, Model model) {
		LotePedido lotePedido = new LotePedidoDAO().buscarLotePedidoPeloId(id);		
		
		return lotePedido;
	}
}
