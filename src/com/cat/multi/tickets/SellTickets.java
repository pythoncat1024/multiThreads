package com.cat.multi.tickets;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by cat on 2018/1/20.
 * 卖票了...
 */
public class SellTickets implements Runnable {


    private int tickets = 100; // default = 100;

    private Lock sellLock = new ReentrantLock();

    public SellTickets(int tickets) {
        this.tickets = tickets;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            sell();
        }
    }

    /**
     * 为何该方法需要被同步锁保护？
     * 因为该方法里面涉及到对共享数据的修改(this.tickets)
     * 而且当前是多线程的环境，如果不保护，就不能让该方法被原子性的访问
     * 原子性的访问(线程xx访问的同时，其他线程只能等待，不能同时访问该方法)
     */
    private void sell() {
        try {
            sellLock.lock(); // 获取锁
            if (this.tickets > 0) {
                System.out.println(Thread.currentThread().getName() + " 当前正在出售第 " + tickets + " 张票.");
                tickets -= 1;
            }
        } finally {
            sellLock.unlock(); // 释放锁
        }
    }
}
