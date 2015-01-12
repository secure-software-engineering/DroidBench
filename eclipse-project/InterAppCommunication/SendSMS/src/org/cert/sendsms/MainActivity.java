package org.cert.sendsms;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.Menu;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button button1 = (Button) findViewById(R.id.button1);
		button1.setOnClickListener(new Button1Listener(this));
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {				// SOURCE
	  if (resultCode == 0 && requestCode == 0 && data != null) {
	    if (data.hasExtra("secret")) {
	    	if(data.getExtras().getString("secret") != null){
	    		Log.v("In SendSMS: ", "Data recieved");	    		
	    		sendSMSMessage(data.getExtras().getString("secret"));
	    	}
	    	else
	    		Log.i("In SendSMS: ", "Data recieved");
	    }
	  }
	  else
  		Log.i("In SendSMS: ", "No data recieved");
		  
	}
	
	@SuppressLint("UnlocalizedSms")
	protected void sendSMSMessage(String message) {
	  try {
	     SmsManager smsManager = SmsManager.getDefault();
	     smsManager.sendTextMessage("1234567890", null, message, null, null);	// SINK
	     Toast.makeText(getApplicationContext(), "SMS sent!", Toast.LENGTH_LONG).show();
	  } catch (Exception e) {
		  Toast.makeText(getApplicationContext(), "Couldn't send SMS!", Toast.LENGTH_LONG).show();
		  e.printStackTrace();
	  }
	}

}
