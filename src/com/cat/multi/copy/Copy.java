package com.cat.multi.copy;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * <pre>
 *
 * Created by cat on 2018/1/20.
 * 小应用：多线程拷贝数据
 * 分析一下：多线程拷贝是否需要考虑线程安全？
 *      1. 是否是多线程运行环境？ ---> 是
 *      2. 是否需要访问共享数据？ ---> 否
 *          * 为什么说没有访问共享数据，因为RandomAccessFile 通过 seek(pos)方法，分别访问文件的不同位置，不涉及共享啊！
 *      3. 操作共享数据是否是多条语句？--> 是
 *
 * </pre>
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
    private void copy() throws IOException {

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
    public void copy1() throws IOException, InterruptedException {
        if (this.pos == this.end) {
            System.out.println(" 文件已经复制结束了....");
            return;
        }
        Thread.sleep(100);
        RandomAccessFile bin = new RandomAccessFile(srcPath, "r");
        RandomAccessFile bout = new RandomAccessFile(dest, "rw");

        byte[] buffer = new byte[bufferSize];
        int read;
        long total = 0;
        bin.seek(pos);
        bout.seek(pos);
        while ((read = bin.read(buffer)) != -1) {

            bout.write(buffer, 0, read);
            total += read;

            if (total + pos == this.end) {
                System.out.println(Thread.currentThread().getName() + " 我的任务完成了. 当前完成的数据为：" + total);
                System.out.println("c1. from：" + pos + " , end=" + end + " , total=" + new File(srcPath).length());
                this.pos = this.end;
                break;
            } else if (total > this.end) {
                throw new RuntimeException("end 计算出错了，需要修改程序，否则数据复制会出错...");
            }
        }
        bout.close();
        bin.close();
    }

    @Override
    public void run() {

        while (true) {
            if (this.pos == this.end) {
                System.out.println(" 文件已经复制结束了....");
                break;
            }
            try {
                copy1();
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
