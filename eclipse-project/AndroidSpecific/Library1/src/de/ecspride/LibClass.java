package de.ecspride;

import android.content.Context;
import android.telephony.TelephonyManager;


/**
 * THIS IS NOT A TEST CASE ON ITS OWN. IT IS A PART OF LIBRARY2.
 * @author Steven Arzt
 */
public class LibClass {

	public String getIMEI(Context context) {
		TelephonyManager telephonyManager = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
		return telephonyManager.getDeviceId(); //source
	}
	
}
