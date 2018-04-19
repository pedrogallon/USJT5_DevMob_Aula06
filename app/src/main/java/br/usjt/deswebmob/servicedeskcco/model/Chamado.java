package br.usjt.deswebmob.servicedeskcco.model;

import java.io.Serializable;
import java.util.Date;
/**
 * @author pedrogallon
 */
public class Chamado implements Serializable{
    private int numero;
    private Date dataAbertura, dataFechamento;
    private String status, descricao;
    private Fila fila;

    public static final String ABERTO = "aberto";
    public static final String FECHADO = "fechado";

    public Chamado(){}

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Date getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(Date dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public Date getDataFechamento() {
        return dataFechamento;
    }

    public void setDataFechamento(Date dataFechamento) {
        this.dataFechamento = dataFechamento;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Fila getFila() {
        return fila;
    }

    public void setFila(Fila fila) {
        this.fila = fila;
    }

    @Override
    public String toString() {
        return "Chamado{" +
                "numero=" + numero +
                ", dataAbertura=" + dataAbertura +
                ", dataFechamento=" + dataFechamento +
                ", status='" + status + '\'' +
                ", descricao='" + descricao + '\'' +
                ", fila=" + fila +
                '}';
    }
}
