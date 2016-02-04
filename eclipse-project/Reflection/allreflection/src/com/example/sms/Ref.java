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

	     // Dynamically do stuff with this class
	     // List constructors, fields, methods, etc.
	     tv.setText("this is going");

	 } catch (ClassNotFoundException e) {
	     // Class not found!
	 } catch (Exception e) {
	     // Unknown exception
	 }       
	 
	 
	 /*try {
	 
	             method = ma.getClass().getMethod("onCreate", new Class<?>[0]);
	 
	             method.invoke(ma);
	             -tv.setText("this is going");
	 
	         } catch (Exception e) {
	 
	             e.printStackTrace();
	             tv.setText("this not possible");
	 
	         }        */  

	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_ref, menu);
		return true;
	}

}
