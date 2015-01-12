package de.ecspride;

import android.util.Log;

public class DataLeak extends NoDataLeak{
	
	public DataLeak(String data){
		super(data);
	}
	
	@Override
	public void logData(){
		Log.i("LOG", super.getData()); //sink
	}
}
