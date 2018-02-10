package com.cat.multi.net;

import com.cat.multi.sql.DaoManager;

import java.io.File;
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
    public static synchronized void taskFinal(String url) {
        String value = PathManager.generatePathFromUrlFinal(url);
        int insert = DaoManager.insert(url, value);
        // todo:这一步出错了， task(url,value); 不能这样写，这样写就失去了数据库的意义了，数据库就是为了保证数据的统一。
        // 但是这样写，无论数据库是否以及有这个key，都会去插入一个新的value,
        // 然后 去 DownLoadManager#downloadRandom（）里面，这里的确会判断断点，但是前提是destPath 不变
        // 不过由于这里是传人 task(key,value);[这里的value 每次都不一样]，导致断点判断失败
        if (insert > 0) {
            // 插入成功才去开启新的task
            task(url, value);
        } else {
            // 插入失败，说明数据库已经存在，但是现在不知道下载完成了没有，所以，去再走一次，不过现在没有关系了，因为有断点保护
            task(url, DaoManager.select(url));
        }
    }

    /**
     * * 保证了路径不重名，以及下载过的url 不会被覆盖或者重复下载
     *
     * @param url url
     */
    public static synchronized void taskFinal(String url, String destPath) {
        String value = PathManager.generatePathFromUrlFinal(url, destPath);
        int insert = DaoManager.insert(url, value);
        // todo:这一步出错了， task(url,value); 不能这样写，这样写就失去了数据库的意义了，数据库就是为了保证数据的统一。
        // 但是这样写，无论数据库是否以及有这个key，都会去插入一个新的value,
        // 然后 去 DownLoadManager#downloadRandom（）里面，这里的确会判断断点，但是前提是destPath 不变
        // 不过由于这里是传人 task(key,value);[这里的value 每次都不一样]，导致断点判断失败
        if (insert > 0) {
            // 插入成功才去开启新的task
            task(url, value);
        } else {
            // 插入失败，说明数据库已经存在，但是现在不知道下载完成了没有，所以，去再走一次，不过现在没有关系了，因为有断点保护
            task(url, DaoManager.select(url));
        }
    }

    private static void task(String urlPath, String destPath) {

        System.out.println("path===" + destPath);
        ExecutorService service = Executors.newFixedThreadPool(3);
        Future<Long> submit = service.submit(() -> {
            System.out.println();
            return DownLoadManager.downloadRandom(urlPath, destPath, 64);
        });
        try {
            System.out.println(submit + " , " + submit.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            boolean delete = new File(destPath).delete();
            System.err.println("task() # delete temp dest when exception: " + delete + " , " + destPath);
        } finally {
            submit.cancel(true);
            service.shutdown();
        }
    }
}
