package com.example.intentonly;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;

import android.R.string;
import android.net.MailTo;
import android.os.Bundle;
import android.provider.Settings;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @testcase_name OnlyTelephony
 * @version 0.1
 * @author Jyoti Gajrani, Malviya National Institute of Technology, Jaipur (INDIA) 
 * @author_mail jyotigajrani@gmail.com
 * 
 * @description Intent itself is reflected during send. Intent is Explicit. Sink is SMS.
 * @dataflow onCreate: source -> intent (imei) -> Activity2 -> sink
 * @number_of_leaks 1
 * @challenges The analysis must be able to handle reflective method invocation
 * and inter-component communication.
 */
public class MainActivity extends Activity {

	String ModelNumber, Board, Brand, Display, FingerPrint, ID, TAGS, Type,
			Product, Serial, AndroidVersion, imei, APILevel, Serialn, User,
			CodeName, INCREMENTAL, DEVICE, HARDWARE, HOST, MANUF;
	TextView text;

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
		TelephonyManager telephonyManager = (TelephonyManager) this
				.getSystemService(Context.TELEPHONY_SERVICE);
		id = telephonyManager.getDeviceId();

		cl.add(Activity2.class);
		co.add(MainActivity.this);

		int1 = "android.content.Intent";
		try {
			i = Class.forName(int1);
			Toast.makeText(this, "trying intent is executed",
					Toast.LENGTH_SHORT).show();
			Object[] obj = { "imeino", id };

			Constructor ctor = i.getConstructor(Context.class, Class.class);

			Class<?> params[] = new Class[obj.length];
			for (int i = 0; i < obj.length; i++) {
				if (obj[i] instanceof Integer) {
					params[i] = Integer.TYPE;
				} else if (obj[i] instanceof String) {
					params[i] = String.class;
				}
			}
			Object instanceOfTheClass = ctor.newInstance(co.get(0), cl.get(0));
			method = instanceOfTheClass.getClass()
					.getMethod("putExtra", params);
			method.invoke(instanceOfTheClass, obj);

			startActivity((Intent) instanceOfTheClass);

		} catch (ClassNotFoundException e) {

			tv.setText("there is error");
		} catch (Exception e) {
			// Unknown exception
			e.printStackTrace();
			tv.setText("there is error");

		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
