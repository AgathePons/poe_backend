package survey.backend.handler;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import survey.backend.error.BadRequestError;
import survey.backend.error.ErrorMessage;
import survey.backend.error.NoDataFoundError;
import survey.backend.error.jwt.DisabledUserException;
import survey.backend.error.jwt.InvalidCredentialsException;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class ErrorHandler extends ResponseEntityExceptionHandler {

  /**
   * Override default handling of validation errors
   * @param ex
   * @param headers
   * @param status
   * @param request
   * @return
   */
  @Override
  public ResponseEntity<Object> handleMethodArgumentNotValid (
          MethodArgumentNotValidException exception,
          HttpHeaders headers,
          HttpStatus status,
          WebRequest request
          ) {
    return ResponseEntity.status(status).body("Data not valid");
  }

  /**
   * handle custom no data found error
   * @param exception no data found exception
   * @param request
   * @return HTTP response entity
   */
  @ExceptionHandler(NoDataFoundError.class)
  public ResponseEntity<ErrorMessage> handleNoDataFoundError(
          Exception exception, WebRequest request
  ){
    return responseEntity(HttpStatus.NOT_FOUND, exception);
  }

  /**
   * handle custom bad request error
   * @param exception bad request exception
   * @param request
   * @return HTTP response entity
   */
  @ExceptionHandler(BadRequestError.class)
  public ResponseEntity<ErrorMessage> handleBadRequest(
          Exception exception, WebRequest request
  ) {
    return responseEntity(HttpStatus.BAD_REQUEST, exception);
  }

  /**
   * handle all authentication errors
   * @param exception authentication exception
   * @param request
   * @return HTTP response entity
   */
  @ExceptionHandler({InvalidCredentialsException.class, DisabledUserException.class})
  public ResponseEntity<ErrorMessage> handleUserAuthenticationException(
          Exception exception, WebRequest request
  ) {
    return responseEntity(HttpStatus.UNAUTHORIZED, exception);
  }

  @ExceptionHandler(DataIntegrityViolationException.class)
  public ResponseEntity<ErrorMessage> handleDataIntegrityViolationException(
          Exception exception, WebRequest request
  ) {
    return responseEntity(HttpStatus.BAD_REQUEST, exception);
  }

  /**
   * Build an HTTP response with:
   * - an HTTP status
   * - the error message from the source exception
   * @param httpStatus HTTP status
   * @param exception exception source
   * @return response built
   */
  private static ResponseEntity<ErrorMessage> responseEntity(HttpStatus httpStatus, Exception exception) {
    return ResponseEntity.status(httpStatus)
            .body(
                    ErrorMessage.builder()
                            .status(httpStatus.value())
                            .error(exception.getMessage())
                            .build()
            );
  }
}
