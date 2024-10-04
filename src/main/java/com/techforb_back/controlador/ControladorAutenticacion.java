package com.techforb_back.controlador;

import com.techforb_back.entidad.LoginUsuario;
import com.techforb_back.entidad.Mensaje;
import com.techforb_back.entidad.RespuestaAutenticacion;
import com.techforb_back.entidad.TokenDto;
import com.techforb_back.entidad.Usuario;
import com.techforb_back.jwt.JwtProvider;
import com.techforb_back.servicio.ServicioUsuario;
import com.techforb_back.servicio.UserDetailImplementacion;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/autenticacion")
@CrossOrigin(origins = {"http://localhost:4200"})
public class ControladorAutenticacion {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private ServicioUsuario servicioUsuario;

    @Autowired
    private UserDetailImplementacion userDetailImplementacion;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginUsuario loginUsuario) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginUsuario.getEmail(), loginUsuario.getPassword())
            );
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(401).body(new Mensaje("Credenciales inválidas"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new Mensaje("Error en el servidor: " + e.getMessage()));
        }

        final UserDetails userDetails = userDetailImplementacion.loadUserByUsername(loginUsuario.getEmail());
        final String jwt = jwtProvider.generarToken(userDetails);

        return ResponseEntity.ok(new RespuestaAutenticacion(jwt));
    }

    @PostMapping("/validarToken")
    public ResponseEntity<?> login(@RequestBody TokenDto tokenDto) {
        String token = tokenDto.getJwt();
        System.out.println("Este es el tokeDto" + token);
        try {
            if (jwtProvider.validarToken(tokenDto.getJwt())) {
                return ResponseEntity.status(200).body(new Mensaje("Token valido."));
            } else {
                return ResponseEntity.status(500).body(new Mensaje("Token NO valido."));
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new Mensaje("Token NO valido." + e.getMessage()));

        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> registrar(@Valid @RequestBody Usuario usuario) {

        if (servicioUsuario.existeUsuario(usuario.getEmail())) {
            return ResponseEntity.status(400).body(new Mensaje("El email " + usuario.getEmail() + " ya está registrado. Intenta con otro."));
        }
        servicioUsuario.registrarUsuario(usuario);
        return ResponseEntity.status(200).body(new Mensaje("Usuario registrado exitosamente"));
    }

}
