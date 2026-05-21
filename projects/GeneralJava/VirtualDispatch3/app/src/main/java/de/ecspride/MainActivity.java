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
 * @testcase_name FactoryMethod1
 * @version 0.1
 * @author Secure Software Engineering Group (SSE),
 * 		European Center for Security and Privacy by Design (EC SPRIDE) 
 * @author_mail steven.arzt@cased.de
 * 
 * @description Two classes implement an interface, but only one of them
 * 		returns sensitive data. The leak however happens on the other
 * 		implementation that only returns constant data.
 * @dataflow source -> no connection to sink
 * @number_of_leaks 0
 * @challenges The callgraph analysis must be able to deal with factory
 * 		methods.
 */
public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		factoryTest();
	}
	
	private void factoryTest() {
		MyInterface myif = createInterfaceImplementation();
		String data = myif.getString();
		SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage("+49 1234", null, data, null, null); // sink, no leak
        
        MyInterface foo = createOtherImplementation();
        System.out.println(foo);
	}

	private MyInterface createOtherImplementation() {
		return new A();
	}

	private MyInterface createInterfaceImplementation() {
		return new B();
	}

	interface MyInterface {
		String getString();
	}
	
	class A implements MyInterface {

		@Override
		public String getString() {
	        TelephonyManager mgr = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
	        return mgr.getDeviceId();	// source
		}
		
	}
	
	class B implements MyInterface {

		@Override
		public String getString() {
			return "constant";
		}
		
	}
	
}
