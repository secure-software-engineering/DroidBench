package aut.atlas;

import android.app.Activity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.content.Context;
import android.util.Log;

/**
 * @testcase_name ImplicitFlow_Polymorphism
 * @version 0.1
 * @author_mail z.bohluli@aut.ac.ir
 * @description IMEI is written to Log through a polymorphic method
 * @dataflow source -> polymorphic method -> sink
 * @number_of_leaks 1
 * @challenges the analysis must be able to handle implicit flows induced by polymorphism
 */


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        String imei = telephonyManager.getDeviceId();
        String lowIMEI = new String();
        Poly[] polys = {new Poly_0(), new Poly_1(), new Poly_2(), new Poly_3(),
                new Poly_4(), new Poly_5(), new Poly_6(), new Poly_7(), new Poly_8(), new Poly_9()} ;

        for (char c: imei.toCharArray()){
            int high = ((int)c) - 48 ;
            Poly poly = polys [high - 0];
            lowIMEI += poly.f();
        }

        Log.i("Info", "IMEI found = " + lowIMEI);
    }
}

class Poly { Poly() {}
    char f() { return 'c'; }
}

class Poly_0 extends Poly
{
    Poly_0() {  super(); }
    char f() { return '0'; }
}

class Poly_1 extends Poly
{
    Poly_1() { super(); }
    char f() { return '1'; }
}

class Poly_2 extends Poly
{
    Poly_2() { super(); }
    char f() { return '2'; }
}

class Poly_3 extends Poly
{
    Poly_3() { super(); }
    char f() { return '3'; }
}

class Poly_4 extends Poly{
    Poly_4() { super(); }
    char f() { return '4'; }
}

class Poly_5 extends Poly
{    Poly_5() { super(); }
    char f() { return '5'; }
}

class Poly_6 extends Poly
{    Poly_6() { super(); }
    char f() { return '6'; }
}

class Poly_7 extends Poly
{
    Poly_7() { super(); }
    char f() { return '7'; }
}

class Poly_8 extends Poly
{
    Poly_8() { super(); }
    char f() { return '8'; }
}

class Poly_9 extends Poly
{
    Poly_9() { super(); }
    char f() { return '9'; }
}



