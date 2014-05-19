package br.com.unisul.bean;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "clientes")
public class Cliente {
	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	@Column(name = "id")
	private Long idCliente;
	@Column
	private String nome;
	@Column
	private String telefone;
	@Column(name = "flag_excluido")
	private boolean flagExcluido; 
	
	
	@ManyToOne
	(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
	@JoinColumn(name = "id_endereco")
	private Endereco endereco;
	
	private String email;
	
	public Cliente() {
		
	}

	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public boolean isFlagExcluido() {
		return flagExcluido;
	}

	public void setFlagExcluido(boolean flagExcluido) {
		this.flagExcluido = flagExcluido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Cliente [idCliente=" + idCliente + ", nome=" + nome
				+ ", telefone=" + telefone + ", flagExcluido=" + flagExcluido
				+ ", endereco=" + endereco + ", email=" + email + "]";
	}

	
}
