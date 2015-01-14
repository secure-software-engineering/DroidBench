package edu.mit.icc_intent_component_name;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

/*
 * This class is used to make sure that privacy detecting tools can distinguish the different components.
 */
public class IsolateActivity extends Activity 
{

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		
		Intent i = getIntent();
		String imei = i.getStringExtra("DroidBench");
		Log.i("DroidBench", imei);
	}
	
}
