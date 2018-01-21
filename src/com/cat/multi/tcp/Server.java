package com.cat.multi.tcp;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executors;

/**
 * Created by cat on 2018/1/21.
 */
public class Server {


    public static void main(String[] args) throws IOException {


        int port = 8888;
        String host = "127.0.0.1";
        // server
        Server server = new Server(port);
        server.service();
    }

    private final int port;

    public Server(int port) {
        this.port = port;
    }

    public void service() throws IOException {

        ServerSocket ss = new ServerSocket(port);

        System.out.println("开始监听连接过来的客户端：....");

        Socket accept = ss.accept();

        InputStream in = accept.getInputStream();  // 这是阻塞方法? 不是！
        System.out.println(" end...");
        byte[] bytes = new byte[1024];
        int read = 0;
        while ((read=in.read(bytes))!=-1){
            String clientHost = accept.getInetAddress().getHostName();
            String receiveFromClient = new String(bytes, 0, read);
            System.out.println("Server:" + clientHost + " , " + receiveFromClient);
        }
    }
}
