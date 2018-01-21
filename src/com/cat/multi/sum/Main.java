package com.cat.multi.sum;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * Created by cat on 2018/1/21.
 */
public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException {


        System.out.println("main--done");
        ExecutorService service = Executors.newCachedThreadPool();
        Integer sum1 = service.submit(new SumCallable(100)).get();

        System.out.println("sum1==" + sum1);
        Integer sum2 = service.submit(new SumCallable(100, 200)).get();
        System.out.println("sum2==" + sum2);

        if (!service.isShutdown() || !service.isTerminated()) {
            service.shutdown();
            System.out.println("kill service...");
        }


        Map<Thread, StackTraceElement[]> traces = Thread.getAllStackTraces();
        Set<Thread> threads = traces.keySet();

        for (Thread th : threads) {
            System.out.println(th + ":" + Arrays.toString(traces.get(th)));
        }
    }
}
