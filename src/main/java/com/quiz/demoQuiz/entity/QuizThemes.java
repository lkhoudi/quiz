package com.quiz.demoQuiz.entity;

public enum QuizThemes {


    ANIMALS("animals.json"),
    BAKERY("bakery.json"),
    CULTURE("cultureGenerale.json"),
    OCEAN("meditarranee.json"),
    MUSIC("music.json"),
    COUNTRIES("worldCountries.json");

    private final String value;

    QuizThemes(String value) {
        this.value = value;
    }


    public String getValue() {
        return value;
    }
}
