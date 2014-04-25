package lu.uni.serval.icc_delete1;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

public class IsolateProvider extends ContentProvider {

	String NAME = "lu.uni.serval.icc_delete1";
	String URL = "content://" + NAME + "/isolate";
	
	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		if (uri.toString().equals(URL))
		{
			if (selection.contains("deviceid"))
			{
				for (String args : selectionArgs)
				{
					Log.i("DroidBench", args);
				}
			}
		}
		
		return 1;
	}

	@Override
	public String getType(Uri arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues cv) {
		return null;
	}

	@Override
	public boolean onCreate() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs,
			String sortOrder) {
		return null;
	}

	@Override
	public int update(Uri arg0, ContentValues arg1, String arg2, String[] arg3) {
		// TODO Auto-generated method stub
		return 0;
	}

	

}
