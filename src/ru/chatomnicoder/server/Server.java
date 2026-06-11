package ru.chatomnicoder.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public void start() {
        try {
            ServerSocket serverSocket = new ServerSocket(8080);
            System.out.println("Сервер старт!");

            while (true) {
                Socket user = serverSocket.accept();

                System.out.println("Пользователь  подключился: " + user.getInetAddress());
                new Thread(new ClientHandler(user)).start();
            }
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
