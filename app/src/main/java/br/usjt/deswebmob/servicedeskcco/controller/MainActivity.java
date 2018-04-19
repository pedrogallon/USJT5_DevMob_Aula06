package br.usjt.deswebmob.servicedeskcco.controller;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.io.IOException;
import java.util.ArrayList;

import br.usjt.deswebmob.servicedeskcco.R;
import br.usjt.deswebmob.servicedeskcco.controller.ListarChamadosActivity;
import br.usjt.deswebmob.servicedeskcco.model.Chamado;
import br.usjt.deswebmob.servicedeskcco.model.ChamadoNetwork;
import br.usjt.deswebmob.servicedeskcco.model.Fila;
/**
 * @author pedrogallon
 */
public class MainActivity extends Activity {
    public static final String CHAMADOS = "br.usjt.deswebmob.servicedeskcco.chamados";
    public static final String FILAS = "br.usjt.deswebmob.servicedeskcco.filas";
    private EditText txtFila;
    Context contexto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtFila = findViewById(R.id.buscar_fila);
        contexto = this;
//        new DownloadJsonChamados().execute("http://192.168.15.20:8080/ArqSft_Chamado/rest/img/");
    }

    public void buscarChamados(View view) {
        String fila = txtFila.getText().toString();
        new DownloadJsonChamados().execute("http://192.168.15.110:8081/arqdes_sdesk_final_aula07/rest/chamados/");

    }

    private class DownloadJsonChamados extends AsyncTask<String, Void, ArrayList<Chamado>>{

        @Override
        protected ArrayList<Chamado> doInBackground(String... strings) {
            ArrayList<Chamado> chamados = new ArrayList<>();
            try {
                chamados = ChamadoNetwork.buscarChamados(strings[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return chamados;
        }

        protected void onPostExecute(ArrayList<Chamado> chamados){
            Intent intent = new Intent(contexto, ListarChamadosActivity.class);
            intent.putExtra(CHAMADOS, chamados);
            startActivity(intent);
        }

    }

//    private class DownloadJsonFilas extends AsyncTask<String, Void, ArrayList<Fila>>{
//
//        @Override
//        protected ArrayList<Fila> doInBackground(String... strings) {
//            ArrayList<Fila> filas = new ArrayList<>();
//            try {
//                filas = ChamadoNetwork.buscarFilas(strings[0]);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            return filas;
//        }
//
//
//        protected void onPostExecute(ArrayList<Fila> filas){
//            Intent intent = new Intent(contexto, ListarChamadosActivity.class);
//            intent.putExtra(FILAS, filas);
//            startActivity(intent);
//        }
//
//    }
}
