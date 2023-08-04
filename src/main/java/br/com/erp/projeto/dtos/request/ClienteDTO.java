package br.com.erp.projeto.dtos.request;

import br.com.erp.projeto.model.Telefone;
import lombok.Data;

import java.util.List;


@Data
public class ClienteDTO {

    private String nome;
    private String cpf;
    private List<Telefone> telefones;
    private String situacao;
}
