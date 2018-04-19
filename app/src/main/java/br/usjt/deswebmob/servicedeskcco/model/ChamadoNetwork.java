package br.usjt.deswebmob.servicedeskcco.model;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
/**
 * @author pedrogallon
 */
public class ChamadoNetwork {
    public static ArrayList<Fila> buscarFilas(String urlRest, String urlImg) throws IOException{
        ArrayList<Fila> filas = getFilas(urlRest);
        for(Fila fila:filas){
            fila.setImagem(getFigura(urlImg+fila.getFigura()+".png"));
        }
        return filas;
    }

    public static ArrayList<Chamado> buscarChamados(String url) throws IOException {
        OkHttpClient client = new OkHttpClient();

        Log.println(Log.DEBUG,"url chamados" , url);

        ArrayList<Chamado> chamados = new ArrayList<>();
        DateFormat formatter = new SimpleDateFormat(Chamado.DATE_PATTERN);

        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        String json = response.body().string();

        Log.println(Log.DEBUG,"json chamados" , json);

        try {
            JSONArray lista = new JSONArray(json);
            for (int i = 0; i < lista.length(); i++) {
                JSONObject item = (JSONObject) lista.get(i);
                Chamado chamado = new Chamado();
                chamado.setNumero(item.getInt("numero"));
                chamado.setDescricao(item.getString("descricao"));
                chamado.setStatus(item.getString("status"));
                String sDataAbertura = (item.getString("dataAbertura"));
                String sDataFechamento = (item.getString("dataFechamento"));
                try {
                    chamado.setDataAbertura(formatter.parse(sDataAbertura));
                } catch (ParseException e) {
                    chamado.setDataAbertura(null);
                }
                try {
                    chamado.setDataFechamento(formatter.parse(sDataFechamento));
                } catch (ParseException e) {
                    chamado.setDataFechamento(null);
                }
                JSONObject filaItem = item.getJSONObject("fila");
                Fila fila = new Fila();
                fila.setId(filaItem.getInt("id"));
                fila.setNome(filaItem.getString("nome"));
                fila.setFigura(filaItem.getString("figura"));
                chamado.setFila(fila);
                chamados.add(chamado);

            }

        } catch (JSONException e) {
            e.printStackTrace();
            throw new IOException(e);
        }


        return chamados;
    }

    public static ArrayList<Fila> getFilas(String url) throws IOException {
        ArrayList<Fila> filas = new ArrayList<>();
        OkHttpClient client = new OkHttpClient();
        DateFormat formatter = new SimpleDateFormat(Fila.DATE_PATTERN);

        Log.println(Log.DEBUG,"url filas" , url);

        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        String json = response.body().string();

        Log.println(Log.DEBUG,"json filas" , json);

        try {
            JSONArray lista = new JSONArray(json);
            for (int i = 0; i < lista.length(); i++) {
                JSONObject item = (JSONObject) lista.get(i);
                Fila fila = new Fila();
                fila.setId(item.getInt("id"));
                fila.setNome(item.getString("nome"));
                fila.setFigura(item.getString("figura"));
                String sDataAtualizacao = (item.getString("dataAtualizacao"));
                try {
                    fila.setDataAtualizacao(formatter.parse(sDataAtualizacao));
                } catch (ParseException e) {
                    fila.setDataAtualizacao(null);
                }
                filas.add(fila);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            throw new IOException(e);
        }


        return filas;

    }

    private static Bitmap getFigura(String url) throws IOException{
        Bitmap img;

        Log.println(Log.DEBUG,"url figuras" , url);

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();

        InputStream is = response.body().byteStream();

        img = BitmapFactory.decodeStream(is);

        is.close();

        return img;
    }
}
