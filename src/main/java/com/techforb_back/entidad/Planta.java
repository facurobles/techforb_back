package com.techforb_back.entidad;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Entity
public class Planta {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; 
   
    @NotNull
    @NotEmpty(message="El campo país no puede estar vacío")
    private String pais;
    
    @NotNull
    @NotEmpty(message="El campo nombre no puede estar vacío")
    private String nombre;
    
    @Positive(message = "Las lecturas deben ser un número positivo")
    private int lecturas;
    
    @Positive(message = "Las lecturas medias deben ser un número positivo")
    private int alertasMedias;
    
    @Positive(message = "Las lecturas rojas deben ser un número positivo")
    private int alertasRojas;

    public Planta() {
    }

    public Planta(String pais, String nombre, int lecturas, int alertasMedias, int alertasRojas) {
        this.pais = pais;
        this.nombre = nombre;
        this.lecturas = lecturas;
        this.alertasMedias = alertasMedias;
        this.alertasRojas = alertasRojas;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getLecturas() {
        return lecturas;
    }

    public void setLecturas(int lecturas) {
        this.lecturas = lecturas;
    }

    public int getAlertasMedias() {
        return alertasMedias;
    }

    public void setAlertasMedias(int alertasMedias) {
        this.alertasMedias = alertasMedias;
    }

    public int getAlertasRojas() {
        return alertasRojas;
    }

    public void setAlertasRojas(int alertasRojas) {
        this.alertasRojas = alertasRojas;
    }
    
    
    
    
    
    
    
}
