package com.cat.multi.tcp;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;

/**
 * Created by cat on 2018/1/21.
 */
public class Client {

    public static void main(String[] args) throws IOException {

        int port = 8888;
        String host = "127.0.0.1";
        // client
        Client client = new Client(host, port);
        client.send();
    }

    private final String host;
    private final int port;

    public Client(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void send() throws IOException {
        Socket socket = new Socket();

        socket.connect(new InetSocketAddress(host,port));
        OutputStream os = socket.getOutputStream();
        System.out.println("控制台输入发送内容：");
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();

        os.write(line.getBytes());
        os.flush();
//        while (true) {
//            System.out.println("socket--"+socket.isClosed());
//            if (line.startsWith(":q") || line.startsWith(":!q")) {
//                System.err.println("Client 主动退出了...");
//                break;
//            }
//            os.write(line.getBytes());
//            os.flush();
//        }
    }
}
