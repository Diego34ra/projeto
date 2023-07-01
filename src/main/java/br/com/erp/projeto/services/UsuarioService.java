package br.com.erp.projeto.services;

import br.com.erp.projeto.dtos.MessageResponseDTO;
import br.com.erp.projeto.exceptions.ResourceNotFoundException;
import br.com.erp.projeto.model.Usuario;
import br.com.erp.projeto.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder encoder;

    public MessageResponseDTO createUser (Usuario usuario){
        String pass = usuario.getPassword();

        usuario.setPassword(encoder.encode(pass));
        usuarioRepository.save(usuario);
        return MessageResponseDTO
                .builder()
                .status("Created")
                .code(201)
                .message("Usuario "+usuario.getUsername()+" criado com sucesso.")
                .build();
    }

    public List<Usuario> findAll(){
        List<Usuario> usuarioList = usuarioRepository.findAll();
        return usuarioList;
    }

    public Usuario findById(Integer id) throws ResourceNotFoundException {
        Usuario usuario = verificaSeExiste(id);
        return usuario;
    }

    public MessageResponseDTO deleteById(Integer id) throws ResourceNotFoundException {
        usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario com id "+id+" não foi encontrado."));
        usuarioRepository.deleteById(id);
        return MessageResponseDTO
                .builder()
                .status("Ok")
                .code(200)
                .message("Usuario com id "+id+" deletado com sucesso.")
                .build();
    }

    public Usuario verificaSeExiste(Integer id) throws ResourceNotFoundException {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario com id "+id+" não foi encontrado."));
        return usuario;
    }
}
