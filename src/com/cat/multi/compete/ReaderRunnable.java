package com.cat.multi.compete;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by cat on 2018/1/21.
 */
public class ReaderRunnable<T> implements Runnable {

    private final Repertory<T> repertory;
    private final Timer timer;

    public ReaderRunnable(Repertory<T> repertory) {
        this.repertory = repertory;
        timer = new Timer();
    }

    @Override
    public void run() {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                repertory.show();
            }
        }, 5000, 5000);
    }
}
