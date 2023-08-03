package br.com.erp.projeto.services;

import br.com.erp.projeto.dtos.request.MessageResponseDTO;
import br.com.erp.projeto.model.Cliente;
import br.com.erp.projeto.model.Telefone;
import br.com.erp.projeto.repository.ClienteRepository;
import br.com.erp.projeto.repository.TelefoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private TelefoneRepository telefoneRepository;

    public MessageResponseDTO create(Cliente cliente){
        clienteRepository.save(cliente);
        List<Telefone> telefoneList = getListTelefone(cliente.getTelefone(),cliente);
        telefoneRepository.saveAll(telefoneList);
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

    public List<Telefone> getListTelefone(List<Telefone> telefoneList, Cliente cliente){
        return telefoneList.stream().map( telefone -> {
            telefone.setCliente(cliente);
            return telefone;
        }).collect(Collectors.toList());
    }
}
