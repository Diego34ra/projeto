package br.com.erp.projeto.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    private Integer id;
    @Column(nullable = false, length = 150)
    private String nome;
    @Column(nullable = false, unique = true)
    private String cpf;
    @OneToMany(mappedBy = "cliente")
    private List<Telefone> telefone;
    @Column(nullable = false)
    private String situacao;

}
