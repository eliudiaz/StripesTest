/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.isis.api.misc;

import com.fasterxml.jackson.databind.JsonMappingException;
import gt.org.isis.api.misc.exceptions.BaseException;
import gt.org.isis.api.misc.exceptions.ext.MalformedJsonException;
import gt.org.isis.api.misc.exceptions.ext.UnknownException;
import gt.org.isis.api.misc.exceptions.ext.ValidationError;
import gt.org.isis.api.misc.exceptions.ext.ValidationException;
import java.io.ByteArrayOutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.MediaType;

/**
 * this class is used to handle any Exception, you must create your owns
 * exceptions based in the BaseException class, this handle requires you to
 * declare a custom error code based in the API documentation, in worse cases it
 * will response a http error code 500
 *
 * @author edcracken
 */
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger LOG = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    public GlobalExceptionHandler() {
        super();
    }

    @ExceptionHandler
    public ResponseEntity<Object> handleJsonMappingException(JsonMappingException ex) {
        System.out.println("JsonMappingexception exception has been thrown >> " + ex);
        return handleException(new MalformedJsonException(ex));
    }

    @ExceptionHandler(RuntimeException.class)
    protected ResponseEntity<Object> handleUnknownException(Exception ex, WebRequest request) {
        LOG.error("Unknown exception has been thrown", ex);
        return handleException(new UnknownException());
    }

    @ExceptionHandler(BaseException.class)
    protected ResponseEntity<Object> baseExceptionHandling(BaseException ex, WebRequest request) {
        LOG.info("known exception has been thrown", ex);
        return handleException(ex);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        List<ValidationError> errors = new ArrayList<ValidationError>();

        for (FieldError fieldError : fieldErrors) {
            errors.add(new ValidationError(fieldError
                    .getField()
                    .replaceAll("(.)(\\p{Upper})", "$1_$2").toLowerCase(), fieldError.getDefaultMessage()
            ));
        }

        ValidationException ve = new ValidationException(errors);
        LOG.info("ValidationException", ve);
        return handleException(ve);
    }

    protected ResponseEntity handleException(BaseException ex) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        List<ValidationError> errors;
        if (ex instanceof ValidationException) {
            errors = ((ValidationException) ex).getErrors();
        } else {
            errors = Arrays.asList(new ValidationError("internal_error", ExceptionUtils.getStackTrace(ex)));
        }
        StringBuffer sb = new StringBuffer("<div style=\"color:#b72222; font-weight: bold\">")
                .append("Listado errores:</div><ol>");
        for (ValidationError e : errors) {
            sb = sb.append("<li style=\"color: #b72222;\">").append(e.getMessage()).append("</li>");
        }
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(MediaType.TEXT_HTML);

        return new ResponseEntity<String>(sb.toString(),
                responseHeaders,
                ex.getHttpStatus());
    }
}
