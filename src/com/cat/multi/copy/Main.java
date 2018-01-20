package com.cat.multi.copy;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

/**
 * Created by cat on 2018/1/20.
 * 多线程拷贝
 */
public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {

        String path = Class.class.getClass().getResource("/").getPath();
        String rootPath = new File(path).getParentFile().getParentFile().getParentFile().getPath();

        File sf = new File(rootPath, "raw/src/memo_methine.pdf"); // src
        File df = new File(rootPath, "raw/dest/memo_methine_copied.pdf"); // dest
        if (df.isFile()) {
            df.delete();
        }

        long srcsize = sf.length();

        System.out.println("srcSzie==" + srcsize);

//        long pos = 0;
//        long end = srcsize / 2 / 1024 * 1024;

        long pos1 = 0;
        long end1= srcsize / 2 / 1024 * 1024;
        Copy copy1 = new Copy(sf.getAbsolutePath(), df.getAbsolutePath(), pos1, end1);

        long pos2 = srcsize / 2 / 1024 * 1024;
        long end2 = srcsize;
        Copy copy2 = new Copy(sf.getAbsolutePath(), df.getAbsolutePath(), pos2, end2);
        new Thread(copy2).start();
        Thread.sleep(10);
        new Thread(copy1).start();
    }
}
