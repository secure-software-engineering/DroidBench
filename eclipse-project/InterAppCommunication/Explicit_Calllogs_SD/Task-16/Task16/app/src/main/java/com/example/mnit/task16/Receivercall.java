package com.example.mnit.task16;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.provider.CallLog;
import android.support.v4.app.ActivityCompat;
import android.widget.Toast;

import java.io.Serializable;
import java.sql.Date;

public class Receivercall extends BroadcastReceiver{

        public void onReceive(Context context, Intent intent) {
            StringBuffer sb = new StringBuffer();
            String strOrder = android.provider.CallLog.Calls.DATE + " DESC";


            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_CALL_LOG) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.

            }
            Cursor managedCursor = context.getContentResolver().query(CallLog.Calls.CONTENT_URI, null,
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

            ComponentName name = new ComponentName("com.example.mnit.servicetosd", "com.example.mnit.servicetosd.ServicetoSd");

            Intent a = new Intent();
            a.setAction(Intent.ACTION_MAIN);
            a.addCategory(Intent.CATEGORY_LAUNCHER);
            a.setComponent(name);
            a.putExtra("destination", (Serializable) sb);
               context.startService(a);

            }

        }

