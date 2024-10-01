package com.techforb_back.servicio;

import com.techforb_back.entidad.Usuario;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserDetailImpl implements UserDetails {

    private String email;
    private String password;
    private String nombre;
    private String apellido;
    private Date nacimiento;
    private Collection<? extends GrantedAuthority> authorities;
    
    public UserDetailImpl(Usuario usuario) {
        this.email = usuario.getEmail();
        this.password = usuario.getPassword();
        this.nombre = usuario.getNombre();
        this.apellido = usuario.getApellido();
        this.nacimiento = usuario.getNacimiento();
        this.authorities = new ArrayList<>(); 
// No voy a usar por ahora los roles pero deberia hacer toda la implementacion y manejo de roles por usuario
    }
    

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }
    
    public String getNombre(){
        return nombre;
    }
    
    public String getApellido(){
        return apellido;
    }
    public Date getNacimiento(){
        return nacimiento;
    }

}
