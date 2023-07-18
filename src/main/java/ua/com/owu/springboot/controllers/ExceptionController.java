package ua.com.owu.springboot.controllers;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice // @RestControllerAdvice - це анотація в Spring Framework, яка вказує, що клас є глобальним обробником виключень для всього контексту додатку. Він поєднує в собі функціональність @ControllerAdvice та @ResponseBody, що дозволяє повертати відповіді у форматі JSON або іншому форматі, якщо використовується інший конвертер.
public class ExceptionController {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> blankNameException(MethodArgumentNotValidException e){
        String message = e.getFieldError().getDefaultMessage();
        System.out.println(message);
        return new ResponseEntity<>(message, HttpStatusCode.valueOf(500));
    }
}
