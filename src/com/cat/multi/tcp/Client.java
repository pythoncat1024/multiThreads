package com.cat.multi.tcp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by cat on 2018/1/21.
 * Client 1
 */
public class Client {

    public static void main(String[] args) throws IOException {

        int port = 8888;
        String addr = InetAddress.getLocalHost().getHostAddress();// 获得本机IP
        System.out.println("self address==" + addr);
        String host = addr;
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

        System.out.println("控制台输入发送内容：");
        while (true) {
            Socket socket = new Socket(host, port);  // 每次连接都创建一个新的 socket 对象 --> 暴力！
            OutputStream os = socket.getOutputStream();
            // 写数据给服务器
            Scanner scanner = new Scanner(System.in);
            String line = scanner.nextLine();
            os.write(line.getBytes());
            os.flush();

            if (line.startsWith(":q") || line.startsWith(":!q")) {
                System.out.println("client exit...");
                break;
            }
            // 接收服务器发送的数据...
            InputStream in = socket.getInputStream();  // 这是阻塞方法? 不是！
            byte[] bytes = new byte[1024];
            int read = 0;
            if ((read = in.read(bytes)) != -1) {
                String receiveFromClient = new String(bytes, 0, read);
                System.out.println(receiveFromClient);
            } else {
                System.err.println("TCP-Client read error:" + read);
            }
        }
    }
}
