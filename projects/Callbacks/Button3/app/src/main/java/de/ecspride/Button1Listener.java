package de.ecspride;

import android.content.Context;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Button1Listener implements OnClickListener {
	
	private final MainActivity act;
	
	public Button1Listener(MainActivity parentActivity) {
		this.act = parentActivity;
	}

	@Override
	public void onClick(View arg0) {
        TelephonyManager telephonyManager = (TelephonyManager) act.getSystemService(Context.TELEPHONY_SERVICE);
		act.imei = telephonyManager.getDeviceId(); //source

		Button button2 = (Button) act.findViewById(R.id.button2);
		button2.setOnClickListener(new Button2Listener(act));
		Log.i("TAG", "button1");
	}

}
