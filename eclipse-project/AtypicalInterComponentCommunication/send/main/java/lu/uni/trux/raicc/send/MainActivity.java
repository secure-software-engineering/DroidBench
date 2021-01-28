package lu.uni.trux.raicc.send;


import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String imei = ((TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId();
        Intent i = new Intent(this, TargetActivity.class);
        i.putExtra("DroidBench", imei);
        PendingIntent pi = PendingIntent.getActivity(this, 0, i, 0);
        try {
            pi.send(this, 0, null, null, null, null, null);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
