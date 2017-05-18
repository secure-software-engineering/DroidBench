package de.ecspride;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
/**
 * @testcase_name LogNoLeak
 * @version 0.1
 * @author Secure Software Engineering Group (SSE), European Center for Security and Privacy by Design (EC SPRIDE) 
 * @author_mail siegfried.rasthofer@cased.de
 * 
 * @description An untainted value is written to the log
 * @dataflow -
 * @number_of_leaks 0
 * @challenges the analysis has to be aware that no tainted value can reach the sink
 */
public class LogNoLeak extends Activity {
	private String notTainted = "";
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_no_leak);
        
        notTainted = "not tainted";
    } 
    
	public void onPause(){
		super.onPause();
        Log.i("TAG", notTainted); //sink, no leak
	}
}
