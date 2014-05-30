package br.com.unisul.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "statusLote")
public class StatusLote {
	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	@Column(name = "id")
	private Long idStatusLote;
	private String descricao;
	
	public StatusLote(){
		
	}

	public Long getIdStatusLote() {
		return idStatusLote;
	}

	public void setIdStatusLote(Long idStatusLote) {
		this.idStatusLote = idStatusLote;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public String toString() {
		return "StatusLote [idStatusLote=" + idStatusLote
				+ ", descricao=" + descricao + "]";
	}
}
