package com.escanan.ealden.race.steps;

import io.cucumber.java.ParameterType;

public class ParameterTypes {
    private static final String YES = "YES";

    @ParameterType(".*")
    public boolean yesno(String string) {
        return YES.equals(string);
    }
}
