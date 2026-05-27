package com.nba.nba_web;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.nba.nba_web.model.Jugador;

@Controller
public class HomeController {

    @GetMapping("/")
    public String inicio(Model model) {

        List<Jugador> jugadores =
                new ArrayList<>();

        try {

        	Connection conexion =
        	        DriverManager.getConnection(
        	                System.getenv("SPRING_DATASOURCE_URL"),
        	                System.getenv("SPRING_DATASOURCE_USERNAME"),
        	                System.getenv("SPRING_DATASOURCE_PASSWORD")
        	        );

            String sql =
            	    "SELECT jugadores.*, " +
            	    "equipos.nombre AS nombre_equipo, " +
            	    "estadisticas.puntos, " +
            	    "estadisticas.rebotes, " +
            	    "estadisticas.asistencias " +
            	    "FROM jugadores " +
            	    "JOIN equipos ON jugadores.equipo_id = equipos.id " +
            	    "JOIN estadisticas ON jugadores.id = estadisticas.jugador_id " +
            	    "ORDER BY estadisticas.puntos DESC " +
            	    "LIMIT 10";
            PreparedStatement ps =
                    conexion.prepareStatement(sql);

            ResultSet rs =
                    ps.executeQuery();

            while(rs.next()) {

                Jugador jugador =
                        new Jugador();

                jugador.setId(
                        rs.getLong("id")
                );

                jugador.setNombre(
                        rs.getString("nombre")
                );

                jugador.setEdad(
                        rs.getInt("edad")
                );

                jugador.setNacionalidad(
                        rs.getString("nacionalidad")
                );

                jugador.setAltura(
                        rs.getString("altura")
                );

                jugador.setPeso(
                        rs.getString("peso")
                );

                jugador.setPosicion(
                        rs.getString("posicion")
                );

                jugador.setEquipo(
                        rs.getString("nombre_equipo")
                );
                
                jugador.setPuntos(
                	    rs.getDouble("puntos")
                	);

                	jugador.setRebotes(
                	    rs.getDouble("rebotes")
                	);

                	jugador.setAsistencias(
                	    rs.getDouble("asistencias")
                	);
                	
                	if(jugador.getPuntos() >= 28){

                	    jugador.setMedia(99);

                	}else if(jugador.getPuntos() >= 25){

                	    jugador.setMedia(97);

                	}else if(jugador.getPuntos() >= 22){

                	    jugador.setMedia(94);

                	}else if(jugador.getPuntos() >= 18){

                	    jugador.setMedia(90);

                	}else{

                	    jugador.setMedia(85);
                	}

                jugadores.add(jugador);
            }

            conexion.close();

        } catch(Exception e) {

            e.printStackTrace();
        }

        model.addAttribute(
                "jugadores",
                jugadores
        );

        return "index";
    }
}