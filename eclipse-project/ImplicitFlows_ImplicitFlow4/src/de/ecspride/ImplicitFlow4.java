package de.ecspride;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class ImplicitFlow4 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_implicit_flow4);
    }

    public void checkUsernamePassword(View view){
		EditText editPassword = (EditText)findViewById(R.id.password);
		EditText editUsername = (EditText)findViewById(R.id.username);
		String password = editPassword.getText().toString();
		String username = editUsername.getText().toString();
		
		Log.i("TAG", "bevor try");
		try{
			boolean passwordCorrect = lookup(username, password);
			if(passwordCorrect)
				Log.i("INFO", "password correct");
			else
				Log.i("INFO", "password not correct");
		}catch(Exception ex){
			Log.i("INFO", "username not available");
		}
		Log.i("TAG", "after try");
	}
	
	//lookup database
	private boolean lookup(String username, String password) throws Exception{
		if(!username.equals("hanns"))
			throw new Exception("username not available");
		else if(username.equals("hanns") && !password.equals("superSecure"))
			return false;
		else 
			return true;
	}
    
}
