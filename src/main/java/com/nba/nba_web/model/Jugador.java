package com.nba.nba_web.model;

import jakarta.persistence.*;

@Entity
@Table(name = "jugadores")

public class Jugador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    private String nombre;

    private int edad;

    private String nacionalidad;

    private String altura;

    private String peso;

    private String posicion;

    @Column(name = "equipo_id")
    private int equipoId;
    
    @Transient
    private String equipo;
    
    @Transient
    private double puntos;

    @Transient
    private double rebotes;

    @Transient
    private double asistencias;
    
    @Transient
    private int media;

    public Jugador() {
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public String getAltura() {
        return altura;
    }

    public String getPeso() {
        return peso;
    }

    public String getPosicion() {
        return posicion;
    }

    public String getEquipo() {
        return equipo;
    }

    public void setEquipo(String equipo) {
        this.equipo = equipo;
    }
    
    public double getPuntos() {
        return puntos;
    }

    public void setPuntos(double puntos) {
        this.puntos = puntos;
    }

    public double getRebotes() {
        return rebotes;
    }

    public void setRebotes(double rebotes) {
        this.rebotes = rebotes;
    }

    public double getAsistencias() {
        return asistencias;
    }

    public void setAsistencias(double asistencias) {
        this.asistencias = asistencias;
    } 
    
    public int getMedia() {
        return media;
    }

    public void setMedia(int media) {
        this.media = media;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public void setAltura(String altura) {
        this.altura = altura;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    public void setEquipoId(int equipoId) {
        this.equipoId = equipoId;
    }
    
   
}
