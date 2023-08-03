package br.com.erp.projeto.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_order")
    private Integer id;

    @Column(name = "valor_total")
    private BigDecimal valorTotal;

    @Column(name = "data_Pedido")
    private LocalDate dataPedido;

    @OneToMany(mappedBy = "order")
    private List<Item> itens;

}
