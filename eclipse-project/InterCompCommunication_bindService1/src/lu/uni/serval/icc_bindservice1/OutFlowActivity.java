package lu.uni.serval.icc_bindservice1;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.telephony.TelephonyManager;

public class OutFlowActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
		String imei = telephonyManager.getDeviceId(); //source
		
		Intent i = new Intent(this, InFlowService.class);
		i.putExtra("DroidBench", imei);
		bindService(i, mConnection, BIND_AUTO_CREATE);
		
		unbindService(mConnection);
	}

	InnerServiceConnection mConnection = new InnerServiceConnection(); 
	
	class InnerServiceConnection implements ServiceConnection
	{
		@Override
		public void onServiceConnected(ComponentName name, IBinder binder) 
		{
		}

		@Override
		public void onServiceDisconnected(ComponentName arg0) 
		{
		}
	};
}
