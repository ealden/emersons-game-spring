package com.escanan.ealden.race;

import com.escanan.ealden.race.service.RaceService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RaceApplication {
    public static void main(String[] args) {
        SpringApplication.run(RaceApplication.class, args);
    }

    @Bean
    public CommandLineRunner seedData(RaceService raceService) {
        return (args -> {
            raceService.newRace();
        });
    }
}
