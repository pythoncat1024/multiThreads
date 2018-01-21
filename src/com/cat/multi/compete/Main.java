package com.cat.multi.compete;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by cat on 2018/1/21.
 * --main--
 */
public class Main {
    public static void main(String[] args) {

//        doTasks();

        doTasksOld();
//        simpleTest();
    }

    private static void doTasksOld() {
        Repertory<String> stringRepertory = new Repertory<>(800);

        PutRunnable<String> putRunnable = new PutRunnable<>(stringRepertory);
        TakeRunnable<String> takeRunnable = new TakeRunnable<>(stringRepertory);
        ReaderRunnable<String> readerRunnable = new ReaderRunnable<>(stringRepertory);
        Thread t1 = new Thread(putRunnable, "put-1");
        t1.start();
        Thread t2 = new Thread(putRunnable, "put-2");
        t2.start();
        Thread t3 = new Thread(putRunnable, "put-3");
        t3.start();
//
        Thread th1 = new Thread(takeRunnable, "TAKE-1");
        th1.start();
        Thread th2 = new Thread(takeRunnable, "TAKE-2");
        th2.start();
        Thread th3 = new Thread(takeRunnable, "TAKE-3");
        th3.start();

        Thread tt1 = new Thread(readerRunnable, "READER-1");
        tt1.start();

        Timer stopTimer = new Timer();

        stopTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                // 停止生产，消费，查看
                putRunnable.setStop(true);
                takeRunnable.setStop(true);
                readerRunnable.setStop(true);
                // 关闭全部线程
                t1.interrupt();
                t2.interrupt();
                t3.interrupt();

                th1.interrupt();
                th2.interrupt();
                th3.interrupt();
                tt1.interrupt();

                stopTimer.cancel();
            }
        }, 15 * 1000);
    }

    private static void simpleTest() {
        ExecutorService service = Executors.newCachedThreadPool();
        Future<?> submit = service.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("hello world...");
            }
        });
        submit.cancel(true);
        service.shutdown();
    }

    private static void doTasks() {
        Repertory<String> stringRepertory = new Repertory<>(800);

        PutRunnable<String> putRunnable = new PutRunnable<>(stringRepertory);
        TakeRunnable<String> takeRunnable = new TakeRunnable<>(stringRepertory);
        ReaderRunnable<String> readerRunnable = new ReaderRunnable<>(stringRepertory);

        // 使用线程池替换 new Thread...
        System.out.println("main....start");

        ExecutorService service1 = Executors.newCachedThreadPool();
        Future<?> submit1 = service1.submit(putRunnable); // 启动生产者

        ExecutorService service2 = Executors.newCachedThreadPool();
        Future<?> submit2 = service2.submit(takeRunnable);  // 启动消费者

        ExecutorService service3 = Executors.newFixedThreadPool(3);
        Future<?> submit3 = service3.submit(readerRunnable); // 启动查看器..(仓管视角~)
        System.out.println("main....done");

        Timer stopAll = new Timer(); // 工厂倒闭了，不生产了，不销售了，仓管也被开除了~
        stopAll.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("全部停止....");
                // 停止生产，消费，查看
                putRunnable.setStop(true);
                takeRunnable.setStop(true);
                readerRunnable.setStop(true);
//                showLog();
                // 关闭线程池
                service1.shutdown();
                service2.shutdown();
                service3.shutdown();
                // 关闭未完成的任务 （生产/消费/查看）
                submit1.cancel(true); // 需要传 true ，否则可能关不了 ~
                submit2.cancel(true);
                submit3.cancel(true);

//                showThreads();

                stopAll.cancel(); // 这个关闭全部的定时器也不需要了
//                showThreads();
            }
        }, 15 * 1000);
    }

    private static void showLog() {
        System.err.println("线程状态-" + Thread.currentThread().getThreadGroup().getName() + " , " +
                Thread.currentThread().getThreadGroup().activeCount() + " , " +
                Thread.currentThread().getThreadGroup().activeGroupCount() + " ### " +
                Thread.currentThread().getName() + " , " + Thread.currentThread().getState().name() + " ..."
        );
    }

    private static void showThreads() {
        System.out.println("线程状态-" + Thread.currentThread().getThreadGroup().getName() + " , " +
                Thread.currentThread().getThreadGroup().activeCount() + " , " +
                Thread.currentThread().getThreadGroup().activeGroupCount() + " ### " +
                Thread.currentThread().getName() + " , " + Thread.currentThread().getState().name() + " ..."
        );

        {
            Map<Thread, StackTraceElement[]> traces = Thread.getAllStackTraces();
            Set<Thread> threads = traces.keySet();
            for (Thread th : threads) {
                System.out.println("线程详情--" + th + ":" + Arrays.toString(traces.get(th)));
            }
        }
    }
}
