package edu.mit.icc_intent_component_name;

import lu.uni.serval.icc_startactivity1.R;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.content.ComponentName;

public class OutFlowActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
		String imei = telephonyManager.getDeviceId(); //source

		ComponentName cn = new ComponentName(this, "lu.uni.serval.icc_startactivity1.InFlowActivity");
		
		Intent i = new Intent();
		i.setComponent(cn);
		i.putExtra("DroidBench", imei);
		
		startActivity(i);
	}

}
