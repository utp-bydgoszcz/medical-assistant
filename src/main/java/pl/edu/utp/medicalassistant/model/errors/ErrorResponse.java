package pl.edu.utp.medicalassistant.model.errors;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
public class ErrorResponse {

    private int errorCode;
    private String message;
    private ErrorType errorType;
    private HttpStatus httpStatus;

    public ErrorResponse(int errorCode, String message, ErrorType errorType, HttpStatus httpStatus) {
        this.errorCode = errorCode;
        this.message = message;
        this.errorType = errorType;
        this.httpStatus = httpStatus;
    }
}
