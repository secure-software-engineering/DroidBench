package com.example.mnit.task30;

import android.content.ComponentName;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onStart() {
        ComponentName name = new ComponentName("com.example.mnit.task30", "com.example.mnit.task30.ServiceCall");

        Intent abc = new Intent();
        abc.setComponent(name);
        ComponentName c = getApplication().startService(abc);
        if (c == null) {
            Log.e("error", "failed to start with " + abc);
        }
        super.onStart();
    }

    @Override
    protected void onResume() {
        ComponentName name = new ComponentName("com.example.mnit.task30", "com.example.mnit.task30.ServiceCall");

        Intent abc = new Intent();
        abc.setComponent(name);
        ComponentName c = getApplication().startService(abc);
        if (c == null) {
            Log.e("error", "failed to start with " + abc);
        }
        super.onResume();
    }
}