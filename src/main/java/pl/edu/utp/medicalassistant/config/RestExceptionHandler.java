package pl.edu.utp.medicalassistant.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import pl.edu.utp.medicalassistant.exception.FileException;
import pl.edu.utp.medicalassistant.model.errors.ErrorResponse;
import pl.edu.utp.medicalassistant.model.errors.ErrorType;

import java.io.IOException;
import java.net.ConnectException;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(HttpClientErrorException.class)
    protected ResponseEntity<Object> httpClientErrorExceptionHandler(HttpClientErrorException ex, WebRequest request) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        ErrorResponse exception = mapper.readValue(ex.getResponseBodyAsString(), ErrorResponse.class);
        if (exception.getErrorType().equals(ErrorType.WARNING)) {
//            log.warn("Request: " + " raised " + "\n" + exception.getMessage());
        } else if (exception.getErrorType().equals(ErrorType.ERROR)) {
//            log.error("Request: " + " raised " + "\n" + exception.getMessage());
        }
        return handleExceptionInternal(ex, exception, new HttpHeaders(), exception.getHttpStatus(), request);
    }

    @ExceptionHandler(HttpServerErrorException.class)
    protected ResponseEntity<Object> httpServerErrorExceptionHandler(HttpServerErrorException ex, WebRequest request) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        ErrorResponse exception = mapper.readValue(ex.getResponseBodyAsString(), ErrorResponse.class);
        if (exception.getErrorType().equals(ErrorType.WARNING)) {
//            log.warn("Request: " + " raised " + "\n" + exception.getMessage());
        } else if (exception.getErrorType().equals(ErrorType.ERROR)) {
//            log.error("Request: " + " raised " + "\n" + exception.getMessage());
        }
        return handleExceptionInternal(ex, exception, new HttpHeaders(), exception.getHttpStatus(), request);
    }

    @ExceptionHandler(ConnectException.class)
    protected ResponseEntity<Object> connectExceptionHandler(RuntimeException ex, WebRequest request) {
//        log.error("Request: " + " raised " + "\n" + "Serwer niedostępny, proszę spróbować ponownie");
        return handleExceptionInternal(ex, new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Serwer niedostępny, proszę spróbować ponownie", ErrorType.ERROR, HttpStatus.INTERNAL_SERVER_ERROR), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    @ExceptionHandler(FileException.class)
    protected ResponseEntity<ErrorResponse> fileException(RuntimeException ex, WebRequest request) {
//        log.error(ex.getMessage());
        return new ResponseEntity<>(new ErrorResponse(HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(), ErrorType.WARNING, HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
    }

}
