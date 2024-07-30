package com.locadora.locadoraapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHandlingAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<MensagemException> exception(Exception ex) {
        return handleException( new MensagemException(ex.getLocalizedMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(VeiculoJaCadastrado.class)
    public ResponseEntity<MensagemException> VeiculoJaCadastrado(Exception ex) {
        return handleException( new MensagemException(ex.getLocalizedMessage()),HttpStatus.CONFLICT );
    }

     @ExceptionHandler(VeiculoNaoCadastrado.class)
    public ResponseEntity<MensagemException> VeiculoNaoCadastrado(Exception ex) {
        return handleException( new MensagemException(ex.getLocalizedMessage()), HttpStatus.NOT_FOUND );
    }

    @ExceptionHandler(VeiculoNaoAlugado.class)
    public ResponseEntity<MensagemException> VeiculoNaoAlugado(Exception ex) {
        return handleException( new MensagemException(ex.getLocalizedMessage()),HttpStatus.NOT_FOUND );
    }

    @ExceptionHandler(ClienteJaCadastrado.class)
    public ResponseEntity<MensagemException> ClienteJaCadastrado(Exception ex) {
        return handleException( new MensagemException(ex.getLocalizedMessage()), HttpStatus.CONFLICT );
    }

    @ExceptionHandler(ClienteNaoCadastrado.class)
    public ResponseEntity<MensagemException> ClienteNaoCadastrado(Exception ex) {
        return handleException( new MensagemException(ex.getLocalizedMessage()) , HttpStatus.NOT_FOUND );
    }

    private ResponseEntity<MensagemException> handleException( MensagemException mensagemException, HttpStatus status) {
        if (status != null) {
            return new ResponseEntity<>( mensagemException, status );
        } else {
            throw new IllegalArgumentException("HttpStatus cannot be null");
        }
    }
    
}
