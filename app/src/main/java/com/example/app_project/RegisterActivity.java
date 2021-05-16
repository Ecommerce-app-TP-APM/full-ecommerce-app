package com.example.app_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
Button btnSignUp;
EditText ediUsername,ediEmail,ediPass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btnSignUp = findViewById(R.id.main_register);
        ediUsername = findViewById(R.id.register_name_user);
        ediEmail = findViewById(R.id.register_num);
        ediPass = findViewById(R.id.register_pass);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(ediEmail.getText().toString()) || TextUtils.isEmpty(ediUsername.getText().toString()) || TextUtils.isEmpty(ediPass.getText().toString())){
                    String msg = "Remplir tout les champs";
                    Toast.makeText(RegisterActivity.this,msg,Toast.LENGTH_LONG).show();
                }else {
                    RequestRegister requestRegister = new RequestRegister();
                    requestRegister.setUsername(ediUsername.getText().toString());
                    requestRegister.setEmail(ediEmail.getText().toString());
                    requestRegister.setPassword(ediPass.getText().toString());
                    register(requestRegister);
                }


            }
        });
    }


    public void register(RequestRegister requestRegister){
        Call<ReposeRegister> reposeRegisterCall = ApiClient.getService().registerUsers(requestRegister);
        reposeRegisterCall.enqueue(new Callback<ReposeRegister>() {
            @Override
            public void onResponse(Call<ReposeRegister> call, Response<ReposeRegister> response) {
                if (response.isSuccessful()) {
                    String msg = "Register Avec Succés";
                    Toast.makeText(RegisterActivity.this,msg,Toast.LENGTH_LONG).show();
                    startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                    finish();

                }else{
                    String msg = "Error....";
                    Toast.makeText(RegisterActivity.this,msg,Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ReposeRegister> call, Throwable t) {
                String msg = t.getLocalizedMessage();
                Toast.makeText(RegisterActivity.this,msg,Toast.LENGTH_LONG).show();
            }
        });
    }
}