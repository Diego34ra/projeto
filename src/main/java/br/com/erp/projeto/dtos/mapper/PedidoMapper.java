package br.com.erp.projeto.dtos.mapper;

import br.com.erp.projeto.dtos.request.PedidoDTO;
import br.com.erp.projeto.model.Pedido;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class PedidoMapper {
    private static final ModelMapper MODEL_MAPPER = new ModelMapper();

    public Pedido toPedidoCreate(PedidoDTO pedidoDTO){
        return MODEL_MAPPER.map(pedidoDTO, Pedido.class);
    }
}
