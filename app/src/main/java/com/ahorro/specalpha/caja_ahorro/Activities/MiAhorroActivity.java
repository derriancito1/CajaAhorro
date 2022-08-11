package com.ahorro.specalpha.caja_ahorro.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ahorro.specalpha.caja_ahorro.API.API;
import com.ahorro.specalpha.caja_ahorro.API.APIServices.WebService;
import com.ahorro.specalpha.caja_ahorro.Models.MiAhorro;
import com.ahorro.specalpha.caja_ahorro.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MiAhorroActivity extends AppCompatActivity {

    private TextView textViewAhorro, textViewAcciones, textViewValorAccion, textViewMiDinero,textViewGananciaTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mi_ahorro);
        bindUI();

        webService();

        this.setTitle("Mi Ahorro");


    }

    private void bindUI() {
        textViewAhorro = (TextView) findViewById(R.id.textViewAhorro);
        textViewAcciones = (TextView) findViewById(R.id.textViewAcciones);
        textViewValorAccion = (TextView) findViewById(R.id.textViewValorAccion);
        textViewMiDinero = (TextView) findViewById(R.id.textViewMiDinero);
        textViewGananciaTotal = (TextView) findViewById(R.id.textViewGananciaTotal);
    }



    private void webService(){
        WebService service = API.getApi().create(WebService.class);
        Call<MiAhorro> ahorroCall = service.getMiAhorro("1", getIntent().getStringExtra("usernameBD"));
        ahorroCall.enqueue(new Callback<MiAhorro>() {
            @Override
            public void onResponse(Call<MiAhorro> call, Response<MiAhorro> response) {
                response.body();
                textViewAhorro.setText("$"+response.body().getAhorro());
                textViewAcciones.setText(response.body().getAcciones());
                textViewValorAccion.setText("$"+response.body().getValorAccion());
                textViewMiDinero.setText("$"+response.body().getMiDinero());
                textViewGananciaTotal.setText("$"+response.body().getGananciaTotal());
            }

            @Override
            public void onFailure(Call<MiAhorro> call, Throwable t) {
                Toast.makeText(MiAhorroActivity.this,"No se puede acceder a la información, favor de intentarlo más tarde", Toast.LENGTH_LONG).show();
            }
        });
    }
}
