package mod.ndk;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

/**
 * @testcase_name JavaIDFunction
 * @version 0.1
 * @author Secure Software Engineering Group (SSE), European Center for Security and Privacy by Design (EC SPRIDE) 
 * @author_mail steven.arzt@cased.de
 * 
 * @description Sends tainted data from Java to Native to Java and back to native where it is leaked
 * @dataflow source -> imei -> native method -> Java idFunc method -> native -> leak in native
 * @number_of_leaks 1
 * @challenges the analysis must handle native code with callbacks back to Java
 */
public class ActMain extends Activity {
	static {
		System.loadLibrary("ndkmod");
	}

	// ___________________
	public native boolean cFuncDoTheMagic(Context c); // C with params
	// ___________________
	public String idFunc(String in) {
		return "foo_" + in + "_bar";
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lay_main);
		Button button = (Button) findViewById(R.id.button1);
		button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				cFuncDoTheMagic(getApplicationContext());
				Toast.makeText(ActMain.this, "ok", Toast.LENGTH_LONG).show();
			}
		});
	}
}