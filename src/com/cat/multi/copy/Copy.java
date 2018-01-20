package com.cat.multi.copy;

import java.io.*;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
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


    /**
     * need copy 2
     *
     * @throws IOException
     */
    public void copy1() throws IOException {

        RandomAccessFile bin = new RandomAccessFile(srcPath, "r");

        RandomAccessFile bout = new RandomAccessFile(dest, "rw");
        long length = bin.length();
        long first = length / 2;

        byte[] buffer = new byte[1024];
        int read;
        long current = 0;
        System.out.println("w seek=" + bout.length());
        bout.seek(bout.length());
        while ((read = bin.read(buffer)) != -1) {
            bout.write(buffer, 0, read);
            current += read;
            if (current > first) {
                System.err.println("只读取了一半的文件数据：" + current + " , total=" + length);
                break;
            }
        }
        bout.close();
        bin.close();
    }

    /**
     * need copy 1
     *
     * @throws IOException
     */
    public void copy2() throws IOException {

        RandomAccessFile bin = new RandomAccessFile(srcPath, "r");

        RandomAccessFile bout = new RandomAccessFile(dest, "rw");
//        BufferedInputStream bin = new BufferedInputStream(new FileInputStream(src));
//        BufferedOutputStream bout = new BufferedOutputStream(new FileOutputStream(df));

        byte[] buffer = new byte[1024];
        int read;
        System.out.println("w seek2=" + bout.length());
        bout.seek(bout.length());
        bin.seek(bout.length());
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
