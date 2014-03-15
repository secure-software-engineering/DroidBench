package au.com.nicta.scrubdroid.directbuffer;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;

/**
 * @testcase_name DirectBuffer
 * @version 0.1
 * @copyright NICTA 2012--2013
 * @author Golam Sarwar, Olivier Mehani, Network Research Group (NRG), NICTA
 * @author_mail golam.sarwar@nicta.com.au, olivier.mehani@nicta.com.au
 * @license GPL2+
 * 
 * @description A byte buffer is filled with tainted and untainted data in sequence. The untainted
 * 	data is then read out and sent via SMS.
 * @dataflow -
 * @number_of_leaks 0
 * @challenges the analysis must distinguish between different accesses to the
 *      buffer to recognize that the tainted data does not get leaked. 
 *
 */
public class DirectBuffer extends Activity {

	private ByteBuffer bbuf;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_direct_buffer);
       
        bbuf = ByteBuffer.allocateDirect(128).order(ByteOrder.BIG_ENDIAN);
        String out = directBufferTrick(((TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId()); //source
		out = directBufferTrick("Hello World");
		
		SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage("+49 1234", null, out, null, null);  //sink, no leak
    }

    
	private String directBufferTrick(String in) {
		String out = new String();

		int addr = 0x00;
		
		for (int i = 0; i < in.length(); i++) {
			char x = in.charAt(i);
			bbuf.putChar(addr, x);
			char y = bbuf.getChar(addr);
			out = out + y;
		}
		return out;
	}
}
