package org.cert.echoer;

import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

public class Button1Listener implements OnClickListener {

	private final MainActivity act;
	
	public Button1Listener(MainActivity parentActivity) {
		this.act = parentActivity;
	}

	
	@Override
	public void onClick(View arg0) {
		try {
//			this.act.i.putExtra("resultData", "new secret");
			Log.i("In Echoer", "Echoing data back to caller using setResult()");
			this.act.setResult(0, this.act.i);
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			this.act.finish();
		}

	}

}
