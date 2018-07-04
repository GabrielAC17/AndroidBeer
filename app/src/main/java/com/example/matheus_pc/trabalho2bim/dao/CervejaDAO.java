package com.example.matheus_pc.trabalho2bim.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.matheus_pc.trabalho2bim.model.Cerveja;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Matheus-PC on 04/07/2018.
 */

public class CervejaDAO {

    private WrapperDB dbHelper;

    private String[] colunas = {WrapperDB.colId, WrapperDB.colNome, WrapperDB.colTipo, WrapperDB.colLocal, WrapperDB.colPreco, WrapperDB.colLocalizacao};
    private SQLiteDatabase db;

    public CervejaDAO (Context context){
        dbHelper = new WrapperDB(context);
    }

    public void open() throws SQLException {
        db = dbHelper.getWritableDatabase();
    }
    public void close(){
        dbHelper.close();
    }

    public Cerveja addCerveja (Cerveja cerveja){
        ContentValues values = new ContentValues();
        values.put( WrapperDB.colNome, cerveja.getNome());
        values.put(WrapperDB.colTipo, cerveja.getTipo());
        values.put(WrapperDB.colPreco, cerveja.getPreco());
        values.put(WrapperDB.colLocal, cerveja.getLocal());
        values.put(WrapperDB.colLocalizacao, cerveja.getLocalizacao());
        cerveja.setId(db.insert(WrapperDB.TABLE, null, values));


        Cursor cursor = db.query(WrapperDB.TABLE, colunas, WrapperDB.colId + " = " + cerveja.getId(), null, null, null, null);
        cursor.moveToFirst();

        Cerveja cervRet = parseCerveja(cursor);
        cursor.close();
        return cervRet;
    }



    public List<Cerveja> getAllCervejas(){
        List cervejas = new ArrayList();
        Cursor cursor = db.query(WrapperDB.TABLE, colunas, null, null, null, null, null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            Cerveja cervRet = parseCerveja(cursor);
            cervejas.add(cervRet);
            cursor.moveToNext();
        }
        cursor.close();
        return cervejas;
    }

    public List<Cerveja> buscaCerveja(String nome, int tipo, String localizacao){
        List cervejas = new ArrayList();

        String filter = "";

        if (nome != null && !nome.isEmpty()){
            filter =  WrapperDB.colNome + " = '" + nome + "' " ;
        }
        if (tipo != 0) {
            if (!filter.isEmpty()){
                filter += " AND " ;
            }
            filter += WrapperDB.colTipo + " = " + tipo;
        }
        if (localizacao != null && !localizacao.isEmpty()){
            if (!filter.isEmpty()){
                filter += " AND " ;
            }
            filter += WrapperDB.colLocalizacao + " = " + localizacao;
        }
        if (filter.isEmpty()){
            filter = null ;
        }
        Cursor cursor = db.query(WrapperDB.TABLE, colunas, filter, null, null, null, null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            Cerveja cervRet = parseCerveja(cursor);
            cervejas.add(cervRet);
            cursor.moveToNext();
        }
        cursor.close();
        return cervejas;


    }

    public Cerveja parseCerveja (Cursor cursor){
        Cerveja c =  new Cerveja(cursor.getInt(0), cursor.getString(1), cursor.getInt(2), cursor.getString(3) , cursor.getFloat(4),cursor.getString(5));
        return c;
    }

    public void deleteAllCervejas(){
        db.delete(WrapperDB.TABLE, null,null);
    }
}

