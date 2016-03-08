package com.example.location_broadcast;

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
        
        Uri uri = Uri.parse("android.resource://com.example.location_broadcast/drawable/ic_launcher");
        im.setImageURI(uri);
        
        Intent in = new Intent("com.example.location_broadcast.location_broadcast");
        sendBroadcast(in);
    }
}
