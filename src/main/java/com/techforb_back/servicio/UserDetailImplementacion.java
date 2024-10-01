package com.techforb_back.servicio;

import com.techforb_back.entidad.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

//implemento la interfaz userDeatailService de psring security 
@Service
public class UserDetailImplementacion implements UserDetailsService { 

    @Autowired
    ServicioUsuario servicioUsuario;

    
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        //busco el usuario por su nombre usando el servicioUsuario
        Usuario usuario = servicioUsuario.buscarPorEmail(email);
        if (usuario == null) {
            throw new UsernameNotFoundException("Usuario " + email + " no encontrado: ");
        }

        //transformo los datos de ese usuario en userDetails y los retorno
        return new UserDetailImpl(usuario);
    }
    
}
