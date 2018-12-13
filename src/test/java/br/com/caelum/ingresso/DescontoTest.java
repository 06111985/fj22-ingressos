package br.com.caelum.ingresso;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;

import org.junit.Before;
import org.junit.Test;

import br.com.caelum.ingresso.model.Filme;
import br.com.caelum.ingresso.model.Ingresso;
import br.com.caelum.ingresso.model.Lugar;
import br.com.caelum.ingresso.model.Sala;
import br.com.caelum.ingresso.model.Sessao;
import br.com.caelum.ingresso.model.descontos.TipoDeIngresso;

import static org.junit.Assert.*;

public class DescontoTest {
	private Lugar lugar;
	private Filme filme;
	private Sala sala;
	private Sessao sessao;

	@Before
	public void setUp() {
		this.lugar =  new Lugar("A",1);;
		this.filme = new Filme("Bohemian Rhapsody", Duration.ofMinutes(120), "Drama",new BigDecimal("10"));
		this.sala = new Sala("Sala 15", new BigDecimal("10"));
		this.sessao = new Sessao(LocalTime.parse("10:00:00"), sala, filme);
	}

	@Test
	public void oPrecoDaSessaoDeveSerSomadoComDescontoDeEstudante(){
		Ingresso ingresso = new Ingresso(sessao, TipoDeIngresso.ESTUDANTE, lugar);
		
		BigDecimal valorEsperado = new BigDecimal("10.00");
		assertEquals(valorEsperado, ingresso.getPreco());
	}
	
	@Test
	public void oPrecoDaSessaoDeveSerIgualSomadoComDescontoDeBanco(){
		Ingresso ingresso = new Ingresso(sessao, TipoDeIngresso.BANCO, lugar);
		
		BigDecimal valorCerto = new BigDecimal("10.00");
		assertEquals(valorCerto, ingresso.getPreco());
	}
	
	@Test
	public void oPrecoDaSessaoDeveSerIgualASomaDoPrecoDaSalaMaisOPrecoDoFIlme(){
		Ingresso ingresso = new Ingresso(sessao, TipoDeIngresso.INTEIRA, lugar);
		
		BigDecimal valorEsperado = new BigDecimal("20.00");
		assertEquals(valorEsperado, ingresso.getPreco());
	}
}
