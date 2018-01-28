package com.cat.multi.net;

import com.cat.multi.sql.DaoManager;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by cat on 2018/1/28.
 * <p>
 * 下载帮助类
 */
public class DownLoadHelper {

    private DownLoadHelper() {

    }

    /**
     * * 保证了路径不重名，以及下载过的url 不会被覆盖或者重复下载
     *
     * @param url url
     */
    public static void taskFinal(String url) {
        String value = PathManager.generatePathFromUrlFinal(url);
        DaoManager.insert(url, value);
        task(url, value);
    }

    @Deprecated
    public static void taskSimple(String url) {
        task(url, PathManager.generatePathFromUrl(url));
    }

    private static void task(String urlPath, String destPath) {
        ExecutorService service = Executors.newFixedThreadPool(3);
        Future<Long> submit = service.submit(() -> {
            System.out.println();
            return DownLoadManager.downloadRandom(urlPath, destPath);
        });
        try {
            System.out.println(submit + " , " + submit.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            submit.cancel(true);
            service.shutdown();
        }
    }
}
