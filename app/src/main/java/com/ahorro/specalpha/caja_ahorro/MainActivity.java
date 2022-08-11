package com.ahorro.specalpha.caja_ahorro;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private AsyncHttpClient cliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.cantidad_disponible);
        cliente= new AsyncHttpClient();

        llenarEdit();
    }


    private void llenarEdit(){
        String url = "https://www.diversitisystems.com/demos/caja_ahorro/caja_ahorro.php?id=1";

        cliente.post(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (statusCode==200){
                    cargarEdit(new String(responseBody));
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(getBaseContext(), "Sin acceso a la base de datos, favor de intentarlo mas tarde", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void cargarEdit(String respuesta){
        /*ArrayAdapter <Ahorro> lista = new ArrayAdapter<Ahorro>();*/

        try{
            JSONArray jsonArray = new JSONArray(respuesta);
            /*Ahorro ah = new Ahorro();*/
            mostrarEdit(jsonArray.getJSONObject(0).getString("cantidad_actual"));
            /*lista.add(ah);*/

        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public void mostrarEdit(String cantidad){
        textView.setText("Por el momento se cuenta con la cantidad de $"+cantidad+ ", para prestamos inmediatos");
    }
}
