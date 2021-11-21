package ru.java.votingsystem.util.exception;

import org.springframework.http.HttpStatus;

public enum ErrorType {
    APP_ERROR("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR),
    //  http://stackoverflow.com/a/22358422/548473
    DATA_NOT_FOUND("Data not found", HttpStatus.UNPROCESSABLE_ENTITY),
    DATA_ERROR("Data error", HttpStatus.CONFLICT),
    VALIDATION_ERROR("Validation Error", HttpStatus.UNPROCESSABLE_ENTITY),
    WRONG_REQUEST("Wrong request", HttpStatus.BAD_REQUEST);

    private final String errorName;
    private final HttpStatus status;

    ErrorType(String errorName, HttpStatus status) {
        this.errorName = errorName;
        this.status = status;
    }

    public String getErrorName() {
        return errorName;
    }

    public HttpStatus getStatus() {
        return status;
    }
}