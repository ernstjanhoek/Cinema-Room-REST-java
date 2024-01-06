package cinema;

import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(AlreadyBookedException.class)
    public ResponseEntity<ErrorMessage> handleAlreadyBooked(AlreadyBookedException e) {
        ErrorMessage body = new ErrorMessage(e.getMessage());
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(SeatIndexOutOfBoundsException.class)
    public ResponseEntity<ErrorMessage> handleSeatIndexOutOfBounds(SeatIndexOutOfBoundsException e) {
        ErrorMessage body = new ErrorMessage(e.getMessage());
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(WrongTokenException.class)
    public ResponseEntity<ErrorMessage> handleWrongToken(WrongTokenException e) {
       ErrorMessage body = new ErrorMessage(e.getMessage());
       return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(WrongPasswordException.class)
    public ResponseEntity<ErrorMessage> handleWrongPassword(WrongPasswordException e) {
        ErrorMessage body = new ErrorMessage(e.getMessage());
        return new ResponseEntity<>(body, HttpStatus.UNAUTHORIZED);
    }
}
