package com.cat.multi.net;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.*;

/**
 * Created by cat on 2018/1/27.
 * https://201712mp4.89soso.com/20171205/9/1/xml/91_1b99a5d6c21a43d3ee8706786d4a9a19.mp4
 */
public class Main {

    public static void main(String[] args) {

        ExecutorService service = Executors.newFixedThreadPool(3);
        Future<Long> submit0 = service.submit(new Callable<Long>() {
            @Override
            public Long call() throws Exception {
                String urlPath = NetPath.urlPath0;
                String destPath = PathManager.generatePath("newly1024.mp4");
                return DownLoadManager.downloadRandom(urlPath, destPath, 2048);
            }
        });

        try {
            System.out.println(submit0 + " , " + submit0.get());
            submit0.cancel(true);
            System.out.println("x---x--x-----x");
            service.shutdown();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

    }
}
