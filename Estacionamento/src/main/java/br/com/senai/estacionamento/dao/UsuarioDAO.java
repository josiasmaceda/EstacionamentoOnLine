/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senai.estacionamento.dao;

import br.com.senai.estacionamento.model.Usuario;
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
public class UsuarioDAO {
    
    @PersistenceContext(unitName = "estacionamentoPU")
    private EntityManager em;

    public void insere(Usuario usuario) {
        em.persist(usuario);
    }
    
    public void excluir(Long id) {
        em.remove(em.getReference(Usuario.class, id));
    }
    
    public Usuario buscar(Long id) {
        return em.find(Usuario.class, id);
    }
    
    public Usuario atualizar(Usuario usuario) {
        return em.merge(usuario);
    }

    public List<Usuario> lista() {
        TypedQuery<Usuario> q = em.createQuery("SELECT m "
                + "FROM Usuario m ORDER BY m.nome", Usuario.class);
        return q.getResultList();
    }    
}
