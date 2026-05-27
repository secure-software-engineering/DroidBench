package de.ecspride;

import android.os.Bundle;
import android.app.Activity;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

/**
 * @testcase_name FlowSensitivity1
 * @version 0.1
 * @author Secure Software Engineering Group (SSE),
 * 		European Center for Security and Privacy by Design (EC SPRIDE) 
 * @author_mail steven.arzt@cased.de
 * 
 * @description Sensitive data is assigned to a heap object. Some other object is
 * 		leaked, then an alias between the object containing the sensitive data and
 * 		the leaked one is created.
 * @dataflow source -> heap object -> nothing
 * @number_of_leaks 0
 * @challenges Aliases must be computed in a flow-sensitive fashion, otherwise a
 * 		false positive is found.
 */
public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		aliasFlowTest();
	}

	class A{
		public String b = "Y";
	}

	private A b = new A();
	
	private void aliasFlowTest() {
        TelephonyManager mgr = (TelephonyManager) this.getSystemService(TELEPHONY_SERVICE);
        String deviceId = mgr.getDeviceId();	// source
        
        A a = new A();
        a.b = deviceId;
        
        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage("+49 1234", null, b.b, null, null); // sink, leak
        
        b = a;
	}

}
