package ru.java.votingsystem.util.exception;

import java.util.Map;

public class ErrorInfo {
    private final String url;
    private final ErrorType type;
    private final String typeMessage;
    private final String message;

    public ErrorInfo(CharSequence url, ErrorType type, String typeMessage, String message) {
        this.url = url.toString();
        this.type = type;
        this.typeMessage = typeMessage;
        this.message = message;
    }
}