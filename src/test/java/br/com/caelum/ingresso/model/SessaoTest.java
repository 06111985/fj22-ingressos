package br.com.caelum.ingresso.model;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.Test;

import br.com.caelum.ingresso.model.descontos.TipoDeIngresso;

import static org.junit.Assert.*;


public class SessaoTest {

	private Filme filme;
	private Sala sala;
	private Sessao sessao;

	@Before
	public void setUp(){
		this.filme = new Filme("Bohemian Rhapsody", Duration.ofMinutes(120), "Drama", BigDecimal.TEN);
		this.sala = new Sala("Sala 15");

		this.sessao = new Sessao(LocalTime.parse("10:00:00"), sala, filme);
	}

	@Test
	public void oPrecoDaSessaoDeveSerIgualASomaDoPrecoDaSalaMaisOPrecoDoFIlme() {
		BigDecimal somaTotal = sala.getPreco().add(filme.getPreco());

		assertEquals(somaTotal, sessao.getPreco());
	}
	
	@Test
	public void garanteQueOLugarA1EstaOcupadoEORestoDisponivel(){
		Lugar a1 = new Lugar("A", 1);
		Lugar a2 = new Lugar("A", 2);
		Lugar a3 = new Lugar("A", 3);
		
		Ingresso ingresso = new Ingresso(sessao, TipoDeIngresso.INTEIRA, a1);
		Set<Ingresso> ingressos = Stream.of(ingresso).collect(Collectors.toSet());
		sessao.setIngressos(ingressos);
		
		assertFalse(sessao.isDisponivel(a1));
		assertTrue(sessao.isDisponivel(a2));
		assertTrue(sessao.isDisponivel(a3));
		
	}
}
