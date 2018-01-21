package com.cat.multi.compete;

/**
 * Created by cat on 2018/1/21.
 * <p>
 * take 取数据 --> 消费者
 */
public class TakeRunnable<T> implements Runnable {

    private final Repertory<T> repertory;
    private boolean stop;

    public TakeRunnable(Repertory<T> repertory) {
        this.repertory = repertory;
    }

    public void setStop(boolean stop) {
        this.stop = stop;
    }

    @Override
    public void run() {
        try {
            while (!stop) {
                repertory.take();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}