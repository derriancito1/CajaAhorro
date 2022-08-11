package com.ahorro.specalpha.caja_ahorro.Activities;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.ahorro.specalpha.caja_ahorro.API.API;
import com.ahorro.specalpha.caja_ahorro.API.APIServices.WebService;
import com.ahorro.specalpha.caja_ahorro.Models.MiCuenta;
import com.ahorro.specalpha.caja_ahorro.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class MainActivity extends AppCompatActivity {

    private SharedPreferences prefs;
    private TextView textViewNombreBD, textViewCantidadDisponible, textViewDeudaActual,textViewAhorroMensual;


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindUI();
        webService();

        textViewNombreBD.setText("Bienvenido "+getIntent().getStringExtra("nombreBD"));

    }

    private void bindUI() {
        prefs = getSharedPreferences("PreferencesAhorro", Context.MODE_PRIVATE);
        textViewCantidadDisponible = (TextView) findViewById(R.id.textViewCantidadDisponible);
        textViewDeudaActual = (TextView) findViewById(R.id.textViewDeudaActual);
        textViewAhorroMensual = (TextView) findViewById(R.id.textViewAhorroMensual);
        textViewNombreBD = (TextView) findViewById(R.id.textViewNombreBD);
    }

    private void webService(){
        WebService service = API.getApi().create(WebService.class);
        Call<MiCuenta> ahorroCall = service.getMiCuenta("1", getIntent().getStringExtra("usernameBD"));
        ahorroCall.enqueue(new Callback<MiCuenta>() {
            @Override
            public void onResponse(Call<MiCuenta> call, Response<MiCuenta> response) {
                response.body();
                textViewCantidadDisponible.setText(response.body().getCantidadActual()!=null ? "$"+response.body().getCantidadActual(): "$0");
                textViewDeudaActual.setText(response.body().getInteresTotal()!=null ? "$"+response.body().getInteresTotal(): "$0");
                textViewAhorroMensual.setText(response.body().getAhorroMensual()!=null ? "$"+response.body().getAhorroMensual(): "$0");
            }

            @Override
            public void onFailure(Call<MiCuenta> call, Throwable t) {
                Toast.makeText(MainActivity.this,"No se puede acceder a la información, favor de intentarlo más tarde", Toast.LENGTH_LONG).show();
            }
        });
    }

    //Activar el menu
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem){
        int id = menuItem.getItemId();
        switch (id){
            case R.id.miCuenta:
                /*Toast.makeText(this, "Mi Cuenta", Toast.LENGTH_LONG).show();*/
                goMiCuenta();
                return true;
            case R.id.mi_ahorro:
                /*Toast.makeText(this, "Mi Ahorro", Toast.LENGTH_LONG).show();*/
                goMiAhorro();
                return true;
            case R.id.mis_prestamos:
                /*Toast.makeText(this, "Mis prestamos", Toast.LENGTH_LONG).show();*/
                goMisPrestamos();
                return true;
            case R.id.cerrar:
                logOut();
                return true;
           /* case R.id.cerrar_borrar:
                removeSharedPreferences();
                logOut();
                return true;*/
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }


    public void logOut(){
        Intent intent = new Intent(this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    public void removeSharedPreferences(){
        prefs.edit().clear().apply();
    }


    public void goMiAhorro(){
        Intent intent = new Intent(this, MiAhorroActivity.class);
        intent.putExtra("nombreBD", getIntent().getStringExtra("nombreBD"));
        intent.putExtra("usernameBD", getIntent().getStringExtra("usernameBD"));
        startActivity(intent);
    }

    public void goMisPrestamos(){
        Intent intent = new Intent(this, MisPrestamosActivity.class);
        intent.putExtra("nombreBD", getIntent().getStringExtra("nombreBD"));
        intent.putExtra("usernameBD", getIntent().getStringExtra("usernameBD"));
        startActivity(intent);
    }

    public void goMiCuenta(){
        Intent intent = new Intent(this, MiCuentaActivity.class);
        intent.putExtra("nombreBD", getIntent().getStringExtra("nombreBD"));
        intent.putExtra("usernameBD", getIntent().getStringExtra("usernameBD"));
        startActivity(intent);

    }
}
