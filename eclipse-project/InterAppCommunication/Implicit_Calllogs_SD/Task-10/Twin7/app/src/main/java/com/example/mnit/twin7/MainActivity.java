package com.example.mnit.twin7;

import android.Manifest;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.provider.CallLog;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.Serializable;
import java.util.Date;

public class MainActivity extends AppCompatActivity {


        @Override
        protected void onCreate(Bundle savedInstanceState) {

            final StringBuffer sb1 = getCallDetails();
                         Intent intent = new Intent();
                        intent.setAction("com.example.mnit.twin7.MainActivity");
                        intent.putExtra("dest", (Serializable) sb1);
                        sendBroadcast(intent);
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
        }

    @Override
    protected void onStart() {
        final StringBuffer sb1 = getCallDetails();
        Intent intent = new Intent();
        intent.setAction("com.example.mnit.twin7.MainActivity");
        intent.putExtra("dest", (Serializable) sb1);
        sendBroadcast(intent);
        super.onStart();
    }

    @Override
    protected void onResume() {
        final StringBuffer sb1 = getCallDetails();
        Intent intent = new Intent();
        intent.setAction("com.example.mnit.twin7.MainActivity");
        intent.putExtra("dest", (Serializable) sb1);
        sendBroadcast(intent);
        super.onResume();
    }

    private StringBuffer getCallDetails() {
            StringBuffer sb = new StringBuffer();
            String strOrder = CallLog.Calls.DATE + " DESC";


            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CALL_LOG) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.

            }
            Cursor managedCursor = getContentResolver().query(CallLog.Calls.CONTENT_URI, null,
                    null, null, strOrder);
            int number = managedCursor.getColumnIndex(CallLog.Calls.NUMBER);
            int type = managedCursor.getColumnIndex(CallLog.Calls.TYPE);
            int date = managedCursor.getColumnIndex(CallLog.Calls.DATE);
            int duration = managedCursor.getColumnIndex(CallLog.Calls.DURATION);
            sb.append("Call Log :");
            int h = 0;
            while (managedCursor.moveToNext() && h != 20) {
                String phNum = managedCursor.getString(number);
                String callTypeCode = managedCursor.getString(type);
                String strcallDate = managedCursor.getString(date);
                Date callDate = new Date(Long.valueOf(strcallDate));
                String callDuration = managedCursor.getString(duration);
                String callType = null;
                h++;
                int callcode = Integer.parseInt(callTypeCode);
                switch (callcode) {
                    case CallLog.Calls.OUTGOING_TYPE:
                        callType = "Outgoing";
                        break;
                    case CallLog.Calls.INCOMING_TYPE:
                        callType = "Incoming";
                        break;
                    case CallLog.Calls.MISSED_TYPE:
                        callType = "Missed";
                        break;
                }
                sb.append("\nPhone Number:--- " + phNum + " \nCall Type:--- "
                        + callType + " \nCall Date:--- " + callDate
                        + " \nCall duration in sec :--- " + callDuration);
                sb.append("\n----------------------------------");
            }
            managedCursor.close();
            return sb;

        }
    }

