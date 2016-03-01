package com.example.deviceid_broadcast;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;

 public class MainActivity extends Activity {

	//ImageView im;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //im = (ImageView)findViewById(R.id.imageView1);
        
       // Uri uri = Uri.parse("android.resource://com.example.deviceid_broadcast/drawable/ic_launcher");
       // im.setImageURI(uri);
        
        Intent in = new Intent("com.example.deviceid_broadcast.broadcast_deviceid");
        sendBroadcast(in);
    }  
}
