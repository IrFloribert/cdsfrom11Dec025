package bi.gov.otraco.ct.orientation.core.exception;

import bi.gov.otraco.ct.orientation.core.dto.MessageResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<MessageResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
        String errors = ex.getBindingResult().getFieldErrors().stream()
                .map(err -> err.getField() + " : " + err.getDefaultMessage())
                .reduce((msg1, msg2) -> msg1 + "; " + msg2)
                .orElse("Requête invalide");

        return ResponseEntity.badRequest()
                .body(new MessageResponse(false,  errors));
    }

    @ExceptionHandler(WebExchangeBindException.class)
    public ResponseEntity<MessageResponse> handleWebFluxValidationExceptions(WebExchangeBindException ex) {
        String errors = ex.getFieldErrors().stream()
                .map(err -> err.getField() + " : " + err.getDefaultMessage())
                .reduce((msg1, msg2) -> msg1 + "; " + msg2)
                .orElse("Requête invalide");

        return ResponseEntity.badRequest()
                .body(new MessageResponse(false,  errors));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<MessageResponse> handleGenericException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new MessageResponse(false,  ex.getMessage()));
    }
}
