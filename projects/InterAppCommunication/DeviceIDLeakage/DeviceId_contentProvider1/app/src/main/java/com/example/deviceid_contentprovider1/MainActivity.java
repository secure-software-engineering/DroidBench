package com.example.deviceid_contentprovider1;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

/**
 * @testcase_name DeviceId_ContentProvider1
 * @version 0.1
 * @author Malaviya National Institute of Technology Jaipur, India 
 * @author_mail er.shwetabhandari@gmail.com
 * 
 * @description The device id is stored in a content provider and, independent from
 * the content provider, sent to the Collector app.
 * @dataflow deviceid -> Content provider -> Intent
 * @number_of_leaks 1
 * @challenges The analysis must correctly handle content providers as well as
 * inter-app communication through intents 
 */
@SuppressLint("NewApi") public class MainActivity extends Activity {

    String num;
    String message = "Device Id :";
    Context context = null;
    String portnum;
    Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1 = (Button)findViewById(R.id.button1);
        b1.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				sendPort();
				
			}
        	
        });
    }

   
    public void sendPort() {

        deleteAll();
        ContentValues values = new ContentValues();

        TelephonyManager telephonyManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
        message = message.concat(telephonyManager.getDeviceId()); //source

        values.put(contentProvider.NUMBER,message);
        getContentResolver().insert(contentProvider.CONTENT_URI, values);

        showAll();
    }

    public void showAll() {
        // Retrieve the port
        String URL = "content://com.example.srikanth.provider/port";
        Uri port = Uri.parse(URL);
        Cursor c = getContentResolver().query(port, null, null, null, "number");
        String result = "Port Results:";

        if (!c.moveToFirst()) {
            Toast.makeText(this, result+" no content yet!", Toast.LENGTH_LONG).show();
        }else{
            do{
                num = c.getString(c.getColumnIndex(contentProvider.NUMBER)); // ICC source
            } while (c.moveToNext());
        }

        portnum = num;
        sendDeviceId();


    }

    public void deleteAll() {

        String URL = "content://com.example.srikanth.provider/port";
        Uri port = Uri.parse(URL);
        int count = getContentResolver().delete(
                port, null, null);
    }

    private void sendDeviceId()
    {
        Intent in = new Intent("com.example.collector");
        in.setType("text/plain");
        in.putExtra(Intent.EXTRA_TEXT,num); // sink
        startActivity(in);



    }
}

