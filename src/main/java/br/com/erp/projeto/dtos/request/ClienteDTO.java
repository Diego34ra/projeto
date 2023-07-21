package br.com.erp.projeto.dtos.request;

import lombok.Data;


@Data
public class ClienteDTO {

    private String nome;
    private String cpf;
    private String telefone;
    private String situacao;
}
