package com.techforb_back.repositorio;

import com.techforb_back.entidad.Usuario;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioUsuario extends JpaRepository<Usuario, Integer> {
    
    public Usuario getByEmail(String email);
    
    public Optional<Usuario> findByEmail (String email);
}
