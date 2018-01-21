package com.cat.multi.sockets;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by cat on 2018/1/21.
 */
public class SocketServer {

    public static void main(String[] args) throws IOException {

        ServerSocket ss = new ServerSocket(8888);

        Socket accept = ss.accept();

        InetAddress inetAddress = accept.getInetAddress();

        System.out.println("SocketServer:intelAddress:"+inetAddress);
        InputStream is = accept.getInputStream();

        byte[] buffer = new byte[2048];
        int read = is.read(buffer);


        java.lang.String from = new java.lang.String(buffer, 0, read);

        System.out.println("receive:" + from);

//        accept.close();

//        ss.close();
    }
}
