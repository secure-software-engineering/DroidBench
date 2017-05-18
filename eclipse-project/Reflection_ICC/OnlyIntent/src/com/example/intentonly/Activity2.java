package com.example.intentonly;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.telephony.SmsManager;
import android.view.Menu;
import android.widget.TextView;

public class Activity2 extends Activity {

	String value,che;
	String phoneNo;
	TextView tv;
	SmsManager sm;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_activity2);
		tv = (TextView)findViewById(R.id.textView1);
		phoneNo = "555-4" ;
		Intent im = getIntent();
		value = im.getStringExtra("imeino");
		sm = SmsManager.getDefault();
		sm.sendTextMessage(phoneNo, null,value,null, null);
		tv.setText("message sent");
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_activity2, menu);
		return true;
	}

}
