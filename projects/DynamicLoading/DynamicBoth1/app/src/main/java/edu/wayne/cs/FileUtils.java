package edu.wayne.cs;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.content.Context;

public class FileUtils {
    public static void copyDex(Context context, String fileName) {
        File f = context.getDir("dex", 0);

        try {
            InputStream localInputStream = context.getAssets().open(fileName);
            FileOutputStream localFileOutputStream = new FileOutputStream(
                    new File(f, fileName));
            byte[] b = new byte[8 * 1024];
            int read;
            while ((read = localInputStream.read(b)) != -1) {
                localFileOutputStream.write(b, 0, read);
                localFileOutputStream.flush();
            }

            localFileOutputStream.close();
            localInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

    }
}
