package com.quiz.demoQuiz.entity;

public class Options {
    private String level;
    private String theme;

    public Options(String level, String theme) {
        this.level = level;
        this.theme = theme;
    }

    public Options() {
    }

    public String getLevel() {
        return level;
    }

    public String getTheme() {
        return theme;
    }
}
