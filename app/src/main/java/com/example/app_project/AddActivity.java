package com.example.app_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddActivity extends AppCompatActivity {
    EditText title_input,MarqueInput,PriceInput;
    Button button_add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        title_input = findViewById(R.id.title_input);
        MarqueInput = findViewById(R.id.MarqueInput);
        PriceInput = findViewById(R.id.priceInput);
        button_add = findViewById(R.id.button_add);

        button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDatabaseHelper myDb = new MyDatabaseHelper(AddActivity.this);
                myDb.AddPhone(title_input.getText().toString().trim(),
                        MarqueInput.getText().toString().trim(),
                        Integer.valueOf(PriceInput.getText().toString().trim()) );

            }
        });
    }
}