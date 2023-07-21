package br.com.erp.projeto.services;

import br.com.erp.projeto.dtos.request.MessageResponseDTO;
import br.com.erp.projeto.model.Cliente;
import br.com.erp.projeto.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public MessageResponseDTO create(Cliente cliente){
        clienteRepository.save(cliente);
        return MessageResponseDTO.builder()
                .code(201)
                .status("Created")
                .message("Cliente cadastrado com sucesso.")
                .build();
    }

    public List<Cliente> findAll(){
        List<Cliente> clienteList = clienteRepository.findAll();
        return clienteList;
    }
}
