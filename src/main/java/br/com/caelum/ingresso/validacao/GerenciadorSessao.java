package br.com.caelum.ingresso.validacao;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import br.com.caelum.ingresso.model.Sessao;

public class GerenciadorSessao {
	private List<Sessao> sessoes;
	
	public GerenciadorSessao(List<Sessao> sessoes) {
		this.sessoes = sessoes;
	}
	
	public boolean cabe(Sessao sessaoNova){
		return sessoes.stream().noneMatch(sessaoAntiga -> horarioIsConflitante(sessaoAntiga, sessaoNova));
	}

	private boolean horarioIsConflitante(Sessao sessaoAntiga, Sessao sessaoNova) {
		LocalDate hoje = LocalDate.now();
		
		LocalDateTime horarioAntigo = sessaoAntiga.getHorario().atDate(hoje);
		LocalDateTime horarioTerminoAntigo = sessaoAntiga.getHorarioTermino().atDate(hoje);
		LocalDateTime horarioNova = sessaoNova.getHorario().atDate(hoje);
		LocalDateTime horarioTerminoNovo = sessaoNova.getHorarioTermino().atDate(hoje);
		
		boolean terminaAntes = horarioTerminoNovo.isBefore(horarioAntigo);
		boolean comecaDepois = horarioTerminoAntigo.isBefore(horarioNova);
		
		if(terminaAntes || comecaDepois) {
			return false;
		}
		
		return true;
		
	}
}
