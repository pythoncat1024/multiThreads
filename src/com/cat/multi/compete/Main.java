package com.cat.multi.compete;

/**
 * Created by cat on 2018/1/21.
 */
public class Main {
    public static void main(String[] args) {

        Repertory<String> stringRepertory = new Repertory<>(80);

        PutRunnable<String> putRunnable = new PutRunnable<>(stringRepertory);
        TakeRunnable<String> takeRunnable = new TakeRunnable<>(stringRepertory);

        new Thread(putRunnable, "put-1").start();
//        new Thread(putRunnable, "put-2").start();
//        new Thread(putRunnable, "put-3").start();
//
        new Thread(takeRunnable, "TAKE-1").start();
        new Thread(takeRunnable, "TAKE-2").start();
        new Thread(takeRunnable, "TAKE-3").start();


        ReaderRunnable<String> readerRunnable = new ReaderRunnable<>(stringRepertory);
        new Thread(readerRunnable, "READER-1").start();
        System.out.println("main....done");
    }
}
