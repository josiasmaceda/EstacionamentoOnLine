/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senai.estacionamento.controller;

import br.com.senai.estacionamento.dao.MovimentoDAO;
import br.com.senai.estacionamento.dao.ConfiguracaoDAO;
import br.com.senai.estacionamento.dao.VeiculoDAO;
import br.com.senai.estacionamento.model.Configuracao;
import br.com.senai.estacionamento.model.Movimento;
import br.com.senai.estacionamento.model.Veiculo;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Victor Matheus
 */
@Stateless
public class MovimentoController {
    @Inject
    private MovimentoDAO movimentoDAO;
    
    @Inject
    private ConfiguracaoDAO configuracaoDAO;
    
    @Inject
    private VeiculoDAO veiculoDAO;
    
    public void insere(Movimento movimento) {
        Configuracao configuracao = configuracaoDAO.buscar(Long.parseLong("1"));
        Veiculo veiculo = veiculoDAO.getVeiculoByPlaca(movimento.getPlaca());
        
        movimento.setValorDiaria(configuracao.getValorDiaria());
        movimento.setValorHora(configuracao.getValorHora());
        movimento.setDataHoraEntrada(new Date());
        if(veiculo != null){
            movimento.setMensalista(veiculo.getMensalista());
        }
        
        movimentoDAO.insere(movimento);
    }
    
    public void excluir(Long id) {
        movimentoDAO.excluir(id);
    }
    
    public Movimento buscar(Long id) {
        return movimentoDAO.buscar(id);
    }
    
    public Movimento atualizar(Movimento movimento) {
        return movimentoDAO.atualizar(movimento);
    }
    
    public Movimento InfosFinalizar(Movimento movimento) {
        movimento.setDataHoraSaida(new Date());
        Calendar dataInicial = Calendar.getInstance();  
        Calendar dataFinal = Calendar.getInstance();  
        dataInicial.setTime(movimento.getDataHoraEntrada());  
        dataFinal.setTime(movimento.getDataHoraSaida());  
        long diferenca = System.currentTimeMillis()- dataInicial.getTimeInMillis();  
        long diferencaHoras = diferenca / (60 * 60 * 1000);    // DIFERENCA EM HORAS         
        
        movimento.setValorPago(movimento.getValorHora() * diferencaHoras);
        return movimento;
    }
    
    public Movimento finalizar(Movimento movimento) {
        movimento.setDataHoraSaida(new Date());
        return movimentoDAO.atualizar(movimento);
    }

    public List<Movimento> lista() {
        return movimentoDAO.lista();
    }    
    
    public List<Movimento> listaPendentes() {
        return movimentoDAO.listaPendentes();
    }  
    
}
