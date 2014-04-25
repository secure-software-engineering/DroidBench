package lu.uni.serval.iac_sendbroadcast1_sink;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class IsolateReceiver extends BroadcastReceiver 
{
	@Override
	public void onReceive(Context ctx, Intent intent) 
	{
		String imei = intent.getStringExtra("DroidBench");
		Log.i("DroidBench", imei);
	}
}
