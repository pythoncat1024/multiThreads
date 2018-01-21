package com.cat.multi.compete;

/**
 * Created by cat on 2018/1/21.
 */
public class TakeRunnable<T> implements Runnable {

    private final Repertory<T> repertory;

    public TakeRunnable(Repertory<T> repertory) {
        this.repertory = repertory;
    }

    @Override
    public void run() {
        try {
            while (true) {
                repertory.take();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}