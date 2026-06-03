package com.example.mnit.twin2;

import android.Manifest;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.CallLog;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import java.io.Serializable;
import java.sql.Date;


/**
 * @testcase_name ImplicitCalllogViaActivity
 * @version 0.1
 * @author Malaviya National Institute of Technology Jaipur, India 
 * @author_mail er.shwetabhandari@gmail.com
 * 
 * @description The call log is sent implicitly to the callwritingactivity activity, which saves the log on the sd card.
 * @dataflow deviceid -> callwritingactivity activity -> sd card
 * @number_of_leaks 1
 * @challenges The analysis must handle inter-app communication through activities
 */
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        final StringBuffer sb1=  getCallDetails();

        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.setAction("com.example.mnit.callwritingactivity.MainActivity");
        intent.setType("text/plain");

        intent.putExtra("destination", (Serializable) sb1);
        startActivity(intent); //sink
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    private StringBuffer getCallDetails() {
        StringBuffer sb = new StringBuffer();
        String strOrder = android.provider.CallLog.Calls.DATE + " DESC";


        if (checkSelfPermission(Manifest.permission.READ_CALL_LOG) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.

        }
        Cursor managedCursor = getContentResolver().query(CallLog.Calls.CONTENT_URI, null, null, null, strOrder); //source
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
