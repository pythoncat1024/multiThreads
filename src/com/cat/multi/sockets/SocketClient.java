package com.cat.multi.sockets;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Created by cat on 2018/1/21.
 */
public class SocketClient {

    public static void main(String[] args) throws IOException {

        Socket socket = new Socket("127.0.0.1", 8888);

        InetAddress inetAddress = socket.getInetAddress();

        System.out.println("SocketClient:IntelAddress:" + inetAddress);

        OutputStream os = socket.getOutputStream();

        os.write("hello, server, i am client".getBytes());

        os.flush();

        os.close();

        System.out.println("client... die..");
    }
}
