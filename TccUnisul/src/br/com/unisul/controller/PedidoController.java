package br.com.unisul.controller;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.unisul.bean.Cliente;
import br.com.unisul.bean.Endereco;
import br.com.unisul.bean.Pedido;
import br.com.unisul.bean.Produto;
import br.com.unisul.bean.ProdutoPedido;
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
		ProdutoDAO dao = new ProdutoDAO();
		List<Produto> produtos = dao.listAll();
		
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
	
	@RequestMapping(value="adicionaPedido", method = RequestMethod.POST)
	public @ResponseBody String adicionaPedido(@RequestBody Pedido pedido,
			BindingResult result) {
				
		if (!result.hasErrors()) {
//			EnderecoDAO daoEndereco = new EnderecoDAO();
//			daoEndereco.salvar(endereco);
//			
//			cliente.setEndereco(endereco);			
//			ClienteDAO daoCliente= new ClienteDAO();
//			daoCliente.salvar(cliente);
//			
//			Pedido pedido = new Pedido();
//			pedido.setCliente(cliente);
//			pedido.setData(new Date());
//			pedido.setObservacao("Testee");
//			pedido.setValorTotalPedido(50);
//			
//			PedidoDAO daoPedido = new PedidoDAO();
//			daoPedido.salvar(pedido);
//			
//			//=================== Não será necessário salvar o produto ===================
//			produto.setIdProduto(1L);
//						
//			//============================================================================
//			
//			ProdutoPedido prodPed = new ProdutoPedido();
//			prodPed.getIdProdutoPedido().setProduto(produto);
//			prodPed.getIdProdutoPedido().setProduto(produto);
//			prodPed.setQuantidade(4);
//			
//			ProdutoPedidoDAO daoProdPed = new ProdutoPedidoDAO();
//			daoProdPed.salvar(prodPed);
		
		}
		return "sucesso";
	}

}
