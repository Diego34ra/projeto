package br.com.erp.projeto.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.nio.file.AccessDeniedException;

@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class ResourceForbiddenException extends AccessDeniedException {

    private static final long serialVersionUID = 1L;

    public ResourceForbiddenException (String message) {
        super(message);
    }
}
