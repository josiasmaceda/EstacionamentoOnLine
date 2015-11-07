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
        Configuracao configuracao = configuracaoDAO.buscar(Long.parseLong("1"));
        movimento.setValorDiaria(configuracao.getValorDiaria());
        movimento.setValorHora(configuracao.getValorHora());
        Veiculo veiculo = veiculoDAO.getVeiculoByPlaca(movimento.getPlaca());
        if(veiculo != null){
            movimento.setMensalista(veiculo.getMensalista());
        }
        
        return movimentoDAO.atualizar(movimento);
    }
    
    public Movimento InfosFinalizar(Movimento movimento) {
        if (!(movimento==null)){
            Movimento movimentoReturn = new Movimento();
            movimentoReturn.setId(movimento.getId());
            movimentoReturn.setMensalista(movimento.getMensalista());
            movimentoReturn.setValorDiaria(movimento.getValorDiaria());
            movimentoReturn.setValorHora(movimento.getValorHora());
            movimentoReturn.setValorPago(movimento.getValorPago());
            movimentoReturn.setPlaca(movimento.getPlaca());
            movimentoReturn.setDataHoraEntrada(movimento.getDataHoraEntrada());
            movimentoReturn.setDataHoraSaida(movimento.getDataHoraSaida());
            
            movimentoReturn.setDataHoraSaida(new Date());
            if(movimentoReturn.getDataHoraEntrada() == null){
                movimentoReturn.setDataHoraEntrada(new Date());
            }
            Calendar dataInicial = Calendar.getInstance();  
            Calendar dataFinal = Calendar.getInstance();  
            dataInicial.setTime(movimentoReturn.getDataHoraEntrada());  
            dataFinal.setTime(movimentoReturn.getDataHoraSaida());  
            long diferenca = System.currentTimeMillis()- dataInicial.getTimeInMillis();  
            Double diferencaHoras = Math.ceil(diferenca / (60.00 * 60.00 * 1000.00));    // DIFERENCA EM HORAS         
            if(diferencaHoras<1.00) diferencaHoras = 1.00;
            movimentoReturn.setValorPago(0.00);
            if(!(movimentoReturn.getValorHora()==null))
               movimentoReturn.setValorPago(movimentoReturn.getValorHora() * diferencaHoras);

            return movimentoReturn;
        } else {
            return null;
        }
        
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
