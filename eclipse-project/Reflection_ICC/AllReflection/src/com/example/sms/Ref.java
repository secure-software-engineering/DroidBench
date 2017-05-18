package com.example.sms;

import java.lang.reflect.Method;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;

public class Ref extends Activity {

	
	TextView tv;
	String res;
	@SuppressWarnings("unused")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ref);
	tv= (TextView)findViewById(R.id.textView1);
	 String sClassName = "android.app.Activity2";
	 try {
	     Class<?> classToInvestigate = Class.forName(sClassName); 
	     tv.setText("this is going");

	 } catch (ClassNotFoundException e) {
	     // Class not found!
	 } catch (Exception e) {
	     // Unknown exception
	 }       
	 
	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_ref, menu);
		return true;
	}

}
