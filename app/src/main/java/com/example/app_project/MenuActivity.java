package com.example.app_project;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton add;

    MyDatabaseHelper myDb;
    ArrayList<String> phone_id,phone_title,phone_marque,phone_price;

    CustomAdapter customAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        recyclerView =findViewById(R.id.myrecycle);
        add = findViewById(R.id.add);



        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this,AddActivity.class);
                startActivity(intent);
            }
        });

        myDb = new MyDatabaseHelper(MenuActivity.this);
        phone_id = new ArrayList<>();
        phone_title = new ArrayList<>();
        phone_marque = new ArrayList<>();
        phone_price = new ArrayList<>();
        StoreData();
        customAdapter = new CustomAdapter(MenuActivity.this,this,phone_id,phone_title,phone_marque,phone_price);
        recyclerView.setAdapter(customAdapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(MenuActivity.this));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1){
            recreate();
        }
    }

    void StoreData(){
        Cursor cursor = myDb.readData();
        if (cursor.getCount() == 0){
            Toast.makeText(this,"Pas donnee",Toast.LENGTH_SHORT).show();
        }else{
            while (cursor.moveToNext()){
                phone_id.add(cursor.getString(0));
                phone_title.add(cursor.getString(1));
                phone_marque.add(cursor.getString(2));
                phone_price.add(cursor.getString(3));

            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.delete_all);
        Toast.makeText(this,"Suppresion",Toast.LENGTH_SHORT).show();
        return super.onOptionsItemSelected(item);
    }
}