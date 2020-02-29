package com.example.library.Models;

import java.util.List;

public class Level {
    public void setLevel(String level) {
        this.levelname = level;
    }


    private static String levelname;
    private List<Course> subjects;
    private  Course course;

    public String getLevelname() {
        return levelname;
    }

    public List<Course> getSubjects() {
        return subjects;
    }

    public void setSubject(Course subject) {
        this.course = subject;
    }

    public Level(String level,Course subject) {
        this.course = subject;
        this.levelname = level;
    }
    public Level(){}
}
