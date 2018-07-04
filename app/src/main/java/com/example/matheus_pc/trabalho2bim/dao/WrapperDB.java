package com.example.matheus_pc.trabalho2bim.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.matheus_pc.trabalho2bim.model.Cerveja;

/**
 * Created by Matheus-PC on 04/07/2018.
 */

public class WrapperDB extends SQLiteOpenHelper {


    public static final String TABLE = "Cerveja";
    public static final String DB = "Cerveja.db" ;

    public static final String colId = "id_cerveja" ;
    public static final String colNome = "nome_cerveja" ;
    public static final String colLocal = "local_cerveja";
    public static final String colLocalizacao = "localizacao_cerveja" ;
    public static final String colPreco = "preco_cerveja" ;
    public static final String colTipo = "tipo_cerveja" ;


    private final String DB_CREATE = " create table " + TABLE + " ( " + colId + " integer primary key autoincrement, "
            + colNome + " text not null , " + colTipo + " integer not null , " + colPreco + " real not null, "
            + colLocal + " text, " + colLocalizacao + " text);" ;

    public WrapperDB(Context context){
        super(context, DB, null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DB_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE);
        onCreate(db);
    }

}
