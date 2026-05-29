package de.ecspride;

import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;

/**
 * @testcase_name HashMapAccess1
 * @version 0.1
 * @author Secure Software Engineering Group (SSE), European Center for Security and Privacy by Design (EC SPRIDE) 
 * @author_mail steven.arzt@cased.de
 * 
 * @description A hash map is filled with both tainted and untainted data. The untainted
 * 	data is then read out and sent via SMS.
 * @dataflow -
 * @number_of_leaks 0
 * @challenges the analysis must distinguish between different hash map entries to recognize that the tainted
 *  data does not get leaked. 
 */
public class HashMapAccess1 extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hash_map_access1);

		Map<String, String> hashMap = new HashMap<String, String>();
		hashMap.put("tainted", ((TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId()); //source
		hashMap.put("untainted", "Hello World");
		
		SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage("+49 1234", null, hashMap.get("untainted"), null, null);  //sink, no leak
	}


}
