package lu.uni.trux.raicc.sendTextMessage2;


import android.app.Activity;
import android.os.Bundle;
import android.util.Log;


public class TargetActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_target);
        String extra = this.getIntent().getStringExtra("Extra");
        Log.i("DroidBench", extra);
    }
}
