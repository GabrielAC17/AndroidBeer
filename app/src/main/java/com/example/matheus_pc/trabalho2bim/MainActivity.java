package com.example.matheus_pc.trabalho2bim;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.example.matheus_pc.trabalho2bim.dao.CervejaDAO;
import com.example.matheus_pc.trabalho2bim.model.Cerveja;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Cerveja> cervejas;
    private CervejaDAO cervejaDAO ;

    //Loading
    ProgressBar pb;
    LoadingTask task = new LoadingTask();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Executa Splash
        pb = findViewById(R.id.progressBar);
        task.execute();



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

    public void exibirProgress(boolean exibir) {
        pb.setVisibility(exibir ? View.VISIBLE : View.GONE);
    }

    //Classe do loading que roda em uma nova thread
    public class LoadingTask extends AsyncTask<Void,Void,Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            exibirProgress(true);
        }

        @Override
        protected void onPostExecute(Void voids) {
            super.onPostExecute(voids);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            exibirProgress(false);
        }


    }
}
