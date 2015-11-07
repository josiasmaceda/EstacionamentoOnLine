/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senai.estacionamento.dao;

import br.com.senai.estacionamento.model.Mensalista;
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
public class MensalistaDAO {

    @PersistenceContext(unitName = "estacionamentoPU")
    private EntityManager em;

    public void insere(Mensalista mensalista) {
        em.persist(mensalista);
    }
    
    public void excluir(Long id) {
        em.remove(em.getReference(Mensalista.class, id));
    }
    
    public Mensalista buscar(Long id) {
        return em.find(Mensalista.class, id);
    }
    
    public Mensalista atualizar(Mensalista mensalista) {
        return em.merge(mensalista);
    }

    public List<Mensalista> lista() {
        TypedQuery<Mensalista> q = em.createQuery("SELECT m "
                + "FROM Mensalista m ORDER BY m.nome", Mensalista.class);
        return q.getResultList();
    }  
}
