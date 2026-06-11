package ru.chatomnicoder;

import ru.chatomnicoder.client.Client;
import ru.chatomnicoder.server.Server;

import java.io.IOException;
import java.net.Socket;

public class Main {
    public static void main(String[] args) {
        Server server = new Server();
        Thread serverThread = new Thread(server::start);
        serverThread.start();

        try {
            Thread.sleep(1000);
        }
        catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }

        try {
            Client client = new Client(new Socket("localhost", 8080));
            Thread clientThread = new Thread(client::run);
            clientThread.start();
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
