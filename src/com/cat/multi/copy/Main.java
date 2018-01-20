package com.cat.multi.copy;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

/**
 * Created by cat on 2018/1/20.
 * 多线程拷贝
 */
public class Main {

    public static void main(String[] args) throws IOException {

        String path = Class.class.getClass().getResource("/").getPath();
        String rootPath = new File(path).getParentFile().getParentFile().getParentFile().getPath();

        File sf = new File(rootPath, "raw/src/memo_methine.pdf"); // src
        File df = new File(rootPath, "raw/dest/memo_methine_copied.pdf"); // dest
        Copy copy = new Copy(df.getAbsolutePath(), sf.getAbsolutePath());
        copy.copy();
    }
}
