package org.cert.sendsms;

import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

public class Button1Listener implements OnClickListener {

	private final MainActivity act;
	
	public Button1Listener(MainActivity parentActivity) {
		this.act = parentActivity;
	}

	@Override
	public void onClick(View arg0) {
		Intent i = new Intent(Intent.ACTION_SEND);
		i.setType("text/plain");
		TelephonyManager tManager = (TelephonyManager) this.act.getSystemService(Context.TELEPHONY_SERVICE);
		String uid = tManager.getDeviceId();			// SOURCE
		i.putExtra("secret", uid);
		Log.i("SendSMS: ", "Sending implicit Intent with MIME data type text/plain: DeviceId "+uid);	// SINK
		this.act.startActivityForResult(i, 0);			// SINK
	}
}
