package br.usjt.deswebmob.servicedeskcco.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Date;

import static br.usjt.deswebmob.servicedeskcco.model.FilaDbContract.FilaBanco;

public class FilaDb {
    private FilaDbHelper dbHelper;

    public FilaDb(Context context) {
        dbHelper = new FilaDbHelper(context);
    }

    public void inserirFila(ArrayList<Fila> filas) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();

        for (Fila fila : filas) {
            values.put(FilaBanco.ID_FILA, fila.getId());
            values.put(FilaBanco.NM_FILA, fila.getNome());
            values.put(FilaBanco.NM_FIGURA, fila.getFigura());
            //data precisa ser convertida no formto long
            long data;
            try{
                data = fila.getDataAtualizacao().getTime();
                Log.println(Log.DEBUG,"FilaDb" , "converteu data para long = "+data);
            } catch(Exception e){
                data = 1L;
                Log.println(Log.DEBUG,"FilaDb" , "Não converteu data para long. Valor default é "+data);
            }
            values.put(FilaBanco.DT_ATUAL, data);
            //no blob é preciso armazenar um byte[], e não um Bitmap
            values.put(FilaBanco.IMG_FIGURA, getPictureByteOfArray(fila.getImagem()));

            db.insert(FilaBanco.TABLE_NAME, null, values);
        }
        db.close();
    }

    public ArrayList<Fila> listarFilas() {
        ArrayList<Fila> filas = new ArrayList<>();

        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] colunas = {FilaBanco.ID_FILA, FilaBanco.NM_FILA,
                FilaBanco.NM_FIGURA, FilaBanco.DT_ATUAL, FilaBanco.IMG_FIGURA};

        String orderBy = FilaBanco.DT_ATUAL;

        Cursor c;

        c = db.query(FilaBanco.TABLE_NAME, colunas, null,
                null, null, null, orderBy);

        while (c.moveToNext()){
            Fila fila = new Fila();
            fila.setId(c.getInt(c.getColumnIndex(FilaBanco.ID_FILA)));
            fila.setNome(c.getString(c.getColumnIndex(FilaBanco.NM_FILA)));
            fila.setFigura((c.getString(c.getColumnIndex(FilaBanco.NM_FIGURA))));
            try {
                fila.setDataAtualizacao(new Date(c.getLong(c.getColumnIndex(FilaBanco.DT_ATUAL))));
                Log.println(Log.DEBUG,"FilaDb" , "Leu o campo dt_atual do banco");
            } catch(Exception e){
                fila.setDataAtualizacao(new Date(1L));
                Log.println(Log.DEBUG,"FilaDb" , "Não leu o campo dt_atual do banco");
            }
            fila.setImagem(getBitmapFromByte(c.getBlob(c.getColumnIndex(FilaBanco.IMG_FIGURA))));

            filas.add(fila);
        }
        c.close();
        db.close();
        return filas;
    }

    public void atualizaFilas(ArrayList<Fila> filas) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        //deleta flas
        String selecao = FilaBanco.ID_FILA +"= ?";
        String[] selectionArgs = new String[1];

        for (Fila fila: filas) {
            selectionArgs[0] = ""+fila.getId();
            db.delete(FilaBanco.TABLE_NAME, selecao, selectionArgs);
        }


        //insere filas
        ContentValues values = new ContentValues();

        for (Fila fila : filas) {
            values.put(FilaBanco.ID_FILA, fila.getId());
            values.put(FilaBanco.NM_FILA, fila.getNome());
            values.put(FilaBanco.NM_FIGURA, fila.getFigura());
            //data precisa ser convertida no formto long
            values.put(FilaBanco.DT_ATUAL, fila.getDataAtualizacao().getTime());
            //no blob é preciso armazenar um byte[], e não um Bitmap
            values.put(FilaBanco.IMG_FIGURA, getPictureByteOfArray(fila.getImagem()));

            db.insert(FilaBanco.TABLE_NAME, null, values);
        }
        db.close();
    }

    private byte[] getPictureByteOfArray(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    private Bitmap getBitmapFromByte(byte[] image) {
        return BitmapFactory.decodeByteArray(image, 0, image.length);
    }
}
