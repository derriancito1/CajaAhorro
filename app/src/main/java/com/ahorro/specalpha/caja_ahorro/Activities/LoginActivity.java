package com.ahorro.specalpha.caja_ahorro.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.ahorro.specalpha.caja_ahorro.API.API;
import com.ahorro.specalpha.caja_ahorro.API.APIServices.WebService;
import com.ahorro.specalpha.caja_ahorro.Models.User;
import com.ahorro.specalpha.caja_ahorro.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private SharedPreferences prefs;

    private EditText editTextUsuario;
    private EditText editTextPassword;
    private Switch switchRecordar;
    private Button buttonEntrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        bindUI();
        setCredentialsIfExist();
        getSupportActionBar().hide();

        buttonEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                webService(editTextUsuario.getText().toString(),editTextPassword.getText().toString());
            }
        });
    }

    private void webService(final String usuario, final String password){
        if (login(usuario, password)){
            WebService service = API.getApi().create(WebService.class);
            Call<User> userCall = service.getUser(usuario, password);
            userCall.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    response.body();
                    String usernameBD = response.body().getUsuario();
                    String nombreBD = response.body().getNombre();
                    if (TextUtils.isEmpty(usernameBD) && TextUtils.isEmpty(nombreBD)){
                        Toast.makeText(LoginActivity.this,"El usuario o Contraseña son incorrectos", Toast.LENGTH_LONG).show();
                    }else{
                        goToMain(nombreBD,usernameBD);
                        saveOnPreferences(usuario, password, true);
                    }
                }
                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    Toast.makeText(LoginActivity.this,"No se puede acceder a la información, favor de intentarlo más tarde", Toast.LENGTH_LONG).show();
                }
            });
        }
    }

    private void bindUI(){
        editTextUsuario = (EditText) findViewById(R.id.editTextUsuario);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        switchRecordar = (Switch) findViewById(R.id.switchRecordar);
        buttonEntrar = (Button) findViewById(R.id.buttonEntrar);
        prefs = getSharedPreferences("PreferencesAhorro", Context.MODE_PRIVATE);
    }

    private boolean login(String usuario, String password){
        if (!isValidUsuario(usuario)){
            Toast.makeText(this, "Usuario no valido", Toast.LENGTH_LONG).show();
            return false;
        }else if (!isValidPassword(password)){
            Toast.makeText(this, "Contraseña no valida", Toast.LENGTH_LONG).show();
            return false;
        }else {
            return true;
        }

    }

    private void saveOnPreferences(String usuario, String password, boolean recordarme){
        if (switchRecordar.isChecked()){
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("usuario", usuario);
            editor.putString("password", password);
            editor.putBoolean("recordarme", recordarme);
            //editor.commit(); // para que no avance hasta guardar la informacion
            editor.apply();
        }else {
            prefs.edit().clear().apply();
        }
    }

    private boolean isValidUsuario(String usuario){
        return !TextUtils.isEmpty(usuario) && usuario.length()>=8;
    }

    private boolean isValidPassword(String password){
        return !TextUtils.isEmpty(password) && password.length()>=6;

    }

    private void goToMain(String nombreBD, String usernameBD){
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.putExtra("nombreBD", nombreBD);
        intent.putExtra("usernameBD", usernameBD);
        startActivity(intent);
    }

    public void setCredentialsIfExist(){
        String usuario = getUserPrefs();
        String password = getPasswordPrefs();
        boolean recordarme = getRecordarmePrefs();
        if (!TextUtils.isEmpty(usuario) && !TextUtils.isEmpty(password)){
            editTextUsuario.setText(usuario);
            editTextPassword.setText(password);
            switchRecordar.setChecked(recordarme);
        }
    }

    private String getUserPrefs(){
        return prefs.getString("usuario", "");
    }

    private String getPasswordPrefs(){
        return prefs.getString("password", "");
    }

    private boolean getRecordarmePrefs(){
        return prefs.getBoolean("recordarme", false);
    }
}
