package com.pycat.multi;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * Created by cat on 2018/8/13.
 *
 * @implNote who you are
 */
public class Main {

    public static void main(String[] args) {
        ThreadFactory factory = Executors.defaultThreadFactory();
        Thread th = factory.newThread(() -> {

            for (int i = 0; i < 10; i++) {
                System.out.print("Nice! ");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        th.start();
    }
}
