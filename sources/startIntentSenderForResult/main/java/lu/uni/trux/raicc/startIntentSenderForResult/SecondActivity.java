package lu.uni.trux.raicc.startIntentSenderForResult;


import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;


public class SecondActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        String imei = this.getIntent().getStringExtra("DroidBench");
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("DroidBench", imei);
        PendingIntent pi = PendingIntent.getActivity(this, 0, intent, 0);
        try {
            pi.send(this, 0, null, null, null, null, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_second);
//        String imei = this.getIntent().getStringExtra("DroidBench");
//        Log.i("DroidBench", imei);
    }
}
