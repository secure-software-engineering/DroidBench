package de.ecspride;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;

public class InheritedObjects1 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inherited_objects1);
        
        int a = 45 + 1;
		TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
		General g;
		if(a == 46){
			g = new VarA();
			g.man = telephonyManager;
		} else{
			g = new VarB();
			g.man = telephonyManager;
		}
		SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage("+49 1234", null, g.getInfo(), null, null);  
    }
}
