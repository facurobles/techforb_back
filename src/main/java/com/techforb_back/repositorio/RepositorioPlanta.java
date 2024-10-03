package com.techforb_back.repositorio;

import com.techforb_back.entidad.Planta;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioPlanta extends JpaRepository<Planta, Integer> {
    
    public Planta findById(int id);
    
    @Query(value = "SELECT SUM(alertas_medias) as alertasMedias, SUM(alertas_rojas) as alertasRojas, SUM(lecturas) as lecturas FROM planta", nativeQuery = true)
    public List<Object[]> obtenerMetricas();
}
