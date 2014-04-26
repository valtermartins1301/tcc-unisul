package br.com.unisul.bean;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "pedidos")
public class Pedido {
	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	@Column(name = "id")
	private Long idPedido;
	@ManyToOne
	@JoinColumn(name = "id_cliente")
	private Cliente cliente;	
	private double valorTotalPedido;
	private Date data;	
	@ManyToOne
	@JoinColumn(name = "id_statusPedido")
	private StatusPedido statusPedido;
	private String observacao;
	private boolean retiradoLocal;
	
	@OneToMany(mappedBy = "idProdutoPedido.produto")
	private Collection<ProdutoPedido> produtoPedidoList;
	
	public Pedido(){
		
	}

	public Long getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(Long idPedido) {
		this.idPedido = idPedido;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public double getValorTotalPedido() {
		return valorTotalPedido;
	}

	public void setValorTotalPedido(double valorTotalPedido) {
		this.valorTotalPedido = valorTotalPedido;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public StatusPedido getStatusPedido() {
		return statusPedido;
	}

	public void setStatusPedido(StatusPedido statusPedido) {
		this.statusPedido = statusPedido;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public boolean getRetiradoLocal() {
		return retiradoLocal;
	}

	public void setRetiradoLocal(boolean retiradoLocal) {
		this.retiradoLocal = retiradoLocal;
	}

	public Collection<ProdutoPedido> getProdutoPedidoList() {
		return produtoPedidoList;
	}

	public void setProdutoPedidoList(Collection<ProdutoPedido> produtoPedidoList) {
		this.produtoPedidoList = produtoPedidoList;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((idPedido == null) ? 0 : idPedido.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pedido other = (Pedido) obj;
		if (idPedido == null) {
			if (other.idPedido != null)
				return false;
		} else if (!idPedido.equals(other.idPedido))
			return false;
		return true;
	}

	

	
	
}
