package com.techforb_back.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class PlantaDto {
   
    @NotNull
    @NotEmpty(message="El campo país no puede estar vacío")
    private String paisDto;
 
    @NotNull
    @NotEmpty(message="El campo nombre no puede estar vacío")
    private String nombreDto;
    
    @Min(value = 0, message = "Las lecturas deben ser un número positivo")
    private int lecturasDto;
    
    @Min(value = 0, message = "Las lecturas medias deben ser un número positivo")
    private int alertasMediasDto;
    
    @Min(value = 0, message = "Las lecturas rojas deben ser un número positivo")
    private int alertasRojasDto;
    
    @Min(value = 0, message = "Las lecturas de los sensores deshabilitados debe ser un número positivo")
    private int sensoresDeshabilitadosDto;

    public PlantaDto() {
    }

    public PlantaDto(String pais, String nombre, int lecturas, int alertasMedias, int alertasRojas, int sensoresDeshabilitados) {
        this.paisDto = pais;
        this.nombreDto = nombre;
        this.lecturasDto = lecturas;
        this.alertasMediasDto = alertasMedias;
        this.alertasRojasDto = alertasRojas;
        this.sensoresDeshabilitadosDto = sensoresDeshabilitados;
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

    public int getSensoresDeshabilitadosDto() {
        return sensoresDeshabilitadosDto;
    }

    public void setSensoresDeshabilitadosDto(int sensoresDeshabilitados) {
        this.sensoresDeshabilitadosDto = sensoresDeshabilitados;
    }
}
