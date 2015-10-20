package edu.mit.to_components_share_memory;

import android.app.Activity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import edu.mit.to_components_share_memory.R;

public class AnotherActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         
        TelephonyManager mgr = (TelephonyManager) this.getSystemService(TELEPHONY_SERVICE);
        Singleton.v().s = mgr.getDeviceId();
    }
}
