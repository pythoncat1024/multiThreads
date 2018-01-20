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

        RandomAccessFile bin = new RandomAccessFile(srcPath, "r");

        RandomAccessFile bout = new RandomAccessFile(dest, "rw");
//        BufferedInputStream bin = new BufferedInputStream(new FileInputStream(src));
//        BufferedOutputStream bout = new BufferedOutputStream(new FileOutputStream(df));

        byte[] buffer = new byte[1024];
        int read;
        while ((read = bin.read(buffer)) != -1) {
            bout.write(buffer, 0, read);
        }
        bout.close();
        bin.close();
    }

    @Override
    public void run() {

    }
}
