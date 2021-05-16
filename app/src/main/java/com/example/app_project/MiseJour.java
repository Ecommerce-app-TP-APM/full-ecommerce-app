package com.example.app_project;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MiseJour extends AppCompatActivity {
    EditText titreInpt,marqueInput,prixInput;
    Button update,delete;
    String id,titre,marque,price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mise_jour);

        titreInpt = findViewById(R.id.title_input2);
        marqueInput = findViewById(R.id.MarqueInput2);
        prixInput = findViewById(R.id.priceInput2);
        delete = findViewById(R.id.button_delete);
        update = findViewById(R.id.button_update);
        get_setIntentData();

        ActionBar  barAct = getSupportActionBar();
        if (barAct != null) {
            barAct.setTitle(titre);
        }
        //button Update
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(MiseJour.this);
                price = prixInput.getText().toString().trim();
                myDB.UpdateData(id, price);

            }
        });

//btton delete
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConfirmationD();
            }
        });
    }
    //affichage apres clique
    void get_setIntentData(){
        if (getIntent().hasExtra("id") && getIntent().hasExtra("title")&& getIntent().hasExtra("marque")
                && getIntent().hasExtra("price")){
            //get data d'aprés intent
            id = getIntent().getStringExtra("id");
            titre = getIntent().getStringExtra("title");
            marque = getIntent().getStringExtra("marque");
            price = getIntent().getStringExtra("price");

            //set data d'aprés intent
            titreInpt.setText(titre);
            marqueInput.setText(marque);
            prixInput.setText(price);

        }else {
            Toast.makeText(this,"Pas Donnée Trouver",Toast.LENGTH_SHORT).show();
        }
    }

    void ConfirmationD(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Supprimer "+ titre +"?");
        builder.setMessage("Tes sur Pour cette suppression ? ");
        builder.setPositiveButton("OUI", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MyDatabaseHelper myDb = new MyDatabaseHelper(MiseJour.this);
                myDb.DeleteData(id);
                finish();
            }
        });
        builder.setNegativeButton("Non", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create().show();
    }
}