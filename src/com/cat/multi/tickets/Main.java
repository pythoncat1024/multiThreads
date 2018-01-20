package com.cat.multi.tickets;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");

        SellTickets tickets = new SellTickets(100);

        Thread t1 = new Thread(tickets, "Window-1");
        Thread t2 = new Thread(tickets, "Window-2");
        Thread t3 = new Thread(tickets, "Window-3");

        t1.start();
        t2.start();
        t3.start();
    }
}
