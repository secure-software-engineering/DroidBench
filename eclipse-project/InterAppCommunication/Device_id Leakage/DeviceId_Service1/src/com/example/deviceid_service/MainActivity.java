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

/**
 * @testcase_name DeviceId_Service1
 * @version 0.1
 * @author Malaviya National Institute of Technology Jaipur, India 
 * @author_mail er.shwetabhandari@gmail.com
 * 
 * @description This app starts a service which sends the device id to the Collector app
 * where it is leaked.
 * @dataflow deviceid -> 
 * @number_of_leaks 1
 * @challenges The analysis must correctly handle services as well as inter-app
 * communication through intents 
 */
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
