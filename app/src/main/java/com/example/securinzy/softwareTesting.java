package com.example.securinzy;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class softwareTesting extends AppCompatActivity {

    private Calendar selectedDate;
    Button bookButton;
    ImageView backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_software_testing);

        // Button setup
        bookButton = findViewById(R.id.book2);

        // ImageView setup for back button
        backButton = findViewById(R.id.button_back);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle back button click by finishing the current activity
                finish();
            }
        });

        // Date picker setup
        EditText etDate = findViewById(R.id.etDate);
        etDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker();
            }
        });

        // Button click listener
        bookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showConfirmationDialog();
                // Start the service when the button is clicked
                startBookingService();
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

    private void startBookingService() {
        // Example service, replace with your actual service implementation
        Intent serviceIntent = new Intent();
        serviceIntent.putExtra("selectedDate", selectedDate.getTimeInMillis());

        // Use startForegroundService for foreground services
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            startForegroundService(serviceIntent);
        } else {
            startService(serviceIntent);
}
}}