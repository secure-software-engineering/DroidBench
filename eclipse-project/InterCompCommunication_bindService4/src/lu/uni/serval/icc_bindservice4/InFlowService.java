package lu.uni.serval.icc_bindservice4;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class InFlowService extends Service {

	String imei = "";
	IBinder mBinder = new LocalBinder();
	
	public String getDeviceId() {
		return imei;
	}

	@Override
	public IBinder onBind(Intent intent) {
		imei = intent.getStringExtra("DroidBench");
		Log.i("DroidBench", imei);
		
		return mBinder;
	}

	class LocalBinder extends Binder
	{
		public InFlowService getServiceInstance()
		{
			return InFlowService.this;
		}
	}
}
