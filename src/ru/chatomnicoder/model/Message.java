package ru.chatomnicoder.model;

import ru.chatomnicoder.utils.StringFormat;

public class Message {
    private String role;
    private String message;

    public Message(String role, String message) {
        this.role = role;
        this.message = message;
    }

    public String toJson() {
        return String.format("{\"role\": \"%s\", \"content\": \"%s\"}",
                role, StringFormat.stringMakeWithoutSymbols(message)
        );
    }
}
