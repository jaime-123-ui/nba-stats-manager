package com.nba.nba_web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.nba.nba_web.model.Partido;
import com.nba.nba_web.service.PlayoffService;

import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PlayoffController {

    @Autowired
    private PlayoffService playoffService;
    
    @PostMapping("/simular-playoffs")
    public String simularPlayoffs(Model model) {

        List<Partido> primeraRonda =
                playoffService.simularPrimeraRonda();

        List<Partido> semifinales =
                playoffService.siguienteRonda(primeraRonda);

        List<Partido> finalesConferencia =
                playoffService.siguienteRonda(semifinales);

        List<Partido> finalNBA =
                playoffService.siguienteRonda(finalesConferencia);

        String campeon =
                finalNBA.get(0).getGanador();

        model.addAttribute("campeon", campeon);

        model.addAttribute("p1", primeraRonda.get(0));
        model.addAttribute("p2", primeraRonda.get(1));
        model.addAttribute("p3", primeraRonda.get(2));
        model.addAttribute("p4", primeraRonda.get(3));

        model.addAttribute("p5", primeraRonda.get(4));
        model.addAttribute("p6", primeraRonda.get(5));
        model.addAttribute("p7", primeraRonda.get(6));
        model.addAttribute("p8", primeraRonda.get(7));

        model.addAttribute("s1", semifinales.get(0));
        model.addAttribute("s2", semifinales.get(1));
        model.addAttribute("s3", semifinales.get(2));
        model.addAttribute("s4", semifinales.get(3));

        model.addAttribute("f1", finalesConferencia.get(0));
        model.addAttribute("f2", finalesConferencia.get(1));

        model.addAttribute("nba", finalNBA.get(0));

        return "playoffs";
    }

    @GetMapping("/playoffs")
    public String playoffs(Model model) {

        model.addAttribute("campeon", null);

        return "playoffs";
    }
}
