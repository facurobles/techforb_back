package com.techforb_back.repositorio;

import com.techforb_back.entidad.Planta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioPlanta extends JpaRepository<Planta, Integer> {
    
    public Planta findById(int id);
    
    
}
