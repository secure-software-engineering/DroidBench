package com.example.mnit.task14;

import android.content.ComponentName;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ComponentName name = new ComponentName("com.example.mnit.task14", "com.example.mnit.task14.ServiceCall");

        Intent abc = new Intent();
        abc.setComponent(name);
        ComponentName c = getApplication().startService(abc);
        if (c == null) {
            Log.e("error", "failed to start with " + abc);
        }


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    protected void onResume() {
        ComponentName name = new ComponentName("com.example.mnit.task14", "com.example.mnit.task14.ServiceCall");

        Intent abc = new Intent();
        abc.setComponent(name);
        ComponentName c = getApplication().startService(abc);
        if (c == null) {
            Log.e("error", "failed to start with " + abc);
        }


        super.onResume();

    }

    @Override
    protected void onStart() {
        ComponentName name = new ComponentName("com.example.mnit.task14", "com.example.mnit.task14.ServiceCall");

        Intent abc = new Intent();
        abc.setComponent(name);
        ComponentName c = getApplication().startService(abc);
        if (c == null) {
            Log.e("error", "failed to start with " + abc);
        }
        super.onStart();
    }
}
