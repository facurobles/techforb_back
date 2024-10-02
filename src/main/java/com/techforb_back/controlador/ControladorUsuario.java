package com.techforb_back.controlador;

import com.techforb_back.dto.UsuarioDto;
import com.techforb_back.entidad.Mensaje;
import com.techforb_back.entidad.RespuestaAutenticacion;
import com.techforb_back.entidad.Usuario;
import com.techforb_back.jwt.JwtProvider;
import com.techforb_back.servicio.ServicioUsuario;
import com.techforb_back.servicio.UserDetailImplementacion;
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

    @Autowired
    private UserDetailImplementacion userDetailImplementacion;

    @Autowired
    private JwtProvider jwtProviderUtil;

    @PutMapping("/actualizar/{email}")
    public ResponseEntity<?> update(@PathVariable("email") String email, @RequestBody UsuarioDto usuarioDto) {

        try {
            Usuario usuario = servicioUsuario.buscarPorEmail(email);

            usuario.setNombre(usuarioDto.getNombreDto());
            usuario.setApellido(usuarioDto.getApellidoDto());
            usuario.setNacimiento(usuarioDto.getNacimientoDto());
            usuario.setEmail(usuarioDto.getEmailDto());

            servicioUsuario.guardarCambiosUsuario(usuario);

            final UserDetails userDetails = userDetailImplementacion.loadUserByUsername(usuario.getEmail());
            final String jwt = jwtProviderUtil.generarToken(userDetails);

            return ResponseEntity.ok(new RespuestaAutenticacion(jwt));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new Mensaje("Error al intentar actualizar: " + e.getMessage()));
        }
        
                

    }

}
