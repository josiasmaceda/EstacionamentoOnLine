/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senai.estacionamento.dao;

import br.com.senai.estacionamento.model.Veiculo;
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
public class VeiculoDAO {
    
    @PersistenceContext(unitName = "estacionamentoPU")
    private EntityManager em;

    public void insere(Veiculo veiculo) {
        em.persist(veiculo);
    }
    
    public void excluir(Long id) {
        em.remove(em.getReference(Veiculo.class, id));
    }
    
    public Veiculo buscar(Long id) {
        return em.find(Veiculo.class, id);
    }
    
    public Veiculo atualizar(Veiculo veiculo) {
        return em.merge(veiculo);
    }

    public List<Veiculo> lista() {
        TypedQuery<Veiculo> q = em.createQuery("SELECT c "
                + "FROM Veiculo c ORDER BY c.placa", Veiculo.class);
        return q.getResultList();
    }  
    
    public Veiculo getVeiculoByPlaca(String placa) {
        String jpql = "SELECT c "
                    + "FROM Veiculo c where c.placa=:placa ORDER BY c.placa";
        TypedQuery<Veiculo> q = em.createQuery(jpql, Veiculo.class);
        q.setParameter("placa", placa);
        if(!(q.getResultList().isEmpty())){
            return q.getSingleResult();
        } else {
            return null;
        }
    }
    
}
