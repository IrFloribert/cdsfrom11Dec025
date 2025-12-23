package bi.gov.otraco.ct.orientation.core.exception;

import bi.gov.otraco.ct.orientation.core.dto.FieldsValidatorResponse;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class RemoteApiException extends RuntimeException {

    private final HttpStatus status;
    private final FieldsValidatorResponse validation;

    public RemoteApiException(HttpStatus status, String message) {
        super(message);
        this.status = status;
        this.validation = null;
    }

    public RemoteApiException(HttpStatus status, FieldsValidatorResponse validation) {
        super("Validation error");
        this.status = status;
        this.validation = validation;
    }
}