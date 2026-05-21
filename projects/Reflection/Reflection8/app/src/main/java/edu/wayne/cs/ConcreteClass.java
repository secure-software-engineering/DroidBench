package edu.wayne.cs;

import android.util.Log;

public class ConcreteClass extends BaseClass {

    public void foo() {
    }

    public void foo(String msg) {
        Log.d("DroidBench", msg); // sink
    }
}
