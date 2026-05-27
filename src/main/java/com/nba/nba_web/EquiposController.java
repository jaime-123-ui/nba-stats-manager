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
import org.springframework.web.bind.annotation.PathVariable;

import com.nba.nba_web.model.Equipo;
import com.nba.nba_web.model.Jugador;

@Controller
public class EquiposController {

    // PAGINA PRINCIPAL EQUIPOS
    @GetMapping("/equipos")
    public String equipos(Model model) {

        List<Equipo> equipos = new ArrayList<>();

        try {

        	Connection conexion =
        	        DriverManager.getConnection(
        	                System.getenv("SPRING_DATASOURCE_URL"),
        	                System.getenv("SPRING_DATASOURCE_USERNAME"),
        	                System.getenv("SPRING_DATASOURCE_PASSWORD")
        	        );

            String sql =
                    "SELECT * FROM equipos";

            PreparedStatement ps =
                    conexion.prepareStatement(sql);

            ResultSet rs =
                    ps.executeQuery();

            while(rs.next()) {

                Equipo equipo = new Equipo();

                equipo.setId(
                        rs.getLong("id")
                );

                equipo.setNombre(
                        rs.getString("nombre")
                );

                equipo.setCiudad(
                        rs.getString("ciudad")
                );
                
                equipo.setImagen(
                	    rs.getString("imagen")
                	);


                equipos.add(equipo);
            }

            conexion.close();

        } catch(Exception e) {

            e.printStackTrace();
        }

        model.addAttribute("equipos", equipos);

        return "equipos";
    }

    // DETALLE DEL EQUIPO
    @GetMapping("/equipos/{id}")
    public String detalleEquipo(
            @PathVariable int id,
            Model model) {

        List<Jugador> jugadores = new ArrayList<>();

        try {

        	Connection conexion =
        	        DriverManager.getConnection(
        	                System.getenv("SPRING_DATASOURCE_URL"),
        	                System.getenv("SPRING_DATASOURCE_USERNAME"),
        	                System.getenv("SPRING_DATASOURCE_PASSWORD")
        	        );

            String sql =
            	    "SELECT jugadores.*, " +
            	    "equipos.nombre AS equipo_nombre, " +
            	    "estadisticas.puntos, " +
            	    "estadisticas.rebotes, " +
            	    "estadisticas.asistencias " +
            	    "FROM jugadores " +
            	    "JOIN estadisticas " +
            	    "ON jugadores.id = estadisticas.jugador_id " +
            	    "JOIN equipos " +
            	    "ON jugadores.equipo_id = equipos.id " +
            	    "WHERE jugadores.equipo_id = ?";

            PreparedStatement ps =
                    conexion.prepareStatement(sql);

            ps.setInt(1, id);

            ResultSet rs =
                    ps.executeQuery();

            while(rs.next()) {

                Jugador jugador = new Jugador();

                jugador.setNombre(
                        rs.getString("nombre")
                );
                
                jugador.setEquipo(
                	    rs.getString("equipo_nombre")
                	);

                jugador.setPosicion(
                        rs.getString("posicion")
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
                
                jugador.setNacionalidad(
                	    rs.getString("nacionalidad")
                	);

                	jugador.setAltura(
                	    rs.getString("altura")
                	);

                	jugador.setPeso(
                	    rs.getString("peso")
                	);

                	jugador.setEdad(
                	    rs.getInt("edad")
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
        
        Equipo equipo = new Equipo();

        try {

        	Connection conexion =
        	        DriverManager.getConnection(
        	                System.getenv("SPRING_DATASOURCE_URL"),
        	                System.getenv("SPRING_DATASOURCE_USERNAME"),
        	                System.getenv("SPRING_DATASOURCE_PASSWORD")
        	        );

            String sqlEquipo =
                    "SELECT * FROM equipos WHERE id = ?";

            PreparedStatement psEquipo =
                    conexion.prepareStatement(sqlEquipo);

            psEquipo.setInt(1, id);

            ResultSet rsEquipo =
                    psEquipo.executeQuery();

            if(rsEquipo.next()) {

                equipo.setNombre(
                        rsEquipo.getString("nombre")
                );

                equipo.setTitulos(
                        rsEquipo.getInt("titulos")
                );

                equipo.setFundacion(
                        rsEquipo.getInt("fundacion")
                );

                equipo.setLeyendas(
                        rsEquipo.getString("leyendas")
                );

                equipo.setEstadio(
                        rsEquipo.getString("estadio")
                );

                equipo.setDescripcion(
                        rsEquipo.getString("descripcion")
                );
            }

            conexion.close();

        } catch(Exception e) {

            e.printStackTrace();
        }

        model.addAttribute("equipo", equipo);

        model.addAttribute("jugadores", jugadores);

        return "equipo_detalle";
    }
}