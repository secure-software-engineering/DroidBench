package lu.uni.serval.icc_sendstickybroadcast1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class InFlowReceiver extends BroadcastReceiver 
{
	@Override
	public void onReceive(Context ctx, Intent intent) 
	{
		String imei = intent.getStringExtra("DroidBench");
		Log.i("DroidBench", imei);
		
		//The sticky broadcast need to explicitly remove it,
		//otherwise, it will keep in ctx all the time.
		ctx.removeStickyBroadcast(intent);
	}
}
