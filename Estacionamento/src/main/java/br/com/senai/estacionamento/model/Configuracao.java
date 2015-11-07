/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senai.estacionamento.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Victor Matheus
 */
@Entity
@Table(name = "CONFIGURACAO")
@XmlRootElement
public class Configuracao implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    @Column(name = "valor_hora", nullable = true)
    private Double valorHora;
    
    @Column(name = "valor_diaria", nullable = true)
    private Double valorDiaria;
    
    @Column(name = "num_horas_diaria", nullable = true)
    private int numHorasDiaria;
    
    @Column(name = "num_vagas_disponiveis", nullable = true)
    private int numVagasDisponiveis;
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getValorHora() {
        return valorHora;
    }

    public void setValorHora(Double valorHora) {
        this.valorHora = valorHora;
    }

    public Double getValorDiaria() {
        return valorDiaria;
    }

    public void setValorDiaria(Double valorDiaria) {
        this.valorDiaria = valorDiaria;
    }

    public int getNumHorasDiaria() {
        return numHorasDiaria;
    }

    public void setNumHorasDiaria(int numHorasDiaria) {
        this.numHorasDiaria = numHorasDiaria;
    }

    public int getNumVagasDisponiveis() {
        return numVagasDisponiveis;
    }

    public void setNumVagasDisponiveis(int numVagasDisponiveis) {
        this.numVagasDisponiveis = numVagasDisponiveis;
    }

    
    
    public Configuracao() {
    }

    public Configuracao(Double valorHora, Double valorDiaria, int numHorasDiaria) {
        this.valorHora = valorHora;
        this.valorDiaria = valorDiaria;
        this.numHorasDiaria = numHorasDiaria;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.id);
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
        final Configuracao other = (Configuracao) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    
}
