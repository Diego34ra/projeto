package br.com.erp.projeto.dtos.mapper;

import br.com.erp.projeto.dtos.request.ClienteDTO;
import br.com.erp.projeto.model.Cliente;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ClienteMapper {

    public static final ModelMapper MODEL_MAPPER = new ModelMapper();

    public Cliente toClienteCreate(ClienteDTO clienteDTO){
        return MODEL_MAPPER.map(clienteDTO,Cliente.class);
    }
}
