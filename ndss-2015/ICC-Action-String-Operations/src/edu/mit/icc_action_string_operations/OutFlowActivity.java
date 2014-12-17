package edu.mit.icc_action_string_operations;

import java.util.List;
import java.util.LinkedList;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import java.util.Random;

import edu.mit.icc_action_string_operations.R;

public class OutFlowActivity extends Activity {


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
		String imei = telephonyManager.getDeviceId(); //source
		Intent i = new Intent("ignore.edu.mit.icc_action_string_operations.ACTION".substring(7))
		i.putExtra("DroidBench", imei);
		
		startActivity(i);
	}

}
