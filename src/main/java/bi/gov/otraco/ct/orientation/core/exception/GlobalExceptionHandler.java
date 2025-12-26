package bi.gov.otraco.ct.orientation.core.exception;

import bi.gov.otraco.ct.orientation.core.dto.MessageResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.bind.MethodArgumentNotValidException;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RemoteApiException.class)
    public Mono<ResponseEntity<ErrorResponse>> handleRemoteApiException(RemoteApiException ex, ServerWebExchange exchange) {
        ErrorResponse error = ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .error("Erreur de validation")
                .message(ex.getMessage())
                .path(exchange.getRequest().getPath().toString())
                .build();
        return Mono.just(ResponseEntity.badRequest().body(error));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Mono<ResponseEntity<MessageResponse>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        String errors = ex.getBindingResult().getFieldErrors().stream()
                .map(err -> err.getField() + " : " + err.getDefaultMessage())
                .reduce((msg1, msg2) -> msg1 + "; " + msg2)
                .orElse("Requête invalide");

        return Mono.just(ResponseEntity.badRequest()
                .body(new MessageResponse(false, errors)));
    }

    @ExceptionHandler(WebExchangeBindException.class)
    public Mono<ResponseEntity<MessageResponse>> handleWebFluxValidationExceptions(WebExchangeBindException ex) {
        String errors = ex.getFieldErrors().stream()
                .map(err -> err.getField() + " : " + err.getDefaultMessage())
                .reduce((msg1, msg2) -> msg1 + "; " + msg2)
                .orElse("Requête invalide");

        return Mono.just(ResponseEntity.badRequest()
                .body(new MessageResponse(false, errors)));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public Mono<ResponseEntity<ErrorResponse>> handleIllegalArgumentException(
            IllegalArgumentException ex, ServerWebExchange exchange) {
        ErrorResponse error = ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .error("Requête Invalide")
                .message(ex.getMessage())
                .path(exchange.getRequest().getPath().toString())
                .build();
        return Mono.just(ResponseEntity.badRequest().body(error));
    }

    @ExceptionHandler(Exception.class)
    public Mono<ResponseEntity<ErrorResponse>> handleAllExceptions(Exception ex, ServerWebExchange exchange) {
        ErrorResponse error = ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .error("Erreur Interne du Serveur")
                .message("Une erreur est survenue lors du traitement de votre demande")
                .path(exchange.getRequest().getPath().toString())
                .build();
        return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error));
    }
}