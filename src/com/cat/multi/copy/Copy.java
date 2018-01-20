package com.cat.multi.copy;

import java.io.*;
import java.util.logging.Level;

/**
 * Created by cat on 2018/1/20.
 * 小应用：多线程拷贝数据
 */
public class Copy implements Runnable {


    private final String srcPath;
    private final String dest;

    /**
     * @param dest    全路径 --> /data/storage/camera/dog.jpg
     * @param srcPath 全路径
     */
    public Copy(String dest, String srcPath) {
        this.dest = dest;
        this.srcPath = srcPath;
    }


    public void copy() throws IOException {
        File df = new File(dest);
        File src = new File(srcPath);

        BufferedInputStream bin = new BufferedInputStream(new FileInputStream(src));
        BufferedOutputStream bout = new BufferedOutputStream(new FileOutputStream(df));

        byte[] buffer = new byte[1024];
        while (bin.read(buffer) != -1) {
            if (buffer.length != 1024) {
                System.out.println("len==" + buffer.length);
            }
            bout.write(buffer, 0, buffer.length);
        }
        bout.close();
        bin.close();
    }

    @Override
    public void run() {

    }
}
