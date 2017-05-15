package com.example.mnit.tuesday;

import android.app.Service;
import android.content.Intent;
import android.os.Environment;
import android.os.IBinder;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;

public class Service21 extends Service {
    private static final String TAG = "MyService";

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "Service created.");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        try {

            File sdCard = Environment.getExternalStorageDirectory();
            File dir = new File (sdCard.getAbsolutePath() + "/dir1");
            dir.mkdirs();
            File file = new File(dir, "calllogs.txt");
            String sb=(String)intent.getSerializableExtra("destination");
            FileOutputStream fos=new FileOutputStream(file);
            fos.write(sb.getBytes());
            fos.close();

        }        catch (Exception e)
        {

        }

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.d("slog", "onDestroy()");
        super.onDestroy();
    }


}