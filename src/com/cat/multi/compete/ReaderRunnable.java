package com.cat.multi.compete;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by cat on 2018/1/21.
 * 只读操作
 */
public class ReaderRunnable<T> implements Runnable {

    private final Repertory<T> repertory;
    private final Timer timer;
    private boolean stop;

    public ReaderRunnable(Repertory<T> repertory) {
        this.repertory = repertory;
        timer = new Timer();
    }


    public void setStop(boolean stop) {
        this.stop = stop;
    }

    @Override
    public void run() {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                repertory.show();
                if (stop) {
                    timer.cancel();
                }
            }
        }, 3000, 3000);
    }
}
