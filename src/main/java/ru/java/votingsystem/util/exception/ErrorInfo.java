package ru.java.votingsystem.util.exception;

import java.util.Map;

public class ErrorInfo {
    private final String url;
    private final ErrorType type;
    private final String typeMessage;
    private final Map<String, String> details;

    public ErrorInfo(CharSequence url, ErrorType type, String typeMessage, Map<String, String> details) {
        this.url = url.toString();
        this.type = type;
        this.typeMessage = typeMessage;
        this.details = details;
    }
}