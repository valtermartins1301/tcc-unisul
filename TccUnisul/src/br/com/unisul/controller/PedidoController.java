package br.com.unisul.controller;

import java.util.Date;
import java.util.List;

import oracle.jrockit.jfr.settings.JSONElement;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.unisul.bean.Cliente;
import br.com.unisul.bean.Endereco;
import br.com.unisul.bean.Pedido;
import br.com.unisul.bean.Produto;
import br.com.unisul.bean.ProdutoPedido;
import br.com.unisul.bean.ProdutoPedidoId;
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
	
	@RequestMapping(value="testeAdicionaProduto", method = RequestMethod.POST)
	public @ResponseBody String testeAdicionaProduto(@RequestBody List<Produto> listaProduto) {
		System.out.println("Executando a lógica com Spring MVC");
		ProdutoDAO dao = new ProdutoDAO();
		List<Produto> produtos = dao.listAll();
		
		
		
		
		return "index";
	}
	
	@RequestMapping(value="adicionaPedido", method = RequestMethod.POST)
	public @ResponseBody String adicionaPedido( 
			@ModelAttribute(value="endereco") Endereco endereco,
			@ModelAttribute(value="cliente") Cliente cliente,			
			@ModelAttribute(value="produto") Produto produto,
			BindingResult result) {
				
		if (!result.hasErrors()) {
			EnderecoDAO daoEndereco = new EnderecoDAO();
			daoEndereco.salvar(endereco);
			
			cliente.setEndereco(endereco);			
			ClienteDAO daoCliente= new ClienteDAO();
			daoCliente.salvar(cliente);
			
			Pedido pedido = new Pedido();
			pedido.setCliente(cliente);
			pedido.setData(new Date());
			pedido.setObservacao("Testee");
			pedido.setValorTotalPedido(50);
			
			PedidoDAO daoPedido = new PedidoDAO();
			daoPedido.salvar(pedido);
			
			//=================== Não será necessário salvar o produto ===================
			produto.setIdProduto(1L);
						
			//============================================================================
			
			ProdutoPedidoId id = new ProdutoPedidoId();
			id.setPedido(pedido);
			id.setProduto(produto);
			ProdutoPedido prodPed = new ProdutoPedido();
			prodPed.setIdProdutoPedido(id);
			prodPed.setQuantidade(4);
			
			ProdutoPedidoDAO daoProdPed = new ProdutoPedidoDAO();
			daoProdPed.salvar(prodPed);
		
		}
		return "sucesso";
	}

}
