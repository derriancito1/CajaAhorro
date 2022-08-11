package com.ahorro.specalpha.caja_ahorro.Activities;
import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ahorro.specalpha.caja_ahorro.API.API;
import com.ahorro.specalpha.caja_ahorro.API.APIServices.WebService;
import com.ahorro.specalpha.caja_ahorro.Models.Ahorro;
import com.ahorro.specalpha.caja_ahorro.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.ahorro.specalpha.caja_ahorro.R.menu.menu;


public class MainActivity extends AppCompatActivity {

    private TextView textView;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.cantidad_disponible);

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
            case R.id.intereses:
                Toast.makeText(this, "Intereses", Toast.LENGTH_LONG).show();
            break;
            case R.id.mi_ahorro:
                Toast.makeText(this, "Mi Ahorro", Toast.LENGTH_LONG).show();
            break;
            case R.id.mis_prestamos:
                Toast.makeText(this, "Mis prestamos", Toast.LENGTH_LONG).show();
            break;
        }
        return super.onOptionsItemSelected(menuItem);
    }
}
