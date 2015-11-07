/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senai.estacionamento.dao;

import br.com.senai.estacionamento.model.Configuracao;
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
public class ConfiguracaoDAO {
    
    
    @PersistenceContext(unitName = "estacionamentoPU")
    private EntityManager em;

    public void insere(Configuracao configuracao) {
        em.persist(configuracao);
    }
    
    public void excluir(Long id) {
        em.remove(em.getReference(Configuracao.class, id));
    }
    
    public Configuracao buscar(Long id) {
        return em.find(Configuracao.class, id);
    }
    
    public Configuracao atualizar(Configuracao configuracao) {
        return em.merge(configuracao);
    }

    public List<Configuracao> lista() {
        TypedQuery<Configuracao> q = em.createQuery("SELECT c "
                + "FROM Configuracao c ORDER BY c.id", Configuracao.class);
        return q.getResultList();
    }    
    
}
