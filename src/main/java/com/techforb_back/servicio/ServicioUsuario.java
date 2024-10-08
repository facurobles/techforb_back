package com.techforb_back.servicio;

import com.techforb_back.entidad.Usuario;
import com.techforb_back.repositorio.RepositorioUsuario;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ServicioUsuario {
    
    @Autowired
    RepositorioUsuario repositorioUsuario;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    public Usuario buscarPorEmail(String email){
        return repositorioUsuario.getByEmail(email);
    }
    
    public Usuario registrarUsuario(Usuario usuario) {
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        return repositorioUsuario.save(usuario);
    }
    
    public boolean existeUsuario (String email){
        Optional<Usuario> usuario = repositorioUsuario.findByEmail(email);
        
        return usuario.isPresent();
    }
    
    public void guardarCambiosUsuario(Usuario usuario){
        repositorioUsuario.save(usuario);
    }
    
    public void borrarUsuario (String email){
        Usuario usuario = repositorioUsuario.getByEmail(email);
        repositorioUsuario.delete(usuario);
    }
            
    
}
