package com.example.sinkintentonly;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.sax.StartElementListener;
import android.telephony.SmsManager;
import android.content.Intent;

public class send 
{
		public String take(Intent i)
		{
			 String st = i.getStringExtra("imeino");
			 return st;
		}

	
}
