package com.example.deviceid_service;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;



public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        
       // Toast.makeText(MainActivity.this, "Service Started", Toast.LENGTH_LONG).show();
		startService(new Intent(MainActivity.this,Service_deviceid.class));
		stopService(new Intent(MainActivity.this,Service_deviceid.class));
		
		
    }
   

}
