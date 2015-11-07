/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senai.estacionamento.model;

/**
 *
 * @author Victor Matheus
 */
public enum TipoVeiculo {
    CARRO(1, "Carro"),
    MOTO(2, "Moto"),
    ONIBUS(3, "Onibus"),
    CAMINHAO(4, "Caminhão");
    
    private final int codigo;
    private final String descricao;   

    private TipoVeiculo(int codigo, String descricao){
            this.descricao=descricao;
            this.codigo=codigo;
    }

    public String getDescricao(){
            return this.descricao;
    }

    public int getCodigo(){
            return this.codigo;
    }    
}
