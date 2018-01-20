package com.cat.multi.copy;

import javax.xml.transform.Source;
import java.io.*;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.logging.Level;

/**
 * Created by cat on 2018/1/20.
 * 小应用：多线程拷贝数据
 */
public class Copy implements Runnable {

    private final int bufferSize = 1024;

    private final String srcPath;
    private final String dest;


    private long pos;
    private long end;

    /**
     * @param dest    全路径 --> /data/storage/camera/dog.jpg
     * @param srcPath 全路径
     */
    public Copy(String srcPath, String dest, long pos, long end) {
        this.srcPath = srcPath;
        this.dest = dest;
        this.pos = pos;
        this.end = end;
    }

    /**
     * 传统的读写文件的方式 --> 可用，但是不能实现分段读写文件。
     *
     * @throws IOException io
     */
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
        if (this.pos == this.end) {
            System.out.println(" 文件已经复制结束了....");
            return;
        }
        RandomAccessFile bin = new RandomAccessFile(srcPath, "r");
        RandomAccessFile bout = new RandomAccessFile(dest, "rw");

        byte[] buffer = new byte[bufferSize];
        int read;
        long total = pos;
        bin.seek(pos);
        bout.seek(pos);
        while ((read = bin.read(buffer)) != -1) {

            bout.write(buffer, 0, read);
            total += read;

            if (total == this.end) {
                System.out.println("我的任务完成了. 当前完成的数据为：" + total);
                System.out.println("c1. from：" + pos + " , end=" + end + " , total=" + new File(srcPath).length());
                this.pos = this.end;
                break;
            } else if (total > this.end) {
                throw new RuntimeException("end 计算出错了，需要修改程序，否则数据复制会出错...");
            }
        }
//        System.out.println("---------------------------total=" + total);

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
