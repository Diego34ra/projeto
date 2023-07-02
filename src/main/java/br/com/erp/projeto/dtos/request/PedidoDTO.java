package br.com.erp.projeto.dtos.request;

import br.com.erp.projeto.model.Item;
import lombok.Data;

import java.util.List;

@Data
public class PedidoDTO {
    private List<Item> itens;
}
