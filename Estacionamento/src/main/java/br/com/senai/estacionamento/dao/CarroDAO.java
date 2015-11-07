/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senai.estacionamento.dao;

import br.com.senai.estacionamento.model.Carro;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Victor Matheus
 */

@Stateless
public class CarroDAO {
    
    @PersistenceContext(unitName = "estacionamentoPU")
    private EntityManager em;

    public void insere(Carro carro) {
        em.persist(carro);
    }
    
    public void excluir(Long id) {
        em.remove(em.getReference(Carro.class, id));
    }
    
    public Carro buscar(Long id) {
        return em.find(Carro.class, id);
    }
    
    public Carro atualizar(Carro carro) {
        return em.merge(carro);
    }

    public List<Carro> lista() {
        TypedQuery<Carro> q = em.createQuery("SELECT c "
                + "FROM Carro c ORDER BY c.placa", Carro.class);
        return q.getResultList();
    }  
}
