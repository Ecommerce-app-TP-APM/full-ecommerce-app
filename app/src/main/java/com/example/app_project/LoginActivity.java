package com.example.app_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
Button btnlogin;
EditText ediUsername, ediPass;
TextView nonAccount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnlogin= findViewById(R.id.main_login);
        ediUsername=findViewById(R.id.login_num);
        ediPass = findViewById(R.id.login_pass);
        nonAccount = findViewById(R.id.non_compte);

        nonAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
            }
        });


        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(ediUsername.getText().toString()) || TextUtils.isEmpty(ediPass.getText().toString()) ){
                    String msg = "Remplir tout les champs";
                    
                    Toast.makeText(LoginActivity.this,msg,Toast.LENGTH_LONG).show();
                }else{
                    RequestLogin requestLogin = new RequestLogin();
                    requestLogin.setUsername(ediUsername.getText().toString());
                    requestLogin.setPassword(ediPass.getText().toString());
                    loginUser(requestLogin);
                }
            }
        });
    }
    public void loginUser(RequestLogin requestLogin){
        Call<ReponseLogin> loginResponseCall = ApiClient.getService().loginUser(requestLogin);
        loginResponseCall.enqueue(new Callback<ReponseLogin>() {
            @Override
            public void onResponse(Call<ReponseLogin> call, Response<ReponseLogin> response) {
                if (response.isSuccessful()){
                    ReponseLogin reponseLogin = response.body();
                    startActivity(new Intent(LoginActivity.this,MenuActivity.class));
                }else{
                    String msg = "Eroooor";
                    Toast.makeText(LoginActivity.this,msg,Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ReponseLogin> call, Throwable t) {
                String msg = t.getLocalizedMessage();
                Toast.makeText(LoginActivity.this,msg,Toast.LENGTH_LONG).show();
            }
        });

    }
}