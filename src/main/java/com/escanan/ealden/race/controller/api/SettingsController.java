package com.escanan.ealden.race.controller.api;

import com.escanan.ealden.race.controller.api.model.Settings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SettingsController {
    @Value("${race.test.enable}")
    private boolean testMode;

    @GetMapping("/api/settings")
    public Settings index() {
        Settings settings = new Settings();
        settings.setTestMode(testMode);

        return settings;
    }
}
