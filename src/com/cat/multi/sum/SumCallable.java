package com.cat.multi.sum;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;

/**
 * Created by cat on 2018/1/21.
 */
public class SumCallable implements Callable<Integer> {

    private static final boolean DEBUG = true;
    private final int start;
    private final int end;

    public SumCallable(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public SumCallable(int end) {
        this(0, end);
    }

    @Override
    public Integer call() throws Exception {

        int result = 0;
        if (DEBUG) {
            for (long x = start; x <= end; x++) {
                result += x;
                Thread.sleep(10);

                if (x % 20 == 0) {
                    System.out.println("----x=" + x + " , start==" + start);
                }
            }
        } else {
            // 1+2+3+4
            // (2+4)*(5-2)/2 = 6*3/2= 9 = 2+3+4;
            result = (start + end) * (1 + end - start) / 2;
        }

        System.err.println(Thread.currentThread().getThreadGroup().getName() + " , " +
                Thread.currentThread().getThreadGroup().activeCount() + " , " +
                Thread.currentThread().getThreadGroup().activeGroupCount() + " ### " +
                Thread.currentThread().getName() + " , " + Thread.currentThread().getState().name() + " ..."
        );

        System.out.println("\n##########\n");
        Map<Thread, StackTraceElement[]> traces = Thread.getAllStackTraces();
        Set<Thread> threads = traces.keySet();

        for (Thread th : threads) {
            System.err.println(th + ":" + Arrays.toString(traces.get(th)));
        }
        System.out.println("==============");
        return result;
    }
}
