package edu.mit;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import java.util.List;
import java.util.LinkedList;

import lu.uni.serval.icc_startactivity4.R;

public class OutFlowActivity extends Activity {


    @Override
	protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_main);
		
	TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
	String imei = telephonyManager.getDeviceId(); //source
		
	Intent i = new Intent(this, InFlowActivity.class);
	i.putExtra("DroidBench", imei);

	List<Intent> iList = new LinkedList<Intent>();
	iList.add(i);

	Intent i2 = iList.get(0);
		
	startActivity(i2);
    }
}
