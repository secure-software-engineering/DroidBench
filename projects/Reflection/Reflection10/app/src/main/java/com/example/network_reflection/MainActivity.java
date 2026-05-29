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

/**
 * @testcase_name Reflection10
 * @version 0.1
 * @author Contributed by Jyoti Gajrani 
 * 
 * @description Reads the telephone number, passes it to an AsyncTask
 * 		which uses reflection to store it in an object.
 * 		It then uses reflection to read the telephone number from the object and
 *      sends it via reflection to the internet
 * @dataflow getLine1Number -> execute -> doInBackground -> ConcreteClass -> Internet via Method.invoke
 * @number_of_leaks 1
 * @challenges AsyncTask data flow and reflection must be handled.
 */
public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		BaseClass bc = null;
		TelephonyManager telephonyManager = null;
		if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
		    if (checkSelfPermission(android.Manifest.permission.READ_PHONE_STATE) 
        	    != android.content.pm.PackageManager.PERMISSION_GRANTED) {
        		requestPermissions(new String[]{android.Manifest.permission.READ_PHONE_STATE}, 0);
				return;
			}
		}
		android.util.Log.i("DroidBench", "Has Permission");

		
		try {
			bc = (BaseClass) Class.forName("com.example.network_reflection.ConcreteClass").newInstance();
			telephonyManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
			bc.line1 = telephonyManager.getLine1Number(); // Source
			String s = bc.line1;
			if (s == null)
				s = "<NO ID>";
			NetworkClass nc= new NetworkClass();
			nc.execute(s); // Temporary sink
				
		} catch (Exception e) {
			android.util.Log.e("DroidBench", "Exception", e);
		}
		
	}
	
}