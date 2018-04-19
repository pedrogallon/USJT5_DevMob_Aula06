package br.usjt.deswebmob.servicedeskcco.controller;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import br.usjt.deswebmob.servicedeskcco.R;
import br.usjt.deswebmob.servicedeskcco.model.Chamado;

import br.usjt.deswebmob.servicedeskcco.model.Util;

/**
 * @author pedrogallon
 */
public class DetalheChamadoActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_chamado);
        Intent intent = getIntent();
        Chamado chamado = (Chamado)intent.getSerializableExtra(ListarChamadosActivity.CHAMADO);
        ImageView foto = findViewById(R.id.foto_fila_detalhe);
        Drawable drawable = Util.getDrawableDinamic(this, chamado.getFila().getFigura());
        foto.setImageDrawable(drawable);
        TextView fila = findViewById(R.id.valor_fila);
        fila.setText(chamado.getFila().getNome());
        TextView numero = findViewById(R.id.valor_numero);
        numero.setText(""+chamado.getNumero());
        TextView status = findViewById(R.id.valor_status);
        status.setText(""+chamado.getStatus());
        TextView abertura = findViewById(R.id.valor_abertura);
        abertura.setText(String.format("%tD",chamado.getDataAbertura()));
        TextView fechamento = findViewById(R.id.valor_fechamento);
        String strFechamento = String.format("%tD",chamado.getDataFechamento());
        fechamento.setText(strFechamento.equals("null")?" ":strFechamento);
        TextView descricao = findViewById(R.id.valor_descricao);
        descricao.setText(""+chamado.getDescricao());
    }
}
