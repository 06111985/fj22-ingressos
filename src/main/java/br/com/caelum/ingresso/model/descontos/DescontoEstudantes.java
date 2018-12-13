package br.com.caelum.ingresso.model.descontos;

import java.math.BigDecimal;

import br.com.caelum.ingresso.model.Sessao;

public class DescontoEstudantes implements Desconto {

	@Override
	public BigDecimal aplica(BigDecimal valor) {
		return valor.multiply(new BigDecimal(0.5));
	}

	@Override
	public String getDescricao() {
		// TODO Auto-generated method stub
		return "Meia-Estudante";
	}

}
