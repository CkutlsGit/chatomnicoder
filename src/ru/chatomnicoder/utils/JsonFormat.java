package ru.chatomnicoder.utils;

import ru.chatomnicoder.model.Message;

import java.util.List;

public class JsonFormat {
    public static String buildJsonRequestAI(List<Message> chatHistory, String body) {
        StringBuilder messagesJson = new StringBuilder("[");

        for (int i = 0; i < chatHistory.size(); i++) {
            if (i > 0) {
                messagesJson.append(",");
            }
            messagesJson.append(chatHistory.get(i).toJson());
        }
        messagesJson.append("]");

        return String.format(
                body,
                messagesJson.toString()
        );
    }

    public static String parseResponseAI(String json) {
        String searchFor = "\"content\":\"";
        int start = json.indexOf(searchFor);

        if (start == -1) {
            searchFor = "\"content\": \"";
            start = json.indexOf(searchFor);
        }

        if (start != -1) {
            start += searchFor.length();
            int end = json.indexOf("\"", start);

            if (end != -1) {
                String content = json.substring(start, end);

                return StringFormat.stringMakeWithoutSymbols(content);
            }
        }

        return "Не удалось извлечь данные.";
    }
}
