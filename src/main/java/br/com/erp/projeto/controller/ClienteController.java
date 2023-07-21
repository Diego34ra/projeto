package br.com.erp.projeto.controller;

import br.com.erp.projeto.dtos.mapper.ClienteMapper;
import br.com.erp.projeto.dtos.request.ClienteDTO;
import br.com.erp.projeto.dtos.request.MessageResponseDTO;
import br.com.erp.projeto.model.Cliente;
import br.com.erp.projeto.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ClienteMapper clienteMapper;

    @PostMapping
    public ResponseEntity<MessageResponseDTO> create(@RequestHeader("Authorization") String authHeader, @RequestBody ClienteDTO clienteDTO){
        var cliente = clienteMapper.toClienteCreate(clienteDTO);
        var message = clienteService.create(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(message);
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> findAll(@RequestHeader("Authorization") String authHeader){
        List<Cliente> clienteList = clienteService.findAll();
        return ResponseEntity.ok().body(clienteList);
    }
}
