package com.example.securinzy;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper( Context context) {
        super(context, "Login.db", null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase MyDB) {
MyDB.execSQL( "create table users(name_fi TEXT , name_la TEXT , email TEXT primary key , password TEXT , ph_num TEXT )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop table if exists users");

    }
    public boolean insertData(String email,String password ,String name_fi,String name_la , String ph_num){
        SQLiteDatabase MyDB= this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("email",email);
        contentValues.put("password",password);
        contentValues.put("name_la",name_la);
        contentValues.put("name_fi",name_fi);
        contentValues.put("ph_num",ph_num);
        long result = MyDB.insert("users",null,contentValues);
        if (result==-1){
            return false;
        }
        else{
            return true;
        }

    }
    public boolean checkemail(String email){
        SQLiteDatabase MyDB =this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("select * from users where email = ?", new String[] {email});
        if (cursor.getCount()>0){
            return true;
        }
        else {
            return false;
        }
    }
    public boolean checkemailpassword(String email, String password){
        SQLiteDatabase MyDB =this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("select * from users where email = ? and password = ?", new String[] {email,password});
        if (cursor.getCount()>0){
            return true;
        }
        else {
            return false;
        }
    }
}
