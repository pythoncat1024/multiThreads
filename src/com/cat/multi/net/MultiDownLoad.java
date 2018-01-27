package com.cat.multi.net;

import sun.util.resources.ga.LocaleNames_ga;

import java.util.concurrent.*;

/**
 * Created by cat on 2018/1/28.
 * multi download
 */
public class MultiDownLoad {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        System.out.println("multi download ... start ###");

        Future<Long> submit0 = Executors.newCachedThreadPool().submit(new Callable<Long>() {
            @Override
            public Long call() throws Exception {
                String urlPath = NetPath.urlPath1;
                String destPath = PathManager.generatePath("newly1025.mp4");
                return DownLoadManager.downloadRandom(urlPath, destPath, 2048);
            }
        });

        System.out.println(submit0 + " , " + submit0.get());

        Future<Long> submit1 = Executors.newCachedThreadPool().submit(new Callable<Long>() {
            @Override
            public Long call() throws Exception {
                String urlPath = NetPath.urlPath1;
                String destPath = PathManager.generatePath("newly1025.mp4");
                return DownLoadManager.downloadRandom(urlPath, destPath);
            }
        });

        System.out.println(submit1 + " , " + submit1.get());
        Future<Long> submit2 = Executors.newCachedThreadPool().submit(new Callable<Long>() {
            @Override
            public Long call() throws Exception {
                String urlPath = NetPath.urlPath2;
                String destPath = PathManager.generatePath("newly1026.mp4");
                return DownLoadManager.downloadRandom(urlPath, destPath);
            }
        });
        System.out.println(submit2 + " , " + submit2.get());
        Future<Long> submit3 = Executors.newCachedThreadPool().submit(new Callable<Long>() {
            @Override
            public Long call() throws Exception {
                String destPath = PathManager.generatePath("newly1027.mp4");
                String urlPath = NetPath.urlPath3;
                return DownLoadManager.downloadRandom(urlPath, destPath);
            }
        });
        System.out.println(submit3 + " , " + submit3.get());
        Future<Long> submit4 = Executors.newCachedThreadPool().submit(new Callable<Long>() {
            @Override
            public Long call() throws Exception {
                String urlPath = NetPath.urlPath4;
                String destPath = PathManager.generatePath("newly1028.mp4");
                return DownLoadManager.downloadRandom(urlPath, destPath);
            }
        });
        System.out.println(submit4 + " , " + submit4.get());

    }
}
