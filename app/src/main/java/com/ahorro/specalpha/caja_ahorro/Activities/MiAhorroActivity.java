package com.ahorro.specalpha.caja_ahorro.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.ahorro.specalpha.caja_ahorro.API.API;
import com.ahorro.specalpha.caja_ahorro.API.APIServices.WebService;
import com.ahorro.specalpha.caja_ahorro.Models.MiAhorro;
import com.ahorro.specalpha.caja_ahorro.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MiAhorroActivity extends AppCompatActivity {

    private EditText editTextAhorro, editTextAcciones, editTextValorAccion, editTextMiDinero,editTextGananciaTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mi_ahorro);
        bindUI();

        webService();


    }

    private void bindUI() {
        editTextAhorro = (EditText) findViewById(R.id.editTextAhorro);
        editTextAcciones = (EditText) findViewById(R.id.editTextAcciones);
        editTextValorAccion = (EditText) findViewById(R.id.editTextValorAccion);
        editTextMiDinero = (EditText) findViewById(R.id.editTextMiDinero);
        editTextGananciaTotal = (EditText) findViewById(R.id.editTextGananciaTotal);
    }



    private void webService(){
        WebService service = API.getApi().create(WebService.class);
        Call<MiAhorro> ahorroCall = service.getMiAhorro("1", getIntent().getStringExtra("usernameBD"));
        ahorroCall.enqueue(new Callback<MiAhorro>() {
            @Override
            public void onResponse(Call<MiAhorro> call, Response<MiAhorro> response) {
                response.body();
                editTextAhorro.setText("$"+response.body().getAhorro());
                editTextAcciones.setText(response.body().getAcciones());
                editTextValorAccion.setText("$"+response.body().getValorAccion());
                editTextMiDinero.setText("$"+response.body().getMiDinero());
                editTextGananciaTotal.setText("$"+response.body().getGananciaTotal());
            }

            @Override
            public void onFailure(Call<MiAhorro> call, Throwable t) {
                Toast.makeText(MiAhorroActivity.this,"No se puede acceder a la información, favor de intentarlo más tarde", Toast.LENGTH_LONG).show();
            }
        });
    }
}
