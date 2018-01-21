package com.cat.multi.tcp;

import java.io.IOException;

/**
 * Created by cat on 2018/1/22.
 * Client2
 */
public class Main {

    public static void main(String[] args) throws IOException {

        int port = 8888;
        String host = "127.0.0.1";
        // client
        Client client = new Client(host, port);
        client.send();
    }
}
