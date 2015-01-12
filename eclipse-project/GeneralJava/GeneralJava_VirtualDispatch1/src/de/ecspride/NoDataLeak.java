package de.ecspride;

import android.util.Log;

public class NoDataLeak {
	private final String data;
	
	public NoDataLeak(String data){
		this.data = data;
	}
	
	public String getData(){
		return data;
	}
	
	public void logData(){
		Log.i("LOG", data);
	}
}
