package com.example.location_service;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;


public class MainActivity extends Activity
{

	ImageView im;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        im = (ImageView)findViewById(R.id.imageView1);
        
        Uri uri = Uri.parse("android.resource://com.example.location_service/drawable/ic_launcher");
        im.setImageURI(uri);
        
        startService(new Intent(MainActivity.this,Locationservice.class));
		stopService(new Intent(MainActivity.this,Locationservice.class));
    }
}
