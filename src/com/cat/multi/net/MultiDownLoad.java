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
            Future<Long> submit0 = service.submit(() -> {
                String urlPath = NetPath.urlPath0;
                String destPath = PathManager.generatePath("newly1024.mp4");
                return DownLoadManager.downloadRandom(urlPath, destPath, 2048);
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
            Future<Long> submit1 = executorService.submit(() -> {
                String urlPath = NetPath.urlPath1;
                String destPath = PathManager.generatePath("newly1025.mp4");
                return DownLoadManager.downloadRandom(urlPath, destPath);
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
            Future<Long> submit3 = service.submit(() -> {
                String destPath = PathManager.generatePath("newly1027.mp4");
                String urlPath = NetPath.urlPath3;
                return DownLoadManager.downloadRandom(urlPath, destPath);
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
            Future<Long> submit4 = service.submit(() -> {
                String urlPath = NetPath.urlPath4;
                String destPath = PathManager.generatePath("newly1028.mp4");
                return DownLoadManager.downloadRandom(urlPath, destPath);
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
            Future<Long> submit = service.submit(() -> {
                String urlPath = NetPath.urlPath5;
                String destPath = PathManager.generatePath("newly1029.mp4");
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

        {
            String urlPath = NetPath.urlPath6;
            String destPath = PathManager.generatePath("newly1030.mp4");
            task(urlPath, destPath);
        }
        {
            String urlPath = NetPath.urlPath7;
            String destPath = PathManager.generatePath("newly1031.mp4");
            task(urlPath, destPath);
        }
        {
            String urlPath = NetPath.urlPath8;
            String destPath = PathManager.generatePath("newly1032.mp4");
            task(urlPath, destPath);
        }

        {
            String urlPath = NetPath.urlPath9;
            String destPath = PathManager.generatePath("newly1033.mp4");
            task(urlPath, destPath);
        }

        {
            String urlPath = "http://ugc-vliveochy.tc.qq.com/om.tc.qq.com/AzEX-2J45ZGkPOnE1UaTHSjcksJQbg7FtcC6rb---5xY/b0371e8epym.p701.1.mp4?sdtfrom=v1105&guid=393dd70feff6e1b0b7998158fd67f596&vkey=3D3F893C12361DD68795048811A716D4345FC94535DDD165F8EE64854228DDEA0CF0924E3E5F2D3F46663119A34B0F6B0315798EFEF5B0C2681DE5693B2FBAD4B638FD54C2F964F08A580640946E9AB941DEDB4C993D6CD8FC0697E99B0165FA45CFE4755CEC985CE9934C3B7546E1ABFFC84504C6724633&ocid=502601900";
            String destPath = PathManager.generatePath("newly1034.mp4");
            task(urlPath, destPath);
        }

        {
            String destPath = PathManager.generatePath("newly1035.mp4");
            String urlPath = "http://www.fcw05.com/get_file/1/c284e57e199dd9905fc47154847fc3baa12573b429/4000/4276/4276.mp4/";
            task(urlPath, destPath);
        }
        {
            String destPath = PathManager.generatePath("newly1036.mp4");
            String urlPath = "http://www.fcw05.com/get_file/1/6a1f98e6ec25231fa922896c24c80d4fbfe825e694/4000/4246/4246.mp4/";
            task(urlPath, destPath);
        }

        {
            String destPath = PathManager.generatePath("newly1037.mp4");
            String urlPath = "http://www.77fcw.com/get_file/1/d98c5fc1009619f7622f9262932bdb4476a7795290/9000/9095/9095.mp4/";
            task(urlPath, destPath);
        }
        {
            String destPath = PathManager.generatePath("newly10377.mp4");
            String urlPath = "http://media66.avtb02.com/media/videos/mp4/23149.mp4?st=4kAT98H22JYSb5NHNrzZSw&e=1517127981";
            task(urlPath, destPath);
        }

        {
            String destPath = PathManager.generatePath("newly1038.mp4");
            task("http://media66.avtb02.com/media/videos/mp4/23142.mp4?st=oWYfxTq4X3MkZu8WiGOe2Q&e=1517128198",
                    destPath);
        }

        {
            String destPath = PathManager.generatePath("newly1039.mp4");
            task("http://media66.avtb02.com/media/videos/mp4/23152.mp4?st=mx8Rx7RCJrP8LTaohjhNQw&e=1517128390",
                    destPath);
        }
        {
            String destPath = PathManager.generatePath("newly1040.mp4");

            task("http://www.77fcw.com/get_file/1/fc63f9efeae477dae01820e0adb2f14b5e26a1e1ea/0/348/348.mp4/",
                    destPath);
        }


        taskSimple("http://www.77fcw.com/get_file/1/3efac06a5cede376d4798c89da4c7690a4c3be6b09/8000/8983/8983.mp4/");

        taskSimple("http://www.77fcw.com/get_file/1/3efac06a5cede376d4798c89da4c7690a4c3be6b09/8000/8983/8983.mp4/");

        taskSimple("http://www.77fcw.com/get_file/1/406935fb83f32093047505f81f8acd11766c89c6c8/8000/8911/8911.mp4/");

        taskSimple("http://www.77fcw.com/get_file/1/b4120d20a41ba01721c7827b1609886eb71ff48f93/8000/8872/8872.mp4/");

        taskSimple("http://www.fcw05.com/get_file/1/f4dccc121a3a179c929791e6d3043f059b5e35fb9c/0/799/799.mp4/");

        taskSimple("http://www.99ff6.com/get_file/3/423492ae67c3d756482cdf059620824b/83000/83600/83600.mp4/");

        taskSimple("http://www.99ff6.com/get_file/3/a2b929192a0f02b2d2fb1cfaa853b673/83000/83630/83630.mp4/");

        taskSimple("http://www.99ff6.com/get_file/3/466db83cc0bc87403191d7d0e9619bc1/64000/64635/64635.mp4/");

        taskSimple("http://www.99ff6.com/get_file/3/ce3794791f39c4ee0388a5dba9e39568/83000/83490/83490.mp4/");

        taskSimple("http://media66.avtb02.com/media/videos/mp4/22983.mp4?st=0G8MzPsdh52_vDccj4wL9w&e=1517131449");

        taskSimple("http://seku.tv/get_file/1/b3f3914b4335a4b60bfbd4065d8aacb36bde46fe51/2000/2351/2351_480p.mp4/?rnd=1517124274430");

        taskSimple("http://seku.tv/get_file/1/8d222c6054f226cfc844868eb53d65bc3a14d948d1/2000/2361/2361_480p.mp4/?rnd=1517124483780");

        taskSimple("http://185.38.13.159//mp43/251664.mp4?st=OkqY4oi8_cnP7yPg6nztWA&e=1517196700");

        taskSimple("http://www.52ppx.com/get_file/1/dc5e0a63c3a54e04850b03f810e916d1/2000/2035/2035.mp4/?rnd=1517124764866");

        taskSimple("http://www.77fcw.com/get_file/1/f0cbbe85258cd5ba10f20129c712917eeb8260d92f/8000/8771/8771.mp4/");

        taskSimple("http://www.77fcw.com/get_file/1/f0cbbe85258cd5ba10f20129c712917eeb8260d92f/8000/8771/8771.mp4/");

    }

    private static void taskSimple(String url) {
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
            System.err.println(submit + " , newly1030");
            e.printStackTrace();
        } finally {
            submit.cancel(true);
            service.shutdown();
        }
    }
}
