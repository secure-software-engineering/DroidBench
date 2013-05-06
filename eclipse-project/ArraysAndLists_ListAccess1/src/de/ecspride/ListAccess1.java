package de.ecspride;

import java.util.LinkedList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;

public class ListAccess1 extends Activity {
	List<String> listData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_access1);
        
        listData = new LinkedList<String>();
		listData.add("not tainted");
		listData.add(((TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId());
		listData.add("neutral text");
		
		SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage("+49 1234", null, listData.get(0), null, null);  
    }
}
