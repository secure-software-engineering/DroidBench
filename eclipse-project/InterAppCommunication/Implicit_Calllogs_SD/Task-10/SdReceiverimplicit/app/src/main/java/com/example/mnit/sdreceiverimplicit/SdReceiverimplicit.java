package com.example.mnit.sdreceiverimplicit;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;

public class SdReceiverimplicit extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        try {

            File sdCard = Environment.getExternalStorageDirectory();
            File dir = new File(sdCard.getAbsolutePath() + "/dir1");
            dir.mkdirs();
            File file = new File(dir, "callbroadcast.txt");
            String sb = (String) intent.getSerializableExtra("dest");
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(sb.getBytes());
            fos.close();

        } catch (Exception e) {

        }
    }

}
