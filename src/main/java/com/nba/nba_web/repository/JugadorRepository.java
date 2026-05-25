package com.nba.nba_web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nba.nba_web.model.Jugador;

public interface JugadorRepository extends JpaRepository<Jugador, Long> {

    @Query(value = """
    SELECT 
    j.id,
    j.nombre,
    j.edad,
    j.nacionalidad,
    j.altura,
    j.peso,
    j.posicion,
    e.nombre AS equipo,

    s.puntos,
    s.rebotes,
    s.asistencias

    FROM jugadores j

    JOIN equipos e
    ON j.equipo_id = e.id

    JOIN estadisticas s
    ON j.id = s.jugador_id
    """, nativeQuery = true)

    List<Object[]> obtenerJugadores();
}