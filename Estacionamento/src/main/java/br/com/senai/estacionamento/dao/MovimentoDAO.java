/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senai.estacionamento.dao;


import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import  br.com.senai.estacionamento.model.Movimento;

/**
 *
 * @author josias
 */

@Stateless
public class MovimentoDAO {

    @PersistenceContext(unitName = "estacionamentoPU")
    private EntityManager em;

    public void insere(Movimento movimento) {
        em.persist(movimento);
    }
    
    public void excluir(Long id) {
        em.remove(em.getReference(Movimento.class, id));
    }
    
    public Movimento buscar(Long id) {
        return em.find(Movimento.class, id);
    }
    
    public Movimento atualizar(Movimento movimento) {
        return em.merge(movimento);
    }

    public List<Movimento> lista() {
        TypedQuery<Movimento> q = em.createQuery("SELECT m "
                + "FROM Movimento m ORDER BY m.dataHoraEntrada", Movimento.class);
        return q.getResultList();
    }    
    
    public List<Movimento> listaPendentes() {
        TypedQuery<Movimento> q = em.createQuery("SELECT m "
                + "FROM Movimento m where m.dataHoraSaida is null ORDER BY m.dataHoraEntrada", Movimento.class);
        return q.getResultList();
    }        
}
