package lu.uni.serval.icc_bindservice2;

import lu.uni.serval.icc_bindservice2.InFlowService.LocalBinder;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

public class OutFlowActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Intent i = new Intent(this, InFlowService.class);
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
	}
}
