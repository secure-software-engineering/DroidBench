package edu.mit.icc_action_string_operations;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

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
		Log.i("DroidBenchImplicitIntent", imei);
		Toast.makeText(getBaseContext(),"tjere is an error", 
                Toast.LENGTH_SHORT).show();
	}
	
}
