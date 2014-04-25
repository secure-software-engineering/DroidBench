package lu.uni.serval.icc_bindservice3;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class InFlowService extends Service {

	@Override
	public IBinder onBind(Intent intent) {
		String imei = intent.getStringExtra("DroidBench");
		LocalBinder binder = new LocalBinder(imei);
		
		return binder;
	}

	class LocalBinder extends Binder
	{
		String imei;
		
		public LocalBinder(String imei)
		{
			this.imei = imei;
		}
		
		public String getDeviceId()
		{
			return imei;
		}
	}
}
