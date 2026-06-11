package ru.chatomnicoder.server;

import ru.chatomnicoder.ai.LMStudioClient;
import ru.chatomnicoder.model.Message;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

class ClientHandler implements Runnable {
    private final Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private List<Message> chatHistory = new ArrayList<>();

    public ClientHandler(Socket socket) {
        this.socket = socket;

        try {
            this.in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            this.out = new PrintWriter(this.socket.getOutputStream(), true);
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void run() {
        try (socket) {
            String message;

            while ((message = in.readLine()) != null) {
                System.out.println("Сообщение от клиента " + socket.getInetAddress() + ": " + message);

                String answer = LMStudioClient.ask(chatHistory);
                out.println("ИИ: " + answer);

                addHistory(message, answer);
                checkSizeHistory();
            }
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void addHistory(String message, String answer) {
        chatHistory.add(new Message("user", message));
        chatHistory.add(new Message("assistant",answer));
    }

    private void checkSizeHistory() {
        if (chatHistory.size() > 20) {
            chatHistory.removeFirst();
        }
    }
}