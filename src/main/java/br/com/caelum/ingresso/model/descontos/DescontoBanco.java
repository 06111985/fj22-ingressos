package br.com.caelum.ingresso.model.descontos;

import java.math.BigDecimal;

public class DescontoBanco implements Desconto{

	@Override
	public BigDecimal aplica(BigDecimal valor) {
		return valor.multiply(new BigDecimal(0.5));
	}

	@Override
	public String getDescricao() {
		// TODO Auto-generated method stub
		return "Meia-Banco";
	}
}
