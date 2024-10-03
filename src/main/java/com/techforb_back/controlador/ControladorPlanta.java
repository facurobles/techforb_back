package com.techforb_back.controlador;

import com.techforb_back.dto.PlantaDto;
import com.techforb_back.entidad.Mensaje;
import com.techforb_back.entidad.Planta;
import com.techforb_back.servicio.ServicioPlanta;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/planta")
@CrossOrigin(origins = {"http://localhost:4200"})
public class ControladorPlanta {
    
    @Autowired
    ServicioPlanta servicioPlanta;
    
    @Transactional
    @PostMapping("/crear")
    public ResponseEntity<?> CrearPlanta(@Valid @RequestBody PlantaDto plantaDto){
        System.out.println(plantaDto.getNombreDto());
        System.out.println(plantaDto.getPaisDto());
        System.out.println(plantaDto.getAlertasMediasDto());
        System.out.println(plantaDto.getAlertasRojasDto());
        System.out.println(plantaDto.getLecturasDto());
        try {
            Planta planta = new Planta(plantaDto.getPaisDto(), plantaDto.getNombreDto(), plantaDto.getLecturasDto(), plantaDto.getAlertasMediasDto(), plantaDto.getAlertasRojasDto());
            servicioPlanta.GuardarPlanta(planta);
            return ResponseEntity.status(200).body(new Mensaje("Planta crerada con éxito."));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new Mensaje("Error. Falló la creación de la planta"+ e.getMessage()));
        }
    }
    
    
    
    
}
