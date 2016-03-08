package com.example.deviceid_orderedintent;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * @testcase_name DeviceId_OrderedIntent1
 * @version 0.1
 * @author Malaviya National Institute of Technology Jaipur, India 
 * @author_mail er.shwetabhandari@gmail.com
 * 
 * @description The device id is obtained and sent to a broadcast receiver in the current
 * app. There are multiple broadcast receivers with different priorities. Only the higher-
 * priority receiver relays the data to the Collector app, the lower-priority receiver
 * only shows the data to the user (no leak).
 * @dataflow deviceid -> 
 * @number_of_leaks 1
 * @challenges The analysis must correctly handle broadcast receiver priorities as well
 * as inter-app communication through intents 
 */
public class MainActivity extends Activity
{
	String s = "DeviceId :";
	Button b1;
	Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context=this;
        b1 = (Button)findViewById(R.id.button1);
        
        b1.setOnClickListener(new OnClickListener()
        {

			@Override
			public void onClick(View arg0)
			{
				Intent in = new Intent("com.example.deviceid_orderedintent");
		        in.putExtra("data", s);
		        sendOrderedBroadcast(in, null);
			}
        	
        });
          
    }  
}
