package br.com.erp.projeto.controller;

import br.com.erp.projeto.dtos.request.MessageResponseDTO;
import br.com.erp.projeto.exceptions.ResourceForbiddenException;
import br.com.erp.projeto.exceptions.ResourceNotFoundException;
import br.com.erp.projeto.model.Usuario;
import br.com.erp.projeto.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<Usuario>> findAll(@RequestHeader("Authorization") String authHeader) throws ResourceForbiddenException {
        var users = usuarioService.findAll();
        return ResponseEntity.ok().body(users);
    }

    @PostMapping
    public ResponseEntity<MessageResponseDTO> createUsuario(@RequestBody Usuario usuario){
        var message = usuarioService.createUser(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(message);
    }

    @GetMapping("{id}")
    public ResponseEntity<Usuario> findById(@RequestHeader("Authorization") String authHeader, @PathVariable Integer id) throws ResourceNotFoundException {
        var cliente = usuarioService.findById(id);
        return ResponseEntity.ok().body(cliente);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<MessageResponseDTO> deleteById(@RequestHeader("Authorization") String authHeader, @PathVariable Integer id) throws ResourceNotFoundException {
        var message = usuarioService.deleteById(id);
        return ResponseEntity.ok().body(message);
    }




}
