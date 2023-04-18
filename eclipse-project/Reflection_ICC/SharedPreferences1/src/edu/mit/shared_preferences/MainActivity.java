package edu.mit.shared_preferences;

import java.lang.reflect.Method;

import android.R.integer;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

/**
 * @testcase_name OnlyTelephony
 * @version 0.1
 * @author Jyoti Gajrani, Malviya National Institute of Technology, Jaipur (INDIA) 
 * @author_mail jyotigajrani@gmail.com
 * 
 * @description Reflection in use of sharedpreference is done. Sink is Log
 * @dataflow onCreate: source -> intent (imei) -> Activity2 -> sink
 * @number_of_leaks 1
 * @challenges The analysis must be able to handle reflective method invocation
 * and inter-component communication.
 */
public class MainActivity extends Activity {

	String id = null;
	String int1, che;
	Object o;
	Class<?>[] param;
	Class<?> c;
	Intent it;
	Method method;

	public static final String PREFS_NAME = "MyPrefsFile";
	public static String im = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		TelephonyManager mgr = (TelephonyManager) this
				.getSystemService(TELEPHONY_SERVICE);
		String imei = mgr.getDeviceId();
		// AnotherActivity at = new AnotherActivity();
		SharedPreferences settings = getSharedPreferences("MyPrefsFile", 0);
		che = "edu.mit.shared_preferences.write";
		try {

			c = Class.forName(che);
			Object[] obj = { settings, imei };
			o = c.newInstance();
			Class<?> params[] = new Class[obj.length];
			for (int i = 0; i < obj.length; i++) {
				if (obj[i] instanceof String) {
					params[i] = String.class;
				} else if (obj[i] instanceof SharedPreferences) {
					params[i] = SharedPreferences.class;
				}
			}
			method = o.getClass().getMethod("write_pref", params);
			method.invoke(o, obj);
		} catch (Exception e) {

			e.printStackTrace();
			Toast.makeText(getBaseContext(), "tjere is an error",
					Toast.LENGTH_SHORT).show();

		}

		Intent i = new Intent(this, AnotherActivity.class);

		startActivity(i);
	}

	public String give() {
		return im;

	}

}
