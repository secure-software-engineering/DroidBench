package edu.wayne.cs;

import java.io.File;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import dalvik.system.DexClassLoader;

/**
 * @testcase_name DynamicSource1
 * @author Wayne State University,
 * @author_mail zhenyu.ning@wayne.edu
 * 
 * @description Use dynamically loaded code to create a source .
 * @dataflow onCreate: source() -> source -> sink
 * @number_of_leaks 1
 * @challenges The analysis should detect dynamically loaded code.
 */
public class MainActivity extends Activity {

    private static final String FILE_NAME = "dynamic.jar";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FileUtils.copyDex(MainActivity.this, FILE_NAME);
        File dexOutputDir = getDir("dex", 0);
        DexClassLoader dcl = new DexClassLoader(dexOutputDir.getPath()
                + File.separator + FILE_NAME, getDir("odex", 0).getPath(),
                getApplicationInfo().nativeLibraryDir, getClassLoader());
        try {
            Class<?> c = dcl.loadClass("edu.wayne.cs.ChildClass");
            ParentClass pc = (ParentClass) c.newInstance();

            Log.d("DroidBench", pc.source(this));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
