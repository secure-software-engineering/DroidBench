package edu.mit.icc_component_not_in_manifest;

import edu.mit.icc_component_not_in_manifest.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class InFlowActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Intent i = getIntent();
		String imei = i.getStringExtra("DroidBench");
		Log.i("DroidBench", imei);
	}

}
