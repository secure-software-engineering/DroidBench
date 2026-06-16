package lu.uni.trux.raicc.startIntentSenderForResult;


import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String imei = this.getIntent().getStringExtra("DroidBench");
        if(imei == null){
            imei = ((TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId();
            Intent i = new Intent(this, SecondActivity.class);
            i.putExtra("DroidBench", imei);
            PendingIntent pi = PendingIntent.getActivity(this, 0, i, 0);
            IntentSender is = pi.getIntentSender();
            try {
                this.startIntentSenderForResult(is, 0, null, 0, 0, 0);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            Log.i("DroidBench", imei);
        }
    }
}
