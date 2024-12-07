package evento.fatec.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {
        // Handle the exception and provide an appropriate response
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Oops! Algo deu Errado! Por favor, tente novamente.\n\t\n\t" +
                        "(PS: Para deletar um Curso ou Turma, é necessário excluir as dependências primeiro.)");
    }
}
