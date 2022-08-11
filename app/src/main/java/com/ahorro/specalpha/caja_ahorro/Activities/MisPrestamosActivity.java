package com.ahorro.specalpha.caja_ahorro.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.ahorro.specalpha.caja_ahorro.API.API;
import com.ahorro.specalpha.caja_ahorro.API.APIServices.WebService;
import com.ahorro.specalpha.caja_ahorro.Models.MisPrestamos;
import com.ahorro.specalpha.caja_ahorro.Models.Prestamo;
import com.ahorro.specalpha.caja_ahorro.Adapters.MyAdapter;
import com.ahorro.specalpha.caja_ahorro.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MisPrestamosActivity extends AppCompatActivity {

    private ListView listView;
    private List<MisPrestamos> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mis_prestamos);
        bindUI();
        webService();


    }

    private void bindUI() {
        listView = (ListView) findViewById(R.id.listView);
    }


    private void webService(){
        WebService service = API.getApi().create(WebService.class);
        Call<Prestamo> misPrestamosCall = service.getMisPrestamos("1", getIntent().getStringExtra("usernameBD"));
        misPrestamosCall.enqueue(new Callback<Prestamo>() {
            @Override
            public void onResponse(Call<Prestamo> call, Response<Prestamo> response) {
                response.body();
                list = response.body().getMisprestamos();

                final MisPrestamos misPrestamos = (new MisPrestamos());

                if (list.size()==0){
                    Toast.makeText(MisPrestamosActivity.this, "No cuentas con prestamos", Toast.LENGTH_LONG).show();
                }else{
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            /*Toast.makeText(MisPrestamosActivity.this, "Id "+list.get(i).getId(), Toast.LENGTH_LONG).show();*/
                            goPrestamo(list.get(i).getId());
                        }
                    });

                    MyAdapter myAdapter = new MyAdapter(MisPrestamosActivity.this, R.layout.list_item, list);
                    listView.setAdapter(myAdapter);
                }

            }

            @Override
            public void onFailure(Call<Prestamo> call, Throwable t) {
                Toast.makeText(MisPrestamosActivity.this,"No se puede acceder a la información, favor de intentarlo más tarde", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void goPrestamo(String id){
        Intent intent = new Intent(this, PrestamoActivity.class);
        intent.putExtra("nombreBD", getIntent().getStringExtra("nombreBD"));
        intent.putExtra("usernameBD", getIntent().getStringExtra("usernameBD"));
        intent.putExtra("id", id);
        startActivity(intent);
    }


}
