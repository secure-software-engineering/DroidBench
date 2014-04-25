package lu.uni.serval.icc_bindservice1;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class IsolateService extends Service {
	
	IBinder mBinder = new LocalBinder();
	
	public String getDeviceId() {
		return "device-id";
	}

	@Override
	public IBinder onBind(Intent intent) {
		String imei = intent.getStringExtra("DroidBench");
		Log.i("DroidBench", imei);
		
		return mBinder;
	}

	class LocalBinder extends Binder
	{
		public IsolateService getServiceInstance()
		{
			return IsolateService.this;
		}
	}
}
