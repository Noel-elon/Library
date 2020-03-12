package com.example.library;

import android.net.Uri;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.library.Models.Course;
import com.example.library.Models.File;
import com.example.library.Models.Level;
import com.example.library.Repository.Repository;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class FileViewModel extends ViewModel {
    private Repository repository = new Repository();
    LiveData<List<String>> courses;
    List<String> files;

    public FileViewModel(){}




    public String uploadFile(Uri fileUri) {
       String url = repository.uploadFile(fileUri);

       return url;
    }

    public void uploadLevel(Level level) {
        repository.uploadLevel(level);
    }

    public Task<QuerySnapshot> getCourses(String level) {

        return Repository.getCourses(level);
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
