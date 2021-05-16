package com.example.app_project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;

class MyDatabaseHelper  extends SQLiteOpenHelper {
    private Context context;
    private static final String DATABASE_NAME = "ProjetMaster.db";
    private static final int DATABASE_VERSION = 1;
    //table Produit
    private static final String TABLE_NAME = "my_shop";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_TITLE = "phone_title";
    private static final String COLUMN_MARQUE = "phone_marque";
    private static final String COLUMN_PRICE = "phone_prix";
//table utilisateur
    private static final String TABLE_USER = "utilisateur";
    private static final String COLUMN_IDU = "_idu";
    private static final String COLUMN_MAIL = "mail_user";
    private static final String COLUMN_MDPS = "mdps_user";
    private static final String COLUMN_TEL = "tel_user";

     MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                        " ("+  COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_TITLE + " TEXT, " +
                        COLUMN_MARQUE + " TEXT, " +
                        COLUMN_PRICE + " INTEGER);";
        db.execSQL(query);

       /* String query2 ;
        db.execSQL(query2);*/
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
    void AddPhone(String title,String marque,int prix){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_TITLE,title);
        cv.put(COLUMN_MARQUE,marque);
        cv.put(COLUMN_PRICE,prix);


       long result =  db.insert(TABLE_NAME,null, cv);
       if (result == -1){
           Toast.makeText(context,"Failed",Toast.LENGTH_SHORT).show();
       }else{
           Toast.makeText(context,"Success Insert",Toast.LENGTH_SHORT).show();
       }
    }
    Cursor readData(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null){
           cursor =  db.rawQuery(query,null);
        }
        return cursor;

    }

    void  UpdateData(String row_id, String price){
         SQLiteDatabase db = this.getWritableDatabase();
         ContentValues cv = new ContentValues();
         cv.put(COLUMN_PRICE,price);

       long result =  db.update(TABLE_NAME, cv, "_id=?", new String[]{row_id});
       if (result == -1 ){
           Toast.makeText(context,"echec deMise a Jour ",Toast.LENGTH_SHORT).show();
       }else{
           Toast.makeText(context,"Mise a Jour Avec Succees",Toast.LENGTH_SHORT).show();
       }
    }

    void DeleteData(String row_id){
         SQLiteDatabase db = this.getWritableDatabase();
      long res= db.delete(TABLE_NAME,"__id=?",new String[]{row_id});
      if (res == -1){
          Toast.makeText(context,"echec de Suppression ",Toast.LENGTH_SHORT).show();
      }else
      {
          Toast.makeText(context,"suppression Avec Succees",Toast.LENGTH_SHORT).show();
      }
    }
}
