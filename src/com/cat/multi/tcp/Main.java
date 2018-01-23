package com.cat.multi.tcp;

import java.io.IOException;
import java.net.InetAddress;

/**
 * Created by cat on 2018/1/22.
 * Client2
 */
public class Main {

    public static void main(String[] args) throws IOException {

        int port = 8888;
        String addr = InetAddress.getLocalHost().getHostAddress();// 获得本机IP
        System.out.println("self address==" + addr);
        String host = addr;
        // client
        Client client = new Client(host, port);
        client.send();
    }
}
