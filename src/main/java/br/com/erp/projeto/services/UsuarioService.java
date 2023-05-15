package br.com.erp.projeto.services;

import br.com.erp.projeto.model.Usuario;
import br.com.erp.projeto.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder encoder;

    public void createUser (Usuario usuario){
        String pass = usuario.getPassword();

        usuario.setPassword(encoder.encode(pass));
        usuarioRepository.save(usuario);
    }
}
