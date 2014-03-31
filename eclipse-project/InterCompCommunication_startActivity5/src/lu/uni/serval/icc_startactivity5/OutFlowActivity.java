package lu.uni.serval.icc_startactivity5;

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
		String imei = telephonyManager.getDeviceId(); //source
		
		Intent i = new Intent();
		i.setAction("lu.uni.serval.icc_startactivity4.ACTION");
		i.setType("text/plain");
		i.putExtra("DroidBench", imei);
		
		startActivity(i);
	}

}
