package com.example.onlytelephony;

import java.lang.reflect.Method;

import android.os.Bundle;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;

public class Activity2 extends Activity {

	String value,che;
	String any = null;
	TextView tv;
	
	Class<?> c = null;
	Method method;
	Method method2;
	String phoneNo;
	PendingIntent i1 = null;
	PendingIntent i2 = null;
	Object o;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_activity2);
		tv = (TextView) findViewById(R.id.textView1);
		//che = "android.telephony.SmsManager";
		che = "com.example.sms.send";
		
		Intent im = getIntent();
		  //value = im.getStringExtra("imei");
		  value= getIntent().getExtras().getString("imei");
		  Log.d("imeino", value);
		  
		 
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_activity2, menu);
		return true;
	}

}
