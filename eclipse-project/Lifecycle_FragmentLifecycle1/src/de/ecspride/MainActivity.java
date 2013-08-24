package de.ecspride;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.telephony.TelephonyManager;
import android.view.Menu;
/**
 * @testcase_name FragmentLifecycle1
 * @version 0.1
 * @author Secure Software Engineering Group (SSE), European Center for Security and Privacy by Design (EC SPRIDE) 
 * @author_mail siegfried.rasthofer@cased.de
 * 
 * @description The imei gets stored in a static variable of an activity which is accessed by a fragment. The fragment sends the imei via SMS. 
 * @dataflow source -> Mainactivity.imei -> ExampleFragment.imei -> sink
 * @number_of_leaks 1
 * @challenges the analysis has to be aware of the lifecycle methods of fragments. The fragment is not defined in xml but in the code.
 */
public class MainActivity extends FragmentActivity {
	public static String imei = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
		imei = telephonyManager.getDeviceId(); //source
		
		ExampleFragment newFragment = new ExampleFragment();
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.add(R.id.fragment_inside, newFragment);
		transaction.commit();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
