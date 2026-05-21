package edu.mit.icc_action_string_operations;

import android.content.Context;
import android.content.Intent;

public class implicit {
	public void rec_intent(Context c,String m,String sl)
	{
		
		Intent intent = new Intent(m)
	    .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);  
		intent.putExtra("DroidBench", sl);
		c.startActivity(intent);
	
	}
}
