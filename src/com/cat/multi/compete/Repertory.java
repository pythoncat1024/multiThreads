package com.cat.multi.compete;

import java.awt.image.LookupOp;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.*;

/**
 * Created by cat on 2018/1/21.
 * 实现模型：
 * <p>
 * 生产者一直生产，直到产品数量达到 N， 就停止生产。
 * <p>
 * 并且，当产品到达 N 的时候，生产者要通知消费者来消费。
 * 同时：
 * <p>
 * 消费者一直消费，直到产品数量为0，就停止消费。
 * <p>
 * 并且，当没有产品的时候，消费者要通知生产者继续生产；
 * <p>
 * 基于多线程的，可以有任意多个生产者，以及任意多个消费者，不限制两个群体的数量。
 * <p>
 * -------------------------------------
 */
public class Repertory<T> {

    private static final int MIN_MAX = 10;
    private static final int SLEEP = 100;
    private static final boolean DEBUG = false;
    private static final boolean Loggable = false;
    private static final boolean LIMIT = false;


    private final Condition putCondition;
    private final Condition takeCondition;

    private List<T> itemList;
    private int max;

    private ReentrantReadWriteLock.WriteLock writeLock;
    private ReentrantReadWriteLock.ReadLock readLock;

    public Repertory() {
        this(MIN_MAX);
    }

    public Repertory(int max) {
        // init collection
        if (max < MIN_MAX) {
            max = MIN_MAX;
        }
        this.max = max;
        if (LIMIT) {
            this.max = 1;
        }
        itemList = new LinkedList<>();
        // init rwLock
        ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
        writeLock = rwLock.writeLock();
        readLock = rwLock.readLock();
        putCondition = writeLock.newCondition();
        takeCondition = writeLock.newCondition();
    }

    private boolean isEmpty() {
        return this.itemList.size() == 0;
    }

    private boolean isFull() {
        return this.itemList.size() == max;
    }

    private boolean needPut() {
        if (LIMIT) {
            return isEmpty();
        }
        return isEmpty() || itemList.size() <= max / 5;
    }

    private boolean needTake() {
        if (LIMIT) {
            return isFull();
        }
        return isFull() || itemList.size() > max * 4 / 5;
    }

    private void checkAccess() {
        if (needPut() && needTake()) {
            throw new RuntimeException("判断条件出错，必须修改程序...");
        }
    }

    /**
     * 该方法只应该被【生产者】线程调用，否则容易出现死锁
     *
     * @param obj 生产的对象
     * @throws InterruptedException 中断异常
     */
    public void put(T obj) throws InterruptedException {

        writeLock.lock();
        try {
            checkAccess();
            while (needTake()) {
                takeCondition.signalAll();
                putCondition.await(); // 满了，就等待，并通知消费者取货
            }
            if (DEBUG || LIMIT) {
                Thread.sleep(SLEEP);
            }
            itemList.add(obj);
            if (DEBUG || Loggable) {
                System.out.println(Thread.currentThread().getName() + "-put--" + obj);
            }
        } finally {
            writeLock.unlock();
        }
    }

    /**
     * 该方法只应该被【消费者】线程调用，否则容易出现死锁
     *
     * @throws InterruptedException 中断异常
     */
    public void take() throws InterruptedException {
        writeLock.lock();
        try {
            checkAccess();
            while (needPut()) {
                putCondition.signalAll(); // TODO:这里要注意，一定要先去唤醒对象，然后再让自己休息！，
                takeCondition.await(); // 否则，自己休息了，也就没有机会唤醒对方了...
            }
            if (DEBUG || LIMIT) {
                Thread.sleep(SLEEP);
            }
            T obj = itemList.remove(0);
            if (DEBUG || Loggable) {
                System.out.println(Thread.currentThread().getName() + "-take--" + obj);
            }
        } finally {
            writeLock.unlock();
        }
    }

    public void show() {
        readLock.lock();
        try {
            System.err.println("SHOW ### " + this.toString());
        } finally {
            readLock.unlock();
        }
    }

    @Override
    public String toString() {
        String msg = "";
        if (DEBUG || Loggable || itemList.size() < MIN_MAX) {
            msg = "Repertory{" +
                    "max=" + max +
                    " , length=" + itemList.size() +
                    " , itemList=" + itemList +
                    '}';
        } else {
            msg = "Repertory{" +
                    "max=" + max +
                    " , length=" + itemList.size() +
                    " , itemList=" + itemList.hashCode() +
                    '}';
        }
        return msg;
    }
}
