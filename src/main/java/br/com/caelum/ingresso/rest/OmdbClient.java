package br.com.caelum.ingresso.rest;

import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import br.com.caelum.ingresso.model.Filme;

@Component
public class OmdbClient {
	
	public <T> Optional<T> request(Filme filme, Class<T> generica) {
		
		RestTemplate rest = new RestTemplate();
		
		String param = filme.getNome().replace(" ", "+");
		
		String url = String.format("https://omdb-fj22.herokuapp.com/movie?title=%s",param);
		
		try{
		return Optional.of(rest.getForObject(url, generica));
		}catch(RestClientException e){
			return Optional.empty();
		}
		
	}
	
	
}
