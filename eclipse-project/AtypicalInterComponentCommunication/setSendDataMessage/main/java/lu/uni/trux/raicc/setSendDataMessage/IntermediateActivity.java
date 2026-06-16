package lu.uni.trux.raicc.setSendDataMessage;


import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;


public class IntermediateActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intermediate);
        String imei = this.getIntent().getStringExtra("DroidBench");
        Intent i = new Intent(this, TargetActivity.class);
        i.putExtra("DroidBench", imei);
        PendingIntent pi = PendingIntent.getActivity(this, 0, i, 0);
        AlarmManager am = ((AlarmManager) this.getSystemService(Context.ALARM_SERVICE));
        am.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() - 100, pi);
    }
}
