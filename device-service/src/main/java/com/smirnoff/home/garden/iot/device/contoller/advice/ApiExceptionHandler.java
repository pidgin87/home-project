package com.smirnoff.home.garden.iot.device.contoller.advice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.time.LocalDateTime;
import java.util.UUID;

@ControllerAdvice
@Slf4j
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @Value("${spring.application.name}")
    public String applicationName;

    @Nullable
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException exception,
                                                                   HttpHeaders headers,
                                                                   HttpStatusCode status,
                                                                   WebRequest request) {
        String errorGuid = UUID.randomUUID().toString();
        log.error(errorGuid, exception);
        return prepare404(errorGuid, exception);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex,
                                                             Object body,
                                                             HttpHeaders headers,
                                                             HttpStatusCode statusCode,
                                                             WebRequest request) {
        if (ex instanceof NoResourceFoundException nrfe) {
            String errorGuid = UUID.randomUUID().toString();
            log.error(errorGuid, nrfe);
            return prepare404(errorGuid, nrfe);
        }
        return super.handleExceptionInternal(ex, body, headers, statusCode, request);
    }

    private ResponseEntity<Object> prepare404(String errorGuid, NoHandlerFoundException exception) {
        return prepare404(errorGuid, exception.getBody().getDetail(), 404, exception.getRequestURL());
    }

    private ResponseEntity<Object> prepare404(String errorGuid, NoResourceFoundException nrfe) {
        return prepare404(errorGuid, nrfe.getBody().getDetail(), 404, nrfe.getResourcePath());
    }

    private ResponseEntity<Object> prepare404(String errorGuid, String detail, int status, String resourcePath) {
        return ResponseEntity.status(404).body(new ErrorResponseDto(
                applicationName,
                errorGuid,
                LocalDateTime.now(),
                detail,
                status,
                "Not Found",
                resourcePath
        ));
    }

    public record ErrorResponseDto(
            String application,
            String uuid,
            LocalDateTime timestamp,
            String message,
            Integer status,
            String error,
            String path
    ) {
    }

}
