package br.usjt.deswebmob.servicedeskcco.controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import br.usjt.deswebmob.servicedeskcco.R;
import br.usjt.deswebmob.servicedeskcco.model.Chamado;
import br.usjt.deswebmob.servicedeskcco.model.Fila;

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
        try {
            foto.setImageBitmap(MainActivity._filas.get(Fila.getFila(MainActivity._filas,
                    chamado.getFila().getId())).getImagem());
        } catch (Exception e){
            foto.setImageDrawable(getDrawable(R.drawable.ic_not_found));
        }
        TextView fila = findViewById(R.id.valor_fila);
        fila.setText(chamado.getFila().getNome());
        TextView numero = findViewById(R.id.valor_numero);
        numero.setText(""+chamado.getNumero());
        TextView status = findViewById(R.id.valor_status);
        status.setText(""+chamado.getStatus());
        DateFormat formatter = new SimpleDateFormat(Chamado.DATE_PATTERN);
        TextView abertura = findViewById(R.id.valor_abertura);
        abertura.setText(formatter.format(chamado.getDataAbertura()));
        TextView fechamento = findViewById(R.id.valor_fechamento);
        fechamento.setText(chamado.getDataFechamento() == null?" ":
                formatter.format(chamado.getDataFechamento()));
        TextView descricao = findViewById(R.id.valor_descricao);
        descricao.setText(""+chamado.getDescricao());
    }
}
