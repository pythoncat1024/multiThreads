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

        long pos = 0;
        long end = srcsize / 2 / 1024 * 1024;
        Copy copy = new Copy(sf.getAbsolutePath(), df.getAbsolutePath(), pos, end);
        copy.copy1();
//
        System.out.println("srcLen==" + sf.length() + " , destLen=" + df.length());

        pos = end;
        end = srcsize;
        copy = new Copy(sf.getAbsolutePath(), df.getAbsolutePath(), pos, end);
        copy.copy1();
        System.out.println("---srcLen==" + sf.length() + " , destLen=" + df.length());
    }
}
