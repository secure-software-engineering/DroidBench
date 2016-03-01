package com.example.deviceid_orderedintent;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

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
