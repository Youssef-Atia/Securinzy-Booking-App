package com.example.securinzy;
import  android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("com.example.broadcast.TEST_ACTION")) {
            // Perform desired actions when the broadcast is received
            Toast.makeText(context, "Broadcast received!", Toast.LENGTH_SHORT).show();
        }
    }
}