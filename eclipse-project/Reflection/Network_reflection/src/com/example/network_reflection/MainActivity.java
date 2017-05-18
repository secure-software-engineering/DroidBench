package com.example.network_reflection;


import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		BaseClass bc = null;
		TelephonyManager telephonyManager = null;
		
		
		try {
			bc = (BaseClass) Class.forName("com.example.network_reflection.ConcreteClass").newInstance();
			telephonyManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
			bc.imei = telephonyManager.getDeviceId(); // Source...
			String s = bc.imei;
			NetworkClass nc= new NetworkClass();
			nc.execute(s); // Temporary sink
				
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
}