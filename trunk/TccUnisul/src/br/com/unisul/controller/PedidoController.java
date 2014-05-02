package br.com.unisul.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.unisul.bean.CalcValorPedido;
import br.com.unisul.bean.Cliente;
import br.com.unisul.bean.Endereco;
import br.com.unisul.bean.JsonResponse;
import br.com.unisul.bean.Pedido;
import br.com.unisul.bean.Produto;
import br.com.unisul.bean.ProdutoPedido;
import br.com.unisul.bean.StatusPedido;
import br.com.unisul.dao.ClienteDAO;
import br.com.unisul.dao.EnderecoDAO;
import br.com.unisul.dao.PedidoDAO;
import br.com.unisul.dao.ProdutoDAO;
import br.com.unisul.dao.ProdutoPedidoDAO;

@Controller
public class PedidoController {
	
//	@RequestMapping(value="salvaTransferencia", method = RequestMethod.POST)
//	public @ResponseBody String salvaTransferencia(@RequestParam("idContaOrigem") int idContaOrigem, @RequestParam("idContaDestino") int idContaDestino,
//			@RequestParam("data") String data, @ModelAttribute(value="transferencia") Transferencia transferencia, BindingResult result) throws ParseException {		
//		if (!result.hasErrors()) {
//			ContasDAO contaDAO = FactoryDAO.criarContasDAO();
//			ContaCorrente contaOrigem = contaDAO.buscarContaPeloId(idContaOrigem);
//			ContaCorrente contaDestino = contaDAO.buscarContaPeloId(idContaDestino);
//			
//			TipoLancamentoDAO tipoLancamentoDAO = FactoryDAO.criarTipoLancamentoDAO();
//			TipoLancamento tipoLancamento = tipoLancamentoDAO.buscarTipoLancamentoPeloId(2);
//			
//			SimpleDateFormat format = new SimpleDateFormat("ddMMyyyy");
//			Date dataLancamento = format.parse(data);
//			transferencia.setDataLancamento(dataLancamento);
//			transferencia.setContaCorrente(contaOrigem);
//			transferencia.setContaDestino(contaDestino);
//			transferencia.setTipoLancamento(tipoLancamento);
//	
//			TransferenciaDAO transferenciaDAO = FactoryDAO.criarTransferenciaDAO();
//			transferenciaDAO.persist(transferencia);
//		}
//		return "sucesso";
//	}
	
	@RequestMapping("/index")
	public String execute(Model model) {
		System.out.println("Executando a lógica com Spring MVC");
	
		List<Produto> produtos = new ProdutoDAO().listAll();
		List<Pedido> pedidos = new PedidoDAO().listAll();
				
		model.addAttribute("pedidos", pedidos);
		model.addAttribute("produtos", produtos);
		
		
		return "index";
	}
	
	@RequestMapping(value="adicionarPedido", method = RequestMethod.POST)
	public @ResponseBody String adicionarPedido(@RequestBody Pedido pedido) {
		Endereco endereco = new Endereco();
		endereco = pedido.getCliente().getEndereco();
		new EnderecoDAO().salvar(endereco);
		
		Cliente cliente = new Cliente();
		cliente = pedido.getCliente();
		new ClienteDAO().salvar(cliente);
		
		Collection<ProdutoPedido> produtoPedido = pedido.getProdutoPedidoList();
		pedido.setProdutoPedidoList(null);
		pedido.setData(new Date());
		pedido.setValorTotalPedido(50);
		new PedidoDAO().salvar(pedido);
		
		for(ProdutoPedido prodPed : produtoPedido){
			prodPed.getIdProdutoPedido().setPedido(pedido);
			new ProdutoPedidoDAO().salvar(prodPed);
		}
		
		return "index";
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
//			Cliente cliente   = pedido.getCliente();
//			Endereco endereco = cliente.getEndereco();
//			Collection<ProdutoPedido> produtoPedido = pedido.getProdutoPedidoList();
			StatusPedido statusPedido = pedido.getStatusPedido();
			pedidosString = "{\"idpedido\":"+pedido.getIdPedido()+","+"\"status\":\""+statusPedido.getDescricao()+"\","+"\"cliente\":";

			
			ObjectMapper mapper = new ObjectMapper();
			 
			try {
				System.out.println(mapper.writeValueAsString(pedido.getCliente()));
				pedidosString = pedidosString + mapper.writeValueAsString(pedido.getCliente());
				status = "ok";
			} catch (JsonGenerationException e) {
				e.printStackTrace();
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			if(i == listaPedidos.size()-1)
				pedidosString = pedidosString+"}";
			else
				pedidosString = pedidosString+"},";
			data += pedidosString;
		}
		data += "]}";
		JsonResponse json = new JsonResponse(status, "", data);
		return json;
	}
	

}
