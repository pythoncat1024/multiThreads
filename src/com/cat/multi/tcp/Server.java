package com.cat.multi.tcp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutionException;

/**
 * Created by cat on 2018/1/21.
 * Server
 */
public class Server {


    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {


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

    private boolean stop = false;

    public void service() throws IOException, ExecutionException, InterruptedException {

        ServerSocket ss = new ServerSocket(port);

        System.out.println("开始监听连接过来的客户端：....");

        while (true) {
            if (stop) {
                System.err.println("服务器终于要关闭了....");
                break;
            }
            Socket accept = ss.accept();
            new Thread(new HandleSocket(ss, accept)).start();
        }
        ss.close();
    }

    private class HandleSocket implements Runnable {

        private final Socket accept;
        private final ServerSocket ss;

        public HandleSocket(ServerSocket ss, Socket socket) {
            this.ss = ss;
            this.accept = socket;
        }

        @Override
        public void run() {
            try {

                InputStream in = accept.getInputStream();  // 这是阻塞方法? 不是！
                // 读取客户端发送的数据
                byte[] bytes = new byte[1024];
                int read = 0;
                String receiveFromClient = null;
                if ((read = in.read(bytes)) != -1) {
                    String clientHost = accept.getInetAddress().getHostName();
                    receiveFromClient = new String(bytes, 0, read);
                    if (receiveFromClient.startsWith(":!q")) {
                        System.err.println("收到客户端要关闭服务器的请求了...");
//                        break;  // 收到关闭服务器的消息，就不发消息给客户端了，直接退出服务器
                        stop = true;
                        accept.close();
                        Thread.currentThread().interrupt(); // 既然要关服务器了，把线程也停掉.
                        ss.close();
                        System.err.println("服务器关闭了...");
                        return;
                    }
                    System.out.println("Server:" + clientHost + " , " + receiveFromClient);
                } else {
                    System.err.println("TCP-Server read error:" + read);
                }
                // 每次收到数据，都给客户端一个响应：
                OutputStream os = accept.getOutputStream();
                String toClient = "toClient:" + receiveFromClient;
                os.write(toClient.getBytes());
                os.flush();
                accept.close();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                System.out.println("---finally----");
            }
        }
    }
}
