package br.usjt.deswebmob.servicedeskcco.model;

import java.io.Serializable;
import java.util.Date;
/**
 * @author pedrogallon
 */
public class Chamado implements Serializable{
    public final static String DATE_PATTERN = "dd-MM-yyyy";

    private int numero;
    private Date dataAbertura, dataFechamento;
    private String status, descricao;
    private Fila fila;

    public static final String ABERTO = "aberto";
    public static final String FECHADO = "fechado";

    public Chamado(int numero, Date dataAbertura, Date dataFechamento, String status, String descricao, Fila fila) {
        this.numero = numero;
        this.dataAbertura = dataAbertura;
        this.dataFechamento = dataFechamento;
        this.status = status;
        this.descricao = descricao;
        this.fila = fila;
    }

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
        return fila.getNome()+": "+ descricao;
    }

}
