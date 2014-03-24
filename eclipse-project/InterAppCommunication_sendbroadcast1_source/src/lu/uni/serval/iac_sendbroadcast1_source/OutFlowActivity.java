package lu.uni.serval.iac_sendbroadcast1_source;

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
		i.setAction("lu.uni.serval.iac_sendbroadcast1.ACTION");
		i.putExtra("DroidBench", imei);
		
		sendBroadcast(i);
	}

}
