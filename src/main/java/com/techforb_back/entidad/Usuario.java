package com.techforb_back.entidad;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.Date;


@Entity
public class Usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @NotNull
    @NotEmpty(message="El campo email no puede estar vacío")
    @Email(message = "Fomarto de email inválido")
    private String email;
    
    @NotNull
    @NotEmpty(message="El campo contraseña no puede estar vacío")
    @Size(min = 8, message = "La contraseña debe tener al menos 8 caracteres")
    private String password;
 
    @NotNull
    @NotEmpty(message="El campo nombre no puede estar vacío")
    @Size(max = 20, message = "El nombre no puede tener más de 20 caracteres")
    private String nombre;

    @NotNull
    @NotEmpty(message="El campo apellido no puede estar vacío")
    @Size(max = 20, message = "El apellido no puede tener más de 20 caracteres")
    private String apellido;
    
    private Date nacimiento;

    public Usuario() {
    }

    public Usuario(String email, String password, String nombre, String apellido, Date nacimiento) {
        this.email = email;
        this.password = password;
        this.nombre = nombre;
        this.apellido = apellido;
        this.nacimiento = nacimiento;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Date getNacimiento() {
        return nacimiento;
    }

    public void setNacimiento(Date nacimiento) {
        this.nacimiento = nacimiento;
    }

    
    
    
    
}
