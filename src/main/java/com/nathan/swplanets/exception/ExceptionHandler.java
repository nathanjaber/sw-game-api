package com.nathan.swplanets.exception;

import com.fasterxml.jackson.databind.JsonMappingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.naming.ServiceUnavailableException;
import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class ExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
    public @ResponseBody
    ExceptionDTO handleBadRequest(HttpServletRequest req, Exception ex) {
        String errorMessage = buildErrorItems(((MethodArgumentNotValidException) ex).getBindingResult());
        return new ExceptionDTO(errorMessage);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @org.springframework.web.bind.annotation.ExceptionHandler(HttpMessageNotReadableException.class)
    public @ResponseBody
    ExceptionDTO handleIlegalArgument2(HttpServletRequest req, Exception ex) {
        String message = handleNotReadableMessage(req, (HttpMessageNotReadableException) ex);
        if (message != null && message.matches(".*request\\sbody\\sis\\smissing.*")) {
            message = "body can not be null";
        }
        return new ExceptionDTO(message);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @org.springframework.web.bind.annotation.ExceptionHandler({HttpMediaTypeNotSupportedException.class})
    @ResponseBody
    public ExceptionDTO handleHttpMediaTypeNotSupportedException(HttpServletRequest req, HttpMediaTypeNotSupportedException e) {
        String errorMessage = "Content type can not be empty";
        if (!ObjectUtils.isEmpty(e.getContentType())) {
            errorMessage = "Content type '" + e.getContentType() + "' is not supported";
        }
        return new ExceptionDTO(errorMessage);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @org.springframework.web.bind.annotation.ExceptionHandler(NullPointerException.class)
    public @ResponseBody
    ExceptionDTO handleNullPointer(HttpServletRequest req, Exception ex) {
        return new ExceptionDTO("java.lang.NullPointerException");
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public @ResponseBody
    ExceptionDTO handleInternalError(HttpServletRequest req, Exception ex) {
        return new ExceptionDTO(ex.getMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @org.springframework.web.bind.annotation.ExceptionHandler(InternalError.class)
    public @ResponseBody
    ExceptionDTO handleInternalErrorImpl(HttpServletRequest req, Exception ex) {
        return new ExceptionDTO(ex.getMessage());
    }

    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    @org.springframework.web.bind.annotation.ExceptionHandler(ServiceUnavailableException.class)
    public @ResponseBody
    ExceptionDTO handleServiceUnavailableError(HttpServletRequest req, Exception ex) {
        return new ExceptionDTO(ex.getMessage());
    }

    private String buildErrorItems(BindingResult bindingResult) {
        if (bindingResult.getFieldErrors().isEmpty()) {
            return "";
        }

        return bindingResult.getFieldErrors().stream().findFirst().get().getDefaultMessage();
    }

    private String handleNotReadableMessage(HttpServletRequest req, HttpMessageNotReadableException e) {
        if (e.getCause() instanceof JsonMappingException) {
            return buildErrorMessage((JsonMappingException) e.getCause());
        }

        return e.getMessage();
    }

    private String buildErrorMessage(JsonMappingException e) {
        String fieldName = e.getPath().get(0).getFieldName();
        return fieldName + "is not valid";
    }
}
