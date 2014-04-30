package br.com.unisul.bean;
import java.math.BigDecimal;
import java.math.BigInteger;

public class CalcValorPedido{

  private BigDecimal valorTotal;
  private BigDecimal valor;
  private int quantidade;
  
	public CalcValorPedido(){
		valorTotal	= new BigDecimal(BigInteger.ZERO,  2);
		valor		= new BigDecimal(BigInteger.ZERO,  2);
		quantidade	= 0;
	}
	public BigDecimal getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

}
