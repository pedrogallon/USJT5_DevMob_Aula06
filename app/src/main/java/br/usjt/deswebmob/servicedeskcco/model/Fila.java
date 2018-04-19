package br.usjt.deswebmob.servicedeskcco.model;

import android.graphics.Bitmap;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * @author pedrogallon
 */
public class Fila implements Serializable {
    public final static String DATE_PATTERN = "dd-MM-yyyy'T'HH:mm:ss'Z'Z";
    private int id;
    private String nome, figura;
    private Bitmap imagem;
    private Date dataAtualizacao;

    public Fila(int id, String nome, String figura) {
        this.id = id;
        this.nome = nome;
        this.figura = figura;
    }

    public Fila() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getFigura() {
        return figura;
    }

    public void setFigura(String figura) {
        this.figura = figura;
    }

    public Bitmap getImagem() {
        return imagem;
    }

    public void setImagem(Bitmap imagem) {
        this.imagem = imagem;
    }

    public Date getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(Date atualizacao) {
        this.dataAtualizacao = atualizacao;
    }

    public static int getFila(ArrayList<Fila> filas, int id){
        for(int i = 0; i <filas.size(); i++){
            if(filas.get(i).getId() == id){
                return i;
            }
        }
        return -1;
    }

    @Override
    public String toString() {
        return "Fila{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", figura='" + figura + '\'' +
                ", imagem=" + imagem +
                ", dataAtualizacao=" + dataAtualizacao +
                '}';
    }
}
