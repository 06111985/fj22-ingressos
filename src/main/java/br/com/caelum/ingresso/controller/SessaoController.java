package br.com.caelum.ingresso.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.caelum.ingresso.dao.FilmeDao;
import br.com.caelum.ingresso.dao.SalaDao;
import br.com.caelum.ingresso.dao.SessaoDao;
import br.com.caelum.ingresso.model.Carrinho;
import br.com.caelum.ingresso.model.ImagemCapa;
import br.com.caelum.ingresso.model.Sessao;
import br.com.caelum.ingresso.model.descontos.TipoDeIngresso;
import br.com.caelum.ingresso.model.form.SessaoForm;
import br.com.caelum.ingresso.rest.OmdbClient;
import br.com.caelum.ingresso.validacao.GerenciadorSessao;

@Controller
public class SessaoController {

	@Autowired
	private SalaDao salaDao;
	@Autowired
	private FilmeDao filmeDao;
	@Autowired
	private SessaoDao sessaoDao;
	@Autowired
    private OmdbClient client;
	@Autowired
	private Carrinho carrinho;
	
	@GetMapping("/admin/sessao")
	public ModelAndView form(@RequestParam("salaId") Integer salaId, SessaoForm form) {
		ModelAndView model = new ModelAndView("sessao/sessao");

		model.addObject("sala", salaDao.findOne(salaId));
		model.addObject("filmes", filmeDao.findAll());
		model.addObject("form", form);
		return model;
	}

	@PostMapping("/admin/sessao")
	@Transactional
	public ModelAndView salva(@Valid SessaoForm form, BindingResult result) {

		if(result.hasErrors()) {
			return form(form.getSalaId(),form);
		}

		ModelAndView model = new ModelAndView("redirect:/admin/sala/"+form.getSalaId()+"/sessoes");
		
		Sessao sessao = form.toSessao(salaDao , filmeDao);
		List<Sessao> sessoes = sessaoDao.buscaSessoes(sessao.getSala());
		GerenciadorSessao gerenciador = new GerenciadorSessao(sessoes);
		
		if(gerenciador.cabe(sessao)) { 
			sessaoDao.save(sessao);
			return model;
		}
		return form(form.getSalaId(),form);
	}
	
	@GetMapping("/sessao/{id}/lugares")
	public ModelAndView lugaresNaSesssao(@PathVariable("id") Integer id) {
		ModelAndView mv = new ModelAndView("sessao/lugares");
		
		Sessao sessao = sessaoDao.findOne(id);
		Optional<ImagemCapa> imagemCapa = client.request(sessao.getFilme(), ImagemCapa.class);
		
		mv.addObject("sessao", sessao);
		mv.addObject("carrinho", carrinho);
		mv.addObject("imagemCapa", imagemCapa.orElse(new ImagemCapa()));
		mv.addObject("tiposDeIngressos",TipoDeIngresso.values());
		return mv;
	}
	
	
	
}
