package com.example.mnit.task15;

import android.Manifest;
import android.content.pm.PackageManager;
import android.content.ComponentName;
import android.content.Intent;
import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
    

        if (checkSelfPermission(Manifest.permission.READ_CALL_LOG) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        ComponentName name = new ComponentName("com.example.mnit.task15", "com.example.mnit.task15.ReceiverCall");

        Intent abc = new Intent();
        abc.setComponent(name);
        sendBroadcast(abc);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        ComponentName name = new ComponentName("com.example.mnit.task15", "com.example.mnit.task15.ReceiverCall");

        Intent abc = new Intent();
        abc.setComponent(name);
        sendBroadcast(abc);
        super.onStart();
    }

    @Override
    protected void onResume() {
        ComponentName name = new ComponentName("com.example.mnit.task15", "com.example.mnit.task15.ReceiverCall");

        Intent abc = new Intent();
        abc.setComponent(name);
        sendBroadcast(abc);
        super.onResume();
    }
}
