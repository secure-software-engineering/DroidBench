package com.example.location_broadcast;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;

/**
 * @testcase_name Location_Broadcast1
 * @version 0.1
 * @author Malaviya National Institute of Technology Jaipur, India 
 * @author_mail er.shwetabhandari@gmail.com
 * 
 * @description This app obtains the location data, and sends it to a broadcast
 * receiver in the same app. This broadcast receiver then sends the data to the
 * Collector app.
 * @dataflow location (longitude+latitude) -> broadcast receiver -> Collector app 
 * @number_of_leaks 2
 * @challenges The analysis must correctly handle broadcast receivers as well as
 * inter-app communication through intents 
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
        
        Uri uri = Uri.parse("android.resource://com.example.location_broadcast/drawable/ic_launcher");
        im.setImageURI(uri);
        
        Intent in = new Intent("com.example.location_broadcast.location_broadcast");
        sendBroadcast(in);
    }
}
