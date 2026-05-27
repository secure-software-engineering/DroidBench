package de.ecspride;

import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;

/**
 * @testcase_name HashMapAccess2
 * @version 0.1
 * @author Secure Software Engineering Group (SSE)
 * @author_mail marc.miltenberger@sit.fraunhofer.de
 * 
 * @description A hash map is filled with both tainted and untainted data. The tainted
 * 	data is then read out and sent via SMS.
 * @dataflow source -> map -> sink
 * @number_of_leaks 1
 * @challenges the analysis must distinguish between different hash map entries to recognize that the tainted
 *  data does get leaked. 
 */
public class HashMapAccess2 extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hash_map_access1);

		Map<String, String> hashMap = new HashMap<String, String>();
		hashMap.put("tainted", ((TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId()); //source
		hashMap.put("untainted", "Hello World");
		
		SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage("+49 1234", null, hashMap.get("tainted"), null, null);  //sink
	}


}
