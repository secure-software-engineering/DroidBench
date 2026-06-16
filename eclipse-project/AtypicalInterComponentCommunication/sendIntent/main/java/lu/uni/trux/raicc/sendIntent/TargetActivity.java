package lu.uni.trux.raicc.sendIntent;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;


public class TargetActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_target);
        String imei = this.getIntent().getStringExtra("extra");
        Log.i("DroidBench", imei);
    }
}
