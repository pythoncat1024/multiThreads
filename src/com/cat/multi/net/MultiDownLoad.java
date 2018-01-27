package com.cat.multi.net;

import java.util.concurrent.*;

/**
 * Created by cat on 2018/1/28.
 * multi download
 */
public class MultiDownLoad {

    @SuppressWarnings("Duplicates")
    public static void main(String[] args) {

        System.out.println("multi download ... start ###");

        {
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

            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            } finally {
                submit0.cancel(true);
                service.shutdown();
            }

        }

        {
            ExecutorService executorService = Executors.newFixedThreadPool(3);
            Future<Long> submit1 = executorService.submit(new Callable<Long>() {
                @Override
                public Long call() throws Exception {
                    String urlPath = NetPath.urlPath1;
                    String destPath = PathManager.generatePath("newly1025.mp4");
                    return DownLoadManager.downloadRandom(urlPath, destPath);
                }
            });
            try {
                System.out.println(submit1 + " , " + submit1.get());

            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            } finally {
                submit1.cancel(true);
                executorService.shutdown();
            }
        }

        {
            ExecutorService service = Executors.newFixedThreadPool(3);
            Future<Long> submit2 = service.submit(new Callable<Long>() {
                @Override
                public Long call() throws Exception {
                    String urlPath = NetPath.urlPath2;
                    String destPath = PathManager.generatePath("newly1026.mp4");
                    return DownLoadManager.downloadRandom(urlPath, destPath);
                }
            });
            try {
                System.out.println(submit2 + " , " + submit2.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            } finally {
                submit2.cancel(true);
                service.shutdown();
            }
        }
        {

            ExecutorService service = Executors.newFixedThreadPool(3);
            Future<Long> submit3 = service.submit(new Callable<Long>() {
                @Override
                public Long call() throws Exception {
                    String destPath = PathManager.generatePath("newly1027.mp4");
                    String urlPath = NetPath.urlPath3;
                    return DownLoadManager.downloadRandom(urlPath, destPath);
                }
            });
            try {
                System.out.println(submit3 + " , " + submit3.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            } finally {
                submit3.cancel(true);
                service.shutdown();
            }
        }
        {
            ExecutorService service = Executors.newFixedThreadPool(3);
            Future<Long> submit4 = service.submit(new Callable<Long>() {
                @Override
                public Long call() throws Exception {
                    String urlPath = NetPath.urlPath4;
                    String destPath = PathManager.generatePath("newly1028.mp4");
                    return DownLoadManager.downloadRandom(urlPath, destPath);
                }
            });
            try {
                System.out.println(submit4 + " , " + submit4.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            } finally {
                submit4.cancel(true);
                service.shutdown();
            }
        }

        {
            ExecutorService service = Executors.newFixedThreadPool(3);
            Future<Long> submit = service.submit(new Callable<Long>() {
                @Override
                public Long call() throws Exception {
                    String urlPath = NetPath.urlPath5;
                    String destPath = PathManager.generatePath("newly1029.mp4");
                    return DownLoadManager.downloadRandom(urlPath, destPath);
                }
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

        {
            ExecutorService service = Executors.newFixedThreadPool(3);
            Future<Long> submit = service.submit(new Callable<Long>() {
                @Override
                public Long call() throws Exception {
                    String urlPath = NetPath.urlPath6;
                    String destPath = PathManager.generatePath("newly1030.mp4");
                    return DownLoadManager.downloadRandom(urlPath, destPath);
                }
            });
            try {
                System.out.println(submit + " , " + submit.get());
            } catch (InterruptedException | ExecutionException e) {
                System.err.println(submit + " , newly1030");
                e.printStackTrace();
            } finally {
                submit.cancel(true);
                service.shutdown();
            }
        }

    }
}
