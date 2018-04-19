package br.usjt.deswebmob.servicedeskcco.model;

import android.graphics.Bitmap;

import java.io.Serializable;

/**
 * @author pedrogallon
 */
public class Fila implements Serializable {
    private int id;
    private String nome, figura;
    private Bitmap imagem;


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

    @Override
    public String toString() {
        return "Fila{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", figura='" + figura + '\'' +
                ", imagem=" + imagem +
                '}';
    }
}
