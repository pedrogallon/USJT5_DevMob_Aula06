package br.usjt.deswebmob.servicedeskcco.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import static br.usjt.deswebmob.servicedeskcco.model.FilaDbContract.FilaBanco;

public class FilaDbHelper extends SQLiteOpenHelper {
    public static final String SQL_CREATE_FILA =
            "CREATE TABLE " + FilaBanco.TABLE_NAME + " ( " +
                    FilaBanco._ID + " INTEGER PRIMARY KEY, " +
                    FilaBanco.ID_FILA + " INTEGER, " +
                    FilaBanco.NM_FILA + " TEXT, " +
                    FilaBanco.NM_FIGURA + " TEXT, " +
                    FilaBanco.DT_ATUAL + " INTEGER," +
                    FilaBanco.IMG_FIGURA + " BLOB ) ";

    public static final String SQL_DROP_FILA =
            "DROP TABLE IF EXISTS " + FilaBanco.TABLE_NAME;

    public static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "Fila.db";

    public FilaDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_FILA);
        Log.println(Log.DEBUG,"FilaDbHelper" , "OnCreate: criou a tabela Fila com o comando " + SQL_CREATE_FILA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DROP_FILA);
        Log.println(Log.DEBUG,"FilaDbHelper" , "OnUpgrade: dropou a tabela Fila com o comando " + SQL_DROP_FILA);
        db.execSQL(SQL_CREATE_FILA);
        Log.println(Log.DEBUG,"FilaDbHelper" , "OnUpgrade: criou a tabela Fila com o comando " + SQL_CREATE_FILA);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DROP_FILA);
        Log.println(Log.DEBUG,"FilaDbHelper" , "onDowngrade: dropou a tabela Fila com o comando " + SQL_DROP_FILA);
        db.execSQL(SQL_CREATE_FILA);
        Log.println(Log.DEBUG,"FilaDbHelper" , "onDowngrade: criou a tabela Fila com o comando " + SQL_CREATE_FILA);
    }
}
