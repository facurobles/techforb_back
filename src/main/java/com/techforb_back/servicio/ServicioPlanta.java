package com.techforb_back.servicio;

import com.techforb_back.entidad.Planta;
import com.techforb_back.repositorio.RepositorioPlanta;
import java.util.List;
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
    
    public void EliminarPlanta(int id){
        repositorioPlanta.deleteById(id);
    }
    
    public void GuardarPlanta(Planta planta){
        repositorioPlanta.save(planta);
    }
    
}
