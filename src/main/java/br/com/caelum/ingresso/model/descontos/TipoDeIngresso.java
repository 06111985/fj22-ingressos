package br.com.caelum.ingresso.model.descontos;

import java.math.BigDecimal;

public enum TipoDeIngresso {

	INTEIRA(new SemDesconto()),
	BANCO(new DescontoBanco()),
	ESTUDANTE(new DescontoEstudantes());
	
	private final Desconto desconto;
	
	TipoDeIngresso(Desconto desconto) {
		this.desconto = desconto;
	}
	
	public BigDecimal aplicaDesconto(BigDecimal valor) {
		return desconto.aplica(valor);
	}
	
	public String getDescricao() {
		return desconto.getDescricao();
		}
		
	
}
