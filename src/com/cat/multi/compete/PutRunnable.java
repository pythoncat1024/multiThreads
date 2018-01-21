package com.cat.multi.compete;

/**
 * Created by cat on 2018/1/21.
 */
public class PutRunnable<T> implements Runnable {

    private final Repertory<T> repertory;

    public PutRunnable(Repertory<T> repertory) {
        this.x = x;
        this.repertory = repertory;
    }

    int x = 0;

    @Override
    public void run() {
        String data;
        try {
            while (true) {
                if (x % 2 == 0) {
                    data = "烤鸭 " + x;
                } else {
                    data = "duck " + x;
                }
                repertory.put((T) data);
                x++;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
