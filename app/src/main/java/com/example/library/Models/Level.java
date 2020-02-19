package com.example.library.Models;

import java.util.List;

public class Level {
    public void setLevel(String level) {
        this.level = level;
    }

    public String level;
    public List<Course> subjects;

    public List<Course> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Course> subjects) {
        this.subjects = subjects;
    }

    public Level(String level,List<Course> subjects) {
        this.subjects = subjects;
        this.level = level;
    }
}
