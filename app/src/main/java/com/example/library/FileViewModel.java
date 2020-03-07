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
    private Repository repository = new Repository();
    List<String> courses;
    List<String> files;

    public FileViewModel(){}




    public String uploadFile(Uri fileUri) {
       String url = repository.uploadFile(fileUri);

       return url;
    }

    public void uploadLevel(Level level) {
        repository.uploadLevel(level);
    }

    public List<String> getCourses(String level) {
        courses = new ArrayList<>();
        courses = repository.getCourses(level);


        return courses;
    }

    public List<String> getFiles(String level,String course) {
        files = new ArrayList<>();
        files = repository.getFiles(level,course);
        return files;
    }

    public String getFileUrl(File file) {
        String url = repository.getFileUrl(file);
        return url;
    }


}
