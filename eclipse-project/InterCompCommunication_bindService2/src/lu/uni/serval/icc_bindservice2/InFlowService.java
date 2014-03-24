package lu.uni.serval.icc_bindservice2;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.telephony.TelephonyManager;

public class InFlowService extends Service {

	IBinder mBinder = new LocalBinder();
	
	public String getDeviceId() {
		TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
		String imei = telephonyManager.getDeviceId();
		return imei;
	}

	@Override
	public IBinder onBind(Intent intent) {
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
