package com.techforb_back.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class PlantaDto {
   
    @NotNull
    @NotEmpty(message="El campo país no puede estar vacío")
    private String paisDto;
 
    @NotNull
    @NotEmpty(message="El campo nombre no puede estar vacío")
    private String nombreDto;
    
    @Positive(message = "Las lecturas deben ser un número positivo")
    private int lecturasDto;
    
    @Positive(message = "Las lecturas medias deben ser un número positivo")
    private int alertasMediasDto;
    
    @Positive(message = "Las lecturas rojas deben ser un número positivo")
    private int alertasRojasDto;

    public PlantaDto() {
    }

    public PlantaDto(String pais, String nombre, int lecturas, int alertasMedias, int alertasRojas) {
        this.paisDto = pais;
        this.nombreDto = nombre;
        this.lecturasDto = lecturas;
        this.alertasMediasDto = alertasMedias;
        this.alertasRojasDto = alertasRojas;
    }


    public String getPaisDto() {
        return paisDto;
    }

    public void setPaisDto(String pais) {
        this.paisDto = pais;
    }

    public String getNombreDto() {
        return nombreDto;
    }

    public void setNombreDto(String nombre) {
        this.nombreDto = nombre;
    }

    public int getLecturasDto() {
        return lecturasDto;
    }

    public void setLecturasDto(int lecturas) {
        this.lecturasDto = lecturas;
    }

    public int getAlertasMediasDto() {
        return alertasMediasDto;
    }

    public void setAlertasMediasDto(int alertasMedias) {
        this.alertasMediasDto = alertasMedias;
    }

    public int getAlertasRojasDto() {
        return alertasRojasDto;
    }

    public void setAlertasRojasDto(int alertasRojas) {
        this.alertasRojasDto = alertasRojas;
    }
}
