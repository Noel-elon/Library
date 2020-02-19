package com.example.library.Models;

import java.util.List;

public class Course {
    public String courseName;

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public List<File> files;

    public List<File> getFiles() {
        return files;
    }

    public void setFiles(List<File> files) {
        this.files = files;
    }

    public Course(String courseName, List<File> files) {
        this.files = files;
        this.courseName = courseName;
    }
}
