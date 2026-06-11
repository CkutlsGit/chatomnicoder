package ru.chatomnicoder.client;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private final Socket clientSocket;
    private final BufferedReader in;
    private final PrintWriter out;

    public Client(Socket socket) throws IOException {
        clientSocket = socket;
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        out = new PrintWriter(clientSocket.getOutputStream(), true);
    }

    public void run() {
        try (clientSocket) {
            Thread readerThread = new Thread(() -> {
                try {
                    String response;
                    while ((response = in.readLine()) != null) {
                        System.out.println("Сообщение от сервера: " + response);
                    }
                }
                catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            });
            readerThread.start();
            requestInput();

        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void requestInput() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите сообщение или код 0 для выхода: ");

        while (true) {
            String message = scanner.nextLine();

            if ("0".equals(message)) {
                break;
            }

            out.println(message);
            System.out.println("Отправлено");
        }

        scanner.close();
        clientSocket.close();
    }
}
