package edu.mit.clinit;

import android.app.Activity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;

/**
 * @testcase_name Clinit
 * 
 * @description Clinit (static initializer test)
 * @dataflow source -> sink
 * @number_of_leaks 1
 * @challenges - The order of execution of static initializers is not defined in Java.  This 
 * test stresses a particular order to link a flow.
 */
public class MainActivity extends Activity {
    public static MainActivity v;
    public String s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
	v = this;
	
	super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
	
        s = "";
        Test t = new Test();	//could call static initializer if has been called previously
                
        Log.i("DroidBench", s);  //sink, possible leak depending on runtime execution of Test's clinit
    }
}

class Test {
    static {
	TelephonyManager mgr = (TelephonyManager) MainActivity.v.getSystemService(Activity.TELEPHONY_SERVICE);
	MainActivity.v.s = mgr.getDeviceId();  //source
    }    
}
