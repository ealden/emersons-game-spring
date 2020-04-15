package com.escanan.ealden.race;

import com.escanan.ealden.race.data.RacerRepository;
import com.escanan.ealden.race.model.Racer;
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
    public CommandLineRunner seedData(RacerRepository racerRepository) {
        return (args -> {
            racerRepository.save(new Racer("Racer 1"));
            racerRepository.save(new Racer("Racer 2"));
            racerRepository.save(new Racer("Racer 3"));
            racerRepository.save(new Racer("Racer 4"));
        });
    }
}
