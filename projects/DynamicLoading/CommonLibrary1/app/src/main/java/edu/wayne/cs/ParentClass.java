package edu.wayne.cs;

import android.content.Context;
import android.util.Log;

public class ParentClass {

    public String source(Context context) {
        return "";
    };

    public void sink(String info) {
        Log.d("DroidBench", info);
    }
}
