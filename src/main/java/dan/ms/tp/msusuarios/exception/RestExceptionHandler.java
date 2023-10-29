package dan.ms.tp.msusuarios.exception;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class RestExceptionHandler {

    private static String ValidationHeaderMessage = "Error de Validacion";
    
    @ExceptionHandler(value= {ApiValidationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResponseEntity<ApiErrorInfo> apiValidationError(ApiValidationException e){
        HttpStatus returnStatus = HttpStatus.BAD_REQUEST; 

        ApiErrorInfo apiErrorInfo = new ApiErrorInfo();
        apiErrorInfo.title = ValidationHeaderMessage;
        apiErrorInfo.status = returnStatus.value();
        apiErrorInfo.detail = e.getMessage();

        return new ResponseEntity<>(apiErrorInfo, returnStatus);
    }
}
