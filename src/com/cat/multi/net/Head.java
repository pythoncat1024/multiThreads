package com.cat.multi.net;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

/**
 * Created by cat on 2018/1/28.
 */
public class Head {

    public static void main(String[] args) {

        String path = Class.class.getClass().getResource("/").getPath();
        String rootPath = new File(path).getParentFile().getParentFile().getParentFile().getPath();


        String urlPath = "https://201712mp4.89soso.com/20171205/9/1/xml/91_1b99a5d6c21a43d3ee8706786d4a9a19.mp4";
        HttpURLConnection conn = null;
        try {
            URL url = new URL(urlPath);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("HEAD");   //设置本次请求的方式 ， 默认是GET方式， 参数要求都是大写字母
            conn.setConnectTimeout(5000);//设置连接超时
            conn.setDoInput(true);//是否打开输入流 ， 此方法默认为true
            conn.setDoOutput(true);//是否打开输出流， 此方法默认为false
            conn.connect();//表示连接
            int code = conn.getResponseCode();
            System.out.println("code == " + code); // 200
            if (code == 200) {
                System.out.printf("conn success. src=%s code: %d\n", urlPath, code);
                Map<String, List<String>> headerFields = conn.getHeaderFields();

                System.out.println("#### head --- start");
                for (String key : headerFields.keySet()) {
                    System.out.printf("%s:%s\n", key, headerFields.get(key));
                    if ("Content-Length".equals(key)) {
                        List<String> length = headerFields.get(key);
                        System.out.println(length + " , " + length.getClass());

                        long remoteLen = Long.parseLong(length.get(0));

                        System.out.println("-------------video length == " + remoteLen);
                        File dest = new File(rootPath, "raw/video/newly706786d4a9a19.mp4");
                        System.out.println("-------------video CURRENT == " + dest.length());


                    }
                }
                System.out.println("#### head --- end##");
                System.out.println("##### download finished #####");
            } else {
                System.err.printf("conn fail. src=%s code: %d", urlPath, code);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != conn) {
                conn.disconnect();
            }
        }
    }
}
