package br.com.erp.projeto.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false, length = 150)
    private String nome;
    @Column(nullable = false)
    private String telefone;
    @Column(nullable = false, unique = true)
    private String cpf;
    @Column(nullable = false)
    private String situacao;
}
