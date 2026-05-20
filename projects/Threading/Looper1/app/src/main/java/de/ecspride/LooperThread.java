package de.ecspride;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

public class LooperThread extends Thread {
	
	public static Handler handler = new Handler() {
        public void handleMessage(Message msg) {
      	  if (msg.obj != null && msg.obj instanceof String)
      		  Log.d("DroidBench", (String) msg.obj);
        }
	};
	public boolean ready = false;
	
	public void run() {
		Looper.prepare();
		ready = true;
		Looper.loop();
	}
	
}
