package com.example.sinkintentonly;

import java.lang.reflect.Method;
import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @testcase_name OnlyTelephony
 * @version 0.1
 * @author Jyoti Gajrani, Malviya National Institute of Technology, Jaipur (INDIA) 
 * @author_mail jyotigajrani@gmail.com
 * 
 * @description Intent Send is not reflected only its receive is reflected by
 * passing intent as object. Intent is Explicit Intent.
 * @dataflow onCreate: source -> intent (imei) -> Activity2 -> sink
 * @number_of_leaks 1
 * @challenges The analysis must be able to handle reflective method invocation
 * and inter-component communication.
 */
public class MainActivity extends Activity {

	TelephonyManager telephonyManager;
	String id = null;
	TextView tv;
	Class<?> c = null;
	Method method;
	String int1;
	Object o;
	Class<?>[] param;
	Class<?> i;
	Intent it;
	ArrayList<Class> cl = new ArrayList<Class>();
	ArrayList<Context> co = new ArrayList<Context>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		tv = (TextView) findViewById(R.id.textView1);

		id = "android.telephony.TelephonyManager";
		int1 = "android.content.Intent";

		try {

			c = Class.forName(id);

			telephonyManager = (TelephonyManager) this
					.getSystemService(Context.TELEPHONY_SERVICE);
			method = c.getMethod("getDeviceId", new Class<?>[0]);
			Toast.makeText(this, "tele manager is executed", Toast.LENGTH_SHORT)
					.show();
			id = (String) method.invoke(telephonyManager);
			tv.setText(id);

		} catch (Exception e) {
			e.printStackTrace();
			tv.setText("there is error");
		}

		Intent i = new Intent(this, Activity2.class);
		i.putExtra("imeino", id);
		startActivity(i);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
