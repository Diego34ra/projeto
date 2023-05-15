package br.com.erp.projeto.controller;

import br.com.erp.projeto.model.Usuario;
import br.com.erp.projeto.services.UsuarioService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/users")
    public String teste(@RequestHeader("Authorization") String authHeader){
        return "Hello!";
    }

    @PostMapping
    public void postUsuario(@RequestBody Usuario usuario){
        usuarioService.createUser(usuario);
    }
}
