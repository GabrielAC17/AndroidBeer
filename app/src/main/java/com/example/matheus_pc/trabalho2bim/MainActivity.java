package com.example.matheus_pc.trabalho2bim;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.matheus_pc.trabalho2bim.dao.CervejaDAO;
import com.example.matheus_pc.trabalho2bim.model.Cerveja;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Cerveja> cervejas;
    private CervejaDAO cervejaDAO ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        atualizaLista();
//        deleteAll();
    }


    @Override
    protected void onResume() {
        super.onResume();
        atualizaLista();
    }

    private void deleteAll(){
        cervejaDAO = new CervejaDAO(this);
        cervejaDAO.open();
        cervejaDAO.deleteAllCervejas();
        cervejaDAO.close();
    }

    private void atualizaLista(){
        cervejaDAO = new CervejaDAO(this);
        cervejaDAO.open();
        List<String> values = new ArrayList<String>();
        ListView listaDeCursos = (ListView) findViewById(R.id.lista);

        cervejas = cervejaDAO.getAllCervejas();
        for (Cerveja c : cervejas){
            values.add(c.getNome());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, values);
        listaDeCursos.setAdapter(adapter);
        cervejaDAO.close();
    }
    public void onClickInsercao(View view) {
        Intent i = new Intent(this, InsercaoCerv.class);
        startActivity(i);
    }
    public void onClickBusca(View view) {
        Intent i = new Intent(this, BuscaCerv.class);
        startActivity(i);
    }
}
