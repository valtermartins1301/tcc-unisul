package br.com.unisul.bean;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name = "entregadores")
public class Entregador {
	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	@Column(name = "id")
	private Long idEntregador;
	@Column(name = "nome")
	private String nomeEntregador;
	@ManyToOne
	(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
	@JoinColumn(name = "id_endereco")
	private Endereco enderecoEntregador;
	private String telefone;
	private String capacidadeEntrega;
	
	@Column(name = "flag_excluido")
	private boolean flagExcluido;
	
	public Entregador(){
		
	}
	
	public Long getIdEntregador() {
		return idEntregador;
	}
	public void setIdEntregador(Long idEntregador) {
		this.idEntregador = idEntregador;
	}
	public String getNomeEntregador() {
		return nomeEntregador;
	}
	public void setNomeEntregador(String nome) {
		this.nomeEntregador = nome;
	}
	public Endereco getEnderecoEntregador() {
		return enderecoEntregador;
	}
	public void setEnderecoEntregador(Endereco enderecoEntregador) {
		this.enderecoEntregador = enderecoEntregador;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getCapacidadeEntrega() {
		return capacidadeEntrega;
	}
	public void setCapacidadeEntrega(String capacidadeEntrega) {
		this.capacidadeEntrega = capacidadeEntrega;
	}
	public boolean getFlagExcluido() {
		return flagExcluido;
	}
	public void setFlagExcluido(boolean flagExcluido) {
		this.flagExcluido = flagExcluido;
	}
}
