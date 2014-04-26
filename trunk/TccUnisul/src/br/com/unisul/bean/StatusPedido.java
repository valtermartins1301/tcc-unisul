package br.com.unisul.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "statusPedido")
public class StatusPedido {
	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	@Column(name = "id")
	private Long idStatusPedido;
	private String descricao;
	
	public StatusPedido(){
		
	}

	public Long getIdStatusPedido() {
		return idStatusPedido;
	}

	public void setIdStatusPedido(Long idStatusPedido) {
		this.idStatusPedido = idStatusPedido;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
}
