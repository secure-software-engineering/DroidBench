package lu.uni.serval.icc_bindservice3;

import lu.uni.serval.icc_bindservice3.InFlowService.LocalBinder;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.telephony.TelephonyManager;
import android.util.Log;

public class OutFlowActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
		String imei = telephonyManager.getDeviceId();
		
		Intent i = new Intent(this, InFlowService.class);
		i.putExtra("DroidBench", imei);
		bindService(i, mConnection, BIND_AUTO_CREATE);
	}

	InnerServiceConnection mConnection = new InnerServiceConnection(); 
	
	class InnerServiceConnection implements ServiceConnection
	{

		@Override
		public void onServiceConnected(ComponentName name, IBinder binder) 
		{
			LocalBinder lb = (LocalBinder) binder;
			InFlowService rs = lb.getServiceInstance();
			String imei = rs.getDeviceId();
			Log.i("DroidBench", imei);
			
			OutFlowActivity.this.unbindService(this);
		}

		@Override
		public void onServiceDisconnected(ComponentName arg0) 
		{
		}
	};
}
