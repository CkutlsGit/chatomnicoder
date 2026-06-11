package ru.chatomnicoder.utils;

public class StringFormat {
    public static String stringMakeWithoutSymbols(String s) {
        return s
                .replace("\\n", "\n")
                .replace("\\\"", "\"")
                .replace("\\\\", "\\")
                .replace("\n", " ")
                .replace("\r", " ")
                .trim();
    }
}
