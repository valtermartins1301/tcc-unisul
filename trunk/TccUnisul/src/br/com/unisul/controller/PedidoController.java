package br.com.unisul.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.unisul.bean.Cliente;
import br.com.unisul.bean.Endereco;
import br.com.unisul.bean.LotePedido;
import br.com.unisul.bean.Pedido;
import br.com.unisul.bean.Produto;
import br.com.unisul.bean.ProdutoPedido;
import br.com.unisul.bean.StatusLote;
import br.com.unisul.bean.StatusPedido;
import br.com.unisul.dao.ClienteDAO;
import br.com.unisul.dao.LotePedidoDAO;
import br.com.unisul.dao.PedidoDAO;
import br.com.unisul.dao.ProdutoDAO;
import br.com.unisul.dao.StatusLoteDAO;
import br.com.unisul.dao.StatusPedidoDAO;
import br.com.unisul.util.CalcValorPedido;
import br.com.unisul.util.JsonResponse;

@Controller
public class PedidoController {
	
	@RequestMapping("/index")
	public String execute(Model model) {
		System.out.println("Executando a lógica com Spring MVC");
	
		List<Produto> produtos = new ProdutoDAO().listAll();
		List<Pedido> pedidos = new PedidoDAO().listAll();
		List<LotePedido> lotePedido = new LotePedidoDAO().listAll();
		List<StatusPedido> statusPedido = new StatusPedidoDAO().listAll();
		List<StatusLote> statusLote = new StatusLoteDAO().listAll();
				
		model.addAttribute("pedidos", pedidos);
		model.addAttribute("produtos", produtos);
		model.addAttribute("lotePedido", lotePedido);
		model.addAttribute("statusPedido", statusPedido);
		model.addAttribute("statusLote", statusLote);
		
		return "index";
	}
	
	@RequestMapping(value="adicionarPedido", method = RequestMethod.POST)
	public @ResponseBody String adicionarPedido(@RequestBody Pedido pedido) {
		
		if(pedido.getCliente().getIdCliente() == null){
			Cliente cliente = pedido.getCliente();
			new ClienteDAO().salvar(cliente);
		}
		
		pedido.setData(new Date());
		List<ProdutoPedido> produtoPedido = (List<ProdutoPedido>) pedido.getProdutoPedidoList();
		for(int i=0; i<produtoPedido.size(); i++){
			produtoPedido.get(i).setPedido(pedido);
		}
		pedido.setProdutoPedidoList(produtoPedido);
		
		if(pedido.getIdPedido() == null){
			new PedidoDAO().salvar(pedido);
		}else{
			new PedidoDAO().editar(pedido);
		}
		

		return "redirect:index";
	}
	
	@RequestMapping(value="somaValorTotalPedido", 
            method = RequestMethod.POST,
            headers = {"Content-type=application/json"})
	@ResponseBody
	public JsonResponse somaValorTotalPedido(@RequestBody CalcValorPedido Data) {
		BigDecimal valorTotal = Data.getValorTotal();
		BigDecimal valor 	  = Data.getValor();
		int	       quantidade = Data.getQuantidade();
		
		BigDecimal result = BigDecimal.ZERO;
		result = result.add(valorTotal.add(valor.multiply(new BigDecimal(quantidade))));	// valorTotal = valorTotal + (valor*quantidade);

		return new JsonResponse("OK", "", "{\"resultado\":"+result+"}");
	}
	
	@RequestMapping(value="diminuiValorTotalPedido", 
            method = RequestMethod.POST,
            headers = {"Content-type=application/json"})
	@ResponseBody
	public JsonResponse diminuiValorTotalPedido(@RequestBody CalcValorPedido Data) {
	
		BigDecimal valorTotal = Data.getValorTotal();
		BigDecimal valor 	  = Data.getValor();
		int	       quantidade = Data.getQuantidade();
		
		BigDecimal result = new BigDecimal(BigInteger.ZERO,  2);
		result = result.add(valorTotal.subtract(valor.multiply(new BigDecimal(quantidade))));	// valorTotal = valorTotal - (valor*quantidade);
		
		return new JsonResponse("OK", "", "{\"resultado\":"+result+"}");
	}
	
	@RequestMapping(value="getPedidos", method = RequestMethod.GET)
	public @ResponseBody JsonResponse getPedidos() {

		PedidoDAO dao = new PedidoDAO();
		List<Pedido> listaPedidos = dao.listAll();
		String data   = "{\"listapedidos\":[";
		String status = "error";
		String pedidosString = "";
		for(int i = 0; i<listaPedidos.size();i++)
		{
			Pedido pedido     = listaPedidos.get(i);
			ObjectMapper mapper = new ObjectMapper();
			 
			try {
				System.out.println(mapper.writeValueAsString(pedido));
				pedidosString = pedidosString + mapper.writeValueAsString(pedido);
				status = "ok";
			} catch (JsonGenerationException e) {
				e.printStackTrace();
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			if(i != listaPedidos.size()-1)
				pedidosString = pedidosString+",";
			data += pedidosString;
		}
		data += "]}";
		JsonResponse json = new JsonResponse(status, "", data);
		return json;
	}
	
	@RequestMapping(value = "carregarPedidoId/{id}", method= RequestMethod.GET)
	public @ResponseBody Pedido getPedido(@PathVariable Long id, Model model) {
		Pedido pedido = new PedidoDAO().buscarPedidoPeloId(id);		
		return pedido;
	}
	
	@RequestMapping(value="excluirPedido", method = RequestMethod.POST)
	public @ResponseBody String excluirPedido(@RequestParam("idPedido") Long idPedido) { 
		
		new PedidoDAO().excluir(idPedido);			

		return "sucesso";
	}
	
	@RequestMapping(value = "carregarPedidoPorStatus/{id}", method= RequestMethod.GET)
	public @ResponseBody List<Pedido> carregarPedidoPorStatus(@PathVariable Long idStatusPedido, Model model) {
		List<Pedido> pedido = null; 
		try{
			pedido = new PedidoDAO().buscarPedidoPorStatus(idStatusPedido);				
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return pedido;
	}
	
	@RequestMapping(value="alterarStatusPedido", method = RequestMethod.POST)
	public @ResponseBody String alterarStatusPedido(@RequestParam("idPedido") Long idPedido, @RequestParam("idStatus") Long idStatusPedido) { 
		Pedido pedido = new PedidoDAO().buscarPedidoPeloId(idPedido);
				
		if(pedido != null){
			pedido.getStatusPedido().setIdStatusPedido(idStatusPedido);
			new PedidoDAO().editar(pedido);
			return "sucesso";
		}
		return "";
	}

}
