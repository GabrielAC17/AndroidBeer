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
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.matheus_pc.trabalho2bim.dao.CervejaDAO;
import com.example.matheus_pc.trabalho2bim.model.Cerveja;

public class InsercaoCerv extends AppCompatActivity {

    private int tipoCerveja;
    private CervejaDAO cervejaDAO;

    //Loc
    LocationManager locationManager;
    LocationListener locationListener;

    TextView loca;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insercao_cerv);

        loca = findViewById(R.id.loc);

        //Funções de localização
        locationManager  = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationListener = new MyLocationListener();

        locationListener = new MyLocationListener();
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

    public void onClickCadastroCerveja(View view) {
        cervejaDAO = new CervejaDAO(this);
        cervejaDAO.open();

        //nome
        String nome = ((EditText)findViewById(R.id.nomeBusca)).getText().toString();
        if (nome == null || nome.isEmpty()){
            Toast.makeText(getApplicationContext(), "Nome inválido", Toast.LENGTH_SHORT).show();
            return;
        }
        String local = ((EditText)findViewById(R.id.local)).getText().toString();
        if (local == null || local.isEmpty()){
            Toast.makeText(getApplicationContext(), "Local inválido", Toast.LENGTH_SHORT).show();
            return;
        }
        //valor
        String valorS = ((EditText)findViewById(R.id.valor)).getText().toString();
        float valor = 0;
        if (valorS == null || valorS.isEmpty() || valorS.equals(".")){
            Toast.makeText(getApplicationContext(), "Valor inválido", Toast.LENGTH_SHORT).show();
            return;
        }
        valor = Float.parseFloat(valorS);
        String loc = ((TextView)findViewById(R.id.loc)).getText().toString();
        if (loc == null || loc.isEmpty()){
            //
        }
        if (tipoCerveja == 0){
            Toast.makeText(getApplicationContext(), "Tipo inválido", Toast.LENGTH_SHORT).show();
            return;
        }
        cervejaDAO.addCerveja(new Cerveja(nome, tipoCerveja, local, valor, loc));
        cervejaDAO.close();
        setResult(1);
        finish();
    }
    private void resetRadios(int id){

    }

    private class MyLocationListener implements LocationListener {

        @Override
        public void onLocationChanged(Location loc) {
            loca.setText(loc.getLatitude() + ":" + loc.getLongitude());
        }

        @Override
        public void onProviderDisabled(String provider) {}

        @Override
        public void onProviderEnabled(String provider) {}

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {}
    }
}
