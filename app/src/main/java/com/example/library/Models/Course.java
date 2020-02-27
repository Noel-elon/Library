package com.example.library.Models;

import java.util.List;

public class Course {
    public String courseName;
    public File file;

    public String getCourseName() {
        return courseName;
    }
    public Course(){};

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public List<File> files;

    public List<File> getFiles() {
        return files;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public Course(String courseName, File file) {
        this.file = file;
        this.courseName = courseName;
    }
}
