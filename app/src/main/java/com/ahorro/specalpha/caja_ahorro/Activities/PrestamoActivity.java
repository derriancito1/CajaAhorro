package com.ahorro.specalpha.caja_ahorro.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.ahorro.specalpha.caja_ahorro.API.API;
import com.ahorro.specalpha.caja_ahorro.API.APIServices.WebService;
import com.ahorro.specalpha.caja_ahorro.Models.MisPrestamos;
import com.ahorro.specalpha.caja_ahorro.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PrestamoActivity extends AppCompatActivity {

    private TextView textViewNombre,textViewPrestamo,textViewFechaPrestamo,textViewFechaPago,textViewCantidadPagada,textViewMeses,textViewDias,textViewIntereses,textViewDeuda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prestamo);
        bindUI();
        webService();

    }

    private void bindUI() {
        textViewNombre = (TextView) findViewById(R.id.textViewNombre);
        textViewPrestamo = (TextView) findViewById(R.id.textViewPrestamo);
        textViewFechaPrestamo = (TextView) findViewById(R.id.textViewFechaPrestamo);
        textViewFechaPago = (TextView) findViewById(R.id.textViewFechaPago);
        textViewCantidadPagada = (TextView) findViewById(R.id.textViewCantidadPagada);
        textViewMeses = (TextView) findViewById(R.id.textViewMeses);
        textViewDias = (TextView) findViewById(R.id.textViewDias);
        textViewIntereses = (TextView) findViewById(R.id.textViewIntereses);
        textViewDeuda = (TextView) findViewById(R.id.textViewDeuda);
    }


    private void webService(){
        WebService service = API.getApi().create(WebService.class);
        Call<MisPrestamos> misPrestamosCall = service.getPrestamo("1", getIntent().getStringExtra("usernameBD"), getIntent().getStringExtra("id"));
        misPrestamosCall.enqueue(new Callback<MisPrestamos>() {
            @Override
            public void onResponse(Call<MisPrestamos> call, Response<MisPrestamos> response) {
                response.body();
                textViewNombre.setText(response.body().getNombre());
                textViewPrestamo.setText(response.body().getPrestamo());
                textViewFechaPrestamo.setText(response.body().getFechaPrestamo());
                textViewFechaPago.setText(response.body().getFechaPago());
                textViewCantidadPagada.setText(response.body().getCantidadPagada());
                textViewMeses.setText(response.body().getMeses());
                textViewDias.setText(response.body().getDias());
                textViewIntereses.setText(response.body().getIntereses());
                textViewDeuda.setText(response.body().getInteresDeuda());
            }

            @Override
            public void onFailure(Call<MisPrestamos> call, Throwable t) {
                Toast.makeText(PrestamoActivity.this,"No se puede acceder a la información, favor de intentarlo más tarde", Toast.LENGTH_LONG).show();
            }
        });
    }
}
