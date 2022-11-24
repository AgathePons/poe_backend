package survey.backend.handler;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import survey.backend.error.ErrorMessage;
import survey.backend.error.NoDataFoundError;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class ErrorHandler extends ResponseEntityExceptionHandler {

  @Override
  public ResponseEntity<Object> handleMethodArgumentNotValid (
          MethodArgumentNotValidException exception,
          HttpHeaders headers,
          HttpStatus status,
          WebRequest request
          ) {
    return ResponseEntity.status(status).body("Data not valid");
  }

  @ExceptionHandler(NoDataFoundError.class)
  public ResponseEntity<ErrorMessage> handleNoDataFoundError(Exception exception, WebRequest request) {
    // TODO: send error jsonified
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
            ErrorMessage.builder()
                    .status(404)
                    .error(exception.getMessage())
                    .build()
    );
  }
}
