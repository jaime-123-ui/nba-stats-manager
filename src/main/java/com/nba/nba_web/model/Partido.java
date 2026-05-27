package com.nba.nba_web.model;

public class Partido {

    private String equipo1;
    private String equipo2;

    private int puntos1;
    private int puntos2;

    private String ganador;

    public Partido(String equipo1, String equipo2) {

        this.equipo1 = equipo1;
        this.equipo2 = equipo2;

        this.puntos1 = (int)(Math.random() * 40) + 80;
        this.puntos2 = (int)(Math.random() * 40) + 80;

        while(puntos1 == puntos2){

            puntos2++;
        }

        if(puntos1 > puntos2){

            ganador = equipo1;

        }else{

            ganador = equipo2;
        }
    }

    public String getEquipo1() {
        return equipo1;
    }

    public String getEquipo2() {
        return equipo2;
    }

    public int getPuntos1() {
        return puntos1;
    }

    public int getPuntos2() {
        return puntos2;
    }

    public String getGanador() {
        return ganador;
    }
    
    public String getResultado1(){

        return equipo1 + " - " + puntos1;
    }

    public String getResultado2(){

        return equipo2 + " - " + puntos2;
    }
}
