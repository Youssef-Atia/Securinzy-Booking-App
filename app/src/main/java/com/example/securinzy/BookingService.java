package com.example.securinzy;

import android.app.IntentService;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class BookingService extends IntentService {

    private Handler handler;

    public BookingService() {
        super("BookingService");
        handler = new Handler(Looper.getMainLooper());
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            long selectedDateInMillis = intent.getLongExtra("selectedDate", 0);

            // Simulate some background work
            SystemClock.sleep(5000);

            // Replace this with your actual service logic (e.g., network request)
            performNetworkRequest();
        }
    }

    private void performNetworkRequest() {
        // Replace this with your actual network request logic
        // This is just a basic example using AsyncTask
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... voids) {
                try {
                    // Example: make a simple HTTP GET request
                    URL url = new URL("https://www.example.com/api/booking");
                    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

                    InputStream in = urlConnection.getInputStream();
                    // Read the InputStream and perform any additional processing

                    return "Network request successful";
                } catch (IOException e) {
                    e.printStackTrace();
                    return "Network request failed";
                }
            }

            @Override
            protected void onPostExecute(String result) {
                // Display the result of the network request
                showToast(result);
            }
        }.execute();
    }

    private void showToast(final String message) {
        // Use Handler to post a Runnable to the main thread for showing the Toast
        handler.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(BookingService.this, message, Toast.LENGTH_SHORT).show();
            }
   });
}
}