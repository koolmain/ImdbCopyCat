package com.koolmain.imdb.global;

import com.koolmain.imdb.dto.ErrorDto;
import com.koolmain.imdb.exceptions.ImdbNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException; 
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ImdbGlobalExceptionHandler {

    @ExceptionHandler({AuthenticationException.class})
    @ResponseBody
    ErrorDto handleAuthenticationException(Exception ex, WebRequest req){
        ErrorDto error = new ErrorDto(); 
        error.setStatus(HttpStatus.NON_AUTHORITATIVE_INFORMATION);
        error.setPath(req.getDescription(false));
        error.setMessage("Authentication Exception");
        error.setDetail("It could be any Authentication Exceptions");
        return error;  
    }

    @ExceptionHandler({ImdbNotFoundException.class})
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    @ResponseBody
    ErrorDto handleNotFoundException(ImdbNotFoundException ex, WebRequest req){
        ErrorDto error = new ErrorDto(); 
        error.setStatus(HttpStatus.NOT_FOUND);
        error.setPath(req.getDescription(false));
        error.setMessage(ex.getErrorMessage());
        error.setDetail(ex.getDetailMessage());
        return error;     
    }
    
}

