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
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class FileViewModel extends ViewModel {
    private Repository repository = new Repository();
    LiveData<List<String>> courses;
    List<String> files;

    public FileViewModel() {
    }


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

    public Task<QuerySnapshot> getFiles(String level, String course) {
        return Repository.getFiles(level, course);
    }

    public Task<DocumentSnapshot> getFileObject(String level, String course, String file) {

        return Repository.getFileObject(level, course, file);
    }


}
