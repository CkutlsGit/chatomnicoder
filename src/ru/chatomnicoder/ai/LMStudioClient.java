package ru.chatomnicoder.ai;

import ru.chatomnicoder.model.Message;
import ru.chatomnicoder.utils.JsonFormat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class LMStudioClient {
    private static final URL URL_SERVER_LLM;

    static {
        try {
            URL_SERVER_LLM = new URL("http://127.0.0.1:1234/v1/chat/completions");
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    public static String ask(List<Message> chatHistory) throws IOException {
        try {
            HttpURLConnection connection = getUrlConnection(chatHistory);

            int responseCode = connection.getResponseCode();
            System.out.println(responseCode);

            BufferedReader reader = new BufferedReader(new InputStreamReader(responseCode >= 200 && responseCode < 300 ? connection.getInputStream() : connection.getErrorStream()));

            String line;
            StringBuilder response = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            return JsonFormat.parseResponseAI(String.valueOf(response));
        }
        catch (IOException e) {
            throw new IOException("Ошибка при запросе к ИИ");
        }
    }

    private static HttpURLConnection getUrlConnection(List<Message> chatHistory) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) URL_SERVER_LLM.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);

        String jsonResponse = JsonFormat.buildJsonRequestAI(chatHistory, "{\"messages\": %s, \"max_tokens\": 40000, \"temperature\": 0.6}");

        try (OutputStream os = connection.getOutputStream()) {
            byte[] input = jsonResponse.getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }
        return connection;
    }
}
