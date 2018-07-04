package com.example.matheus_pc.trabalho2bim;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.matheus_pc.trabalho2bim.dao.CervejaDAO;
import com.example.matheus_pc.trabalho2bim.model.Cerveja;

import java.util.ArrayList;
import java.util.List;

public class BuscaCerv extends AppCompatActivity {

    private int tipoCerveja;
    private CervejaDAO cervejaDAO ;
    private List<Cerveja> cervejas;

    TextView locaat;

    double latAtual = 0;
    double longAtual = 0;

    //Loc
    LocationManager locationManager;
    LocationListener locationListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busca_cerv);

        locaat = findViewById(R.id.locaat);

        //Funções de localização
        locationManager  = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationListener = new BuscaCerv.MyLocationListener();

        locationListener = new BuscaCerv.MyLocationListener();
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Cara, eu preciso do GPS para continuar, sério, senão eu fecho a porra toda.")
                        .setTitle("ALERTA, ATENÇÃO, CUIDADO!!!");

                builder.setPositiveButton("Foi mal cara, tomarei cuidado", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        ActivityCompat.requestPermissions(getParent(),
                                new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                1);
                    }
                });
                AlertDialog dialog = builder.create();

            } else {
                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        1);
            }
        } else {
            locationManager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER, 5000, 10, locationListener);
        }
    }

    public double CalculaDistInKM(double lat1, double long1, double lat2, double long2){
        double raio = 6371;
        double dlat = deg2rad(lat2-lat1);
        double dlong = deg2rad(long2-long1);

        double a = Math.sin(dlat/2)* Math.sin(dlat/2) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) *
                        Math.sin(dlong/2) * Math.sin(dlong/2);

        double c =  2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));

        double d = raio * c;
        return d;
    }

    public double deg2rad(double deg){
        return deg * (Math.PI/180);
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
            obj += "Tipo: " + Cerveja.getNomeTipo(c.getTipo()) + "\n";
            if (c.getLocalizacao()!= null && !c.getLocalizacao().isEmpty()){

                double lat = Double.parseDouble(c.getLocalizacao().split(":")[0]);
                double longi = Double.parseDouble(c.getLocalizacao().split(":")[1]);

                if (latAtual != 0 && longAtual != 0){
                    Double res = CalculaDistInKM(latAtual,longAtual,lat,longi)*1000;
                    obj += "Distância: " + res + "\n";
                    if (tipoRaio == null || tipoRaio.isEmpty()){
                        values.add(obj);
                    }
                    else if (tipoRaio.contains("2")){
                        if (res <= 2){
                            values.add(obj);
                        }
                    }
                    else if (tipoRaio.contains("5")){
                        if (res <= 5){
                            values.add(obj);
                        }
                    }
                    else if (tipoRaio.contains("10")){
                        if (res <= 10){
                            values.add(obj);
                        }
                    }
                }
                else{
                    Toast.makeText(getApplicationContext(),"Não consegui obter sua localiação para teste",Toast.LENGTH_SHORT).show();
                }
            }

        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, values);



        listResult.setAdapter(adapter);
    }

    private class MyLocationListener implements LocationListener {

        @Override
        public void onLocationChanged(Location loc) {
            locaat.setText(loc.getLatitude() + ":" + loc.getLongitude());
            latAtual = loc.getLatitude();
            longAtual = loc.getLongitude();
        }

        @Override
        public void onProviderDisabled(String provider) {}

        @Override
        public void onProviderEnabled(String provider) {}

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {}
    }
}
