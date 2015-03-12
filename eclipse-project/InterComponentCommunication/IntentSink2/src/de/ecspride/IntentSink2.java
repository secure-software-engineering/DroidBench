package de.ecspride;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.EditText;
/**
 * @testcase_name IntentSink2
 * @version 0.1
 * @author Secure Software Engineering Group (SSE), European Center for Security and Privacy by Design (EC SPRIDE) 
 * @author_mail siegfried.rasthofer@cased.de
 * 
 * @description A callback method is called via user interaction. It starts a new Activity based on user input and
 *  leaks a tainted value. 
 * @dataflow startIntent: source -> deviceId -> intent -> sink
 * @number_of_leaks 1
 * @challenges the analysis must be able to parse the layout xml file correctly,
 *  track the taint in the intent and recognize the startActivity sink. 
 */
public class IntentSink2 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_sink2);
    }

    //callback method defined in XML:
	public void startIntent(View view){
		EditText appName = (EditText) findViewById(R.id.name);
		
		TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        String deviceId = tm.getDeviceId(); //source
        
        Intent intent=new Intent();
		intent.setClassName(this,appName.getText().toString());
		intent.putExtra("id",deviceId); 
		startActivity(intent); //sink, leak
	}
    
}
