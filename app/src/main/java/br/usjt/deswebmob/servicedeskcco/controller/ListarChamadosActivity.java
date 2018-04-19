package br.usjt.deswebmob.servicedeskcco.controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import br.usjt.deswebmob.servicedeskcco.R;
import br.usjt.deswebmob.servicedeskcco.model.Chamado;
import br.usjt.deswebmob.servicedeskcco.model.ChamadoAdapter;
/**
 * @author pedrogallon
 */
public class ListarChamadosActivity extends Activity {

    public static final String CHAMADO = "br.usjt.deswebmob.servicedeskcco.chamado";
    ArrayList<Chamado> chamados;
    ListView listView;
    Activity contexto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_chamados);
        Intent intent = getIntent();
        chamados = (ArrayList<Chamado>)intent.getSerializableExtra(MainActivity.CHAMADOS);
        listView = (ListView) findViewById(R.id.lista_chamados);
        ChamadoAdapter adapter = new ChamadoAdapter(this, chamados);
        listView.setAdapter(adapter);
        contexto = this;

        listView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Chamado chamado = chamados.get(position);
                        Intent intent1 = new Intent(contexto , DetalheChamadoActivity.class);
                        intent1.putExtra(CHAMADO, chamado);
                        startActivity(intent1);
                    }
                }
        );
    }

}
