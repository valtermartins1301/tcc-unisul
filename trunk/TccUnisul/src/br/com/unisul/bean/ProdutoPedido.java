package br.com.unisul.bean;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "produto_pedido")
public class ProdutoPedido {
	
	@EmbeddedId
	@Column(name = "id")
	private ProdutoPedidoId idProdutoPedido;
	
	private int quantidade;
	
	public ProdutoPedido(){
		
	}

	public ProdutoPedidoId getIdProdutoPedido() {
		return idProdutoPedido;
	}

	public void setIdProdutoPedido(ProdutoPedidoId idProdutoPedido) {
		this.idProdutoPedido = idProdutoPedido;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	
	
}
