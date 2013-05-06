package de.ecspride;

import android.app.Activity;
import android.os.Bundle;
import android.telephony.SmsManager;

public class Activity1 extends Activity {
	public static String data1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity1);
        
        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage("+49", null, data1, null, null);   
    }    
}
