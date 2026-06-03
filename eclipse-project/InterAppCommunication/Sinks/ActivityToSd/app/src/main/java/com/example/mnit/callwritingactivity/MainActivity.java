package com.example.mnit.callwritingactivity;

import android.content.Intent;
import android.os.Environment;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.io.File;
import java.io.FileOutputStream;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            Intent intent = getIntent();
            File sdCard = Environment.getExternalStorageDirectory();
            File dir = new File(sdCard.getAbsolutePath() + "/sinks");
            dir.mkdirs();
            File file = new File(dir, "sink-activity.txt");
            String sb = (String) intent.getSerializableExtra("destination");
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(sb.getBytes());
            fos.close();


        } catch (Exception e) {

        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
