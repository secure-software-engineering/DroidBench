package lu.uni.trux.raicc.requestNetwork;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class TargetService extends Service {
    public TargetService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int startId, int ii) {
        super.onStartCommand(intent, startId, ii);
        String imei = intent.getStringExtra("DroidBench");
        Log.i("DroidBench", imei);
        return 0;
    }
}
