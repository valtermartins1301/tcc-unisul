package br.com.unisul.bean;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "lotePedido")
public class LotePedido {
	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	private Long idLotePedido;
	
//	@OneToMany (fetch = FetchType.EAGER)
//	@JoinColumn(name = "id_pedido")
//	private List<Pedido> pedidos;
	@ManyToOne
	@JoinColumn(name = "id_entregadores")
	private Entregador entregador;
	
	public Long getIdLotePedido() {
		return idLotePedido;
	}
	public void setIdLotePedido(Long idLotePedido) {
		this.idLotePedido = idLotePedido;
	}
//	public List<Pedido> getPedidos() {
//		return pedidos;
//	}
//	public void setPedidos(List<Pedido> pedidos) {
//		this.pedidos = pedidos;
//	}
	public Entregador getEntregador() {
		return entregador;
	}
	public void setEntregador(Entregador entregador) {
		this.entregador = entregador;
	}
	
	
	
}
