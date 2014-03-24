package lu.uni.serval.iac_startservice1_sink;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class InFlowService extends Service {

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		String imei = intent.getStringExtra("DroidBench");
		Log.i("DroidBench", imei);

		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}

	
}
