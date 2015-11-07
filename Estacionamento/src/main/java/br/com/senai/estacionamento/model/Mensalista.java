/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senai.estacionamento.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Victor Matheus
 */

@Entity
@Table(name = "MENSALISTA")
@XmlRootElement
public class Mensalista implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    @Column(name = "nome", nullable = false, length = 50)
    private String nome;    

    //@OneToMany(fetch=FetchType.EAGER,mappedBy="mensalista",cascade={CascadeType.PERSIST,CascadeType.REMOVE})
    //private List<Veiculo> listaVeiculos = new ArrayList<Veiculo>();
    
    //@OneToMany(fetch=FetchType.EAGER,mappedBy="mensalista",cascade={CascadeType.PERSIST,CascadeType.REMOVE})
    //private List<Movimento> listaMovimentos = new ArrayList<Movimento>();
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    //public List<Veiculo> getListaVeiculos() {
    //        return listaVeiculos;
    //}


    //public void setListVeiculos(List<Veiculo> listaVeiculos) {
    //        this.listaVeiculos = listaVeiculos;
    //        for(Veiculo x : listaVeiculos){
    //                x.setMensalista(this);
    //        }
    //}    
    
    
    //public List<Movimento> getListaMovimentos() {
    //        return listaMovimentos;
    //}

    //public void setListaMovimentos(List<Movimento> listaMovimentos) {
    //        this.listaMovimentos = listaMovimentos;
    //        for(Movimento x : listaMovimentos){
    //                x.setMensalista(this);
    //        }
    //}        

    public Mensalista(String nome) {
        this.nome = nome;
    }

    public Mensalista() {
    }

    
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Mensalista other = (Mensalista) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    
}
