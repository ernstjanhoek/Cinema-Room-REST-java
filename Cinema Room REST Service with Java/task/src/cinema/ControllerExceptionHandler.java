package cinema;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler({IllegalStateException.class, IllegalArgumentException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage badRequest(Exception e) {
        return new ErrorMessage(e.getMessage());
    }

    @ExceptionHandler({WrongPasswordException.class})
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    public ErrorMessage unauthorized(WrongPasswordException e) {
        return new ErrorMessage(e.getMessage());
    }

    public record ErrorMessage(String error) {}
}
