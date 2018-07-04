package com.example.matheus_pc.trabalho2bim;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.matheus_pc.trabalho2bim.dao.CervejaDAO;
import com.example.matheus_pc.trabalho2bim.model.Cerveja;

import java.util.ArrayList;
import java.util.List;

public class BuscaCerv extends AppCompatActivity {

    private int tipoCerveja;
    private CervejaDAO cervejaDAO ;
    private List<Cerveja> cervejas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busca_cerv);
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radio01:
                if (checked){
                    ((RadioButton)findViewById(R.id.radio02)).setChecked(false);
                    ((RadioButton)findViewById(R.id.radio03)).setChecked(false);
                    ((RadioButton)findViewById(R.id.radio04)).setChecked(false);
                    ((RadioButton)findViewById(R.id.radio05)).setChecked(false);
                    ((RadioButton)findViewById(R.id.radio06)).setChecked(false);
                    ((RadioButton)findViewById(R.id.radio07)).setChecked(false);
                    tipoCerveja = 1;
                }
                break;
            case R.id.radio02:
                if (checked){
                    ((RadioButton)findViewById(R.id.radio01)).setChecked(false);
                    ((RadioButton)findViewById(R.id.radio03)).setChecked(false);
                    ((RadioButton)findViewById(R.id.radio04)).setChecked(false);
                    ((RadioButton)findViewById(R.id.radio05)).setChecked(false);
                    ((RadioButton)findViewById(R.id.radio06)).setChecked(false);
                    ((RadioButton)findViewById(R.id.radio07)).setChecked(false);
                    tipoCerveja = 2;
                }
                break;
            case R.id.radio03:
                if (checked){
                    ((RadioButton)findViewById(R.id.radio01)).setChecked(false);
                    ((RadioButton)findViewById(R.id.radio02)).setChecked(false);
                    ((RadioButton)findViewById(R.id.radio04)).setChecked(false);
                    ((RadioButton)findViewById(R.id.radio05)).setChecked(false);
                    ((RadioButton)findViewById(R.id.radio06)).setChecked(false);
                    ((RadioButton)findViewById(R.id.radio07)).setChecked(false);
                    tipoCerveja = 3;
                }
                break;
            case R.id.radio04:
                if (checked){
                    ((RadioButton)findViewById(R.id.radio01)).setChecked(false);
                    ((RadioButton)findViewById(R.id.radio02)).setChecked(false);
                    ((RadioButton)findViewById(R.id.radio03)).setChecked(false);
                    ((RadioButton)findViewById(R.id.radio05)).setChecked(false);
                    ((RadioButton)findViewById(R.id.radio06)).setChecked(false);
                    ((RadioButton)findViewById(R.id.radio07)).setChecked(false);
                    tipoCerveja = 4;
                }
                break;
            case R.id.radio05:
                if (checked){
                    ((RadioButton)findViewById(R.id.radio01)).setChecked(false);
                    ((RadioButton)findViewById(R.id.radio03)).setChecked(false);
                    ((RadioButton)findViewById(R.id.radio04)).setChecked(false);
                    ((RadioButton)findViewById(R.id.radio02)).setChecked(false);
                    ((RadioButton)findViewById(R.id.radio06)).setChecked(false);
                    ((RadioButton)findViewById(R.id.radio07)).setChecked(false);
                    tipoCerveja = 5;
                }
                break;
            case R.id.radio06:
                if (checked){
                    ((RadioButton)findViewById(R.id.radio01)).setChecked(false);
                    ((RadioButton)findViewById(R.id.radio03)).setChecked(false);
                    ((RadioButton)findViewById(R.id.radio04)).setChecked(false);
                    ((RadioButton)findViewById(R.id.radio05)).setChecked(false);
                    ((RadioButton)findViewById(R.id.radio02)).setChecked(false);
                    ((RadioButton)findViewById(R.id.radio07)).setChecked(false);
                    tipoCerveja = 6;
                }
                break;
            case R.id.radio07:
                if (checked){
                    ((RadioButton)findViewById(R.id.radio01)).setChecked(false);
                    ((RadioButton)findViewById(R.id.radio03)).setChecked(false);
                    ((RadioButton)findViewById(R.id.radio04)).setChecked(false);
                    ((RadioButton)findViewById(R.id.radio05)).setChecked(false);
                    ((RadioButton)findViewById(R.id.radio06)).setChecked(false);
                    ((RadioButton)findViewById(R.id.radio02)).setChecked(false);
                    tipoCerveja = 7;
                }
                break;
        }
    }

    public void onClickBuscar(View view) {
        cervejaDAO = new CervejaDAO(this);
        cervejaDAO.open();

        List<String> values = new ArrayList<String>();
        ListView listResult = (ListView) findViewById(R.id.listResult);

        String nome = ((EditText)findViewById(R.id.nomeBusca)).getText().toString();
        RadioGroup rg = (RadioGroup)findViewById(R.id.radioRaio);
        int idRadioChecked = rg.getCheckedRadioButtonId();
        String tipoRaio = "";
        if (idRadioChecked != -1){
            RadioButton rb = (RadioButton)findViewById(idRadioChecked);
            tipoRaio = rb.getText().toString();
        }

        cervejas = cervejaDAO.buscaCerveja(nome, tipoCerveja, tipoRaio);
        cervejaDAO.close();
        for (Cerveja c : cervejas){
            String obj = "Nome: " + c.getNome() + "\n";
            obj += "Local: " + c.getLocal() + "\n";
            obj += "Preço: R$" + c.getPreco() + "\n";
            obj += "Localização: " + c.getLocalizacao() + "\n";
            obj += "Tipo: " + Cerveja.getNomeTipo(c.getTipo()) + "\n";
            values.add(obj);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, values);
        listResult.setAdapter(adapter);

    }
}
