package com.techforb_back.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.Date;

public class UsuarioDto {

    @NotNull
    @NotEmpty(message="El campo email no puede estar vacío")
    @Email(message = "Fomarto de email inválido")
    private String emailDto;
    
    @NotNull
    @NotEmpty(message="El campo nombre no puede estar vacío")
    @Size
        (max = 20, message = "El nombre no puede tener más de 20 caracteres")
    private String nombreDto;

    @NotNull
    @NotEmpty(message="El campo apellido no puede estar vacío")
    @Size(max = 20, message = "El apellido no puede tener más de 20 caracteres")
    private String apellidoDto;
    
    private Date nacimientoDto;

    public UsuarioDto() {
    }

    public UsuarioDto(String email, String nombre, String apellido, Date nacimiento) {
        this.emailDto = email;
        this.nombreDto = nombre;
        this.apellidoDto = apellido;
        this.nacimientoDto = nacimiento;
    }


    public String getEmailDto() {
        return emailDto;
    }

    public void setEmailDto(String email) {
        this.emailDto = email;
    }

    public String getNombreDto() {
        return nombreDto;
    }

    public void setNombreDto(String nombre) {
        this.nombreDto = nombre;
    }

    public String getApellidoDto() {
        return apellidoDto;
    }

    public void setApellidoDto(String apellido) {
        this.apellidoDto = apellido;
    }

    public Date getNacimientoDto() {
        return nacimientoDto;
    }

    public void setNacimientoDto(Date nacimiento) {
        this.nacimientoDto = nacimiento;
    }
}
