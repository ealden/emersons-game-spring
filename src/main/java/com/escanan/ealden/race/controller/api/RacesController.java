package com.escanan.ealden.race.controller.api;

import com.escanan.ealden.race.data.RacerRepository;
import com.escanan.ealden.race.model.Racer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RacesController {
    @Autowired
    private RacerRepository racerRepository;

    @GetMapping("/api/racers")
    public Iterable<Racer> index() {
        return racerRepository.findAll();
    }
}
