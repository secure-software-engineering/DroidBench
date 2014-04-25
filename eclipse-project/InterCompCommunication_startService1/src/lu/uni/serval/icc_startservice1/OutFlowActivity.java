package lu.uni.serval.icc_startservice1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;

public class OutFlowActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
		String imei = telephonyManager.getDeviceId();
		
		Intent i = new Intent(this, InFlowService.class);
		i.putExtra("DroidBench", imei);
		
		startService(i);
	}
}
