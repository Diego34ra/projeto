package br.com.erp.projeto.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_item")
    private Integer id;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_order")
    private Pedido order;
    @ManyToOne
    @JoinColumn(name = "id_product")
    private Product produto;
    @Column(name = "quantidade")
    private int quantidade;
}
