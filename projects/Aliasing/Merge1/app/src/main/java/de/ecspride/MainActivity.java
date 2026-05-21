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
 * @testcase_name Merge1
 * @version 0.1
 * @author Secure Software Engineering Group (SSE),
 * 		European Center for Security and Privacy by Design (EC SPRIDE) 
 * @author_mail steven.arzt@cased.de
 * 
 * @description Sensitive data is assigned to a heap object which is
 * 		then shuffled around. Only constant data is leaked.
 * @dataflow source -> no connection to sink
 * @number_of_leaks 0
 * @challenges Aliases must be computed precisely or a false positive
 * 		will be found.
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

	public class B{
		public A attr = new A();
	}

	private void aliasFlowTest() {
        TelephonyManager mgr = (TelephonyManager) this.getSystemService(TELEPHONY_SERVICE);
        String deviceId = mgr.getDeviceId();	// source
        
        A b, q, y;
		B a, p, x;
		
		a = new B();
		p = new B();
		
		b = new A();
		q = new A();
		
		if (Math.random() < 0.5) {
			x = a;
			y = b;
		}
		else {
			x = p;
			y = q;
		}
		x.attr = y;
		q.b = deviceId;

		SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage("+49 1234", null, a.attr.b, null, null); // sink, no leak
	}

}
