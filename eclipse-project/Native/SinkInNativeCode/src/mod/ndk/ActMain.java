package mod.ndk;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

/**
 * @testcase_name SinkInNativeCode
 * @version 0.1
 * @author Secure Software Engineering Group (SSE), European Center for Security and Privacy by Design (EC SPRIDE) 
 * @author_mail steven.arzt@cased.de
 * 
 * @description Obtains the IMEI in Java code and leaks it in native code by calling back to the Java-based Android API
 * @dataflow source -> imei -> native method -> leak in native
 * @number_of_leaks 1
 * @challenges the analysis must handle native method invocations
 */
public class ActMain extends Activity {
	static {
		System.loadLibrary("ndkmod");
	}

	// ___________________
	public native boolean cFuncSendSMS(String message); // Source in C code
	// ___________________

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lay_main);
		Button button = (Button) findViewById(R.id.button1);
		button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
				String strIMEI = telephonyManager.getDeviceId();
				Toast.makeText(ActMain.this, "ok: " + cFuncSendSMS(strIMEI), Toast.LENGTH_LONG).show();
			}
		});
	}
}