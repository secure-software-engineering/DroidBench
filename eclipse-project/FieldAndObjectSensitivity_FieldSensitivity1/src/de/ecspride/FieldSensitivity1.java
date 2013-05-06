package de.ecspride;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;

public class FieldSensitivity1 extends Activity {
	private Datacontainer d1;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_field_sensitivity1);
        
        setTaint(d1);
		sendTaint();
    }

	private void setTaint(Datacontainer data){
		data = new Datacontainer();
		data.setDescription("abc");
		TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
		data.setSecret(telephonyManager.getSimSerialNumber());
	}
	
	private void sendTaint(){
		SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage("+49 1234", null, d1.getDescription(), null, null); 
	}
    
}
