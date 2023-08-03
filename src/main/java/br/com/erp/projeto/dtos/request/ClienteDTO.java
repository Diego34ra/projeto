package br.com.erp.projeto.dtos.request;

import lombok.Data;

import java.util.List;


@Data
public class ClienteDTO {

    private String nome;
    private String cpf;
    private List<TelefoneDTO> telefone;
    private String situacao;
}
