package lu.uni.serval.icc_insert1;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.TelephonyManager;

public class OutFlowActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		String URL = "content://lu.uni.serval.icc_insert1/deviceid";
		Uri uri = Uri.parse(URL);
		
		TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
		String imei = telephonyManager.getDeviceId(); //source
		
		ContentValues cv = new ContentValues();
		cv.put("DroidBench", imei);
		getContentResolver().insert(uri, cv);
	}

}
