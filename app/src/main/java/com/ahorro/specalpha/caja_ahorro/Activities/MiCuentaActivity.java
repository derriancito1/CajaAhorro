package com.ahorro.specalpha.caja_ahorro.Activities;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ahorro.specalpha.caja_ahorro.API.API;
import com.ahorro.specalpha.caja_ahorro.API.APIServices.WebService;
import com.ahorro.specalpha.caja_ahorro.Models.MiCuenta;
import com.ahorro.specalpha.caja_ahorro.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MiCuentaActivity extends AppCompatActivity {
    private EditText editTextNombre,editTextTelefono,editTextTarjeta,editTextEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mi_cuenta);
        bindUI();
        webService();

        this.setTitle("Mi Cuenta");

    }

    private void bindUI() {
        editTextNombre = (EditText) findViewById(R.id.editTextNombre);
        editTextTelefono = (EditText) findViewById(R.id.editTextTelefono);
        editTextTarjeta = (EditText) findViewById(R.id.editTextTarjeta);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
    }

    private void webService(){
        WebService service = API.getApi().create(WebService.class);
        Call<MiCuenta> ahorroCall = service.getMiCuenta("1", getIntent().getStringExtra("usernameBD"));
        ahorroCall.enqueue(new Callback<MiCuenta>() {
            @Override
            public void onResponse(Call<MiCuenta> call, Response<MiCuenta> response) {
                response.body();
                editTextNombre.setText(response.body().getNombre()!=null ? response.body().getNombre(): "");
                editTextTelefono.setText(response.body().getTelefono()!=null ? response.body().getTelefono(): "");
                editTextTarjeta.setText(response.body().getTarjeta()!=null ? response.body().getTarjeta(): "");
                editTextEmail.setText(response.body().getEmail()!=null ? response.body().getEmail(): "");
            }

            @Override
            public void onFailure(Call<MiCuenta> call, Throwable t) {
                Toast.makeText(MiCuentaActivity.this,"No se puede acceder a la información, favor de intentarlo más tarde", Toast.LENGTH_LONG).show();
            }
        });
    }
}
