package app.errorhandling

import org.axonframework.commandhandling.CommandExecutionException
import org.springframework.http.{HttpHeaders, HttpStatus, ResponseEntity}
import org.springframework.web.bind.annotation.{ControllerAdvice, ExceptionHandler}
import org.springframework.web.context.request.WebRequest

import java.util.Date

@ControllerAdvice
class ProductsErrorHandler {
  @ExceptionHandler(Array(classOf[IllegalStateException]))
  def handle(exc: IllegalStateException, r: WebRequest): ResponseEntity[ErrorMessage] = errorToResponse(exc)

  @ExceptionHandler(Array(classOf[Exception]))
  def handle(exc: Exception, r: WebRequest): ResponseEntity[ErrorMessage] = errorToResponse(exc)

  @ExceptionHandler(Array(classOf[CommandExecutionException]))
  def handle(exc: CommandExecutionException, r: WebRequest): ResponseEntity[ErrorMessage] = errorToResponse(exc)

  private def errorToResponse(exc: Exception) =
    new ResponseEntity(ErrorMessage(new Date(), exc.getMessage), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR)
}
