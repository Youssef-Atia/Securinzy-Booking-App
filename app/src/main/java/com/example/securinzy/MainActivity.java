package com.example.securinzy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DBHelper DB;
    EditText emailEditText;
    EditText passwordEditText;
    TextView signUpTextView;
    TextView help;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DB = new DBHelper(this);

        emailEditText = findViewById(R.id.editTextTextEmailAddress);
        passwordEditText = findViewById(R.id.editTextTextPassword);

        signUpTextView = findViewById(R.id.textView);
        signUpTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, signup.class);
                startActivity(intent);
            }
        });

        Button loginButton = findViewById(R.id.button);
        DB = new DBHelper(MainActivity.this);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                if (email.equals("") || password.equals("")) {
                    Toast.makeText(MainActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                } else {
                    boolean checkEmailPassword = DB.checkemailpassword(email, password);
                    if (checkEmailPassword) {
                        Toast.makeText(MainActivity.this, "Sign in successfully", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(MainActivity.this, SERVICES.class);
                        startActivity(i);

                        // Send the custom broadcast
                        Intent customBroadcastIntent = new Intent("com.example.securinzy.CUSTOM_ACTION");
                        sendBroadcast(customBroadcastIntent);
                    } else {
                        Toast.makeText(MainActivity.this, "Invalid E-mail or Password", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        help = findViewById(R.id.help);
        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://n26.com/en-eu/blog/what-is-freelancing";
                Intent x = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(x);
            }
        });
    }
}