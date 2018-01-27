package com.cat.multi.net;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by cat on 2018/1/27.
 * https://201712mp4.89soso.com/20171205/9/1/xml/91_1b99a5d6c21a43d3ee8706786d4a9a19.mp4
 */
public class Main {

    public static void main(String[] args) {

        String path = Class.class.getClass().getResource("/").getPath();
        String rootPath = new File(path).getParentFile().getParentFile().getParentFile().getPath();
        File dest = new File(rootPath, "raw/video/newly706786d4a9a19.mp4"); // src
        String urlPath = "https://201712mp4.89soso.com/20171205/9/1/xml/91_1b99a5d6c21a43d3ee8706786d4a9a19.mp4";

        try {
            String destPath = rootPath + File.separator + "raw/video/newly1024.mp4";
            System.out.println("destPath==" + destPath);
            long random = DownLoadManager.downloadRandom(urlPath, destPath);
            System.out.println("down downloadRandom ... " + random);
            long random2 = DownLoadManager.downloadRandom(urlPath, destPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
