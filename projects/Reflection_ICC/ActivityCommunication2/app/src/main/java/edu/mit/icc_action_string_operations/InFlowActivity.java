package edu.mit.icc_action_string_operations;

import edu.mit.icc_action_string_operations.R;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class InFlowActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Intent i = getIntent();
		String imei = i.getStringExtra("DroidBench");
		//String imei = getIntent().getExtras().getString("DroidBench");
		Log.i("DroidBenchImplicitIntent", imei); //sink leak
		Toast.makeText(getBaseContext(),"this is another", 
                Toast.LENGTH_SHORT).show();
	}

	

}
