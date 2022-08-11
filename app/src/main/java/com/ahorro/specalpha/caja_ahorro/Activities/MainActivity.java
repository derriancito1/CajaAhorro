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
import com.ahorro.specalpha.caja_ahorro.Models.Ahorro;
import com.ahorro.specalpha.caja_ahorro.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class MainActivity extends AppCompatActivity {

    private SharedPreferences prefs;
    private TextView textViewNombreBD;
    private TextView textView;


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
        textView = (TextView) findViewById(R.id.cantidad_disponible);
        textViewNombreBD = (TextView) findViewById(R.id.textViewNombreBD);
    }

    private void webService(){
        WebService service = API.getApi().create(WebService.class);
        Call<Ahorro> ahorroCall = service.getAhorro("1");
        ahorroCall.enqueue(new Callback<Ahorro>() {
            @Override
            public void onResponse(Call<Ahorro> call, Response<Ahorro> response) {
                response.body();
                textView.setText("Por el momento se cuenta con la cantidad de "+response.body().getName()+", para prestamos inmediatos");
            }

            @Override
            public void onFailure(Call<Ahorro> call, Throwable t) {
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
            /*case R.id.intereses:
                Toast.makeText(this, "Intereses", Toast.LENGTH_LONG).show();
                return true;*/
            case R.id.mi_ahorro:
                Toast.makeText(this, "Mi Ahorro", Toast.LENGTH_LONG).show();
                goMiAhorro();
                return true;
            /*case R.id.mis_prestamos:
                Toast.makeText(this, "Mis prestamos", Toast.LENGTH_LONG).show();
                return true;*/
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
        //intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.putExtra("nombreBD", getIntent().getStringExtra("nombreBD"));
        intent.putExtra("usernameBD", getIntent().getStringExtra("usernameBD"));
        startActivity(intent);
    }
}
