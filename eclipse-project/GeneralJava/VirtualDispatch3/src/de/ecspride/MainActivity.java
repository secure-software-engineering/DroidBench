package de.ecspride;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
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
public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
		
		factoryTest();
	}
	
	private void factoryTest() {
		MyInterface myif = createInterfaceImplementation();
		String data = myif.getString();
		SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage("+49 1234", null, data, null, null); // sink, leak
        
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
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
	}

}
