package com.cat.multi.tickets;

/**
 * Created by cat on 2018/1/20.
 * 卖票了...
 */
public class SellTickets implements Runnable {


    private int tickets = 100; // default = 100;

    public SellTickets(int tickets) {
        this.tickets = tickets;
    }

    @Override
    public void run() {
        while (true) {

//            try {
//                Thread.sleep(100);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            if (this.tickets > 0) {
                System.out.println(Thread.currentThread().getName() + " 当前正在出售第 " + tickets + " 张票.");
                tickets -= 1;
            }
        }
    }
}
