package br.com.unisul.bean;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class ProdutoPedidoId implements Serializable{
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pedido_id")
	private Pedido pedido;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "produto_id")
	private Produto produto;

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

		
	
}