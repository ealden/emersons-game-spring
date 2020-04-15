package com.escanan.ealden.race.steps;

import com.escanan.ealden.race.data.RacerRepository;
import io.cucumber.java.Before;
import org.springframework.beans.factory.annotation.Autowired;

public class CommonSteps {
    @Autowired
    private RacerRepository racerRepository;

    @Before
    public void clearData() {
        racerRepository.deleteAll();
    }
}
