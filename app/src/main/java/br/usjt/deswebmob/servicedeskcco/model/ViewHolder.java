package br.usjt.deswebmob.servicedeskcco.model;

import android.widget.ImageView;
import android.widget.TextView;
/**
 * @author pedrogallon
 */
public class ViewHolder {
    private TextView numero, datas, descricao;
    private ImageView imagem;

    public TextView getNumero() {
        return numero;
    }

    public void setNumero(TextView numero) {
        this.numero = numero;
    }

    public TextView getDatas() {
        return datas;
    }

    public void setDatas(TextView datas) {
        this.datas = datas;
    }

    public TextView getDescricao() {
        return descricao;
    }

    public void setDescricao(TextView descricao) {
        this.descricao = descricao;
    }

    public ImageView getImagem() {
        return imagem;
    }

    public void setImagem(ImageView imagem) {
        this.imagem = imagem;
    }
}
