package exception;

import lombok.Getter;
import validator.Error;

import java.util.List;
import java.util.Collections;

@Getter
public class ValidationException extends RuntimeException {

    private final List<Error> errors;

    public ValidationException(List<Error> errors) {
        this.errors = errors != null ? List.copyOf(errors) : Collections.emptyList();
    }

    public ValidationException(String message) {
        super(message);
        this.errors = List.of(Error.of("validation.error", message));
    }

    public ValidationException(String code, String message) {
        super(message);
        this.errors = List.of(Error.of(code, message));
    }
}