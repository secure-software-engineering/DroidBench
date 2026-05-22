package com.example.location_service;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;

/**
 * @testcase_name Location_Service1
 * @version 0.1
 * @author Malaviya National Institute of Technology Jaipur, India 
 * @author_mail er.shwetabhandari@gmail.com
 * 
 * @description This app starts a service that obtains the location data and
 * sends it to the Collector app.
 * @dataflow location (longitude+latitude) -> Collector app 
 * @number_of_leaks 2
 * @challenges The analysis must correctly handle services as well as inter-app
 * communication through intents 
 */
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
