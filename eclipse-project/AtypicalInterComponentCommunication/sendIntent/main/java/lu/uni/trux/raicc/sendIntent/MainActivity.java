package lu.uni.trux.raicc.sendIntent;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.telephony.TelephonyManager;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String imei = ((TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId();
        Intent i = new Intent("ACTION");
        i.putExtra("DroidBench", imei);
        i.putExtra("extra", "noLeak");
        PendingIntent pi = PendingIntent.getActivity(this, 0, i, 0);
        IntentSender is = pi .getIntentSender();
        try{
            is.sendIntent(this, 0, null, null, null);
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
