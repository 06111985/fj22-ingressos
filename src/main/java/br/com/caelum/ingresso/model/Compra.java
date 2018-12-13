package br.com.caelum.ingresso.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Compra {
	@Id
	@GeneratedValue
	private Long id;
	
	@OneToMany(cascade = CascadeType.PERSIST)
	List<Ingresso> ingressos = new ArrayList<>();
	
	public Compra() {
		// TODO Auto-generated constructor stub
	}
	
	public Compra(List<Ingresso> ingressos){
		this.ingressos = ingressos;
	}
}
