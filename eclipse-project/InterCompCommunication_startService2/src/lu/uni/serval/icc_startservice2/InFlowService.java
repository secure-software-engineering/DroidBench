package lu.uni.serval.icc_startservice2;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

public class InFlowService extends IntentService {

	public InFlowService()
	{
		super("ReceiverService");
	}
	
	@Override
	protected void onHandleIntent(Intent intent) 
	{
		String imei = intent.getStringExtra("DroidBench");
		Log.i("DroidBench", imei);
		
	}
}
