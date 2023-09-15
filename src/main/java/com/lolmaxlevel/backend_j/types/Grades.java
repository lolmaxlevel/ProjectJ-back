package com.lolmaxlevel.backend_j.types;

public enum Grades {
    FIFTH_N_SIXTH("5-6"),
    SEVENTH_A("7A"),
    SEVENTH_L("7L"),
    EIGHTH_A("8A"),
    EIGHTH_L("8L"),
    NINTH_A("9A"),
    NINTH_L("9L"),
    TENTH("10"),
    ELEVENTH("11");

    private final String grade;

    Grades(String grade) {
        this.grade = grade;
    }

}
