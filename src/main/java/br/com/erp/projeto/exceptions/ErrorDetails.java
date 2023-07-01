package br.com.erp.projeto.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDetails {

    private Date timestamp;
    private int status;
    private String message;
    private String path;

    public ErrorDetails(String message) {
        this.message = message;
    }
}