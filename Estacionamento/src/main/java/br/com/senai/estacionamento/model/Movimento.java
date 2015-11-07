/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senai.estacionamento.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author josias
 */

@Entity
@Table(name = "MOVIMENTO")
@XmlRootElement
public class Movimento implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "placa", nullable = false, length = 7)
    private String placa;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "datahora_entrada", nullable = false)
    private Date dataHoraEntrada;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "datahora_saida", nullable = true)
    private Date dataHoraSaida;
    
    @Column(name = "valor_hora", nullable = true)
    private Double valorHora;
    
    @Column(name = "valor_diaria", nullable = true)
    private Double valorDiaria;

    @Column(name = "valor_pago", nullable = true)
    private Double valorPago;
    
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="idmensalista")
    private Mensalista mensalista;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlaca() {
        return placa.toUpperCase();
    }

    public void setPlaca(String placa) {
        this.placa = placa.toUpperCase();
    }

    public Date getDataHoraEntrada() {
        return dataHoraEntrada;
    }

    public void setDataHoraEntrada(Date dataHoraEntrada) {
        this.dataHoraEntrada = dataHoraEntrada;
    }

    public Date getDataHoraSaida() {
        return dataHoraSaida;
    }

    public void setDataHoraSaida(Date dataHoraSaida) {
        this.dataHoraSaida = dataHoraSaida;
    }

    public Movimento() {
    }

    public Movimento(String placa) {
        this.placa = placa;
    }

    public Movimento(String placa, Date dataHoraEntrada) {
        this.placa = placa;
        this.dataHoraEntrada = dataHoraEntrada;
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

    public Double getValorPago() {
        return valorPago;
    }

    public void setValorPago(Double valorPago) {
        this.valorPago = valorPago;
    }

    public Mensalista getMensalista() {
        return mensalista;
    }

    public void setMensalista(Mensalista mensalista) {
        this.mensalista = mensalista;
    }
    
    
    public void calculaValorHora(int horasParaDiaria){
        if(this.getDataHoraEntrada() == null){
                this.setDataHoraEntrada(new Date());
            }
            Calendar dataInicial = Calendar.getInstance();  
            Calendar dataFinal = Calendar.getInstance();  
            dataInicial.setTime(this.getDataHoraEntrada());  
            dataFinal.setTime(this.getDataHoraSaida());  
            long diferenca = System.currentTimeMillis()- dataInicial.getTimeInMillis();  
            Double diferencaHoras = Math.ceil(diferenca / (60.00 * 60.00 * 1000.00));    // DIFERENCA EM HORAS         
            if(diferencaHoras<1.00) diferencaHoras = 1.00;
            this.setValorPago(0.00);
            if(diferencaHoras>horasParaDiaria){
                this.setValorPago(this.getValorDiaria());
            } else if(!(this.getValorHora()==null)){
                this.setValorPago(this.getValorHora() * diferencaHoras);
            }
               
    }
    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.id);
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
        final Movimento other = (Movimento) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    
    
}
