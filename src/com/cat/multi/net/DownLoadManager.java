package com.cat.multi.net;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by cat on 2018/1/28.
 * 下载的工具类
 */
public final class DownLoadManager {

    private DownLoadManager() {
        throw new RuntimeException("can not fuck me");
    }

    /**
     * 获取下载的文件长度
     *
     * @param urlPath 下载路径
     * @return long
     * @throws IOException ex
     */
    public static long getRemoteLength(String urlPath) throws IOException {

        URL url;

        HttpURLConnection conn = null;
        try {
            url = new URL(urlPath);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("HEAD");   //设置本次请求的方式 ， 默认是GET方式， 参数要求都是大写字母
            conn.setConnectTimeout(5000);//设置连接超时
            conn.setDoInput(true);//是否打开输入流 ， 此方法默认为true
            conn.setDoOutput(true);//是否打开输出流， 此方法默认为false
            conn.connect();//表示连接
            int code = conn.getResponseCode();
            System.out.println("code == " + code); // 200
            if (code == 200) {
                String contentLength = conn.getHeaderField("Content-Length");
                String fileType = conn.getHeaderField("Content-Type");
                long contentLen = Long.parseLong(contentLength);
                System.out.println("fileType =" + fileType + " , contentLen = " + contentLen);

                return contentLen;
            } else {
                throw new RuntimeException("连接失败，code = " + code);
            }
        } finally {
            if (conn != null) {
                conn.disconnect();
                System.out.println("### close connection.....");

            }
        }
    }


    public static boolean download(String urlPath, String destPath) throws IOException {
        URL url = null;
        HttpURLConnection conn = null;
        BufferedInputStream bin = null;
        BufferedOutputStream bos = null;
        try {
            url = new URL(urlPath);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");   // 设置本次请求的方式 ， 默认是GET方式， 参数要求都是大写字母
            conn.setConnectTimeout(5000);   // 设置连接超时
            conn.setDoInput(true);    //  是否打开输入流 ， 此方法默认为true
            conn.setDoOutput(true);  //  是否打开输出流， 此方法默认为false
            conn.connect();    //  表示连接

            int code = conn.getResponseCode();
            System.out.println("code == " + code); // 200
            if (code == 200) {
                System.out.printf("conn success. src=%s code: %d\n", urlPath, code);
                InputStream is = conn.getInputStream();
                bin = new BufferedInputStream(is);
                byte[] buffer = new byte[1024 * 100];

                bos = new BufferedOutputStream(new FileOutputStream(destPath));
                int read;
                while ((read = bin.read(buffer)) != -1) {
                    bos.write(buffer, 0, read);
                    System.out.println("each read: " + read);
                }
                System.out.println("##### download success #####");
                return true;
            } else {
                System.err.printf("conn fail. src=%s code: %d", urlPath, code);
                return false;
            }
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
            if (bos != null) {
                bos.close();
            }
            if (bin != null) {
                bin.close();
            }
        }
    }

    public static boolean downloadWithCheck(long contentLen, String urlPath, String destPath) throws IOException {
        URL url = null;
        HttpURLConnection conn = null;
        BufferedInputStream bin = null;
        BufferedOutputStream bos = null;
        try {
            url = new URL(urlPath);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");   // 设置本次请求的方式 ， 默认是GET方式， 参数要求都是大写字母
            conn.setConnectTimeout(5000);   // 设置连接超时
            conn.setDoInput(true);    //  是否打开输入流 ， 此方法默认为true
            conn.setDoOutput(true);  //  是否打开输出流， 此方法默认为false
            conn.connect();    //  表示连接

            int code = conn.getResponseCode();
            System.out.println("code == " + code); // 200
            if (code == 200) {
                System.out.printf("conn success. src=%s code: %d\n", urlPath, code);
                InputStream is = conn.getInputStream();
                bin = new BufferedInputStream(is);
                byte[] buffer = new byte[1024 * 100];

                bos = new BufferedOutputStream(new FileOutputStream(destPath));
                int read;
                long total = 0;
                while ((read = bin.read(buffer)) != -1) {
                    bos.write(buffer, 0, read);
                    total += read;
                    System.out.println("each read: " + read);
                }
                System.out.println("##### download finish #####");
                if (total == contentLen) {
                    System.out.println("##### download success #####");
                    return true;
                } else {
                    return false;
                }
            } else {
                System.err.printf("conn fail. src=%s code: %d", urlPath, code);
                return false;
            }
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
            if (bos != null) {
                bos.close();
            }
            if (bin != null) {
                bin.close();
            }
        }
    }

    /**
     * @param urlPath  URL
     * @param destPath file
     * @return 累计下载量
     * @throws IOException ex
     */
    public static long downloadRandom(String urlPath, String destPath) throws IOException {
        long remoteLength = getRemoteLength(urlPath);
        if (checkFinish(remoteLength, destPath)) {
            System.out.println("download already finished...");
            return remoteLength;
        }

        File target = new File(destPath);

        URL url;
        HttpURLConnection conn = null;
        BufferedInputStream bin = null;

        RandomAccessFile rout = null;
        try {
            url = new URL(urlPath);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");   // 设置本次请求的方式 ， 默认是GET方式， 参数要求都是大写字母
            conn.setConnectTimeout(5000);   // 设置连接超时
            conn.setDoInput(true);    //  是否打开输入流 ， 此方法默认为true
            conn.setDoOutput(true);  //  是否打开输出流， 此方法默认为false
            conn.connect();    //  表示连接

            int code = conn.getResponseCode();
            System.out.println("code == " + code); // 200
            if (code == 200) {
                System.out.printf("conn success. src=%s code: %d\n", urlPath, code);
                InputStream is = conn.getInputStream();
                bin = new BufferedInputStream(is);
                byte[] buffer = new byte[1024 * 100];
                rout = new RandomAccessFile(destPath, "rw");
                int read;
                long total = 0;
                final long currentLen = target.length();
                long skip = bin.skip(currentLen);
                System.out.println(">>>>>>>>>> skip==" + skip);
                rout.seek(currentLen);
                int times = 0;
                while ((read = bin.read(buffer)) != -1) {
                    rout.write(buffer, 0, read);
                    total += read;
                    if (times % 5 == 0 && times != 0) {
                        System.out.print("times:" + times + " read: " + read + "\t");
                    }
                    times++;
                }
                System.out.println("##### download finish #####");
                return total + currentLen;
            } else {
                System.err.printf("conn fail. src=%s code: %d", urlPath, code);
                return -1;
            }
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
            if (rout != null) {
                rout.close();
            }
            if (bin != null) {
                bin.close();
            }
        }
    }

    public static boolean checkFinish(long contentLength, String destPath) {
        return contentLength == new File(destPath).length();
    }

}
