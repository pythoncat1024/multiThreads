package com.cat.multi.net;

import java.io.File;
import java.io.IOException;

/**
 * Created by cat on 2018/1/28.
 */
public class SomeCheck {

    public static void main(String[] args) throws IOException {
        String destPath = PathManager.generatePath("newly1024.mp4");
        long total = DownLoadManager.getRemoteLength(NetPath.urlPath0);

        long current = new File(destPath).length();

        System.err.println("total==" + total + " \t current=" + current);
    }
}
