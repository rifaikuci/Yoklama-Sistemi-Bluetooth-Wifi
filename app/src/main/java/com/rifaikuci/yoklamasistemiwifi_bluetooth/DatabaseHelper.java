package com.rifaikuci.yoklamasistemiwifi_bluetooth;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public  static final String DATABASE_NAME="dersler.db";
    public  static final String TABLE_NAME="dersler";
    public  static final String ID="ID";
    public  static final String dersAdiCol="dersAdi";


    //Database Oluşturulması
    public  DatabaseHelper(Context context)
    {
        super(context,DATABASE_NAME,null,1);
    }

    //Tablo Oluşturulması
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+TABLE_NAME+" (ID INTEGER PRIMARY KEY AUTOINCREMENT, dersAdi TEXT )");
    }

    //Eğer aynı isimli bir tablo var ise eskisinin üzerine yazar
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(db);
    }

    //Oluşturduğumuz tabloya veri ekleme
    public  boolean insertData( String dersAdi)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(dersAdiCol,dersAdi);
        long result= db.insert(TABLE_NAME,null,contentValues);
        if (result== -1 )
        {
            return  false;
        }
        else
        {
            return true;
        }
    }

    //Tablodaki verilerin hepsini çekme
    public  Cursor getAllData()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);
        return  res;
    }

    //id'ye göre verileri çekme
    public  boolean updateData(String id ,String dersAdi)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID,id);
        contentValues.put(dersAdiCol,dersAdi);
        db.update(TABLE_NAME,contentValues,"ID = ? ",new String[] {id});
        return true;
    }

    //seçilen veri'yi id'ye göre silme işlemi
    public  Integer deleteData(String id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME,"ID=?",new String[]{id});
    }
}
