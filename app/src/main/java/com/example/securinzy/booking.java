package com.example.securinzy;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class booking extends AppCompatActivity {

    private Calendar selectedDate;
    ImageView backButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);
        backButton = findViewById(R.id.button_back);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate back to pentestActivity
                Intent intent = new Intent(booking.this, SERVICES.class);
                startActivity(intent);
            }
        });
        EditText etDate = findViewById(R.id.etDate);
        etDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker();
            }
        });

        Button bookButton = findViewById(R.id.book);
        bookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showConfirmationDialog();
            }
        });
    }

    private void showDatePicker() {
        final Calendar currentDate = Calendar.getInstance();
        int year = currentDate.get(Calendar.YEAR);
        int month = currentDate.get(Calendar.MONTH);
        int day = currentDate.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                selectedDate = Calendar.getInstance();
                selectedDate.set(Calendar.YEAR, year);
                selectedDate.set(Calendar.MONTH, monthOfYear);
                selectedDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                // Update the EditText with the selected date
                EditText etDate = findViewById(R.id.etDate);
                etDate.setText(String.format("%02d/%02d/%04d", dayOfMonth, monthOfYear + 1, year));
            }
        }, year, month, day);

        datePickerDialog.getDatePicker().setMinDate(currentDate.getTimeInMillis()); // Set minimum date to today
        datePickerDialog.show();
    }

    private void showConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Booking Confirmation")
                .setMessage("Thank you so much! Your request has been submitted successfully.")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Perform any additional actions on OK button click if needed
                    }
                })
                .show();
    }

    public static class DBHelper extends SQLiteOpenHelper {
        public static final String DBNAME= "Login.db";

        public DBHelper( Context context) {
            super(context, "Login.db", null, 1);
        }



        @Override
        public void onCreate(SQLiteDatabase MyDB) {
            MyDB.execSQL("create table users(name_fi TEXT , email TEXT primary key , password TEXT ,name_la TEXT , ph_num TEXT)");

        }

        @Override
        public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
              MyDB.execSQL("drop table if exists users");

        }

        public boolean insertData (String email ,String name_fi, String password , String ph_num , String name_la){
            SQLiteDatabase MyDB = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("email",email);
            contentValues.put("name_fi",name_fi);
            contentValues.put("name_la",name_la);
            contentValues.put("password",password);
            contentValues.put("ph_num",ph_num);
            long result = MyDB.insert("users",null,contentValues);
            if(result==-1){
                return false;
            }
            else {
                return true;
            }

        }
        public boolean checkemail(String email){
            SQLiteDatabase MyDB=this.getWritableDatabase();
            Cursor cursor= MyDB.rawQuery("select * from users where email = ?",new String[] {email});
            if (cursor.getCount()>0){
                return  true;
            }
            else{
                return false;
            }

        }
        public boolean checkemailpassword (String email , String password){
            SQLiteDatabase MyDB = this.getWritableDatabase();
            Cursor cursor= MyDB.rawQuery("select  * from users where email = ? and password = ?", new String[] {email,password});
            if (cursor.getCount()>0){
                return true;
            }
            else{
                return false;
            }
        }
    }
}