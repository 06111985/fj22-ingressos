package br.com.caelum.ingresso.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.caelum.ingresso.model.Filme;

/**
 * Created by nando on 03/03/17.
 */
@Repository
public class FilmeDao {

    @PersistenceContext
    private EntityManager manager;


    public Filme findOne(Integer id) {
        return manager.find(Filme.class, id);
    }

    public void save(Filme filme) {
        manager.persist(filme);
    }

    public List<Filme> findAll() {
        return manager.createQuery("select f from Filme f", Filme.class).getResultList();
    }

    public void delete(Integer id) {
        manager.remove(findOne(id));
    }
  
}
