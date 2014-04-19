package br.com.unisul.bean;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Pedido {
	@Id @GeneratedValue
	private Long id;
	@Column
	private Cliente cliente;
	private List<Produto> produto;
	private double valorTotalPediso;
	private Date data;
	private Endereco enderecoPedido;
	
	public Pedido(){
		
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public List<Produto> getProduto() {
		return produto;
	}
	public void setProduto(List<Produto> produto) {
		this.produto = produto;
	}
	public double getValorTotalPediso() {
		return valorTotalPediso;
	}
	public void setValorTotalPediso(double valorTotalPediso) {
		this.valorTotalPediso = valorTotalPediso;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public Endereco getEnderecoPedido() {
		return enderecoPedido;
	}
	public void setEnderecoPedido(Endereco enderecoPedido) {
		this.enderecoPedido = enderecoPedido;
	}
	
	
	
	

}
