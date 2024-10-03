package com.techforb_back.servicio;

import com.techforb_back.entidad.Planta;
import com.techforb_back.repositorio.RepositorioPlanta;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ServicioPlanta {
    
    @Autowired
    RepositorioPlanta repositorioPlanta;
    
    public Planta buscarPorId(int id){
        return repositorioPlanta.findById(id);
    }
    
    public List<Planta> listarPlantas(){
        return repositorioPlanta.findAll();
    }
    
    public void eliminarPlanta(int id){
        repositorioPlanta.deleteById(id);
    }
    
    public void guardarPlanta(Planta planta){
        repositorioPlanta.save(planta);
    }
    
    public List<Planta> traerTodasPlantas(){
        return repositorioPlanta.findAll();
    }
    
     public Map<String, Integer> obtenerSumas() {
         
        List<Object[]> arraySumas = repositorioPlanta.obtenerMetricas();
        
        Object[] suma = arraySumas.get(0);
        
        Map<String, Integer> resultado = new HashMap<>();
        
        resultado.put("alertasMedias", ((Number) suma[0]).intValue());
        resultado.put("alertasRojas", ((Number) suma[1]).intValue());
        resultado.put("lecturas", ((Number) suma[2]).intValue());
        resultado.put("sensoresDeshabilitados", ((Number) suma[3]).intValue());
        
        return resultado;
    }
    
    
}
