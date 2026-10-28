package dasturlash.uz.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    // Biz tashlagan xatolar (topilmadi, balans yetarli emas va h.k.)
    @ExceptionHandler(AppException.class)
    public ResponseEntity<ErrorResponse> handleAppException(AppException ex) {
        ErrorResponse body = new ErrorResponse(
                ex.getStatus().value(), ex.getMessage(), LocalDateTime.now());
        return ResponseEntity.status(ex.getStatus()).body(body);
    }

    // DTO validatsiyasi (@Valid) xatolari
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidation(MethodArgumentNotValidException ex) {
        FieldError fieldError = ex.getBindingResult().getFieldError();
        String message = fieldError != null ? fieldError.getDefaultMessage() : "Noto'g'ri ma'lumot";
        ErrorResponse body = new ErrorResponse(400, message, LocalDateTime.now());
        return ResponseEntity.badRequest().body(body);
    }

    // Mavjud bo'lmagan URL. Bu holat bo'lmasa 500 bo'lib ketardi
    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ErrorResponse> handleNoResource(NoResourceFoundException ex) {
        ErrorResponse body = new ErrorResponse(404, "Sahifa topilmadi", LocalDateTime.now());
        return ResponseEntity.status(404).body(body);
    }

    // Kutilmagan barcha xatolar: ichki ma'lumot klientga chiqmaydi, faqat logga yoziladi
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleUnexpected(Exception ex) {
        log.error("Kutilmagan xatolik", ex);
        ErrorResponse body = new ErrorResponse(500, "Ichki server xatosi", LocalDateTime.now());
        return ResponseEntity.status(500).body(body);
    }
}
