package com.example.securinzy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.opengl.GLDebugHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class signup extends AppCompatActivity {
    DBHelper DB;

    private EditText editTextFirstName;
    private EditText editTextSecondName;
    private EditText editTextTextEmailAddress;
    private EditText editTextTextPassword;
    private EditText editTextphonenumber;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        editTextFirstName = findViewById(R.id.editTextFirstName);
        editTextSecondName = findViewById(R.id.editTextSecondName);
        editTextTextEmailAddress = findViewById(R.id.editTextTextEmailAddress);
        editTextTextPassword = findViewById(R.id.editTextTextPassword);
        editTextphonenumber = findViewById(R.id.editTextphonenumber);

        Button back = findViewById(R.id.button_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ii = new Intent(signup.this, MainActivity.class);
                startActivity(ii);
            }
        });

        Button sign_up = findViewById(R.id.button);
        DB= new DBHelper(signup.this);
        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name_fi=editTextFirstName.getText().toString();
                String name_la=editTextSecondName.getText().toString();
                String ph_num = editTextphonenumber.getText().toString();
                String email = editTextTextEmailAddress.getText().toString();
                String password = editTextTextPassword.getText().toString();
                if (name_fi.equals("")||name_la.equals("")||ph_num.equals("")||email.equals("")||password.equals("")){
                    Toast.makeText(signup.this,"Please enter all the fields",Toast.LENGTH_SHORT).show();
                }
                else {
                    boolean checkemail = DB.checkemail(email);
                    if (checkemail==false){
                        boolean insert = DB.insertData(email,password,name_fi,name_la,ph_num);
                        if(insert==true){
                            Toast.makeText(signup.this,"Sign up successfully",Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(signup.this, SERVICES.class);
                            startActivity(i);
                        }
                        else {
                            Toast.makeText(signup.this,"Sign up failed",Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(signup.this,"Email already exists! please sign in",Toast.LENGTH_SHORT).show();
                    }

                }


            }
        });


    }


}