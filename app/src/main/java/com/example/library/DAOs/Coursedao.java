package com.example.library.DAOs;

import com.example.library.Models.Course;

import java.util.List;

public interface Coursedao {

    public List<Course> getCourses();

    public void setCourse(Course course);

}
