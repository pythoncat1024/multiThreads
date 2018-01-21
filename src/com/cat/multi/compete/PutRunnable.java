package com.cat.multi.compete;

/**
 * Created by cat on 2018/1/21.
 * <p>
 * take 取数据 --> 消费者
 */
public class PutRunnable<T> implements Runnable {

    private final Repertory<T> repertory;

    private boolean stop = false;

    public PutRunnable(Repertory<T> repertory) {
        this.x = x;
        this.repertory = repertory;
    }

    public void setStop(boolean stop) {
        this.stop = stop;
    }

    int x = 0;

    @Override
    public void run() {
        String data;
        try {
            while (!stop) {
                if (x % 2 == 0) {
                    data = "烤鸭 " + x;
                } else {
                    data = "duck " + x;
                }
                //noinspection unchecked
                repertory.put((T) data);
                x++;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
