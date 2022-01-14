package ru.java.votingsystem.web;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import ru.java.votingsystem.error.IllegalRequestDataException;
import ru.java.votingsystem.util.exception.ErrorInfo;
import ru.java.votingsystem.util.exception.ErrorType;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

import static ru.java.votingsystem.util.exception.ErrorType.APP_ERROR;
import static ru.java.votingsystem.util.exception.ErrorType.VALIDATION_ERROR;

@RestControllerAdvice(annotations = RestController.class)
public class ExceptionInfoHandler {
    @ExceptionHandler({BindException.class})
    public ResponseEntity<ErrorInfo> bindValidationError(HttpServletRequest req, BindException e) {
        Map<String, String> details = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            details.put(fieldName, errorMessage);
        });

        return getErrorInfo(req, e, VALIDATION_ERROR, details);
    }

    @ExceptionHandler({
            IllegalRequestDataException.class,
            MethodArgumentTypeMismatchException.class,
            HttpMessageNotReadableException.class,
            IllegalArgumentException.class
    })
    public ResponseEntity<ErrorInfo> illegalRequestDataError(HttpServletRequest req, Exception e) {
        return getErrorInfo(req, e, VALIDATION_ERROR, new HashMap<>());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorInfo> handleError(HttpServletRequest req, Exception e) {
        return getErrorInfo(req, e, APP_ERROR, new HashMap<>());
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ErrorInfo> wrongRequest(HttpServletRequest req, NoHandlerFoundException e) {
        return getErrorInfo(req, e, ErrorType.WRONG_REQUEST, new HashMap<>());
    }

    private ResponseEntity<ErrorInfo> getErrorInfo(HttpServletRequest req, Exception e, ErrorType errorType, Map<String, String> details) {
        return ResponseEntity.status(errorType.getStatus())
                .body(new ErrorInfo(
                        req.getRequestURL(),
                        errorType,
                        errorType.getErrorName(),
                        details.isEmpty() ? Map.of("error", e.getMessage()): details
                ));
    }
}