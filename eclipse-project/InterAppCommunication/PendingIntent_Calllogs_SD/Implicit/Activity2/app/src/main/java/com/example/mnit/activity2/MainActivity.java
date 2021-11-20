package com.example.mnit.activity2;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.File;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            Intent intent = getIntent();
            File sdCard = Environment.getExternalStorageDirectory();
            File dir = new File(sdCard.getAbsolutePath() + "/dir1");
            dir.mkdirs();
            File file = new File(dir, "calls.txt");
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