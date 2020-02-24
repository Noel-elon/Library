package com.example.library;

import android.net.Uri;

import androidx.lifecycle.ViewModel;

import com.example.library.Models.Course;
import com.example.library.Models.File;
import com.example.library.Models.Level;
import com.example.library.Repository.Repository;

import java.util.ArrayList;
import java.util.List;

public class FileViewModel extends ViewModel {
    private Repository repository;
    List<Course> courses;
    List<File> files;


    public void uploadFile(Uri fileUri) {
        repository.uploadFile(fileUri);
    }

    public void uploadLevel(Level level) {
        repository.uploadLevel(level);
    }

    public List<Course> getCourses(Level level) {
        courses = new ArrayList<>();
        courses = repository.getCourses(level);


        return courses;
    }

    public List<File> getFiles(Course course) {
        files = new ArrayList<>();
        files = repository.getFiles(course);
        return files;
    }

    public String getFileUrl(File file) {
        String url = repository.getFileUrl(file);
        return url;
    }


}
