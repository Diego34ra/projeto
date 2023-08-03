package br.com.erp.projeto.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Telefone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_telefone")
    private Integer id;
    @Column(nullable = false)
    private String DDI;
    @Column(nullable = false)
    private String DDD;
    @Column(nullable = false)
    private String numero;
    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;
}
