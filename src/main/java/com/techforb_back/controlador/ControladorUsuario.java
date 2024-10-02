package com.techforb_back.controlador;

import com.techforb_back.dto.UsuarioDto;
import com.techforb_back.entidad.Mensaje;
import com.techforb_back.entidad.RespuestaAutenticacion;
import com.techforb_back.entidad.Usuario;
import com.techforb_back.jwt.JwtProvider;
import com.techforb_back.servicio.ServicioUsuario;
import com.techforb_back.servicio.UserDetailImplementacion;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuario")
@CrossOrigin(origins = {"http://localhost:4200"})
public class ControladorUsuario {

    @Autowired
    ServicioUsuario servicioUsuario;

    @Transactional
    @PutMapping("/actualizar/{email}")
    public ResponseEntity<?> update(@PathVariable("email") String email, @RequestBody UsuarioDto usuarioDto) {
                    System.out.println("se inicio el metodo update");

        try {
            Usuario usuario = servicioUsuario.buscarPorEmail(email);
            
            System.out.println("se encontro el usuario por email: " + usuario);

            usuario.setNombre(usuarioDto.getNombreDto());
            usuario.setApellido(usuarioDto.getApellidoDto());
            usuario.setNacimiento(usuarioDto.getNacimientoDto());
            usuario.setEmail(usuarioDto.getEmailDto());
            
            System.out.println("se setearon los campos de usuario : " + usuario);


            servicioUsuario.guardarCambiosUsuario(usuario);

            System.out.println("se guardaron los cambios en el usuario" );
            return ResponseEntity.status(200).body(new Mensaje("Usuario actualizado con éxito"));


        } catch (Exception e) {
            System.out.println("Fallo el metodo update" );
            return ResponseEntity.status(500).body(new Mensaje("Error al intentar actualizar: " + e.getMessage()));
                        

        }
        

    }

}
