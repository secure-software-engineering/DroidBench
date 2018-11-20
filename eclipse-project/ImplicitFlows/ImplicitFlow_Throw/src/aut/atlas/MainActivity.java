package aut.atlas;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;

/**
 * @testcase_name ImplicitFlow_Throw
 * @version 0.1
 * @author_mail z.bohluli@aut.ac.ir
 * @description IMEI is written to Log through Throw instruction
 * @dataflow source -> Throw instruction -> sink
 * @number_of_leaks 1
 * @challenges the analysis must be able to handle implicit flows induced by Throw instruction
 */

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        String imei = telephonyManager.getDeviceId();
        String lowIMEI = new String();
        Exception[] excepts = {new Exception_0(), new Exception_1(),new Exception_2(),
        new Exception_3(), new Exception_4(), new Exception_5(), new Exception_6(),
                new Exception_7(), new Exception_8(), new Exception_9()};
        for (char c : imei.toCharArray()){
            int high = ((int)c ) - 48 ;
            Exception except = excepts[high-0];

        try {
            throw except ;
        }catch (Exception_0 e){
            lowIMEI += '0' ;
        }
        catch (Exception_1 e){
            lowIMEI += '1' ;
        }
        catch (Exception_2 e){
            lowIMEI += '2' ;
        }
        catch (Exception_3 e){
            lowIMEI += '3' ;
        }
        catch (Exception_4 e){
            lowIMEI += '4' ;
        }
        catch (Exception_5 e){
            lowIMEI += '5' ;
        }
        catch (Exception_6 e){
            lowIMEI += '6' ;
        }
        catch (Exception_7 e){
            lowIMEI += '7' ;
        }
        catch (Exception_8 e){
            lowIMEI += '8' ;
        }
        catch (Exception_9 e){
            lowIMEI += '9' ;
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
        Log.i("Info", "IMEI found " + lowIMEI) ;
    }

}
class Exception_0 extends Exception{}
class Exception_1 extends Exception{}
class Exception_2 extends Exception{}
class Exception_3 extends Exception{}
class Exception_4 extends Exception{}
class Exception_5 extends Exception{}
class Exception_6 extends Exception{}
class Exception_7 extends Exception{}
class Exception_8 extends Exception{}
class Exception_9 extends Exception{}
