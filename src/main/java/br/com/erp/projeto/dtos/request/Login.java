package br.com.erp.projeto.dtos.request;

import lombok.Data;

@Data
public class Login {
    private String username;
    private String password;
}
