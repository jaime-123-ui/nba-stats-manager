package com.nba.nba_web.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.nba.nba_web.model.Partido;

@Service
public class PlayoffService {

    public List<Partido> simularPrimeraRonda() {

        List<Partido> partidos = new ArrayList<>();

        partidos.add(new Partido("Celtics", "Heat"));
        partidos.add(new Partido("Bucks", "76ers"));
        partidos.add(new Partido("Knicks", "Cavaliers"));
        partidos.add(new Partido("Pacers", "Magic"));

        partidos.add(new Partido("Nuggets", "Lakers"));
        partidos.add(new Partido("Suns", "Warriors"));
        partidos.add(new Partido("Mavericks", "Kings"));
        partidos.add(new Partido("Timberwolves", "Clippers"));

        return partidos;
    }

    public List<Partido> siguienteRonda(List<Partido> rondaAnterior){

        List<String> ganadores = new ArrayList<>();

        for(Partido partido : rondaAnterior){

            ganadores.add(partido.getGanador());
        }

        List<Partido> nuevaRonda = new ArrayList<>();

        for(int i = 0; i < ganadores.size(); i += 2){

            nuevaRonda.add(
                new Partido(
                    ganadores.get(i),
                    ganadores.get(i + 1)
                )
            );
        }

        return nuevaRonda;
    }
}
