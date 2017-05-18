package edu.mit.icc_action_string_operations;

import java.lang.reflect.Method;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.widget.Toast;

/**
 * @testcase_name OnlyTelephony
 * @version 0.1
 * @author Jyoti Gajrani, Malviya National Institute of Technology, Jaipur (INDIA) 
 * @author_mail jyotigajrani@gmail.com
 * 
 * @description Data is obtained and sent through an implicit intent The intent is
 * reflected. Source is getDeviceId and sink is Log.
 * @dataflow onCreate: source -> intent (imei) -> Activity2 -> sink
 * @number_of_leaks 1
 * @challenges The analysis must be able to handle reflective method invocation
 * and inter-component communication.
 */
public class OutFlowActivity extends Activity {

	String id = null;
	String int1, che;
	Object o;
	Class<?>[] param;
	Class<?> c;
	Intent it;
	Method method;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
		String imei = telephonyManager.getDeviceId(); // source

		int1 = "edu.mit.icc_action_string_operations.ACTION";
		che = "edu.mit.icc_action_string_operations.implicit";
		try {

			c = Class.forName(che);
			Object[] obj = { this, int1, imei };
			o = c.newInstance();
			Class<?> params[] = new Class[obj.length];
			for (int i = 0; i < obj.length; i++) {
				if (obj[i] instanceof String) {
					params[i] = String.class;
				} else if (obj[i] instanceof Context) {
					params[i] = Context.class;
				}
			}
			method = o.getClass().getMethod("rec_intent", params);
			method.invoke(o, obj);

		} catch (Exception e) {

			e.printStackTrace();
			Toast.makeText(getBaseContext(), "tjere is an error",
					Toast.LENGTH_SHORT).show();

		}

	}

}
