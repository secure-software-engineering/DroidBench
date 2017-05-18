package com.example.sms;

import java.lang.reflect.Method;
import android.os.Bundle;
import android.app.Activity;
import android.app.PendingIntent;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.view.Menu;
import android.widget.TextView;

public class Activity2 extends Activity {

	String value, che;
	String any = null;
	TextView tv;
	SmsManager smsManager;
	Class<?> c = null;
	Method method;
	Method method2;
	String phoneNo;
	PendingIntent i1 = null;
	PendingIntent i2 = null;
	Object o;
	int l;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_activity2);
		tv = (TextView) findViewById(R.id.textView1);
		che = "com.example.sms.send";
		phoneNo = "555-4";
		value = getIntent().getExtras().getString("imeino");
		tv.setText(value);
		try {

			Object[] obj = { "555-4", value };
			c = Class.forName(che);
			o = c.newInstance();
			Class<?> params[] = { String.class, String.class };
			for (int i = 0; i < obj.length; i++) {
				if (obj[i] instanceof Integer) {
					params[i] = Integer.TYPE;
				} else if (obj[i] instanceof String) {
					params[i] = String.class;
				}
				l++;
			}

			method = o.getClass().getMethod("se", params);

			method.invoke(o, obj);
		} catch (Exception e) {
			e.printStackTrace();
			tv.setText("there is error");
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_activity2, menu);
		return true;
	}

}
