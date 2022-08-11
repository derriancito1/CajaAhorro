package com.ahorro.specalpha.caja_ahorro.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.ahorro.specalpha.caja_ahorro.API.API;
import com.ahorro.specalpha.caja_ahorro.API.APIServices.WebService;
import com.ahorro.specalpha.caja_ahorro.Models.MisPrestamos;
import com.ahorro.specalpha.caja_ahorro.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MisPrestamosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mis_prestamos);
        bindUI();
        webService();
    }

    private void bindUI() {
    }


    private void webService(){
        WebService service = API.getApi().create(WebService.class);
        Call<MisPrestamos> misPrestamosCall = service.getMisPrestamos("1", getIntent().getStringExtra("usernameBD"));
        misPrestamosCall.enqueue(new Callback<MisPrestamos>() {
            @Override
            public void onResponse(Call<MisPrestamos> call, Response<MisPrestamos> response) {
                response.body();
            }

            @Override
            public void onFailure(Call<MisPrestamos> call, Throwable t) {
                Toast.makeText(MisPrestamosActivity.this,"No se puede acceder a la información, favor de intentarlo más tarde", Toast.LENGTH_LONG).show();
            }
        });
    }


}
