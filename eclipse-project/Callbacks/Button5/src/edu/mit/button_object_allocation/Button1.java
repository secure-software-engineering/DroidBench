package edu.mit.button_object_allocation;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;

/**
 * @testcase_name Button-Object-Allocation
 * 
 * @description Test correct modeling of button object maintained by the runtime and delivered to onClick events.
 *  handler is defined via XML.
 * @number_of_leaks 1
 * @challenges Must correctly model that a Button is represented by a single object in the runtime, and that object 
 * is delivered to multiple calls of onClick
 */
public class Button1 extends Activity {
	private static String imei = null;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button1);
        
        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
	imei = telephonyManager.getDeviceId(); //source
    }

    public void sendMessage(View view){
	Log.i("DroidBench", ((Button)view).getHint().toString());  //sink on second call to sendMessage(), second click of button
	((Button)view).setHint(imei);  
    }
}
