package com.techforb_back.controlador;

import com.techforb_back.dto.PlantaDto;
import com.techforb_back.entidad.Mensaje;
import com.techforb_back.entidad.Planta;
import com.techforb_back.servicio.ServicioPlanta;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    public ResponseEntity<?> crearPlanta(@Valid @RequestBody PlantaDto plantaDto) {
        System.out.println(plantaDto.getNombreDto());

        try {
            Planta planta = new Planta(plantaDto.getPaisDto(), plantaDto.getNombreDto(), plantaDto.getLecturasDto(), plantaDto.getAlertasMediasDto(), plantaDto.getAlertasRojasDto());
            servicioPlanta.guardarPlanta(planta);
            return ResponseEntity.status(200).body(new Mensaje("Planta crerada con éxito."));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new Mensaje("Error. Falló la creación de la planta" + e.getMessage()));
        }
    }

    @Transactional
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarPlanta(@PathVariable int id) {

        try {
            servicioPlanta.eliminarPlanta(id);
            return ResponseEntity.status(200).body(new Mensaje("Planta eliminada con éxito."));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new Mensaje("Error. Falló la eliminación de la planta" + e.getMessage()));
        }
    }

    @Transactional
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> actualizarPlanta(@PathVariable("id") int id, @RequestBody PlantaDto plantaDto) {

        try {
            Planta planta = servicioPlanta.buscarPorId(id);

            if (planta == null) {
                return ResponseEntity.status(500).body(new Mensaje("Error. No se encontró la planta"));
            }

            planta.setPais(plantaDto.getPaisDto());
            planta.setNombre(plantaDto.getNombreDto());
            planta.setAlertasMedias(plantaDto.getAlertasMediasDto());
            planta.setAlertasRojas(plantaDto.getAlertasRojasDto());
            planta.setLecturas(plantaDto.getLecturasDto());

            servicioPlanta.guardarPlanta(planta);

            return ResponseEntity.status(200).body(new Mensaje("Planta actualizada con éxito."));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new Mensaje("Error. Falló la actualización de la planta" + e.getMessage()));
        }
    }

    @Transactional
    @GetMapping("/obtenerMetricas")
    public ResponseEntity<?> obtenerMetricas() {

        try {

            Map<String, Integer> sumas = servicioPlanta.obtenerSumas();

            return ResponseEntity.ok(sumas);
            
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new Mensaje("Error. Falló la obtencion de metricas." + e.getMessage()));
        }
    }

}
